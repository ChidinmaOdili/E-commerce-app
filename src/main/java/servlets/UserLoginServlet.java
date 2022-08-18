package servlets;

import dao.LoginDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.User;
import util.DatabaseConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "UserLogin", value = "/userLoginServletA")
public class UserLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //Getting all data from user/customer
        String email = request.getParameter("email");
        System.out.println(email);
        String password = request.getParameter("password");
        System.out.println(password);
        LoginDao loginDao = new LoginDao();
        //Creating Session
        HttpSession hs = request.getSession();
        try(PrintWriter out = response.getWriter()) {

            User user = loginDao.fetchLoginFromDB(email, password);
            String emailFromDB = user.getEmail();
            String passwordFromDB = user.getPassword();
            if (emailFromDB.equals(email) && passwordFromDB.equals(password)) {
                hs.setAttribute("emailLogin", email);
                response.sendRedirect("index.jsp");
            }else {
                response.sendRedirect("ulogin.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





