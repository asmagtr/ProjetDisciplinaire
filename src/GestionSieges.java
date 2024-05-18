import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestionSieges {
    private Connection connection;

    public GestionSieges(Connection connection) {
        this.connection = connection;
    }

    public List<Siege> getAllSieges() {
        List<Siege> sieges = new ArrayList<>();
        String query = "SELECT * FROM sieges";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int numero = rs.getInt("numero");
                String type = rs.getString("type");
                boolean estLibre = rs.getBoolean("est_libre");
                Siege siege = new Siege(numero, type, estLibre);
                sieges.add(siege);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des sièges : " + e.getMessage());
        }
        return sieges;
    }

    public boolean reserverSiege(int numeroSiege) {
        String updateQuery = "UPDATE sieges SET est_libre = false WHERE numero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(updateQuery)) {
            stmt.setInt(1, numeroSiege);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la réservation du siège : " + e.getMessage());
            return false;
        }
    }

    public boolean annulerReservationSiege(int numeroSiege) {
        String updateQuery = "UPDATE sieges SET est_libre = true WHERE numero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(updateQuery)) {
            stmt.setInt(1, numeroSiege);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'annulation de la réservation du siège : " + e.getMessage());
            return false;
        }
    }

    public boolean isSiegeLibre(int numeroSiege) {
        String query = "SELECT est_libre FROM sieges WHERE numero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, numeroSiege);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("est_libre");
            } else {
                return false; // Siège non trouvé
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification du statut du siège : " + e.getMessage());
            return false;
        }
    }
}
