package fr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;
import fr.escapegame.propriete.ChargerPropriete;

public class Launcher {

	public static void main(String[] args) {

		System.out.println("Le nombre de combinaison est " + ChargerPropriete.NB_COMBINAISON);
		System.out.println("Le nombre d'essais est " + ChargerPropriete.NB_ESSAI);
		System.out.println("Activation du mode développeur est " + ChargerPropriete.MODE_DEV);
		System.out.println("");
		reglement();
		ModeDeJeux jeu = selectionDuModeDeJeux();
	}

	/**
	 * Méthode pour l'introduction
	 */
	public static void reglement() {

		System.out.println(
				" Bonjour :-) \n\r Si tu as pris connaissance des règles de jeux nous pouvons commencer tout de suite. \n\r ");
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

		try {
			int nbMode = sc.nextInt();
			switch (nbMode) {
			case 1:
				System.out.println(" Tu as choisi le mode Challenger");
				break;
			case 2:
				System.out.println(" Tu as choisi le mode Defenseur");
				break;
			case 3:
				System.out.println(" Tu as choisi le mode Duel");
				break;
			case 4:
				System.out.println("Fin de partie");
				break;
			default:
				System.out.println("Mauvaise saisie, recommence ");
				selectionDuModeDeJeux();
				break;
			}

		} catch (InputMismatchException e) {
			System.out.println("Mauvaise saisie");
			selectionDuModeDeJeux();

		}

		return null;

	}
}
