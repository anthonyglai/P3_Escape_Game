package fr.escapegame;

import java.util.Random;
import java.util.Scanner;

import fr.escapegame.propriete.ChargerPropriete;

public class ModeChallenger extends ModeDeJeux {

	private Boolean modeDev = ChargerPropriete.MODE_DEV;
	private int nbCombinaison = ChargerPropriete.NB_COMBINAISON;
	private int nbEssai = ChargerPropriete.NB_ESSAI;
	

	/*
	 * On crée un tableau pour générer un random Le nombre de chiffres sera crée en
	 * fonction de la variable nbCombinaison qui contient la constante
	 * NB_COMBINAISON du fichier de propriété
	 */

	
		// TODO Auto-generated method stub

		/*
		 * On charge 2 constantes du fichier de propriété dans 2 variables
		 */

		
		/*
		 * On crée un tableau pour générer un random Le nombre de chiffres sera crée en
		 * fonction de la variable nbCombinaison qui contient la constante
		 * NB_COMBINAISON du fichier de propriété
		 */
	public void combinaisonIa() {
		int[] combinaison = new int[nbCombinaison];
		for (int i = 0; i < nbCombinaison; i++) {
			combinaison[i] = (int) (Math.random() * 9);

		}

		/*
		 * Une boucle for va afficher la combinaison On peut désactiver l'affichage avec
		 * la variable modeDev qui contient la constance MODE_DEV
		 */
		for (int j = 0; j < combinaison.length; j++)
			if (modeDev == true) {
				System.out.print(combinaison[j]);
			} else {
				System.out.println();

			}
	
		System.out.println();
		System.out.println();

		Scanner sc = new Scanner(System.in);
		/* On saisi une phrase */
		System.out.println("Saisir " + nbCombinaison + " chiffres ");
		/* La sasie scanner est transféré dans une variable nb */
		String nb = sc.nextLine();
		/* La variable nb est transféré dans un tableau */
		char[] tab = nb.toCharArray();
		int[] saisie = new int[tab.length];
		for (int k = 0; k < tab.length; k++) {
			saisie[k] = Integer.parseInt(String.valueOf(tab[k]));
			for (int j = 0; j < combinaison[j]; j++) {
				System.out.print(saisie[k]);
				for (int g = 0; g < combinaison[j] && g <= nbEssai;  g++) {
					if (saisie[k] == combinaison[j]) {
						System.out.println("=");
					}else if (saisie[k] > combinaison[j]) {
						System.out.print("-");
					} else {
						System.out.println("+");
					}
						
						
				}}}}
	
public void jouer() {
	combinaisonIa();
	
}
}