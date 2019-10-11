package fr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.log4j.Logger;
import fr.escapegame.propriete.ChargerPropriete;

public class Launcher {

	private static final Logger LOGGER = Logger.getLogger(Launcher.class);

	public static void main(String[] args) {

		LOGGER.info("Le nombre de combinaison est " + ChargerPropriete.NB_COMBINAISON);
		LOGGER.info("Le nombre d'essais est " + ChargerPropriete.NB_ESSAI);
		LOGGER.info("Activation du mode développeur: " + ChargerPropriete.MODE_DEV);
		LOGGER.info("");
		reglement();
		ModeDeJeux jeu = selectionDuModeDeJeux();
		jeu.jouer();
		jeu.presentationIa();

	}

	/**
	 * Méthode pour l'introduction
	 */

	public static void reglement() {
		LOGGER.info("Début de partie");
		LOGGER.info(" Bonjour :-) \n\r Si tu as pris connaissance des règles de jeux nous pouvons commencer tout de suite. \n\r ");
		LOGGER.info(" Tout d'abord fais ton choix parmi les 3 modes de jeux ci-dessous:");
		LOGGER.info("");
		
	}

	public static void presentationDesModes() {

		LOGGER.info(" 1 - Le mode Challenger  ");
		LOGGER.info(" 2 - Le mode Defenseur   ");
		LOGGER.info(" 3 - Le mode Duel        ");
		LOGGER.info(" 4 - Sortie de la partie ");

	}

	public static ModeDeJeux selectionDuModeDeJeux() {

		presentationDesModes();

		Scanner sc = new Scanner(System.in);
		ModeDeJeux jeu = null;
		
		
		try {
			int nbMode = sc.nextInt();
			switch (nbMode) {
			case 1:
				LOGGER.debug(" Tu as choisi le mode Challenger");
				jeu = new ModeChallenger();
				break;
			case 2:
				LOGGER.debug(" Tu as choisi le mode Defenseur");
				jeu = new ModeDefenseur();
				break;
			case 3:
				LOGGER.debug(" Tu as choisi le mode Duel");
				jeu = new ModeDuel();
				break;
			case 4:
				LOGGER.debug("fin de partie");
				System.exit(0);
				break;
			default:
				LOGGER.debug("Erreur de saisie, recommence. ");
				selectionDuModeDeJeux();
				break;
			}

		} catch (InputMismatchException e) {
			LOGGER.debug("Erreur de saisie, recommence");
			selectionDuModeDeJeux();

		}

		return jeu;

	}
}
