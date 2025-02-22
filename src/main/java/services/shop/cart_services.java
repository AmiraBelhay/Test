package services.shop;

import entities.shop.Cart;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class cart_services {
    Connection con = MyDatabase.getInstance().getCon();

    public void create(Cart cart, int userId) throws SQLException {
        String query = "INSERT INTO cart (id_user, total_price) VALUES (?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, userId);
            pstmt.setDouble(2, cart.getTotalPrice());
            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                cart.setId(generatedKeys.getInt(1));
                System.out.println("Cart created successfully with ID: " + cart.getId());
            } else {
                throw new SQLException("Creating cart failed, no ID obtained.");
            }
        }
    }

    public List<Cart> readList() throws SQLException {
        String query = "SELECT * FROM cart";
        List<Cart> carts = new ArrayList<>();

        try (Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(query)) {
            while (rs.next()) {
                Cart cart = new Cart(
                        rs.getInt("id_cart"),
                        rs.getInt("id_user"),
                        rs.getTimestamp("date_created"),
                        rs.getDouble("total_price")
                );
                carts.add(cart);
            }
        }
        return carts;
    }

    public Cart findById(int id) {
        String query = "SELECT * FROM cart WHERE id_cart = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Cart(
                        rs.getInt("id_cart"),
                        rs.getInt("id_user"),
                        rs.getTimestamp("date_created"),
                        rs.getDouble("total_price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Cart cart) {
        String query = "UPDATE cart SET total_price = ? WHERE id_cart = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setDouble(1, cart.getTotalPrice());
            pstmt.setInt(2, cart.getId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Cart updated successfully!");
            } else {
                System.out.println("Update failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM cart WHERE id_cart = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Cart with ID " + id + " deleted successfully!");
            } else {
                System.out.println("No cart found with ID " + id);
            }
        }
    }
}
