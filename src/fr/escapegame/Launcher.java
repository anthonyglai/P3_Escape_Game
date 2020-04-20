package fr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.log4j.Logger;
import fr.escapegame.propriete.ChargerPropriete;

public class Launcher extends ModeDeJeux  {

    /*private Integer selected = null;*/
    private static final Logger LOGGER = Logger.getLogger(Launcher.class);

    /**
     * Methode affichant les differents parametrages du jeu
     */
    public void parametrage() {
        LOGGER.info("Le nombre de combinaison est " + ChargerPropriete.NB_COMBINAISON);
        LOGGER.info("Le nombre d'essais est " + ChargerPropriete.NB_ESSAI);
        LOGGER.info("Activation du mode développeur: " + ChargerPropriete.MODE_DEV + "\n");
    }

    /**
     * Methode pour selectionner les modes de jeux et sortir du jeu
     */
    public void presentationDesModes() {
        System.out.println("Veuillez choisir parmi les 3 modes de jeux ci-dessous");
        LOGGER.info(" 1 - Le mode Challenger  ");
        LOGGER.info(" 2 - Le mode Defenseur   ");
        LOGGER.info(" 3 - Le mode Duel        ");
        LOGGER.info(" 4 - Sortie du jeu ");
    }

    /*
     * Methode pour selectionner des 3 modes de jeux
     */
    public void ModeDeJeux() {
        System.out.println("tata");
        try {
            if (this.selected == null) {
                System.out.println("toto");
                Scanner sc = new Scanner(System.in);
                this.selected = sc.nextInt();
            }
            System.out.println("titi");
            switch (this.selected) {
            case 1:
                System.out.println("Vous avez choisi le mode challenger, vous allez devoir deviner la combinaison de l IA de " + getNbCombinaison() + " chiffre(s) en " + getNbEssai() + " essais. \n");
                System.out.println("La partie commence.\n");                
                ModeChallenger modeChallenger = new ModeChallenger();
                modeChallenger.jouer();
                break;
            case 2:
                System.out.println("Vous avez choisi le mode Defenseur, l'IA doit deviner votre combinaison secrète de "+ getNbCombinaison() + " chiffre(s) en " + getNbEssai() + " essais. \n");
                System.out.println("La partie commence.\n");
                ModeDefenseur modeDefenseur = new ModeDefenseur();
                modeDefenseur.jouer();
                break;
            case 3:
                LOGGER.info("Vous avez choisi le mode Duel, le premier entre l'IA ou le joueur qui trouve la combinaison de " + getNbCombinaison()+ " chiffre(s) a gagné. ");
                System.out.println("Chaque participant à " + getNbEssai() + " essais. \n");
                System.out.println("La partie commence \n ");
                ModeDuel modeDuel = new ModeDuel();
                modeDuel.jouer();
                break;
            case 4:
                LOGGER.info("fin de partie");
                System.exit(0);
                break;
            default:
                LOGGER.error("Recommencez votre saisie il n y a que 4 possibilité :");
                this.selected = null;
                ModeDeJeux();
                break;
            }
            choixApresUneFinDePartie();
        } catch (InputMismatchException e) {
            LOGGER.error("Erreur de saisie, veuillez recommencer");
            ModeDeJeux();
        }
    }

    /**
     * Methode qui permet au joueur en fonction du choix propose et de sa saisie de
     * faire une nouvelle partie de retourner dans le menu de selection des modes de
     * jeux ou de sortir du jeu
     */
    public void choixApresUneFinDePartie() {
        System.out.println("\n");
        System.out.println("Pour poursuivre, veuillez choisir entre les 3 modes ci-dessous:");
        System.out.println();
        System.out.println("1- Recommencer une partie ");
        System.out.println("2- Changer de mode de jeu");
        System.out.println("3- Quitter le jeu");  
        Scanner sc = new Scanner(System.in);
        {
            try {

                int nbMode = sc.nextInt();
                switch (nbMode) {

                case 1:
                    System.out.println("La partie va recommencer\n");
                    System.out.println("test variable selected = " + selected);
                    this.ModeDeJeux();
                    break;
                case 2:
                    this.selected = null;
                    System.out.println("Retour à l'accueil pour choisir un autre mode de jeux\n");
                    this.presentationDesModes();
                    this.ModeDeJeux();
                    break;
                case 3:
                    this.selected = null;
                    System.out.println("fin de partie");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Recommencez votre saisie il n y a que 4 possibilités\n");
                    choixApresUneFinDePartie();
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur de saisie, recommence\n");
                choixApresUneFinDePartie();
            }
        }

    }
}
