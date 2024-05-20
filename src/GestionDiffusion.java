import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GestionDiffusion {
    private Connection conn;

    public GestionDiffusion(Connection conn) {
        this.conn = conn;
    }

    public void ajouterDiffusion(Diffusion diffusion) {
        try {
            String query = "INSERT INTO diffusions (film_id, salle_id, date, heureDebut, heureFin) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, diffusion.getFilm().getId());
            pstmt.setInt(2, diffusion.getSalle().getId());
            pstmt.setDate(3, diffusion.getDate());
            pstmt.setString(4, diffusion.getHeureDebut());
            pstmt.setString(5, diffusion.getHeureFin());
            pstmt.executeUpdate();
            System.out.println("Diffusion ajoutée avec succès");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void modifierDiffusion(Diffusion diffusion, int id) {
        try {
            String query = "UPDATE diffusions SET film_id = ?, salle_id = ?, date = ?, heureDebut = ?, heureFin = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, diffusion.getFilm().getId());
            pstmt.setInt(2, diffusion.getSalle().getId());
            pstmt.setDate(3, diffusion.getDate());
            pstmt.setString(4, diffusion.getHeureDebut());
            pstmt.setString(5, diffusion.getHeureFin());
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
            System.out.println("Diffusion modifiée avec succès");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void supprimerDiffusion(int id) {
        try {
            String query = "DELETE FROM diffusions WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Diffusion supprimée avec succès");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Diffusion> afficherDiffusionsParDateSalle(Date date, int salleId) {
        List<Diffusion> diffusions = new ArrayList<>();
        try {
            String query = "SELECT d.id, f.id AS film_id, f.titre, f.genre, f.description, f.duree, f.version, " +
                    "s.id AS salle_id, s.nom, s.capacite, d.date, d.heureDebut, d.heureFin " +
                    "FROM diffusions d " +
                    "JOIN films f ON d.film_id = f.id " +
                    "JOIN salles s ON d.salle_id = s.id " +
                    "WHERE d.date = ? AND s.id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDate(1, date);
            pstmt.setInt(2, salleId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Film film = new Film(
                        rs.getInt("film_id"),
                        rs.getString("titre"),
                        rs.getString("genre"),
                        rs.getString("description"),
                        rs.getInt("duree"),
                        rs.getString("version")
                );
                Salle salle = new Salle(
                        rs.getInt("salle_id"),
                        rs.getString("nom"),
                        rs.getInt("capacite")
                );
                Diffusion diffusion = new Diffusion(
                        film,
                        salle,
                        rs.getDate("date"),
                        rs.getString("heureDebut"),
                        rs.getString("heureFin")
                );
                diffusions.add(diffusion);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return diffusions;
    }
}