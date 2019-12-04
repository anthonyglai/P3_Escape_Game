package fr.escapegame;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import fr.escapegame.propriete.ChargerPropriete;

public class ModeChallenger extends ModeDeJeux {

	private Boolean modeDev = ChargerPropriete.MODE_DEV;
	private int nbCombinaison = ChargerPropriete.NB_COMBINAISON;
	private int nbEssai = ChargerPropriete.NB_ESSAI;
	private int[] combinaison;

	/*
	 * @param combinaisonIA Génère une combinaison aléatoire entre 0 et 9 de x
	 * chiffresdéterminé par la variable nbcombinaison
	 * Activation ou non de la visibilité de la combinaison
	 * 
	 */
	public void combiAleatoire() {
		this.combinaison = new int[nbCombinaison];
		for (int i = 0; i < nbCombinaison; i++) {
			combinaison[i] = (int) (Math.random() * 9);
		}
		for (int j = 0; j < combinaison.length; j++)
			if (modeDev == true) {
				System.out.print(combinaison[j]);
			} else {
				System.out.println();

			}
	}

	/*
	 * @param saisieJoueur Méthode qui permet à l'utilisateur de saisir une
	 * combinaison
	 */

	public void saisieJoueur() {

		System.out.println();
		System.out.println();
		Scanner sc = new Scanner(System.in);
		/* On saisi une phrase */
		System.out.println("Saisir " + nbCombinaison + " chiffres ");
		/* La sasie scanner est transféré dans une variable nb */
		String nb = sc.nextLine();
		char[] tab = nb.toCharArray();
		int[] saisie = new int[tab.length];
		for (int k = 0; k < tab.length; k++) {
			saisie[k] = Integer.parseInt(String.valueOf(tab[k]));
			System.out.println("");
			 System.out.println(saisie[k]); 
			for (int z = 0; z < 1 ; z++) {
				System.out.print(combinaison[z]);

			}
		}
	
	/* System.out.print("nombre" + combinaison[z]); */

	public void jouer() {
		combiAleatoire();
		saisieJoueur();

	}
}