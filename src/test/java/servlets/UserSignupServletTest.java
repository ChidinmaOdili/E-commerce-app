package servlets;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserSignupServletTest {

    private UserSignupServlet userSignupServlet;

    @BeforeEach
    void setUp() {
        userSignupServlet = new UserSignupServlet();
    }

    @Test
    @DisplayName("Should save the customer when the email is not taken")
    void doPostWhenEmailIsNotTaken() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getParameter("name")).thenReturn("Chidinma");
        when(request.getParameter("email")).thenReturn("chidinma@gmail.com");
        when(request.getParameter("password")).thenReturn("chidinma1234");
        when(request.getParameter("mobile")).thenReturn("08023456789");
        when(request.getParameter("gender")).thenReturn("female");
        when(request.getParameter("address")).thenReturn("Lagos");
        when(request.getParameter("pincode")).thenReturn("23401");
        when(request.getSession()).thenReturn(session);

        try {
            userSignupServlet.doPost(request, response);
            verify(response).sendRedirect("signup.jsp?msg=valid");
            verify(session).setAttribute("success-message", "Customer register successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Test
//    @DisplayName("Should throw an exception when the email is already taken")
//    void doPostWhenEmailIsAlreadyTakenThenThrowException() {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        HttpSession session = mock(HttpSession.class);
//        when(request.getParameter("name")).thenReturn("Chidinma");
//        when(request.getParameter("email")).thenReturn("chidinma@gmail.com");
//        when(request.getParameter("password")).thenReturn("chidinma1234");
//        when(request.getParameter("mobile")).thenReturn("08023456789");
//        when(request.getParameter("gender")).thenReturn("female");
//        when(request.getParameter("address")).thenReturn("Lagos");
//        when(request.getParameter("pincode")).thenReturn("23401");
//        when(request.getSession()).thenReturn(session);
//
//
//
//        try {
//            userSignupServlet.doPost(request, response);
//            verify(response).sendRedirect("signup.jsp?msg=invalid");
//            verify(session).setAttribute("success-message", "Customer not registered successfully.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
  //  }
}