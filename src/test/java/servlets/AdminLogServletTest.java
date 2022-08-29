package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.mockito.Mockito.*;

class AdminLogServletTest {

    private AdminLogServlet adminLogServlet;

    @BeforeEach
    void setUp() {
        adminLogServlet = new AdminLogServlet();
    }

//    @Test
//    @DisplayName("Should set the session attribute uname when the email and password are correct")
//    void doPostWhenEmailAndPasswordAreCorrectThenSetSessionAttributeUname() throws SQLException {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        HttpSession session = mock(HttpSession.class);
//        Connection connection = mock(Connection.class);
//        Statement statement = mock(Statement.class);
//        ResultSet resultSet = mock(ResultSet.class);
//
//        when(request.getParameter("email")).thenReturn("admin@gmail.com");
//        when(request.getParameter("upass")).thenReturn("admin");
//        when(request.getSession()).thenReturn(session);
//        when(DatabaseConnection.getConnection()).thenReturn(connection);
//        when(connection.createStatement()).thenReturn(statement);
//        when(statement.executeQuery(
//                "select * from tbladmin where email='admin@gmail.com' AND password='admin'"))
//                .thenReturn(resultSet);
//        when(resultSet.next()).thenReturn(true);
//
//        try {
//            adminLogServlet.doPost(request, response);
//            verify(session, times(1)).setAttribute("uname", "admin@gmail.com");
//            verify(response, times(1)).sendRedirect("admindashboard.html");
//        } catch (IOException | ServletException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    @DisplayName(
//            "Should set the session attribute credential when the email and password are incorrect")
//    void doPostWhenEmailAndPasswordAreIncorrectThenSetSessionAttributeCredential() throws SQLException {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        HttpSession session = mock(HttpSession.class);
//        Connection connection = mock(Connection.class);
//        Statement statement = mock(Statement.class);
//        ResultSet resultSet = mock(ResultSet.class);
//
//        when(request.getParameter("email")).thenReturn("admin@gmail.com");
//        when(request.getParameter("upass")).thenReturn("admin");
//        when(request.getSession()).thenReturn(session);
//        when(DatabaseConnection.getConnection()).thenReturn(connection);
//        when(connection.createStatement()).thenReturn(statement);
//        when(statement.executeQuery(
//                "select * from tbladmin where email='admin@gmail.com' AND password='admin'"))
//                .thenReturn(resultSet);
//
//        try {
//            adminLogServlet.doPost(request, response);
//            verify(session, times(1))
//                    .setAttribute("credential", "You have enter wrong credentials");
//            verify(response, times(1)).sendRedirect("signin.jsp");
//        } catch (IOException | ServletException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    @DisplayName("Should redirect to admin dashboard when the email and password are correct")
//    void doPostWhenEmailAndPasswordAreCorrectThenRedirectToAdminDashboard() {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        HttpSession session = mock(HttpSession.class);
//        when(request.getParameter("email")).thenReturn("admin@gmail.com");
//        when(request.getParameter("upass")).thenReturn("admin");
//        when(request.getSession()).thenReturn(session);
//        try {
//            adminLogServlet.doPost(request, response);
//            verify(response).sendRedirect("adminDashboard.html");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
  //  }

    @Test
    @DisplayName("Should redirect to sign in page when the email and password are incorrect")
    void doPostWhenEmailAndPasswordAreIncorrectThenRedirectToSignInPage() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getParameter("email")).thenReturn("admin@gmail.com");
        when(request.getParameter("upass")).thenReturn("admin");
        when(request.getSession()).thenReturn(session);
        try {
            adminLogServlet.doPost(request, response);
            verify(response).sendRedirect("signin.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}