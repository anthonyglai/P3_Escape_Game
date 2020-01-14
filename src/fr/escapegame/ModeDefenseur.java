package fr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import fr.escapegame.propriete.ChargerPropriete;

public class ModeDefenseur extends ModeDeJeux {

    private int[] combinaisonJoueur;
    private int messagePerteIa = 1;

    /**
     * @param introduction Méthode pour présenter le mode de jeu choisi
     */

    public void introduction() {
        System.out.println("\n");
        System.out.println("Bienvenue dans le mode Defenseur.");
        System.out.println("Une IA va devoir deviner ta combinaison de " + getNbCombinaison() + " chiffres en "
                + getNbEssai() + " essais. ");
        System.out.println("La partie commence ");

    }

    public void combinaisonAleatoireJoueur() {
        this.combinaisonJoueur = new int[getNbCombinaison()];
        for (int j = 0; j < getNbCombinaison(); j++) {
            combinaisonJoueur[j] = (int) (Math.random() * 9);
            if (getModeDev() == true) {
                System.out.print(combinaisonJoueur[j]);

            }
        }
    }

    public void combinaisonVisibleJoueur() {
        System.out.println("\n");
        System.out.print("Ta combinaison est :");
        this.combinaisonAleatoireJoueur();
        System.out.println("\n");
    }

    public void combinaisonAleatoireIa() {
        this.setCombinaisonIa(new int[getNbCombinaison()]);
        for (int i = 0; i < getNbCombinaison(); i++) {
            getCombinaisonIa()[i] = (int) (Math.random() * 9);
            if (getModeDev() == true) {
                System.out.print(getCombinaisonIa()[i]);

            }
        }
    }

    /**
     * @ param combinaisonVisibleIa La méthode retourne la méthode combiAleatoire
     */
    public void combinaisonVisibleIa() {
        System.out.println("\n");
        System.out.print("l'Ia demande si la combinaison du joueur est :");
        this.combinaisonAleatoireIa();

    }

    public void comparaisonDeCombinaisonIAetJoueur() {
        System.out.println("\n");
        for (int i = 0; i < getCombinaisonIa().length && i < combinaisonJoueur.length; i++) {
            if (getCombinaisonIa()[i] == combinaisonJoueur[i]) {
                System.out.print("=");
            } else if (getCombinaisonIa()[i] < combinaisonJoueur[i]) {
                System.out.print("+");
            } else {
                System.out.print("-");

            }
        }
    }

    public void tentativePourTrouverLaCombinaisonDuJoueur() {

        do {
            setChanceUtilisee(getChanceUtilisee() + 1);
            setNbEssai(getNbEssai());
            setNombreDeTours(getNombreDeTours() + 1);
            System.out.println("Essai n°" + getChanceUtilisee() + " pour l'IA");
            combinaisonVisibleIa();
            comparaisonDeCombinaisonIAetJoueur();
            System.out.println();
            if (getCombinaisonIa() == combinaisonJoueur) {
                System.out.print("Bravo, l'IA a trouvé le résultat ");
            } else
                System.out.println("L'IA n'a pas trouvé le résultat \n ");
            if (messagePerteIa++ == getNbEssai()) {
                System.out.println("La partie est terminée l'IA a perdu");
            }

            getNombreDeTours();

        } while (getCombinaisonIa() != combinaisonJoueur && getNombreDeTours() != getNbEssai());

    }

    public void propositionApresUneFinDePartie() {
        tentativePourTrouverLaCombinaisonDuJoueur();
        System.out.println();
        System.out.println("Pour poursuivre veuillez choisir entre les 3 modes ci-dessous");
        System.out.println();
        System.out.println("1- Recommencer une partie ");
        System.out.println("2- Changer de mode de jeu");
        System.out.println("3- Quitter le jeu");
    }

    public void choixApresUneFinDePartie() {
        Scanner sc = new Scanner(System.in);
        propositionApresUneFinDePartie();
        resetChanceUtilisee();
        resetNbTour();
        try {
            int nbMode = sc.nextInt();
            switch (nbMode) {
            case 1:
                System.out.println("La partie va recommencer ");
                jouer();
                break;

            case 2:
                System.out.println("Retour à l'acceuil pour choisir un mode de jeux");
                Launcher retourALauncher = new Launcher();
                retourALauncher.ModeDeJeux();
                break;
            case 3:
                System.out.println("fin de partie");
                System.exit(0);
                break;
            default:
                System.out.println("Recommence ta saisie il n y a que 4 possibilités :");
                choixApresUneFinDePartie();
                break;
            }

        } catch (InputMismatchException e) {
            System.out.println("Erreur de saisie, recommence");
            choixApresUneFinDePartie();
        }

    }

    public void jouer() {
        introduction();
        combinaisonVisibleJoueur();
        choixApresUneFinDePartie();

    }
}
