package fr.escapegame;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import fr.escapegame.propriete.ChargerPropriete;

public class ModeChallenger extends ModeDeJeux {

	private Boolean modeDev = ChargerPropriete.MODE_DEV;
	private int nbCombinaison = ChargerPropriete.NB_COMBINAISON;
	private int nbEssai = ChargerPropriete.NB_ESSAI;
	private int[] combinaison;
	private char[] tab;
	private int saisie = 0;
	private int combinaisonAleatoire = 0;
	private int tentative = 0;

	/*
	 * @param combinaisonIA Génère une combinaison aléatoire entre 0 et 9 de x
	 * chiffresdéterminé par la variable nbcombinaison Activation ou non de la
	 * visibilité de la combinaison
	 * 
	 */
	public void combiAleatoire() {
		this.combinaison = new int[nbCombinaison];
		for (int i = 0; i < nbCombinaison; i++) {
			combinaison[i] = (int) (Math.random() * 9);
			if (modeDev == true) {
				System.out.print(combinaison[i]);
			}
		}
	}

	/* méthode retournant la combinaison */
	public void combinaisonVisible() {
		System.out.println("");
		System.out.println("La combinaison à deviner est :");
		this.combiAleatoire();
	}

	/*
	 * @param saisieJoueur Méthode qui permet à l'utilisateur de saisir une
	 * combinaison
	 */

	public void saisieJoueur() {
		System.out.println();
		System.out.println();
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisir " + nbCombinaison + " chiffres ");
		String nb = sc.nextLine();
		tab = nb.toCharArray();
		System.out.println("Vous avez saisi " + nb);

	}

	/*
	 * @param comparaisonDeCombinaison Méthode qui compare la saisie joueur à l
	 * combinaison aléatoire
	 * 
	 */

	public void comparaisonDeCombinaison() {

		System.out.println("Le resultat est le suivant");
		for (int k = 0; k < tab.length; k++) {
			saisie = Integer.parseInt(String.valueOf(tab[k]));
			combinaisonAleatoire = combinaison[k];
			if (saisie == combinaisonAleatoire) {
				System.out.print("=");
			} else if (saisie < combinaisonAleatoire) {
				System.out.print("-");
			} else {
				System.out.print("+");

			}

		}
	}

	/*
	 * @param tentativeDeTrouverLaCombinaison Méthode permettant au joueur de faire
	 * une saisie de la comparer à la combinaison aléatoire autant de fois que
	 * nombre d'essai paramétré dans le fichier de propriété
	 */
	public void tentativeDeTrouverLaCombinaison() {
		do {
			this.saisieJoueur();
			this.comparaisonDeCombinaison();
			System.out.println("");
			System.out.println("");
			if (saisie == combinaisonAleatoire) {
				System.out.print("Vous avez gagné");
			} else if (saisie < combinaisonAleatoire) {
				System.out.println("Vous n'avez pas trouvé la combinaison exact, veuillez rententer votre chance.");
			} else
				System.out.println("Vous n'avez pas trouvé la combinaison exact, veuillez retenter votre chance.");
		} while (saisie != combinaisonAleatoire & tentative < nbEssai);

	}

	/*
	 * @ propositionApresUneFinDePartie Méthode proposant plusieurs choix après la
	 * fin d'une partie
	 */

	public void propositionApresUneFinDePartie() {
		System.out.println("");
		System.out.println("");
		System.out.println(" Maintenant que la partie est fini ");
		System.out.println("");
		System.out.println(" Veuillez saisir un numéro parmi les 3 choix");
		System.out.println("");
		System.out.println(" 1- Recommencer une partie ");
		System.out.println(" 2- Changer de mode de jeu");
		System.out.println(" 3- Quitter la partie");

	}

	public void jouer() {
		combinaisonVisible();
		tentativeDeTrouverLaCombinaison();
		propositionApresUneFinDePartie();

	}
}