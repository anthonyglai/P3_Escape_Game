package fr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import fr.escapegame.propriete.ChargerPropriete;

public class ModeDefenseur extends ModeDeJeux {
	
	private int[] combinaisonJoueur;
	private int messagePerteIa = 1;
	/**
	 * @param introduction 
	 * Méthode pour présenter le mode de jeu choisi
	 */
	
	
	public void introduction() {
		System.out.println("\n");
		System.out.println("Bienvenue dans le mode Defenseur.");
		System.out.println("Une IA va devoir deviner ta combinaison de " + getNbCombinaison() + " chiffres en " + getNbEssai() + " essais. ");
		System.out.println("La partie commence ");
		
	}
	
	public void combinaisonAleatoireJoueur() {
		this.combinaisonJoueur = new int[getNbCombinaison()];
		for (int j = 0; j < getNbCombinaison(); j++) {
			combinaisonJoueur[j] = (int) (Math.random() * 9);
			if (getModeDev() == true) {
				System.out.print(combinaisonJoueur[j]);
	
			}
		}}
			public void combinaisonVisibleJoueur() {
				System.out.println("\n");
				System.out.print("Ta combinaison est :");
				this.combinaisonAleatoireJoueur();	
				System.out.println("\n");
		}
			
			public void combinaisonAleatoireIa() {
				this.combinaisonIa = new int[getNbCombinaison()];
				for (int i = 0; i < getNbCombinaison(); i++) {
					combinaisonIa[i] = (int) (Math.random() * 9);
					if (getModeDev() == true) {
						System.out.print(combinaisonIa[i]);

					}
				}
			}

			
			/**
			 * @ param combinaisonVisibleIa La méthode retourne la méthode combiAleatoire
			 */
			public void combinaisonVisibleIa() {
				System.out.println("\n");
				System.out.print("l'Ia demande si la combinaison du joueur est :");
				this.combinaisonAleatoireIa();
				
			}
			
			public void comparaisonDeCombinaisonIAetJoueur() {
				System.out.println("\n");
				for(int i=0; i< combinaisonIa.length && i <combinaisonJoueur.length;i++){
					  if(combinaisonIa[i]==combinaisonJoueur[i]){
						  System.out.print("=");  
					  } else if (combinaisonIa[i] < combinaisonJoueur[i]) {
						  System.out.print("+");
						} else {
							System.out.print("-");

					  } 
				}}
			
			
			public void tentativePourTrouverLaCombinaisonDuJoueur() {

				do {
					System.out.println("Essai n°" + chanceUtilisee++ + " pour l'IA");
					combinaisonVisibleIa();
					comparaisonDeCombinaisonIAetJoueur();

					System.out.println();
					if (combinaisonIa == combinaisonJoueur) {
						System.out.print("Bravo, l'IA a trouvé le résultat ");
					} else  
						System.out.println("L'IA n'a pas trouvé le résultat \n "  );
					if (messagePerteIa++ == nbEssai) {
						System.out.println("La partie est terminée l'IA a perdu");
					}
				
					
				
					nombreDeTours++;

				} while (combinaisonIa != combinaisonJoueur && nombreDeTours != nbEssai);
				

			}
			
			
			public void propositionApresUneFinDePartie() {
				tentativePourTrouverLaCombinaisonDuJoueur();	
				System.out.println();
				System.out.println("Pour poursuivre veuillez choisir entre les 3 modes ci-dessous");
				System.out.println();
				System.out.println("1- Recommencer une partie ");
				System.out.println("2- Changer de mode de jeu");
				System.out.println("3- Quitter le jeu");
			}

			
			
			public void choixApresUneFinDePartie() {
				Scanner sc = new Scanner(System.in);
				propositionApresUneFinDePartie();
				try {
					int nbMode = sc.nextInt();
					switch (nbMode) {
					case 1:
						System.out.println("La partie va recommencer ");
						jouer();
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
						System.out.println("Recommence ta saisie il n y a que 4 possibilités :");
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
	combinaisonVisibleJoueur();
	choixApresUneFinDePartie();
	
}
}
