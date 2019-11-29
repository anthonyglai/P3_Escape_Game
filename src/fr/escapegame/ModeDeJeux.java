package fr.escapegame;

import java.util.Random;
import java.util.Scanner;
import org.apache.log4j.Logger;

import fr.escapegame.propriete.ChargerPropriete;



public class ModeDeJeux {
	

	private  Boolean modeDev = ChargerPropriete.MODE_DEV;
	private  int nbCombinaison = ChargerPropriete.NB_COMBINAISON;
	private  int nbEssai = ChargerPropriete.NB_ESSAI;

	
	public void saisieJoueur() {

		Scanner sc = new Scanner(System.in);
		/* On saisi une phrase */
		System.out.println("Saisir " + nbCombinaison + " chiffres ");
		/* La sasie scanner est transféré dans une variable nb */
		String nb = sc.nextLine();		
			char[] tab = nb.toCharArray();
			int[] saisie = new int[tab.length];
			for (int k = 0; k < tab.length; k++) {
				saisie[k] = Integer.parseInt(String.valueOf(tab[k]));
				for (int g = 0; g < nbCombinaison; g++) {
					System.out.println(saisie[g]);
					
				
				}}}
		

	
	
	public void jouer() {
				

	}

}
