package util;

//import jakarta.servlet.http.HttpServletRequest;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTable {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
            Connection con = DatabaseConnection.getConnection();


            String query = "CREATE TABLE `admin` (\n" +
                    "            `id` bigint(20) NOT NULL AUTO_INCREMENT,\n" +
                    " `added_date` datetime NOT NULL,\n" +
                    "            `email` varchar(100) NOT NULL,\n" +
                    " `password` varchar(100) NOT NULL,\n" +
                    " `name` varchar(100) NOT NULL,\n" +
                    "    PRIMARY KEY (`id`)\n" +
                    ")";
            PreparedStatement createTable = con.prepareStatement(query);
            createTable.execute();
            System.out.println("Table created successfully.");

            PreparedStatement statement = con.prepareStatement("INSERT INTO admin(email,password,name) VALUES('admin@gmail.com','admin','Uchenna');");
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
