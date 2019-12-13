package fr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.log4j.Logger;
import fr.escapegame.propriete.ChargerPropriete;

public class Launcher {

	private static final Logger LOGGER = Logger.getLogger(Launcher.class);

	public void paramétrage() {

		System.out.println("Le nombre de combinaison est " + ChargerPropriete.NB_COMBINAISON);
		System.out.println("Le nombre d'essais est " + ChargerPropriete.NB_ESSAI);
		System.out.println("Activation du mode développeur: " + ChargerPropriete.MODE_DEV);
		System.out.println("");

	}

	/*
	 * @param presentationDesModes La présentation des modes de jeux
	 */
	public void presentationDesModes() {

		System.out.println("Veuillez choisir parmi les 3 modes de jeux ci-dessous.");
		System.out.println("\n");

		System.out.println(" 1 - Le mode Challenger  ");
		System.out.println(" 2 - Le mode Defenseur   ");
		System.out.println(" 3 - Le mode Duel        ");
		System.out.println(" 4 - Sortie de la partie ");

	}

	/*
	 * @param selectionDuModeDeJeux Présentation des 3 modes de jeux La selection
	 * est retourné dans le mode de jeu
	 * 
	 */

	public void ModeDeJeux() {
		paramétrage();
		presentationDesModes();

		Scanner sc = new Scanner(System.in);

		try {
			int nbMode = sc.nextInt();
			switch (nbMode) {
			case 1:
				System.out.println("Tu as choisi le mode Challenger");
				ModeChallenger modeChallenger = new ModeChallenger();
				modeChallenger.jouer();
				break;
			case 2:
				System.out.println("Tu as choisi le mode Defenseur");
				ModeChallenger modeDefenseur = new ModeDefenseur();
				modeDefenseur.jouer();
				break;
			case 3:
				System.out.println(" Tu as choisi le mode Duel");
				ModeDuel modeDuel = new ModeDuel();
				modeDuel.jouer();
				break;
			case 4:
				System.out.println("fin de partie");
				System.exit(0);
				break;
			default:
				System.out.println("Recommence ta saisie il n y a que 4 possibilité :");
				ModeDeJeux();
				break;
			}

		} catch (InputMismatchException e) {
			System.out.println("Erreur de saisie, recommence");
			ModeDeJeux();

		}

	}
}
