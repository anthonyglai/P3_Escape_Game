
package fr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.log4j.Logger;
import fr.escapegame.propriete.ChargerPropriete;

public class Launcher {

	private static final Logger LOGGER = Logger.getLogger(Launcher.class);

	public static void main(String[] args) {

		System.out.println("Le nombre de combinaison est " + ChargerPropriete.NB_COMBINAISON);
		System.out.println("Le nombre d'essais est " + ChargerPropriete.NB_ESSAI);
		System.out.println("Activation du mode développeur: " + ChargerPropriete.MODE_DEV);
		System.out.println("");
		reglement();
		ModeDeJeux jeu = selectionDuModeDeJeux();
		jeu.jouer();
		
	}

	/**
	 * Méthode pour l'introduction
	 */

	public static void reglement() {
		System.out.println(" Bonjour :-) \n\r Si tu as pris connaissance des règles de jeux nous pouvons commencer tout de suite. \n\r ");
		System.out.println(" Tout d'abord fais ton choix parmi les 3 modes de jeux ci-dessous:");
		System.out.println("");
		
	}

	public static void presentationDesModes() {

		System.out.println(" 1 - Le mode Challenger  ");
		System.out.println(" 2 - Le mode Defenseur   ");
		System.out.println(" 3 - Le mode Duel        ");
		System.out.println(" 4 - Sortie de la partie ");

	}

	public static ModeDeJeux selectionDuModeDeJeux() {

		presentationDesModes();

		Scanner sc = new Scanner(System.in);
		ModeDeJeux jeu = null;
		
		
		try {
			int nbMode = sc.nextInt();
			switch (nbMode) {
			case 1:
				System.out.println(" Tu as choisi le mode Challenger");
				jeu = new ModeChallenger();
				break;
			case 2:
				System.out.println(" Tu as choisi le mode Defenseur");
				jeu = new ModeDefenseur();
				break;
			case 3:
				System.out.println(" Tu as choisi le mode Duel");
				jeu = new ModeDuel();
				break;
			case 4:
				System.out.println("fin de partie");
				System.exit(0);
				break;
			default:
				System.out.println("Erreur de saisie, recommence. ");
				selectionDuModeDeJeux();
				break;
			}

		} catch (InputMismatchException e) {
			System.out.println("Erreur de saisie, recommence");
			selectionDuModeDeJeux();

		}

		return jeu;

	}
}
