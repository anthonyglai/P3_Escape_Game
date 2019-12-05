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
		}}
	}
	/*méthode retournant la combinaison*/
	/*public int combiVisible( ) {
		this.combiAleatoire();*/
		
		
		
	
			
	
		
	/*
	 * @param saisieJoueur Méthode qui permet à l'utilisateur de saisir une
	 * combinaison
	 */

	public void saisieJoueur() {
		int nbr = 0;
		System.out.println();
		System.out.println();
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisir " + nbCombinaison + " chiffres ");
		String nb = sc.nextLine();
		char[] tab = nb.toCharArray();
		int saisie = 0;
		for (int k = 0; k < tab.length; k++) {
			saisie = Integer.parseInt(String.valueOf(tab[k]));
			if (combinaison[k] == saisie) {
				System.out.print("=");
			} else if (saisie < combinaison[k]) {
				System.out.print("-");
			} else {
				System.out.print("+");
				}
		}
	}
	/* System.out.print("nombre" + combinaison[z]); */

	public void jouer() {
		combiAleatoire();
		saisieJoueur();

	}
}