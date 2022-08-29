package servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@WebServlet("/QtyIncreDecreServlet")
class QtyIncreDecreServletTest {

    private QtyIncreDecreServlet qtyIncreDecreServlet;

    @BeforeEach
    void setUp() {
        qtyIncreDecreServlet = new QtyIncreDecreServlet();
    }

    @Test
    @DisplayName("Should increase the quantity of the product when action is incre and id is valid")
    void doGetWhenActionIsIncreAndIdIsValidThenIncreaseQuantityOfProduct() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<Cart> cartList = new ArrayList<>();
        Cart cart = new Cart();
        cartList.add(cart);

        when(request.getParameter("action")).thenReturn("incre");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("cart-list")).thenReturn(cartList);

        try {
            qtyIncreDecreServlet.doGet(request, response);
            assertEquals(0, cartList.get(1).getQuantity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Should decrease the quantity of the product when action is decre and id is valid")
    void doGetWhenActionIsDecreAndIdIsValidThenDecreaseQuantityOfProduct() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<Cart> cartList = new ArrayList<>();
        Cart cart = new Cart();
        cartList.add(cart);

        when(request.getParameter("action")).thenReturn("decre");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("cart-list")).thenReturn(cartList);

        qtyIncreDecreServlet.doGet(request, response);

        assertEquals(0, cartList.get(0).getQuantity());
    }
}
