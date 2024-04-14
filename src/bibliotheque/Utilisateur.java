package bibliotheque;

import java.util.ArrayList;

public class Utilisateur {
    private String nom;
    private int numeroIdentification;
    private ArrayList<Livre> livresEmpruntes;
    private boolean estAJourCotisations;

    // Constructeur
    public Utilisateur(String nom, int numeroIdentification) {
        this.nom = nom;
        this.numeroIdentification = numeroIdentification;
        this.livresEmpruntes = new ArrayList<>();
        this.estAJourCotisations = true; // Par défaut, l'utilisateur est à jour
    }

    // Getteurs
    public int getNumeroIdentification() {
        return this.numeroIdentification;
    }

    public ArrayList<Livre> getLivresEmpruntes() {
        return this.livresEmpruntes;
    }

    // Méthode pour initialiser la liste de livres empruntés
    public void initialiserLivresEmpruntes() {
        this.livresEmpruntes = new ArrayList<>();
    }

    // Méthode pour emprunter un livre
    public void emprunterLivre(Livre livre) {
        livresEmpruntes.add(livre);
    }

    // Méthode pour retourner un livre
    public void retournerLivre(Livre livre) {
        livresEmpruntes.remove(livre);
    }

    // Méthode pour afficher les livres empruntés par l'utilisateur
    public void afficherLivresEmpruntes() {
        System.out.println("Livres empruntés par " + this.nom + ":");
        int i = 0;
        for (Livre livre : livresEmpruntes) {
            System.out.println(livre);
            i++;
        }
        if (i == 0) {
            System.out.println("Aucun livre n'a été emprunté.");
        }
    }

    // Méthode pour vérifier si l'utilisateur est à jour par rapport à ses
    // cotisations
    public boolean estAJourCotisations() {
        return estAJourCotisations;
    }

    public String toString() {
        return "Utilisateur [Nom=" + this.nom + ", Numéro d'identification=" + this.numeroIdentification + "]";
    }
}