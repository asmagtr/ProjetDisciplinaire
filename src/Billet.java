
import java.util.Date;

public class Billet {
    private int id;
    private double prix;
    private Diffusion diffusion;
    private Siege siege;
    private Date dateCreation;

    // Constructeur
    public Billet(int id, double prix, Diffusion diffusion, Siege siege, Date dateCreation) {
        this.id = id;
        this.prix = prix;
        this.diffusion = diffusion;
        this.siege = siege;
        this.dateCreation = dateCreation;
    }

    // Méthode pour obtenir l'ID du billet
    public int getId() {
        return id;
    }

    // Méthode pour obtenir le prix du billet
    public double getPrix() {
        return prix;
    }

    // Méthode pour obtenir la diffusion associée au billet
    public Diffusion getDiffusion() {
        return diffusion;
    }

    // Méthode pour obtenir le siège associé au billet
    public Siege getSiege() {
        return siege;
    }

    // Méthode pour obtenir la date de création du billet
    public Date getDateCreation() {
        return dateCreation;
    }

    // Méthode pour afficher les détails du billet
    public void afficherDetails() {
        System.out.println("Billet #" + id);
        System.out.println("Prix: " + prix + " DA");
        System.out.println("Diffusion: " + diffusion.getTitre()); // Supposons que getTitre retourne le titre du film
        System.out.println("Salle: " + diffusion.getSalle().getNumero()); // Supposons que getNumero retourne le numéro de la salle
        System.out.println("Date de diffusion: " + diffusion.getDateDiffusion());
        System.out.println("Here de début: " + diffusion.getHeureDebut());
        System.out.println("Siège: " + siege.getNum()); // Supposons que getNumero retourne le numéro du siège
        System.out.println("Date de création: " + dateCreation);
    }
}
