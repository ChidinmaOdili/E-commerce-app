package servlets;

import dao.ProductDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Product;

import java.io.IOException;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        double price = Double.parseDouble(request.getParameter("price"));
        String product_category = request.getParameter("product_category");
        String name = request.getParameter("name");
        String image = request.getParameter("attachimage");
        String description = request.getParameter("description");
        String available = request.getParameter("available");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int id = Integer.parseInt(request.getParameter("id"));
         //Product.setProductId(id);
        Product product = new Product(id,price,product_category,name,image,description,available, quantity);
        ProductDao productDao = new ProductDao();
        if (productDao.addProduct(product)) {
            request.setAttribute("message", "Product added succesffully");
            response.sendRedirect("productform.jsp?msg=valid");
            System.out.println("hello uchenna");
        }else {
            response.sendRedirect("productform.jsp?msg=invalid");
            System.out.println("hello madam");
        }

    }


}








   // }
//}
