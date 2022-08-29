package dao;

//public class Productdao {
//
//
//}
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Cart;
import model.Product;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class ProductDao {
    //private int id;
    private String query;
    private PreparedStatement statement;
    private ResultSet resultSet;
    public Connection con;

    public ProductDao(Connection con) {
        this.con = con;
    }

//    public ProductDao(Connection con) {
//        this.con = con;
//    }


    public boolean addProduct(Product product) {
        boolean result = false;
        try {
            query = "INSERT INTO tblproduct(id,price, product_category, name, image, description, available,quantity) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement st = DatabaseConnection.getConnection().prepareStatement(query);
            //st.setInt( product.getId());
            st.setInt(1,product.getProductId());
            st.setDouble(2, product.getPrice());
            st.setString(3, product.getProduct_category());
            st.setString(4, product.getName());
            st.setString(5, product.getImage());
            st.setString(6, product.getDescription());
            st.setString(7, product.getAvailable());
            st.setInt(8, product.getQuantity());

            int rs = st.executeUpdate();
            if (rs > 0) {
                result = true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }



    public List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<>();
        try {
            query = "SELECT * FROM tblproduct";
            statement = DatabaseConnection.getConnection().prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                double price = Double.parseDouble(resultSet.getString("price"));
                String product_category = resultSet.getString("product_category");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");
                String available = resultSet.getString("available");
                int quantity = resultSet.getInt("quantity");
                Product row = new Product(id, price, product_category, name,image, description, available,quantity);
                productList.add(row);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    public List<Cart> getCartProduct(ArrayList<Cart> cartList) {
        ArrayList<Cart> produsts = new ArrayList<Cart>();
        try {
            if (cartList.size() > 0) {
                for (Cart list : cartList) {
                    query = "SELECT * FROM tblproduct WHERE id = ?";
                    statement = con.prepareStatement(query);
                    statement.setInt(1, list.getProductId());
                    resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        Cart cart = new Cart();
                        cart.setProductId(resultSet.getInt("id"));
                        cart.setName(resultSet.getString("name"));
                        cart.setAvailable(resultSet.getString("available"));
                        cart.setImage(resultSet.getString("image"));
                        cart.setPrice(resultSet.getDouble("price")* list.getQuantity());
                        cart.setProduct_category(resultSet.getString("product_category"));
                        cart.setQuantity(list.getQuantity());
                        produsts.add(cart);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return produsts;
    }


    public double getTotalCartPrice(ArrayList<Cart> cartList2) {
        double totalPrice = 0;
        try {
            if (cartList2 != null) {
                for (Cart item: cartList2) {
                    query = "SELECT price FROM tblproduct WHERE id = ?";
                    statement = con.prepareStatement(query);
                    statement.setInt(1, item.getProductId());
                    resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        totalPrice += resultSet.getDouble("price")*item.getQuantity();
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return totalPrice;
    }




    public boolean deleteProducts(int id) {
        try {
            String Query = "DELETE from products WHERE id = ?";
            statement = DatabaseConnection.getConnection().prepareStatement(Query);
            statement.setInt(1,id);

            int resultSet1 = statement.executeUpdate();
            if (resultSet1 > 0) return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }
}


