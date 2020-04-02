package fr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

/**
 * ModeChallenger est la classe enfant de ModeDeJeux
 * 
 * @author Glairon Anthony
 * @version 1.0
 */

public class ModeChallenger extends ModeDeJeux {
    public static final Logger LOGGER = Logger.getLogger(Launcher.class);

    /**
     * Introduction du mode defenseur
     */

    public void introduction() {
        System.out.println("Dans le mode challenger, vous allez devoir deviner la combinaison de l IA de " + getNbCombinaison()+ " chiffre(s)");
        System.out.println("Vous avez " + getNbEssai() + " essais\n");
        System.out.println("La partie commence\n" );
    }

    /**
     * La methode genere une combinaison aleatoire pour l IA entre 0 et 9 chiffres.
     * La variable nbCombinaison determinera le nombre de chiffre que comportera la
     * combinaison La variable modeDev permet l'affichage de la combinaison si elle
     * est = a true
     */
    public void combinaisonAleatoireIa() {
        this.setCombinaisonIa(new int[getNbCombinaison()]);
        for (int i = 0; i < getNbCombinaison(); i++) {
            getCombinaisonIa()[i] = (int) (Math.random() * 9);
            if (getModeDev() == true) {
                System.out.print(getCombinaisonIa()[i]);
            } else {
                System.out.println("?");
            }
          }
        }
    

    /**
     * La methode affiche ou non la combinaison aleatoire de l IA si la variable
     * modeDev dans la methode combinaisonAleatoireIa est true
     */
    public void combinaisonIaSecrete() {
        System.out.print("La combinaison de l'IA à deviner est ");
        this.combinaisonAleatoireIa();
        System.out.println("\n");
        }
    
    /**
     * La methode permet au joueur de saisir une combinaison de x chiffres et 
     * de l afficher
     */
    public void saisieJoueur() {
        setChanceUtiliseeJoueur(getChanceUtiliseeJoueur() + 1);
        System.out.println("\n");
        System.out.println("Veuillez tenter votre essai n°" + getChanceUtiliseeJoueur()+"\n");
        Scanner scan = new Scanner(System.in);
        Pattern combinaison = Pattern.compile("[0-9]{" + getNbCombinaison() + "}");
        System.out.println("Saisir une combinaison de " + getNbCombinaison() + " chiffre(s)");
        while (!scan.hasNext(combinaison)) {
            if (scan.hasNext()) {
                System.out.println("Erreur vous avez saisi " + scan.next());
                System.out.println("Veuillez bien saisir " + getNbCombinaison() + " chiffres");
                String nb = scan.nextLine();
                setTab(nb.toCharArray());
                System.out.println("Vous proposez la combinaison " + nb +"\n");
            }
        }
        String nb = scan.nextLine();
        setTab(nb.toCharArray());
        System.out.println("Vous proposez la combinaison " + nb +"\n");
    }

    /**
     * La methode compare la saisie du joueur a la combinaison aleatoire de l IA Un
     * signe + - ou = indique au joueur si il a trouve ou non la combinaison de l IA
     */
    public void comparaisonDeCombinaison() {
        saisieJoueur();
        System.out.print("Le resultat est ");
        for (int k = 0; k < getTab().length; k++) {
            setSaisieJoueur(Integer.parseInt(String.valueOf(getTab()[k])));
            setCombinaisonAleatoire(getCombinaisonIa()[k]);
            if (getSaisieJoueur() == getCombinaisonAleatoire()) {
                System.out.print("=");
            } else if (getSaisieJoueur() < getCombinaisonAleatoire()) {
                System.out.print("+");
            } else {
                System.out.print("-");
            }
            System.out.println("\n");
        }
    }

    /**
     * Methode retournant au joueur en fonction de sa saisie si il a trouve ou 
     * non la combinaison. Cette methode est repetee.
     * tant que le joueur trouve un resultat different de la combinaison de l IA et
     * dans la limite du nombre d essai parametre dans la variable nbEssai du
     * fichier config.properties
     */
    public void tentativePourTrouverLaCombinaisonDeIa() {
        do {
            setNbEssai(getNbEssai());
            setNombreDeTours(getNombreDeTours() + 1);
            setMessagePerteDuJoueur(getMessagePerteDuJoueur());
            comparaisonDeCombinaison();
            if (getSaisieJoueur() == getCombinaisonAleatoire()) {
                System.out.print("Vous avez gagné, vous avez trouvé la combinaison de l' IA\n");
            } else if (getSaisieJoueur() < getCombinaisonAleatoire() || getSaisieJoueur() > getCombinaisonAleatoire())
                System.out.println("Vous n'avez pas trouvé la combinaison de l' IA\n");
                getNombreDeTours();
        } while (getSaisieJoueur() != getCombinaisonAleatoire() && getNombreDeTours() != getNbEssai());
    }
  
    /**
     * Methode qui affiche le resultat de la combinaison aleatoire de l IA 
     * quand la partie est terminee
     */    
    public void defaiteJoueur() {
        tentativePourTrouverLaCombinaisonDeIa();
        if (getSaisieJoueur() != getCombinaisonAleatoire()) {
            System.out.print("Vous avez perdu, la combinaison de l'IA est ");{
                for (int z = 0; z < getCombinaisonIa().length; z++)
                    if (getSaisieJoueur() != getCombinaisonAleatoire())
                        System.out.print(getCombinaisonIa()[z]);
            }
        }
    }

    /**
     * Methode demandant au joueur de saisir si il souhaite recommencer changer ou
     * quitter le jeu
     */
    public void propositionApresUneFinDePartie() {
        defaiteJoueur();
        System.out.println("\n");
        System.out.println("Pour poursuivre, veuillez choisir entre les 3 modes ci-dessous:");
        System.out.println();
        System.out.println("1- Recommencer une partie ");
        System.out.println("2- Changer de mode de jeu");
        System.out.println("3- Quitter le jeu");
    }

    /**
     * Methode qui permet au joueur en fonction du choix propose et de sa saisie de
     * faire une nouvelle partie de retourner dans le menu de selection des modes
     * de jeux ou de sortir du jeu
     */
    public void choixApresUneFinDePartie() {
        Scanner sc = new Scanner(System.in);
        propositionApresUneFinDePartie();
        resetChanceUtiliseeJoueur();
        resetNbTour();
        try {
            int nbMode = sc.nextInt();
            switch (nbMode) {
            case 1:
                System.out.println("La partie va recommencer\n");
                introduction();
                combinaisonIaSecrete();
                choixApresUneFinDePartie();
                break;
            case 2:
                System.out.println("Retour à l'accueil pour choisir un autre mode de jeux\n");
                Launcher retourALauncher = new Launcher();
                retourALauncher.ModeDeJeux();
                break;
            case 3:
                System.out.println("fin de partie");
                System.exit(0);
                break;
            default:
                System.out.println("Recommencez votre saisie il n y a que 4 possibilité\n");
                choixApresUneFinDePartie();
                break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Erreur de saisie, recommence\n");
            choixApresUneFinDePartie();
        }
    }
    
    /** Methode generant le deroulement du jeu */
    public void jouer() {
        introduction();
        combinaisonIaSecrete();
        choixApresUneFinDePartie();
    }
}
