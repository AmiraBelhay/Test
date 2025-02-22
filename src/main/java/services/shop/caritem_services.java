package services.shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.shop.Cartitem;
import entities.shop.Product;
import utils.MyDatabase;

public class caritem_services implements ProductDAO<Cartitem> {
    Connection con = MyDatabase.getInstance().getCon();

    @Override
    public void create(Cartitem cartItem) throws SQLException {
        String query = "INSERT INTO cartitem (id_cart, id_product, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = this.con.prepareStatement(query)) {
            pstmt.setInt(1, cartItem.getCartId());
            pstmt.setInt(2, cartItem.getProductId());
            pstmt.setInt(3, cartItem.getQuantity());
            pstmt.setDouble(4, cartItem.getPrice());
            pstmt.executeUpdate();
            System.out.println("Cart item added successfully!");
        }
    }

    @Override
    public List<Cartitem> readList() throws SQLException {
        String query = "SELECT * FROM cartitem";
        List<Cartitem> cartItems = new ArrayList<>();

        try (Statement stmt = this.con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            ProductServices productService = new ProductServices();

            while (rs.next()) {
                Product product = productService.findById(rs.getInt("id_product"));
                int cartId = rs.getInt("id_cart");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                Cartitem cartItem = new Cartitem(rs.getInt("id_cart_item"), cartId, product.getId(), quantity, price);
                cartItems.add(cartItem);
            }
        }
        return cartItems;
    }

    @Override
    public Cartitem findById(int id) {
        String query = "SELECT * FROM cartitem WHERE id_cart_item = ?";
        try (PreparedStatement pstmt = this.con.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            ProductServices productService = new ProductServices();

            if (rs.next()) {
                Product product = productService.findById(rs.getInt("id_product"));
                int cartId = rs.getInt("id_cart");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                return new Cartitem(rs.getInt("id_cart_item"), cartId, product.getId(), quantity, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Cartitem cartItem) {
        String query = "UPDATE cartitem SET quantity = ?, price = ? WHERE id_cart_item = ?";
        try (PreparedStatement pstmt = this.con.prepareStatement(query)) {
            pstmt.setInt(1, cartItem.getQuantity());
            pstmt.setDouble(2, cartItem.getPrice());
            pstmt.setInt(3, cartItem.getId());

            // Debug: Log the query and parameters
            System.out.println("Executing query: " + query);
            System.out.println("Values - Quantity: " + cartItem.getQuantity() + ", Price: " + cartItem.getPrice() + ", ID: " + cartItem.getId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Cart item updated successfully!");
            } else {
                System.out.println("Update failed: No rows affected. Possible reasons could be incorrect ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM cartitem WHERE id_cart_item = ?";
        try (PreparedStatement pstmt = this.con.prepareStatement(query)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Cart item deleted successfully!");
            } else {
                System.out.println("No cart item found with ID " + id);
            }
        }
    }
}
