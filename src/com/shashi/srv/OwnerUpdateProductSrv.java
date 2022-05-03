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

import com.shashi.beans.OwnerCompanyBean;
import com.shashi.beans.ProductBean;
import com.shashi.dao.OwnerCompanyDaoImpl;
import com.shashi.dao.ProductDaoImpl;
/**
 * Servlet implementation class UpdateProductSrv
 */
@WebServlet("/OwnerUpdateProductSrv")

public class OwnerUpdateProductSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public OwnerUpdateProductSrv() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("usertype");
		String username = (String)session.getAttribute("username");
		String password = (String)session.getAttribute("password");
	
		if(userType== null ||  !userType.equals("startupOwner")){
			
			response.sendRedirect("loginFirst.jsp");
			
		}
		
		else if(username == null || password==null){
	
			response.sendRedirect("loginFirst.jsp");
		}	
		
		//Login success
		PrintWriter pw = response.getWriter();
		
		response.setContentType("text/html");
		
		String prodId = request.getParameter("pid");
		String prodName = request.getParameter("name");
		String prodType = request.getParameter("type");
		String prodInfo = request.getParameter("info");
		double prodPrice = Double.parseDouble(request.getParameter("price"));
		int prodQuantity = Integer.parseInt(request.getParameter("quantity"));
		
		ProductBean product = new ProductBean();
		product.setProdId(prodId);
		product.setProdName(prodName);
		product.setProdInfo(prodInfo);
		product.setProdPrice(prodPrice);
		product.setProdQuantity(prodQuantity);
		product.setProdType(prodType);
		
		ProductDaoImpl dao = new ProductDaoImpl();
		
		OwnerCompanyBean product1= new OwnerCompanyBean();
		product1.setProdId(prodId);
		product1.setProdName(prodName);
		product1.setProdInfo(prodInfo);
		product1.setProdPrice(prodPrice);
		product1.setProdQuantity(prodQuantity);
		product1.setProdType(prodType);
		
		OwnerCompanyDaoImpl dao1 = new OwnerCompanyDaoImpl();
		dao1.updateProductWithoutImage(prodId, product1);
		String status = dao.updateProductWithoutImage(prodId, product);
		
		RequestDispatcher rd = request.getRequestDispatcher("OwnerUpdateProduct.jsp?prodid="+prodId+"");
		
		rd.include(request,response);
		
		pw.println("<script>document.getElementById('message').innerHTML='" + status +"'</script>");
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

