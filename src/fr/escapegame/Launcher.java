package fr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.log4j.Logger;
import fr.escapegame.propriete.ChargerPropriete;

public class Launcher {

	private static final Logger LOGGER = Logger.getLogger(Launcher.class);

	public void param�trage() {
		LOGGER.info ("Le nombre de combinaison est " + ChargerPropriete.NB_COMBINAISON);
		LOGGER.info("Le nombre d'essais est " + ChargerPropriete.NB_ESSAI);
		LOGGER.info("Activation du mode d�veloppeur: " + ChargerPropriete.MODE_DEV + "\n");
	}

	/*
	 * @param presentationDesModes La pr�sentation des modes de jeux
	 */
	public void presentationDesModes() {
		System.out.println("Veuillez choisir parmi les 3 modes de jeux ci-dessous");
		LOGGER.info(" 1 - Le mode Challenger  ");
		LOGGER.info(" 2 - Le mode Defenseur   ");
		LOGGER.info(" 3 - Le mode Duel        ");
		LOGGER.info(" 4 - Sortie du jeu ");
	}

	/*
	 * @param selectionDuModeDeJeux Pr�sentation des 3 modes de jeux La selection
	 * est retourn� dans le mode de jeu
	 * 
	 */
	public void ModeDeJeux() {
		param�trage();
		presentationDesModes();
		Scanner sc = new Scanner(System.in);
		try {
			int nbMode = sc.nextInt();
			switch (nbMode) {
			case 1:
			    LOGGER.info("Vous avez choisi le mode Challenger");
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
			    LOGGER.error("Recommencez votre saisie il n y a que 4 possibilit� :");
				ModeDeJeux();
				break;
			}

		} catch (InputMismatchException e) {
			LOGGER.error("Erreur de saisie, veuillez recommencer");
			ModeDeJeux();
			sc.close();
		}
	}
}
