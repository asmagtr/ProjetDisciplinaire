import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionSalles {
    private Connection connection;

    public GestionSalles(Connection connection) {
        this.connection = connection;
    }

    public void ajouterSalle(Salle salle) {
        String query = "INSERT INTO salles (numero, capacite) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, salle.getId());
            stmt.setInt(2, salle.getCapacite());
            stmt.executeUpdate();
            System.out.println("Salle ajoutée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la salle : " + e.getMessage());
        }
    }

    public void modifierSalle(Salle salle) {
        String query = "UPDATE salles SET capacite = ? WHERE numero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, salle.getCapacite());
            stmt.setInt(2, salle.getId());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Salle modifiée avec succès !");
            } else {
                System.out.println("Aucune salle trouvée avec ce numéro.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification de la salle : " + e.getMessage());
        }
    }

    public void supprimerSalle(int numeroSalle) {
        String query = "DELETE FROM salles WHERE numero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, numeroSalle);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Salle supprimée avec succès !");
            } else {
                System.out.println("Aucune salle trouvée avec ce numéro.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la salle : " + e.getMessage());
        }
    }

    public void afficherListeSalles() {
        String query = "SELECT * FROM salles";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int numero = rs.getInt("numero");
                int capacite = rs.getInt("capacite");
                System.out.println("Salle " + numero + " - Capacité: " + capacite + " places");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des salles : " + e.getMessage());
        }
    }
}