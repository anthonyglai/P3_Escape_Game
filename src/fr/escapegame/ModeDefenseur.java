package fr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import fr.escapegame.propriete.ChargerPropriete;

public class ModeDefenseur extends ModeDeJeux {

    /**
     * Méthode pour présenter le mode de jeu defenseur
     */

    public void introduction() {
        System.out.println("\n");
        System.out.println("Bienvenue dans le mode Defenseur.");
        System.out.println("Une IA va devoir deviner ta combinaison de " + getNbCombinaison() + " chiffres en "
                + getNbEssai() + " essais. ");
        System.out.println("La partie commence ");

    }

    /**
     * La methode genere une combinaison aleatoire pour le joueur entre 0 et 9
     * chiffres. La variable nbCombinaison determinera le nombre de chiffre que
     * comportera la combinaison La variable modeDev permet l'affichage de la
     * combinaison si elle est = a true .
     */

    public void combinaisonAleatoireJoueur() {
        this.setCombinaisonJoueur(new int[getNbCombinaison()]);
        for (int j = 0; j < getNbCombinaison(); j++) {
            getCombinaisonJoueur()[j] = (int) (Math.random() * 9);
            if (getModeDev() == true) {
                System.out.print(getCombinaisonJoueur()[j]);

            }
        }
    }

    /**
     * La methode affiche ou non la combinaison aleatoire du joueur si la variable
     * modeDev dans la methode combinaisonAleatoireJoueur est true
     */
    public void combinaisonVisibleJoueur() {
        System.out.println("\n");
        System.out.print("Ta combinaison est :");
        this.combinaisonAleatoireJoueur();
        System.out.println("\n");
    }

    /**
     * La methode genere une combinaison aleatoire pour l IA entre 0 et 9 chiffres.
     * La variable nbCombinaison determinera le nombre de chiffre que comportera la
     * combinaison La variable modeDev permet l'affichage de la combinaison si elle
     * est = a true .
     */

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
     * La methode affiche ou non la combinaison aleatoire de l IA si la variable
     * modeDev dans la methode combinaisonAleatoireIa est true
     */
    public void combinaisonVisibleIa() {
        System.out.println("\n");
        System.out.print("l'Ia demande si la combinaison du joueur est :");
        this.combinaisonAleatoireIa();

    }

    /**
     * La methode compare la combinaison de l IA a celle du joueur en fonction du
     * resultat les operateurs + - ou = sont affiche pour
     */

    public void comparaisonDeCombinaisonIAetJoueur() {
        System.out.println("\n");
        for (int i = 0; i < getCombinaisonIa().length && i < getCombinaisonJoueur().length; i++) {
            if (getCombinaisonIa()[i] == getCombinaisonJoueur()[i]) {
                System.out.print("=");
            } else if (getCombinaisonIa()[i] < getCombinaisonJoueur()[i]) {
                System.out.print("+");
            } else {
                System.out.print("-");

            }}}
    

     

        public void resulat() {
            System.out.println("\n");
            System.out.print("Le résultat est le suivant");
            comparaisonDeCombinaisonIAetJoueur();
        }
    
    /**
     * La methode indique au joueur si l IA a gagnee, n a pas trouvee le resultat ou
     * si elle a perdu
     */

    public void tentativePourTrouverLaCombinaisonDuJoueur() {

        do {
            setChanceUtilisee(getChanceUtilisee() + 1);
            setMessagePerteIa(getMessagePerteIa() + 1);
            setNbEssai(getNbEssai());
            setNombreDeTours(getNombreDeTours() + 1);
            System.out.println("Essai n°" + getChanceUtilisee() + " pour l'IA");
            combinaisonVisibleIa();
            resulat();
            System.out.println();
            if (getCombinaisonIa() == getCombinaisonJoueur()) {
                System.out.print("Bravo, l'IA a trouvé le résultat ");
            } else
                System.out.println("L'IA n'a pas trouvé le résultat \n ");
            if (getMessagePerteIa() == getNbEssai()) {
                System.out.println("La partie est terminée l'IA a perdu");
            }

            getNombreDeTours();

        } while (getCombinaisonIa() != getCombinaisonJoueur() && getNombreDeTours() != getNbEssai());

    }

    /**
     * Methode demandant au joueur de saisir si il souhaite recommencer changer ou
     * quitter le jeu
     */
    public void propositionApresUneFinDePartie() {
        tentativePourTrouverLaCombinaisonDuJoueur();
        System.out.println();
        System.out.println("Pour poursuivre veuillez choisir entre les 3 modes ci-dessous");
        System.out.println();
        System.out.println("1- Recommencer une partie ");
        System.out.println("2- Changer de mode de jeu");
        System.out.println("3- Quitter le jeu");
    }

    /**
     * Methode qui permet au joueur en fonction du choix proposé et de sa saisie de
     * faire une nouvelle partie , de retourner dans le menu de selection des modes
     * de jeux ou de sortir du jeu
     */

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
       /* rand();*/

    }
}
