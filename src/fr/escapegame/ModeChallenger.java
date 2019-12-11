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
	 * @param combinaisonIA G�n�re une combinaison al�atoire entre 0 et 9 de x
	 * chiffresd�termin� par la variable nbcombinaison Activation ou non de la
	 * visibilit� de la combinaison
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

	/* m�thode retournant la combinaison */
	public void combinaisonVisible() {
		System.out.println("");
		System.out.println("La combinaison � deviner est :");
		this.combiAleatoire();
	}

	/*
	 * @param saisieJoueur M�thode qui permet � l'utilisateur de saisir une
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
	 * @param comparaisonDeCombinaison M�thode qui compare la saisie joueur � l
	 * combinaison al�atoire
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
	 * @param tentativeDeTrouverLaCombinaison M�thode permettant au joueur de faire
	 * une saisie de la comparer � la combinaison al�atoire autant de fois que
	 * nombre d'essai param�tr� dans le fichier de propri�t�
	 */
	public void tentativeDeTrouverLaCombinaison() {
		do {
			this.saisieJoueur();
			this.comparaisonDeCombinaison();
			System.out.println("");
			System.out.println("");
			if (saisie == combinaisonAleatoire) {
				System.out.print("Vous avez gagn�");
			} else if (saisie < combinaisonAleatoire) {
				System.out.println("Vous n'avez pas trouv� la combinaison exact, veuillez rententer votre chance.");
			} else
				System.out.println("Vous n'avez pas trouv� la combinaison exact, veuillez retenter votre chance.");
		} while (saisie != combinaisonAleatoire & tentative < nbEssai);

	}

	/*
	 * @ propositionApresUneFinDePartie M�thode proposant plusieurs choix apr�s la
	 * fin d'une partie
	 */

	public void propositionApresUneFinDePartie() {
		System.out.println("");
		System.out.println("");
		System.out.println(" Maintenant que la partie est fini ");
		System.out.println("");
		System.out.println(" Veuillez saisir un num�ro parmi les 3 choix");
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