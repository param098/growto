package com.shashi.srv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shashi.dao.ProductDaoImpl;
import com.shashi.dao.UserProductDaoImpl;

@WebServlet("/RemoveProductSrv")
public class RemoveProductSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public RemoveProductSrv() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("usertype");
		String username = (String)session.getAttribute("username");
		String password = (String)session.getAttribute("password");
	
		if(userType== null || !userType.equals("customer") || !userType.equals("admin")){
			
			response.sendRedirect("loginFirst.jsp");
			
		}
		
		else if(username == null || password==null){
	
			response.sendRedirect("loginFirst.jsp");
		}	
		
		//login checked
		
		String status = null;
		String prodId = request.getParameter("prodid");
		/*System.out.println("Here: ");
		System.out.println("Hi"+prodId+"Hi");*/
		
		PrintWriter pw = response.getWriter();
		response.setContentType("removeProduct.jsp");
		RequestDispatcher rd = null ;
		if(userType.equals("admin")) {
		ProductDaoImpl product = new ProductDaoImpl();
		
		 status = product.removeProduct(prodId);
		  rd = request.getRequestDispatcher("removeProduct.jsp");
		}
		if(userType.equals("customer")) {
			UserProductDaoImpl product = new UserProductDaoImpl();
			
			 status = product.removeProduct(prodId);
			 rd = request.getRequestDispatcher("UserRemoveProduct.jsp");
			}
		
		
		rd.include(request, response);
		
		pw.println("<script>document.getElementById('message').innerHTML='"+status+"'</script>");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
