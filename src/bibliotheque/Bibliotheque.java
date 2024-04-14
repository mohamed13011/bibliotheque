package bibliotheque;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Bibliotheque {
    private ArrayList<Livre> listeLivres;
    private HashMap<Utilisateur, ArrayList<Livre>> empruntsUtilisateurs;

    Scanner scanner = new Scanner(System.in);

    // Constructeur
    public Bibliotheque() {
        this.listeLivres = new ArrayList<>();
        this.empruntsUtilisateurs = new HashMap<>();
    }

    // Méthode pour ajouter un livre à la bibliothèque
    public void ajouterLivre(Livre livre) {
        listeLivres.add(livre);
    }

    // Méthode pour supprimer un livre de la bibliothèque
    public void supprimerLivre(Livre livre) {
        listeLivres.remove(livre);
    }

    // Méthode pour ajouter un utilisateur à la bibliothèque
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        // Initialiser la liste de livres empruntés pour le nouvel utilisateur
        utilisateur.initialiserLivresEmpruntes();
        // Ajouter l'utilisateur à la liste des emprunts utilisateurs
        empruntsUtilisateurs.put(utilisateur, utilisateur.getLivresEmpruntes());
    }

    // Méthode pour rechercher un utilisateur par son numéro d'identification
    public Utilisateur rechercherUtilisateur(int numeroIdentification) {
        for (Utilisateur utilisateur : empruntsUtilisateurs.keySet()) {
            if (utilisateur.getNumeroIdentification() == numeroIdentification) {
                return utilisateur;
            }
        }
        return null; // Retourne null si l'utilisateur n'est pas trouvé
    }

    // Méthode pour rechercher un livre par titre, auteur ou ISBN
    public Livre rechercherLivre(String critere) {
        for (Livre livre : listeLivres) {
            if (livre.getTitre().equalsIgnoreCase(critere) ||
                    livre.getAuteur().equalsIgnoreCase(critere) ||
                    livre.getISBN().equalsIgnoreCase(critere)) {
                return livre;
            }
        }
        return null; // Retourne null si le livre n'est pas trouvé
    }

    // Méthode pour modifier un livre
    public void modifierLivre(String critere) {
        for (Livre livre : listeLivres) {
            if (livre.getTitre().equalsIgnoreCase(critere) ||
                    livre.getAuteur().equalsIgnoreCase(critere) ||
                    livre.getISBN().equalsIgnoreCase(critere)) {
                switch (critere) {
                    case "auteur":
                        System.out.println("Veuillez saisir le nouveau nom de l'auteur");
                        String nouveauNom = scanner.nextLine();
                        livre.setAuteur(nouveauNom);
                        break;
                    case "titre":
                        System.out.println("Veuillez saisir le nouveau titre du livre");
                        String nouveauTitre = scanner.nextLine();
                        livre.setTitre(nouveauTitre);
                        break;
                    case "isbn":
                        System.out.print("Entrez le nouveau code ISBN : ");
                        String newIsbn = scanner.nextLine();
                        livre.setISBN(newIsbn);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    // Méthode pour enregistrer l'emprunt d'un livre par un utilisateur
    public void enregistrerEmprunt(Utilisateur utilisateur, Livre livre) {
        if (verifierEligibilite(utilisateur)) {
            ArrayList<Livre> emprunts = empruntsUtilisateurs.getOrDefault(utilisateur, new ArrayList<>());
            if (emprunts.size() < 3) { // Vérifie si l'utilisateur a moins de 3 emprunts actuels
                emprunts.add(livre);
                empruntsUtilisateurs.put(utilisateur, emprunts);
                System.out.println("L'emprunt a été enregistré avec succès.");
            } else {
                System.out.println(
                        "L'utilisateur a déjà emprunté trois livres et ne peut pas emprunter plus pour le moment.");
            }
        } else {
            System.out.println("L'utilisateur non trouvé ou non éligible à l'emprunt.");
        }
    }

    // Méthode pour enregistrer le retour d'un livre par un utilisateur
    public void enregistrerRetour(Utilisateur utilisateur, Livre livre) {
        if (empruntsUtilisateurs.containsKey(utilisateur)) {
            empruntsUtilisateurs.get(utilisateur).remove(livre);
        }
    }

    // Méthode pour vérifier l'éligibilité d'un utilisateur à emprunter un livre
    private boolean verifierEligibilite(Utilisateur utilisateur) {
        if (empruntsUtilisateurs.containsKey(utilisateur)) {
            // On suppose qu'un utilisateur est éligible si il est à jour par rapport à ses
            // cotisations
            return utilisateur.estAJourCotisations();
        } else {
            return false; // Retourne false si l'utilisateur n'est pas trouvé
        }
    }

    // Méthode pour afficher les statistiques de la bibliothèque
    public void afficherStatistiques() {
        System.out.println("Nombre total de livres dans la bibliothèque: " + listeLivres.size());
        // Ajoutez d'autres statistiques selon vos besoins
    }
}