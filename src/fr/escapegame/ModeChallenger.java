package fr.escapegame;


import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;



public class ModeChallenger extends ModeDeJeux  {
	
	/**
	 * @param introduction Méthode pour présenter le mode de jeu choisi
	 */
	
	public void introduction() {
		System.out.println("\n");
		System.out.println("Bienvenue dans le mode Challenger.");
		System.out.println("Tu vas devoir affronter une IA et deviner sa combinaison secrète de " + getNbCombinaison() + " chiffres. ");
		System.out.println("Tu as " + getNbEssai() + " essais. ");
		System.out.println("La partie commence ");

	}
	
	/**
	 * @param combinaisonAléatoire
	 * Génère une combinaison aléatoire entre 0 et 9 de x
	 * chiffres déterminé par la variable nbcombinaison
	 * Activation ou non de la visibilité de la combinaison
	 */

	public void combinaisonAleatoireIa() {
		this.combinaisonIa = new int[getNbCombinaison()];
		for (int i = 0; i < getNbCombinaison(); i++) {
			getCombinaison()[i] = (int) (Math.random() * 9);
			if (getModeDev() == true) {
				System.out.print(getCombinaison()[i]);
	
			}}}
		
	/**
	 * @ param combinaisonVisible La méthode retourne la méthode combiAleatoire
	 */
	
	public void combinaisonVisibleIa() {
		System.out.println("\n");
		System.out.print("La combinaison à deviner est :" );
		this.combinaisonAleatoireIa();
		
	}
	
	/**
	 * @param saisieJoueur Méthode qui permet à l'utilisateur de saisir une
	 * combinaison
	 */	
	
public void saisieJoueur() {
		
		System.out.println("\n");
		System.out.println("Veuillez tenter votre essai n°" + chanceUtilisee++);
		
		Scanner scan = new Scanner(System.in);
		Pattern combinaison = Pattern.compile("[0-9]{"+ getNbCombinaison() +"}");
		System.out.println("Saisir " + getNbCombinaison() + " chiffres");
		while (!scan.hasNext(combinaison)) {
		  if (scan.hasNext()) {
		    System.out.println("Erreur vous avez saisi " + scan.next());
		    System.out.println("Veuillez bien saisir " + getNbCombinaison() + " chiffres");
		    
		  }
		}
		
		String nb = scan.nextLine();
		tab = nb.toCharArray();
		System.out.println("Vous avez saisi: " + nb);
		
		
}	
		
		/**
		 * @param comparaisonDeCombinaison Méthode qui compare la saisie joueur à la
		 * combinaison aléatoire
		 */	
		
public void comparaisonDeCombinaison() {
	saisieJoueur();
	System.out.println("Le resultat est le suivant");
	for (int k = 0; k < tab.length; k++) {
		setSaisieJoueur(Integer.parseInt(String.valueOf(tab[k])));
		setCombinaisonAleatoire( combinaisonIa[k]);
		if (getSaisieJoueur() == getCombinaisonAleatoire()) {
			System.out.print("=");
		} else if (getSaisieJoueur() < getCombinaisonAleatoire()) {
			System.out.print("-");
		} else {
			System.out.print("+");
			
		}

	}
}

/**
 * @param tentativeDeTrouverLaCombinaison Méthode permettant au joueur de faire
 *une saisie de la comparer à la
 *combinaison aléatoire autant de fois
 *que nombre d'essai paramétré dans le
 *fichier de propriété
 */


public void tentativePourTrouverLaCombinaison() {
	do {
		comparaisonDeCombinaison();

		System.out.println();
		if (getSaisieJoueur() == getCombinaisonAleatoire()) {
			System.out.print("Vous avez gagné, vous avez trouvé la combinaison ");
		} else if ( getSaisieJoueur() < getCombinaisonAleatoire() || getSaisieJoueur() > getCombinaisonAleatoire())
			System.out.println("Vous n'avez pas trouvé la combinaison \n "  );
		if (messagePerteDuJoueur++ == nbEssai) 
			{
			System.out.print("Vous avez utilisé vos " + nbEssai +
			" essais, vous avez perdu. la combinaison était ");
		}
	
		
	
		nombreDeTours++;

	} while (getSaisieJoueur() != getCombinaisonAleatoire() && nombreDeTours != nbEssai);
}	


/** @param affichageDuResultat
 * Méthode qui affiche le résultat de la combinaison aléatoire à la 
 * fin de la partie
 */

	public void affichageDuResultat() {
		tentativePourTrouverLaCombinaison();
		for (int z = 0; z < combinaisonIa.length; z++)
			System.out.print(combinaisonIa[z]);
			System.out.print(".");
			System.out.println("\n");
		
		
	}

/**
 * @ propositionApresUneFinDePartie Méthode proposant plusieurs choix après la
 * fin d'une partie
 */

public void propositionApresUneFinDePartie() {
	affichageDuResultat();	
	System.out.println();
	System.out.println("Pour poursuivre veuillez choisir entre les 3 modes ci-dessous:");
	System.out.println();
	System.out.println("1- Recommencer une partie ");
	System.out.println("2- Changer de mode de jeu");
	System.out.println("3- Quitter le jeu");

}

/**
 * @param choixApresUneFinDePartie Méthode proposant de faire une nouvelle
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
			jouer();
			/**introduction();
			combinaisonVisibleIa();
			choixApresUneFinDePartie();*/
			break;

		case 2:
			System.out.println("Retour à l'acceuil pour choisir un mode de jeux");
			Launcher retourALauncher = new Launcher();
			retourALauncher.ModeDeJeux();
			break;
		case 3:
			System.out.println("fin de partie");
			System.exit(0);
			break;
		default:
			System.out.println("Recommence ta saisie il n y a que 4 possibilité :");
			choixApresUneFinDePartie();
			break;
		}

	} catch (InputMismatchException e) {
		System.out.println("Erreur de saisie, recommence");
		choixApresUneFinDePartie();
		
	}
	 

}
		
	public void jouer() {
		introduction();
		combinaisonVisibleIa();
		choixApresUneFinDePartie();

	}

}
	

