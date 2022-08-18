package servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "QtyIncreDecreServlet", value = "/QtyIncreDecreServlet")
public class QtyIncreDecreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");

        try(PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            int id = Integer.parseInt(request.getParameter("id"));

            System.out.println(action);
            System.out.println(id);
            HttpSession session = request.getSession();
            ArrayList<Cart> cartList1 = (ArrayList<Cart>) session.getAttribute("cart-list");

            if (action != null && id >= 1) {
                if (action.equals("incre")) {
                    for (Cart c: cartList1) {
                        if (c.getProductId() == id) {
                            int qty = c.getQuantity();
                            qty++;
                            c.setQuantity(qty);
                            response.sendRedirect("cart.jsp");
                        }
                    }
                }
                if (action.equals("decre")) {
                    for (Cart c: cartList1) {
                        if (c.getProductId() == id && c.getQuantity() > 1) {
                            int qty = c.getQuantity();
                            qty--;
                            c.setQuantity(qty);
                            response.sendRedirect("cart.jsp");
                        }
                    }

                }
            }
            else {
                response.sendRedirect("cart.jsp");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
