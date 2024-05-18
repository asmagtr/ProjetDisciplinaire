import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestionBillet {
    private Connection connection;

    // Constructeur pour initialiser la connexion à la base de données
    public GestionBillet(Connection connection) {
        this.connection = connection;
    }

    // Méthode pour réserver un billet
    public boolean reserverBillet(Billet billet) {
        try {
            // Préparation de la requête SQL pour insérer un nouveau billet dans la table 'billet'
            String sql = "INSERT INTO billet (id_diffusion, id_siege, prix, date_creation) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, billet.getDiffusion().getId());
            statement.setInt(2, billet.getSiege().getId());
            statement.setDouble(3, billet.getPrix());
            statement.setDate(4, new java.sql.Date(billet.getDateCreation().getTime()));

            // Exécution de la requête d'insertion
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0; // Retourne true si l'insertion a réussi, sinon false
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour annuler un billets
    public boolean annulerBillet(int idBillet) {
        try {
            // Préparation de la requête SQL pour supprimer un billet de la table 'billet' par son ID
            String sql = "DELETE FROM billet WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idBillet);

            // Exécution de la requête de suppression
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0; // Retourne true si la suppression a réussi, sinon false
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour obtenir tous les billets dans la base de données
    public List<Billet> getAllBillets() {
        List<Billet> billets = new ArrayList<>();
        try {
            // Préparation de la requête SQL pour sélectionner tous les billets de la table 'billet'
            String sql = "SELECT * FROM billet";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            // Parcours des résultats de la requête
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idDiffusion = resultSet.getInt("id_diffusion");
                int idSiege = resultSet.getInt("id_siege");
                double prix = resultSet.getDouble("prix");
                Date dateCreation = resultSet.getDate("date_creation");

                // Obtention de la diffusion et du siège associés à ce billet
                Diffusion diffusion = obtenirDiffusionParId(idDiffusion);
                Siege siege = obtenirSiegeParId(idSiege);

                // Création d'un nouvel objet Billet avec les informations récupérées et ajout à la liste
                Billet billet = new Billet(id, prix, diffusion, siege, dateCreation);
                billets.add(billet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billets;
    }

    // Méthode privée pour obtenir une diffusion par son ID
    private Diffusion obtenirDiffusionParId(int idDiffusion) {
        Diffusion diffusion = null;
        try {
            String sql = "SELECT * FROM diffusion WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idDiffusion);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Récupération des informations de la diffusion depuis le ResultSet
                String nomFilm = resultSet.getString("nom_film");
                Date dateDiffusion = resultSet.getDate("date_diffusion");
                String horaireDebut = resultSet.getString("horaire_debut");
                String horaireFin = resultSet.getString("horaire_fin");
                // Création d'un nouvel objet Diffusion avec les informations récupérées
                diffusion = new Diffusion(idDiffusion, nomFilm, dateDiffusion, horaireDebut, horaireFin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diffusion;
    }

    // Méthode privée pour obtenir un siège par son ID
    private Siege obtenirSiegeParId(int idSiege) {
        Siege siege = null;
        try {
            String sql = "SELECT * FROM siege WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idSiege);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Récupération des informations du siège depuis le ResultSet
                String typeSiege = resultSet.getString("type_siege");
                boolean estLibre = resultSet.getBoolean("est_libre");
                // Création d'un nouvel objet Siege avec les informations récupérées
                siege = new Siege(idSiege, typeSiege, estLibre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return siege;
    }
}

