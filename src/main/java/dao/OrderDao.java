package dao;

import model.Order;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {


    private Connection con;

    private String query;
    private PreparedStatement pst;
    private ResultSet rs;



    public  OrderDao(Connection con) {
        super();
        this.con = con;
    }

    public boolean insertOrder(Order model) {
        boolean result = false;
        try {
            query = "insert into orders (productId, userId, orderQuantity, orderDate) values(?,?,?,?)";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, model.getOrderId());
            pst.setInt(2, model.getUid());
            pst.setInt(3, model.getQunatity());
            pst.setString(4, model.getDate());
            pst.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }


    public List<Order> userOrders(int id) {
        List<Order> list = new ArrayList<>();
        try {
            query = "select * from orders where userId=? order by orders.orderId desc";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                ProductDao productDao = new ProductDao(this.con);
                int productId = rs.getInt("productId");
//                Product product = productDao.getSingleProduct(productId);
                order.setOrderId(rs.getInt("orderId"));
//                order.setOrderId(productId);
//                order.setName(product.getName());
//                order.setProduct_category(product.getProduct_category());
//                order.setPrice(product.getPrice()*rs.getInt("orderQuantity"));
//                order.setQunatity(rs.getInt("orderQuantity"));
                order.setDate(rs.getString("orderDate"));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void cancelOrder(int id) {
        //boolean result = false;
        try {
            query = "DELETE FROM orders WHERE orderId=?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            pst.execute();
            //result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        //return result;
    }

}


