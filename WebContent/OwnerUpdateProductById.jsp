<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Startup Home</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
	<%
	/* Checking the user credentials */
		String userType = (String)session.getAttribute("usertype");
		String username = (String)session.getAttribute("username");
		String password = (String)session.getAttribute("password");
	
		if(userType== null || !userType.equals("startupOwner")){
			
			response.sendRedirect("loginFirst.jsp");
			
		}
		
		else if(username == null || password==null){
	
			response.sendRedirect("loginFirst.jsp");
		}	
		
	%>

	<%@ include file="OwnerHeader.html"%>

	<div class="products" style="background-color: #E6F9E6;">

		<p id="message" class="tab" align="center">

			Update Company

			<!-- <form>
			<button type="submit" formaction="addProduct.jsp" style="background-color:red; color:white">Add products</button>
			<button type="submit" formaction="#">Remove Products</button>
			<button type="submit" formaction="#">Update Products</button>
			</form> -->

		</p>
		<br>

		<div class="tab" align="center">

			<div style="margin: 5px">

				<form action="OwnerUpdateProduct.jsp" method="post">
					<table border="0">
						<tr>
							<td>Enter Company Id:</td>
							<td><input type="text" name="prodid"
								style="font-size: 15px; font-weight: normal; width: 80%"
								required></td>
						</tr>

						<tr>
							<td><br></td>
							<td><br></td>
						</tr>

						<tr colspan="2" align="center">
							<td><button>
									<a href="startupHome.jsp">Cancle</a>
								</button>&nbsp;</td>
							<td><input type="submit" name="submit"
								value="Update Product"></td>
						</tr>

					</table>

				</form>
			</div>

		</div>
	</div>

	<%@ include file="footer.html"%>
</body>
</html>