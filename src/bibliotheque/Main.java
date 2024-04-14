package bibliotheque;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bibliotheque bibliotheque = new Bibliotheque();
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        // Interface utilisateur
        System.out.println(" ");
        System.out.println("+-------Bienvenue dans la bibliothèque!--------+");

        while (true) {
            System.out.println("|     \t\t==Menu:==\t\t       |");
            System.out.println("|  1. Ajouter un utilisateur-------------------|");
            System.out.println("|  2. Ajouter un livre-------------------------|");
            System.out.println("|  3. Supprimer un livre-----------------------|");
            System.out.println("|  4. Modifier un livre------------------------|");
            System.out.println("|  5. Rechercher un livre----------------------|");
            System.out.println("|  6. Enregistrer un emprunt-------------------|");
            System.out.println("|  7. Enregistrer un retour--------------------|");
            System.out.println("|  8. Afficher les empruntés d'un utilisateur--|");
            System.out.println("|  9. Afficher les statistiques----------------|");
            System.out.println("+ 10. Quitter----------------------------------+");
            System.out.print(" Veuillez choisir une option: ");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    // Ajouter un utilisateur
                    System.out.println("Ajout d'un nouvel utilisateur:");
                    System.out.print("Nom: ");
                    String nom = scanner.nextLine();
                    System.out.print("Numero d'identification: ");
                    int numeroIdentification = scanner.nextInt();
                    Utilisateur userCreation = new Utilisateur(nom, numeroIdentification);
                    bibliotheque.ajouterUtilisateur(userCreation);
                    System.out.println(userCreation + " a été ajouté(e).");
                    break;
                case 2:
                    // Ajouter un livre
                    System.out.println("Ajout d'un nouveau livre:");
                    System.out.print("Titre: ");
                    String titre = scanner.nextLine();
                    System.out.print("Auteur: ");
                    String auteur = scanner.nextLine();
                    System.out.print("Année de publication: ");
                    int anneePublication = scanner.nextInt();
                    scanner.nextLine(); // Pour consommer la nouvelle ligne
                    System.out.print("ISBN: ");
                    String ISBN = scanner.nextLine();
                    Livre nouveauLivre = new Livre(titre, auteur, anneePublication, ISBN);
                    bibliotheque.ajouterLivre(nouveauLivre);
                    System.out.println("Le livre a été ajouté avec succès!");
                    break;
                case 3:
                    // Supprimer un livre
                    System.out.print("ISBN du livre à supprimer: ");
                    String isbnSupprimer = scanner.nextLine();
                    Livre livreASupprimer = bibliotheque.rechercherLivre(isbnSupprimer);
                    if (livreASupprimer != null) {
                        bibliotheque.supprimerLivre(livreASupprimer);
                        System.out.println("Le livre a été supprimé avec succès!");
                    } else {
                        System.out.println("Le livre avec l'ISBN spécifié n'existe pas dans la bibliothèque.");
                    }
                    break;
                case 4:
                    // Modifier un livre
                    System.out.print("Saisir un champs du livre a modifie (titre, auteur ou ISBN): ");
                    String champAModifier = scanner.nextLine().toLowerCase();
                    bibliotheque.modifierLivre(champAModifier);
                    break;
                case 5:
                    // Rechercher un livre
                    System.out.print("Rechercher un livre par titre, auteur ou ISBN: ");
                    String critereRecherche = scanner.nextLine();
                    Livre livreRecherche = bibliotheque.rechercherLivre(critereRecherche);
                    if (livreRecherche != null) {
                        System.out.println("Livre trouvé:");
                        System.out.println(livreRecherche);
                    } else {
                        System.out.println("Aucun livre trouvé avec le critère de recherche spécifié.");
                    }
                    break;
                case 6:
                    // Enregistrer un emprunt
                    System.out.println(" ==Enregistrer un emprunt==");
                    System.out.print("Numéro d'identification de l'utilisateur: ");
                    int numeroIdentificationEmprunt = scanner.nextInt();
                    scanner.nextLine(); // Pour consommer la nouvelle ligne
                    System.out.print("ISBN du livre à emprunter: ");
                    String isbnEmprunt = scanner.nextLine();

                    Utilisateur utilisateurEmprunt = bibliotheque.rechercherUtilisateur(numeroIdentificationEmprunt);
                    Livre livreEmprunt = bibliotheque.rechercherLivre(isbnEmprunt);

                    bibliotheque.enregistrerEmprunt(utilisateurEmprunt, livreEmprunt);
                    break;
                case 7:
                    // Enregistrer un retour
                    System.out.println("Enregistrer un retour:");
                    System.out.print("Numéro d'identification de l'utilisateur: ");
                    int numeroIdentificationRetour = scanner.nextInt();
                    scanner.nextLine(); // Pour consommer la nouvelle ligne
                    System.out.print("ISBN du livre à retourner: ");
                    String isbnRetour = scanner.nextLine();

                    Utilisateur utilisateurRetour = bibliotheque.rechercherUtilisateur(numeroIdentificationRetour);
                    Livre livreRetour = bibliotheque.rechercherLivre(isbnRetour);

                    if (utilisateurRetour != null && livreRetour != null) {
                        bibliotheque.enregistrerRetour(utilisateurRetour, livreRetour);
                        System.out.println("Le retour a été enregistré avec succès!");
                    } else {
                        System.out.println("Utilisateur ou livre non trouvé.");
                    }
                    break;
                case 8:
                    // Afficher les livres empruntés d'un utilisateur
                    System.out.print("Numéro d'identification de l'utilisateur: ");
                    int idUtilisateurEmprunt = scanner.nextInt();
                    Utilisateur userAffichage = bibliotheque.rechercherUtilisateur(idUtilisateurEmprunt);
                    System.out.println(userAffichage + " a été ajouté(e).");
                    userAffichage.afficherLivresEmpruntes();
                    break;
                case 9:
                    // Afficher les statistiques
                    bibliotheque.afficherStatistiques();
                    break;
                case 10:
                    // Quitter
                    System.out.println("Merci d'avoir utilisé notre bibliothèque. Au revoir!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez choisir une option valide.");
            }
        }
    }
}
