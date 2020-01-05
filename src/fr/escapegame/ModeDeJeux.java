package fr.escapegame;


import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
import fr.escapegame.propriete.ChargerPropriete;

public abstract class ModeDeJeux {

	
	private Boolean modeDev = ChargerPropriete.MODE_DEV;
	private int nbCombinaison = ChargerPropriete.NB_COMBINAISON;
	protected int nbEssai = ChargerPropriete.NB_ESSAI;
	protected int[] combinaison;
	protected char[] tab;
	private int saisie = 0;
	private int combinaisonAleatoire = 0;
	protected int nombreDeTours = 0;
	protected int chanceUtilisee = 1;
	protected int messagePerteDuJoueur= 1;
	
	private int[] combinaisonJoueur;
	private int messagePerteIa = 1;
	
	/**
	 * @param combinaisonIA Génère une combinaison aléatoire entre 0 et 9 de x
	 * chiffresdéterminé par la variable nbcombinaison
	 * Activation ou non de la visibilité de la combinaison
	 * 
	 */
	public void combinaisonAleatoireIa() {
		this.combinaison = new int[nbCombinaison];
		for (int i = 0; i < nbCombinaison; i++) {
			combinaison[i] = (int) (Math.random() * 9);
			if (modeDev == true) {
				System.out.print(combinaison[i]);

			}
		}
	}

	
	/**
	 * @ param combinaisonVisibleIa La méthode retourne la méthode combiAleatoire
	 */
	public void combinaisonVisibleIa() {
		System.out.println("\n");
		System.out.println("La combinaison à deviner est :");
		this.combinaisonAleatoireIa();

	}
	
	/**
	 * @ param combinaisonVisibleIa 
	 * La méthode créée la combinaison aléatoire du joueur
	 */
	public void combinaisonAleatoireJoueur() {
		this.combinaisonJoueur = new int[getNbCombinaison()];
		for (int j = 0; j < getNbCombinaison(); j++) {
			combinaisonJoueur[j] = (int) (Math.random() * 9);
			if (getModeDev() == true) {
				System.out.print(combinaisonJoueur[j]);
	
			}
		}}
	/**
	 * @ param combinaisonVisibleIa 
	 * La méthode affiche la combinaison aléatoire du joueur
	 */
	
			public void combinaisonVisibleJoueur() {
				System.out.println("\n");
				System.out.print("Ta combinaison est :");
				this.combinaisonAleatoireJoueur();	
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

	
	public void comparaisonDeCombinaisonIAetJoueur() {
		System.out.println("\n");
		for(int i=0; i< combinaison.length && i <combinaisonJoueur.length;i++){
			  if(combinaison[i]==combinaisonJoueur[i]){
				  System.out.print("=");  
			  } else if (combinaison[i] < combinaisonJoueur[i]) {
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
			if (combinaison == combinaisonJoueur) {
				System.out.print("Bravo, l'IA a trouvé le résultat ");
			} else  
				System.out.println("L'IA n'a pas trouvé le résultat \n "  );
			if (messagePerteIa++ == nbEssai) {
				System.out.println("La partie est terminée l'IA a perdu");
			}
		
			
		
			nombreDeTours++;

		} while (combinaison != combinaisonJoueur && nombreDeTours != nbEssai);
		

	}
		

	public int[] getCombinaisonJoueur() {
		return combinaisonJoueur;
	}


	public int getMessagePerteIa() {
		return messagePerteIa;
	}


	public void setCombinaisonJoueur(int[] combinaisonJoueur) {
		this.combinaisonJoueur = combinaisonJoueur;
	}


	public void setMessagePerteIa(int messagePerteIa) {
		this.messagePerteIa = messagePerteIa;
	}


	/**
	 * @param comparaisonDeCombinaison Méthode qui compare la saisie joueur à la
	 * combinaison aléatoire
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
			if (getSaisie() == getCombinaisonAleatoire()) {
				System.out.print("Vous avez gagné, vous avez trouvé la combinaison ");
			} else if ( getSaisie() < getCombinaisonAleatoire() || getSaisie() > getCombinaisonAleatoire())
				System.out.println("Vous n'avez pas trouvé la combinaison \n "  );
			if (messagePerteDuJoueur++ == nbEssai) 
				{
				System.out.print("Vous avez utilisé vos " + nbEssai +
				" essais, vous avez perdu. la combinaison était ");
			}
		
			
		
			nombreDeTours++;

		} while (getSaisie() != getCombinaisonAleatoire() && nombreDeTours != nbEssai);
		

	}
	
	/** @param affichageDuResultat
	 * Méthode qui affiche le résultat de la combinaison aléatoire à la 
	 * fin de la partie
	 */

	public void affichageDuResultat() {
		tentativePourTrouverLaCombinaison();
		for (int z = 0; z < combinaison.length; z++)
			System.out.print(combinaison[z]);
			System.out.print(".");
			System.out.println("\n");
		
		
	}
	
	/**
	 * @ propositionApresUneFinDePartie Méthode proposant plusieurs choix après la
	 * fin d'une partie
	 */

	public void propositionApresUneFinDePartie() {
		tentativePourTrouverLaCombinaison();	
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
				combinaisonVisibleIa();
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

	public Boolean getModeDev() {
		return modeDev;
	}

	public int getNbCombinaison() {
		return nbCombinaison;
	}

	public int getNbEssai() {
		return nbEssai;
	}

	public int[] getCombinaison() {
		return combinaison;
	}

	public char[] getTab() {
		return tab;
	}

	public int getSaisie() {
		return saisie;
	}

	public int getCombinaisonAleatoire() {
		return combinaisonAleatoire;
	}

	public int getNombreDeTours() {
		return nombreDeTours;
	}

	public int getChanceUtilisee() {
		return chanceUtilisee;
	}

	public void setModeDev(Boolean modeDev) {
		this.modeDev = modeDev;
	}

	public void setNbCombinaison(int nbCombinaison) {
		this.nbCombinaison = nbCombinaison;
	}

	public void setNbEssai(int nbEssai) {
		this.nbEssai = nbEssai;
	}

	public void setCombinaison(int[] combinaison) {
		this.combinaison = combinaison;
	}

	public void setTab(char[] tab) {
		this.tab = tab;
	}

	public void setSaisie(int saisie) {
		this.saisie = saisie;
	}

	public int getMessagePerteDuJoueur() {
		return messagePerteDuJoueur;
	}


	public void setMessagePerteDuJoueur(int messagePerteDuJoueur) {
		this.messagePerteDuJoueur = messagePerteDuJoueur;
	}


	public void setCombinaisonAleatoire(int combinaisonAleatoire) {
		this.combinaisonAleatoire = combinaisonAleatoire;
	}

	public void setNombreDeTours(int nombreDeTours) {
		this.nombreDeTours = nombreDeTours;
	}

	public void setChanceUtilisee(int chanceUtilisee) {
		this.chanceUtilisee = chanceUtilisee;
	}}