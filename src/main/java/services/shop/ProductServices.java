package services.shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entities.shop.Product;
import utils.MyDatabase;

public class ProductServices implements ProductDAO<Product> {
    Connection con = MyDatabase.getInstance().getCon();

    @Override
    public void create(Product product) throws SQLException {
        String query = "INSERT INTO product (id_discipline, name, description, price, stock, category, number_of_purchases, photo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = this.con.prepareStatement(query);
        pstmt.setInt(1, product.getIdDiscipline());
        pstmt.setString(2, product.getName());
        pstmt.setString(3, product.getDescription());
        pstmt.setDouble(4, product.getPrice());
        pstmt.setInt(5, product.getQuantity()); // 'stock' column maps to quantity
        pstmt.setString(6, product.getCategory());
        pstmt.setInt(7, product.getNumberOfPurchases());
        pstmt.setString(8, product.getImage()); // 'photo' column maps to image
        pstmt.executeUpdate();
        System.out.println("Product added successfully!");
    }

    @Override
    public List<Product> readList() throws SQLException {
        String query = "SELECT * FROM product";
        List<Product> products = new ArrayList<>();
        Statement stm = this.con.createStatement();

        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {
            Product p = new Product(
                    rs.getInt("id_product"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("stock"),            // Maps to quantity
                    rs.getString("description"),
                    rs.getString("photo"),           // Maps to image
                    rs.getInt("id_discipline"),
                    rs.getString("category"),
                    rs.getInt("number_of_purchases")
            );
            products.add(p);
        }
        return products;
    }

    public Product findById(int id) {
        String query = "SELECT * FROM product WHERE id_product = ?";
        try (PreparedStatement pstmt = this.con.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("id_product"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),            // Maps to quantity
                        rs.getString("description"),
                        rs.getString("photo"),           // Maps to image
                        rs.getInt("id_discipline"),
                        rs.getString("category"),
                        rs.getInt("number_of_purchases")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Product product) {
        Product existingProduct = findById(product.getId());
        if (existingProduct == null) {
            System.out.println("Product not found, update aborted.");
            return;
        }

        String query = "UPDATE product SET id_discipline = ?, name = ?, description = ?, price = ?, stock = ?, category = ?, number_of_purchases = ?, photo = ? WHERE id_product = ?";
        try (PreparedStatement pstmt = this.con.prepareStatement(query)) {
            pstmt.setInt(1, product.getIdDiscipline());
            pstmt.setString(2, product.getName());
            pstmt.setString(3, product.getDescription());
            pstmt.setDouble(4, product.getPrice());
            pstmt.setInt(5, product.getQuantity()); // 'stock' maps to quantity
            pstmt.setString(6, product.getCategory());
            pstmt.setInt(7, product.getNumberOfPurchases());
            pstmt.setString(8, product.getImage()); // 'photo' maps to image
            pstmt.setInt(9, product.getId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Product updated successfully!");
            } else {
                System.out.println("Update failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM product WHERE id_product = ?";
        PreparedStatement pstmt = this.con.prepareStatement(query);
        pstmt.setInt(1, id);
        int affectedRows = pstmt.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("Product with ID " + id + " deleted successfully!");
        } else {
            System.out.println("No product found with ID " + id);
        }
    }

    public int createAndReturnId(Product product) throws SQLException {
        String query = "INSERT INTO product (id_discipline, name, description, price, stock, category, number_of_purchases, photo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = this.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, product.getIdDiscipline());
            pstmt.setString(2, product.getName());
            pstmt.setString(3, product.getDescription());
            pstmt.setDouble(4, product.getPrice());
            pstmt.setInt(5, product.getQuantity()); // 'stock' maps to quantity
            pstmt.setString(6, product.getCategory());
            pstmt.setInt(7, product.getNumberOfPurchases());
            pstmt.setString(8, product.getImage()); // 'photo' maps to image
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating product failed, no ID obtained.");
                }
            }
        }
    }
}
