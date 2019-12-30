package fr.escapegame;


import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import fr.escapegame.propriete.ChargerPropriete;

public class ModeDeJeux {

private Boolean modeDev = ChargerPropriete.MODE_DEV;
private int nbCombinaison = ChargerPropriete.NB_COMBINAISON;
private int nbEssai = ChargerPropriete.NB_ESSAI;
private int[] combinaison;
private char[] tab;
private int saisie = 0;
private int combinaisonAleatoire = 0;
private int nombreDeTours = 0;

/**
 * @param combinaisonIA G�n�re une combinaison al�atoire entre 0 et 9 de x
 * chiffresd�termin� par la variable nbcombinaison
 * Activation ou non de la visibilit� de la combinaison
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

/**
 * @param introduction M�thode pour pr�senter le mode de jeu choisi
 */

public void introduction() {
	System.out.println("\n");
	System.out.println("Bienvenue dans le mode Challenger.");
	System.out.println("Tu vas devoir affronter une IA et deviner sa combinaison secr�te de " + nbCombinaison + " chiffres. ");
	System.out.println("Tu as " + nbEssai + " essais. ");
	System.out.println("La partie commence ");

}

/**
 * @ param combinaisonVisible La m�thode retourne la m�thode combiAleatoire
 */
public void combinaisonVisible() {
	System.out.println("\n");
	System.out.println("La combinaison � deviner est :");
	this.combiAleatoire();

}

/**
 * @param saisieJoueur M�thode qui permet � l'utilisateur de saisir une
 * combinaison
 */
public void saisieJoueur() {
/**	System.out.println("\n");
	Scanner sc = new Scanner(System.in);
	System.out.println("Saisir " + nbCombinaison + " chiffres ");
	while (!sc.hasNextInt()) {
		System.out.println("veuillez ne saisir que " + nbCombinaison + " chiffres");
		sc.nextLine();

	}
	String nb = sc.nextLine();
	tab = nb.toCharArray();
	System.out.println("Vous avez saisi: " + nb);*/

	System.out.println();
	System.out.println();
	Scanner sc = new Scanner(System.in);
	System.out.println("Saisir " + nbCombinaison + " chiffres ");
	String nb = sc.nextLine();
	tab = nb.toCharArray();
	System.out.println("Vous avez saisi " + nb);

}

/**
 * @param comparaisonDeCombinaison M�thode qui compare la saisie joueur � la
 * combinaison al�atoire
 */

public void comparaisonDeCombinaison() {
	saisieJoueur();
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

/**
 * @param tentativeDeTrouverLaCombinaison M�thode permettant au joueur de faire
 *une saisie de la comparer � la
 *combinaison al�atoire autant de fois
 *que nombre d'essai param�tr� dans le
 *fichier de propri�t�
 */
public void tentativePourTrouverLaCombinaison() {

	do {
		comparaisonDeCombinaison();

		System.out.println();
		if (saisie == combinaisonAleatoire) {
			System.out.print("Bravo, vous avez trouv� la combinaison ");
		} else if (saisie < combinaisonAleatoire && saisie > combinaisonAleatoire)
			System.out.println("Combinaison in�xacte.");

		nombreDeTours++;

	} while (saisie != combinaisonAleatoire && nombreDeTours != nbEssai);
	

}


/**
 * @ propositionApresUneFinDePartie M�thode proposant plusieurs choix apr�s la
 * fin d'une partie
 */

public void propositionApresUneFinDePartie() {
	tentativePourTrouverLaCombinaison();	
	System.out.println("La partie est termin�e ");
	System.out.println();
	System.out.println("Veuillez saisir un num�ro parmi les 3 choix ci-dessous");
	System.out.println();
	System.out.println("1- Recommencer une partie ");
	System.out.println("2- Changer de mode de jeu");
	System.out.println("3- Quitter le jeu");

}

/**
 * @param choixApresUneFinDePartie M�thode proposant de faire une nouvelle
 * partie , un nouveau jeu ou sortir du jeu
 */

public void choixApresUneFinDePartie() {
	Scanner sc = new Scanner(System.in);
	propositionApresUneFinDePartie();
	try {
		int nbMode = sc.nextInt();
		switch (nbMode) {
		case 1:
			System.out.println("La partie va recommencer ");
			introduction();
			combinaisonVisible();
			choixApresUneFinDePartie();
			break;

		case 2:
			System.out.println("Retour � l'acceuil pour choisir un mode de jeux");
			Launcher retourALauncher = new Launcher();
			retourALauncher.ModeDeJeux();
			break;
		case 3:
			System.out.println("fin de partie");
			System.exit(0);
			break;
		default:
			System.out.println("Recommence ta saisie il n y a que 4 possibilit� :");
			choixApresUneFinDePartie();
			break;
		}

	} catch (InputMismatchException e) {
		System.out.println("Erreur de saisie, recommence");
		choixApresUneFinDePartie();
	}

}



}

