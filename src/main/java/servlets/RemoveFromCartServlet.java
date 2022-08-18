package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cart;

//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
import javax.swing.table.TableRowSorter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "RemoveFromCartServlet", value = "/RemoveFromCartServlet")
public class RemoveFromCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(PrintWriter out = response.getWriter()) {
            response.setContentType("text/html; charset=UTF-8");

            String id = request.getParameter("id");
            if (id != null) {
                ArrayList<Cart> cart_list1 = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
                if (cart_list1 != null) {
                    for (Cart c: cart_list1) {
                        if (c.getProductId() == Integer.parseInt(id)) {
                            cart_list1.remove(cart_list1.indexOf(c));
                            break;
                        }
                    }
                    response.sendRedirect("cart.jsp");
                }
            }
            else {
                response.sendRedirect("cart.jsp");
            }
        }
    }

}
