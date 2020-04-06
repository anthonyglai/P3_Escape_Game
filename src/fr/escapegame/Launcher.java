package fr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.log4j.Logger;
import fr.escapegame.propriete.ChargerPropriete;

public class Launcher {

    public int nbMode =0;
    private Integer selected = null;

	private static final Logger LOGGER = Logger.getLogger(Launcher.class);

	/**
	 * Methode affichant les differents parametrages du jeu
	 */
	public void parametrage() {
		LOGGER.info ("Le nombre de combinaison est " + ChargerPropriete.NB_COMBINAISON);
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
		
		try {
		    if (this.selected == null) {
		    Scanner sc = new Scanner(System.in);
		    this.selected = sc.nextInt();
		    }
			switch (this.selected) {
			case 1:
			    ModeChallenger modeChallenger = new ModeChallenger();
				modeChallenger.jouer();
				break;
			
			case 2:
			    LOGGER.info("Vous avez choisi le mode Defenseur");
				ModeDefenseur modeDefenseur = new ModeDefenseur();
				modeDefenseur.jouer();
				break;
			case 3:
			    LOGGER.info("Vous avez choisi le mode Duel");
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
			propositionApresUneFinDePartie();
			choixApresUneFinDePartie();
		} catch (InputMismatchException e) {
			LOGGER.error("Erreur de saisie, veuillez recommencer");
			ModeDeJeux();
		}	
}	
		
	
	/**
     * Methode demandant au joueur de saisir si il souhaite recommencer changer ou
     * quitter le jeu
     */
    public void propositionApresUneFinDePartie() {
        System.out.println("\n");
        System.out.println("Pour poursuivre, veuillez choisir entre les 3 modes ci-dessous:");
        System.out.println();
        System.out.println("1- Recommencer une partie ");
        System.out.println("2- Changer de mode de jeu");
        System.out.println("3- Quitter le jeu");
    }

    /**
     * Methode qui permet au joueur en fonction du choix propose et de sa saisie de
     * faire une nouvelle partie de retourner dans le menu de selection des modes
     * de jeux ou de sortir du jeu
     */
    public void choixApresUneFinDePartie() {
        Scanner sc = new Scanner(System.in);
        {
        try {
            
            int nbMode = sc.nextInt();
            switch (nbMode) {
            
            case 1:
                System.out.println("La partie va recommencer\n");
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
	
        }}
