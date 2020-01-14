package fr.escapegame;


import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
import fr.escapegame.propriete.ChargerPropriete;

public abstract class ModeDeJeux {

	
	private Boolean modeDev = ChargerPropriete.MODE_DEV;
	private int nbCombinaison = ChargerPropriete.NB_COMBINAISON;
	private int nbEssai = ChargerPropriete.NB_ESSAI;
	private int[] combinaisonIa;
	private char[] tab;
	private int saisieJoueur = 0;
	private int combinaisonAleatoire = 0;
	private int nombreDeTours = 0;
	private int chanceUtilisee = 0;
	private int messagePerteDuJoueur= 1;
	private int[] combinaisonJoueur;
	private int messagePerteIa = 1;
	
	
	/**
	 * @param combinaisonIA G�n�re une combinaison al�atoire entre 0 et 9 de x
	 * chiffresd�termin� par la variable nbcombinaison
	 * Activation ou non de la visibilit� de la combinaison
	 * 
	 */
	public void combinaisonAleatoireIa() {
		this.combinaisonIa = new int[nbCombinaison];
		for (int i = 0; i < nbCombinaison; i++) {
			combinaisonIa[i] = (int) (Math.random() * 9);
			if (modeDev == true) {
				System.out.print(combinaisonIa[i]);

			}
		}
	}

	
	/**
	 * @ param combinaisonVisibleIa La m�thode retourne la m�thode combiAleatoire
	 */
	public void combinaisonVisibleIa() {
		System.out.println("\n");
		System.out.println("La combinaison � deviner est :");
		this.combinaisonAleatoireIa();

	}
	
	/**
	 * @ param combinaisonVisibleIa 
	 * La m�thode cr��e la combinaison al�atoire du joueur
	 */
	public void combinaisonAleatoireJoueur() {
		this.combinaisonJoueur = new int[nbCombinaison];
		for (int j = 0; j < nbCombinaison; j++) {
			combinaisonJoueur[j] = (int) (Math.random() * 9);
			if (modeDev == true) {
				System.out.print(combinaisonJoueur[j]);
	
			}
		}}
	/**
	 * @ param combinaisonVisibleIa 
	 * La m�thode affiche la combinaison al�atoire du joueur
	 */
	
			public void combinaisonVisibleJoueur() {
				System.out.println("\n");
				System.out.print("Ta combinaison est :");
				this.combinaisonAleatoireJoueur();	
		}

	/**
	 * @param saisieJoueur M�thode qui permet � l'utilisateur de saisir une
	 * combinaison
	 */
		public void saisieJoueur() {
		
		
		System.out.println("\n");
		System.out.println("Veuillez tenter votre essai n�" + chanceUtilisee++);
		
		
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
			System.out.println("Essai n�" + chanceUtilisee++ + " pour l'IA");
			combinaisonVisibleIa();
			comparaisonDeCombinaisonIAetJoueur();

			System.out.println();
			if (combinaisonIa == combinaisonJoueur) {
				System.out.print("Bravo, l'IA a trouv� le r�sultat ");
			} else  
				System.out.println("L'IA n'a pas trouv� le r�sultat \n "  );
			if (messagePerteIa++ == nbEssai) {
				System.out.println("La partie est termin�e l'IA a perdu");
			}
		
			
		
			nombreDeTours++;

		} while (combinaisonIa != combinaisonJoueur && nombreDeTours != nbEssai);
		

	}
		



	/**
	 * @param comparaisonDeCombinaison M�thode qui compare la saisie joueur � la
	 * combinaison al�atoire
	 */

	public void comparaisonDeCombinaison() {
		System.out.println("Le resultat est le suivant");
		for (int k = 0; k < tab.length; k++) {
			saisieJoueur = Integer.parseInt(String.valueOf(tab[k]));
			combinaisonAleatoire = combinaisonIa[k];
			if (saisieJoueur == combinaisonAleatoire) {
				System.out.print("=");
			} else if (saisieJoueur < combinaisonAleatoire) {
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
			if (saisieJoueur == combinaisonAleatoire) {
				System.out.print("Vous avez gagn�, vous avez trouv� la combinaison ");
			} else if ( saisieJoueur < combinaisonAleatoire || saisieJoueur > combinaisonAleatoire)
				System.out.println("Vous n'avez pas trouv� la combinaison \n "  );
			if (messagePerteDuJoueur++ == nbEssai) 
				{
				System.out.print("Vous avez utilis� vos " + nbEssai +
				" essais, vous avez perdu. la combinaison �tait ");
			}
		
			
		
			nombreDeTours++;

		} while (saisieJoueur != combinaisonAleatoire && nombreDeTours != nbEssai);
		
	}
	
	/** @param affichageDuResultat
	 * M�thode qui affiche le r�sultat de la combinaison al�atoire � la 
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
	 * @ propositionApresUneFinDePartie M�thode proposant plusieurs choix apr�s la
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

	public void resetChanceUtilisee() { 
	this.chanceUtilisee = 0;
	
	}
	
    

	public void resetNbTour() { 
	    
	    this.nombreDeTours = 0;

	}
	
	
	
	/**
	 * @param choixApresUneFinDePartie M�thode proposant de faire une nouvelle
	 * partie , un nouveau jeu ou sortir du jeu
	 */

	public void choixApresUneFinDePartie() {
		Scanner sc = new Scanner(System.in);
		try {
			int nbMode = sc.nextInt();
			switch (nbMode) {
			case 1:
				System.out.println("La partie va recommencer ");
				combinaisonVisibleIa();
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
			 sc.close();
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
		return combinaisonIa;
	}

	public char[] getTab() {
		return tab;
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
		this.combinaisonIa = combinaison;
	}

	public void setTab(char[] tab) {
		this.tab = tab;
	}

	

	public int[] getCombinaisonIa() {
		return combinaisonIa;
	}


	public int getSaisieJoueur() {
		return saisieJoueur;
	}


	public void setCombinaisonIa(int[] combinaisonIa) {
		this.combinaisonIa = combinaisonIa;
	}


	public void setSaisieJoueur(int saisieJoueur) {
		this.saisieJoueur = saisieJoueur;
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
	}}