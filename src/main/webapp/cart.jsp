<%@ page import="model.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.ProductDao" %>
<%@ page import="util.DatabaseConnection" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat" %>

<%
	DecimalFormat dcf = new DecimalFormat("#.##");
	request.setAttribute("dfc", dcf);

	if (session.getAttribute("emailLogin") == null) {
		response.sendRedirect("ulogin.jsp");
	}

	ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");
	List<Cart> carProduct = null;
	if (cartList != null) {
		ProductDao dao = new ProductDao(DatabaseConnection.getConnection());
		carProduct = dao.getCartProduct(cartList);
		request.setAttribute("cart_list", cartList);

		double totalCartPrice = dao.getTotalCartPrice(cartList);
		request.setAttribute("totalPrice", totalCartPrice);
	}

%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<title>E-Commerce Cart</title>
	<style type="text/css">

.table tbody td{
vertical-align: middle;
}
.btn-incre, .btn-decre{
box-shadow: none;
font-size: 25px;
}
</style>
</head>
<body>
	<%@include file="/navbar.jsp"%>

	<div class="container my-3">
		<div class="d-flex py-3"><h3>Total Price: $${ (totalPrice > 0)? dfc.format(totalPrice): 0 } </h3> <a class="mx-3 btn btn-primary" href="cart-check-out">Check Out</a></div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (cartList != null) {
					for (Cart c : carProduct) {
				%>
				<tr>
					<td><%= c.getName() %></td>
					<td><%= c.getProduct_category() %></td>
					<td>$<%= c.getPrice() %></td>
					<td>
						<form action="OrderNowServlet" method="post" class="form-inline">
						<input type="hidden" name="id" value="<%= c.getProductId() %>" class="form-input">
							<div class="form-group d-flex justify-content-between">
								<a class="btn btn-sm btn-incre" href="QtyIncreDecreServlet?action=incre&id=<%= c.getProductId() %>"><i class="fas fa-plus-square"></i></a>
								<input type="text" name="quantity" class="form-control"  value="<%= c.getQuantity() %>" readonly>
								<a class="btn bnt-sm btn-decre" href="QtyIncreDecreServlet?action=decre&id=<%= c.getProductId() %>"><i class="fas fa-minus-square"></i></a>
							</div>
							<button type="submit" class="btn btn-primary btn-sm" >Buy</button>
						</form>
					</td>
					<td><a href="RemoveFromCartServlet?id=<%= c.getProductId() %>" class="btn btn-sm btn-danger">Remove</a></td>
				</tr>

				<%
				}}%>
			</tbody>
		</table>
	</div>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>


</body>
</html>