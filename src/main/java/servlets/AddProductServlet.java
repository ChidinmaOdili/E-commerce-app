//package servlets;
//
//import dao.ProductDao;
//import jakarta.servlet.*;
//import jakarta.servlet.http.*;
//import jakarta.servlet.annotation.*;
//import model.Product;
//
//import java.io.IOException;
//
//@WebServlet(name = "AddProductServlet", value = "/AddProductServlet")
//public class AddProductServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        int id = Integer.parseInt(request.getParameter("id"));
//        double price = Double.parseDouble(request.getParameter("price"));
//        String product_category = request.getParameter("product_category");
//        String name = request.getParameter("name");
//        String image = request.getParameter("image");
//        String description = request.getParameter("description");
//        String available = request.getParameter("available");
//
//        System.out.println(price);
//        System.out.println(name);
//        //System.out.println(productQuantity);
//
//        Product product = new Product(id, price,product_category,name,image,description,available);
//        ProductDao productDao = new ProductDao();
//        if (productDao.addProduct(product)) {
//            request.setAttribute("message", "Product added succesffully");
//            response.sendRedirect("productform.jsp?msg=valid");
//
//        }else {
//            response.sendRedirect("productform.jsp?msg=invalid");
//
//        }
//    }
//}
