package fr.escapegame;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import fr.escapegame.propriete.ChargerPropriete;

public class ModeChallenger extends ModeDeJeux {

	private Boolean modeDev = ChargerPropriete.MODE_DEV;
	private int nbCombinaison = ChargerPropriete.NB_COMBINAISON;
	private int nbEssai = ChargerPropriete.NB_ESSAI;
	private int[] combinaison;
	private char[] tab;
	private int saisie = 0;
	private int combinaisonAleatoire = 0;
	private int nombreDeTours = 0;
	private int chanceUtilisee = 1;
	

	/**
	 * @param combinaisonIA Génère une combinaison aléatoire entre 0 et 9 de x
	 * chiffresdéterminé par la variable nbcombinaison
	 * Activation ou non de la visibilité de la combinaison
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
	 * @param introduction Méthode pour présenter le mode de jeu choisi
	 */

	public void introduction() {
		System.out.println("\n");
		System.out.println("Bienvenue dans le mode Challenger.");
		System.out.println("Tu vas devoir affronter une IA et deviner sa combinaison secrète de " + nbCombinaison + " chiffres. ");
		System.out.println("Tu as " + nbEssai + " essais. ");
		System.out.println("La partie commence ");

	}

	/**
	 * @ param combinaisonVisible La méthode retourne la méthode combiAleatoire
	 */
	public void combinaisonVisible() {
		System.out.println("\n");
		System.out.println("La combinaison à deviner est :");
		this.combiAleatoire();

	}

	/**
	 * @param saisieJoueur Méthode qui permet à l'utilisateur de saisir une
	 * combinaison
	 */
	public void saisieJoueur() {
		
		
		System.out.println("\n");
		System.out.println("Veuillez tenter votre essai n°" + chanceUtilisee++);
		
		
		Scanner scan = new Scanner(System.in);
		Pattern combinaison = Pattern.compile("[0-9]{"+ nbCombinaison +"}");
		System.out.println("Saisir " + nbCombinaison + " chiffres");
		while (!scan.hasNext(combinaison)) {
		  if (scan.hasNext()) {
		    System.out.println("Erreur vous avez saisi " + scan.next());
		    System.out.println("Veuillez bien saisir " + nbCombinaison + " chiffres");
		    
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
			if (saisie == combinaisonAleatoire) {
				System.out.print("Bravo, vous avez trouvé la combinaison ");
			} else if (saisie < combinaisonAleatoire || saisie > combinaisonAleatoire)
				System.out.println("Vous n'avez pas trouvé la combinaison \n "  );
			
			
		
			nombreDeTours++;

		} while (saisie != combinaisonAleatoire && nombreDeTours != nbEssai);
		

	}
	

	
	/**
	 * @ propositionApresUneFinDePartie Méthode proposant plusieurs choix après la
	 * fin d'une partie
	 */

	public void propositionApresUneFinDePartie() {
		tentativePourTrouverLaCombinaison();	
		System.out.println("La partie est terminée ");
		System.out.println();
		System.out.println("Pour poursuivre veuillez choisir entre les 3 modes ci-dessous");
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
				introduction();
				combinaisonVisible();
				choixApresUneFinDePartie();
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

	/**
	 * @param jouer Méthode retournant 3 méthodes combinaisonVisible
	 * tentativePourTrouverLaCombinaison propositionApresUneFinDePartie
	 * Cette méthode est appellée dans la classe Launcher quand il y a
	 * instanciation de la classe ModeChallenger
	 */
	public void jouer() {
		introduction();
		combinaisonVisible();
		choixApresUneFinDePartie();

	}
}
