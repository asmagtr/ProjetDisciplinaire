
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionFilms {
    private Connection connection;

    public GestionFilms(Connection connection) {
        this.connection = connection;
    }

    public void ajouterFilm(Film film) {
        String query = "INSERT INTO films (nom, duree, genre, version) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, film.getTitre());
            stmt.setInt(2, film.getDuree());
            stmt.setString(3, film.getGenre());
            stmt.setString(4, film.getVersion());
            stmt.executeUpdate();
            System.out.println("Film ajouté avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du film : " + e.getMessage());
        }
    }

    public void modifierFilm(Film film) {
        String query = "UPDATE films SET duree = ?, genre = ?, version = ? WHERE nom = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, film.getDuree());
            stmt.setString(2, film.getGenre());
            stmt.setString(3, film.getVersion());
            stmt.setString(4, film.getTitre());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Film modifié avec succès !");
            } else {
                System.out.println("Aucun film trouvé avec ce nom.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification du film : " + e.getMessage());
        }
    }

    public void supprimerFilm(String nomFilm) {
        String query = "DELETE FROM films WHERE nom = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nomFilm);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Film supprimé avec succès !");
            } else {
                System.out.println("Aucun film trouvé avec ce nom.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du film : " + e.getMessage());
        }
    }

    public void afficherListeFilms() {
        String query = "SELECT * FROM films";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String nom = rs.getString("nom");
                int duree = rs.getInt("duree");
                String genre = rs.getString("genre");
                String version = rs.getString("version");
                System.out.println(nom + " - Durée: " + duree + " minutes - Genre: " + genre + " - Version: " + version);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des films : " + e.getMessage());
        }
    }
}
