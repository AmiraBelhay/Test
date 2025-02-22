package services.shop;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import entities.shop.Command;
import utils.MyDatabase;

public class command_services implements ProductDAO<Command> {
    private final Connection con;

    public command_services() {
        this.con = MyDatabase.getInstance().getCon();
    }

    @Override
    public void create(Command command) throws SQLException {
        String query = "INSERT INTO command (id_cart, id_user, status, payment_method, date_created) VALUES (?, ?, ?, ?, ?)";
        // On laisse la base générer l'id (auto-incrémenté) donc on ne l'insère pas.
        try (PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, command.getCartId());
            pstmt.setInt(2, command.getUserId());  // Passer id_user comme entier
            pstmt.setString(3, command.getStatus().name());
            pstmt.setString(4, command.getPaymentMethod());
            pstmt.setTimestamp(5, Timestamp.valueOf(command.getDateCreated()));

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                // Récupérer l'id généré par la BD et l'affecter à l'objet command
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // Vous pouvez créer un setter pour id si nécessaire
                        // Par exemple : command.setId(generatedKeys.getInt(1));
                        System.out.println("Commande ajoutée avec succès, id généré : " + generatedKeys.getInt(1));
                    }
                }
            } else {
                System.out.println("Aucune commande n'a été insérée.");
            }
        }
    }

    @Override
    public List<Command> readList() throws SQLException {
        List<Command> commands = new ArrayList<>();
        String query = "SELECT * FROM command";

        try (Statement stm = con.createStatement();
             ResultSet rs = stm.executeQuery(query)) {

            while (rs.next()) {
                Command command = new Command(
                        rs.getInt("id_command"),         // id en int
                        rs.getInt("id_cart"),              // cartId en int
                        rs.getInt("id_user"),              // userId en int
                        rs.getTimestamp("date_created").toLocalDateTime(),
                        Command.Status.valueOf(rs.getString("status")),
                        rs.getString("payment_method")
                );
                commands.add(command);
            }
        }
        return commands;
    }

    @Override
    public Command findById(int id) {
        String query = "SELECT * FROM command WHERE id_command = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id);  // Ici, on passe l'id en entier
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Command(
                        rs.getInt("id_command"),
                        rs.getInt("id_cart"),
                        rs.getInt("id_user"),  // userId en int
                        rs.getTimestamp("date_created").toLocalDateTime(),
                        Command.Status.valueOf(rs.getString("status")),
                        rs.getString("payment_method")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Command command) {
        String query = "UPDATE command SET id_cart = ?, id_user = ?, status = ?, payment_method = ? WHERE id_command = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, command.getCartId());
            pstmt.setInt(2, command.getUserId());  // id_user comme entier
            pstmt.setInt(3, command.getStatus().ordinal());  // Use ordinal instead of name
            pstmt.setString(4, command.getPaymentMethod());
            pstmt.setInt(5, command.getId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Commande mise à jour avec succès !");
            } else {
                System.out.println("Échec de la mise à jour de la commande.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM command WHERE id_command = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Commande supprimée avec succès !");
            } else {
                System.out.println("Aucune commande trouvée avec cet ID.");
            }
        }
    }
}
