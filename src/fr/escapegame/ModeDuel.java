package fr.escapegame;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import fr.escapegame.propriete.ChargerPropriete;

/**
 * ModeDuel est la classe enfant de ModeDeJeux
 * 
 * @author Glairon Anthony
 * @version 1.0
 */
public class ModeDuel extends ModeDeJeux {                                 
    public String resultat = "";
    public String combinaisonSecreteJoueur = "";
   //* public String nouvelleCombinaisonIa = "";
    public int nbretrs = 1; // *saisieCombinaisonSecreteJoueur()
    public String resetNouvelleCombi = "";
    private int saisieJoueurDef = 0;

    public int[] combinaisonIaAtt; // *creationCombinaisonAleatoireIa();
    public int combinaisonAleatoireIaAtt = 0;
    public int chanceUtiliseeIa =2;
    private char[] tabDef; // *saisieCombinaisonSecreteJoueur()

    public void introduction() {                                                        
        System.out.println("\n");
        System.out.println("Bienvenue dans le mode duel.");
        System.out.println("Le premier entre l'IA ou le joueur qui trouve la combinaison de " + getNbCombinaison()
                + " chiffres a gagné. ");
        System.out.println("Chaque participant à " + getNbEssai() + " essais. ");
        System.out.println("La partie commence ");

    }
    
    public void saisieCombinaisonSecreteJoueur() {
        System.out.println("\n");
        Scanner scanner = new Scanner(System.in);
        Pattern combinaisonSecrete = Pattern.compile("[0-9]{" + getNbCombinaison() + "}");
        System.out.println("Veuillez saisir une combinaison secrète de " + getNbCombinaison() + " chiffres");
        while (!scanner.hasNext(combinaisonSecrete)) {
            if (scanner.hasNext()) {
                System.out.println("Erreur vous avez saisi " + scanner.next());
                System.out.println("Veuillez bien saisir " + getNbCombinaison() + " chiffres");

            }
        }

        String nbre = scanner.nextLine();
        setTabDef(nbre.toCharArray());
        setCombinaisonSecreteJoueur(getCombinaisonSecreteJoueur() + (nbre));
        System.out.println("Vous avez saisi la combinaison secrète " + getCombinaisonSecreteJoueur());


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

            }
        }
    }

    /**
     * La methode affiche ou non la combinaison aleatoire de l IA si la variable
     * modeDev dans la methode combinaisonAleatoireIa est true
     */

    public void combinaisonIaSecrete() {
        System.out.println("\n");
        System.out.print("La combinaison de l'IA à deviner est :");
        this.combinaisonAleatoireIa();

    }
    
    /**
     * La methode creee une combinaison aleatoire pour l IA entre 0 et 9 chiffres.
     * Mode defenseur La variable nbCombinaison determinera le nombre de chiffre que
     * comportera la combinaison.
     */
    public void creationCombinaisonAleatoireIa() {

        this.setCombinaisonIaAtt(new int[getNbCombinaison()]);
        for (int i = 0; i < getNbCombinaison(); i++) {
            getCombinaisonIaAtt()[i] = (int) (Math.random() * 9);

            System.out.print(getCombinaisonIaAtt()[i]);
            setCombiIaConvertiEnString(getCombiIaConvertiEnString() + getCombinaisonIaAtt()[i]);

        }

    }
    
    /** 
     * Methode pour indiquer que c est au tour de l IA de jouer
     */
    
    public void tourIa() {
        System.out.println("\n");
        System.out.println("C'est au tour de l' IA de faire un proposition");
    }


    /**
     * Methode qui affiche la combinaison aleatoire de l IA Mode defenseur
     */

    public void propositionCombinaisonIa() {
        System.out.println("\n");
        System.out.println("Essai n° 1");
        System.out.print("l'IA propose la combinaison :");
        this.creationCombinaisonAleatoireIa();

    }
    
    /**
     * Cette methode compare la combinaison aleatoire de l IA a la combinaison
     * secrete du joueur et affiche un resultat
     */

    public void comparaisonDeCombinaisonPourIa() {

        System.out.println("\n");
        System.out.print("Le resultat est le suivant ");
        for (int k = 0; k < getTabDef().length; k++) {
            setSaisieJoueurDef(Integer.parseInt(String.valueOf(getTabDef()[k])));
            setCombinaisonAleatoireIaAtt(getCombinaisonIaAtt()[k]);
            if (getCombinaisonAleatoireIaAtt() == getSaisieJoueurDef()) {
                setResultat(getResultat() + ("="));
            } else if (getCombinaisonAleatoireIaAtt() > getSaisieJoueurDef()) {
                setResultat(getResultat() + ("-"));
            } else {
                setResultat(getResultat() + ("+"));

            }

        }
        System.out.println(getResultat());
    }
    
    /**
     * Methode affichant que l Ia a trouve ou non la combinaison du joueur
     */

    public void resultatPourIa() {
        if (getCombinaisonAleatoireIaAtt() == getSaisieJoueurDef()) {
            System.out.println("L'IA a trouvé la combinaison du joueur");
        } else if (getCombinaisonAleatoireIaAtt() > getSaisieJoueurDef()
                || getCombinaisonAleatoireIaAtt() < getSaisieJoueurDef()) {
            System.out.println("L'ia n'a pas trouvé la combinaison du joueur ");

        }

    }
    
    /** 
     * Methode pour annoncer que c est au tour du joueur de jouer
     */
    
    public void tourJoueur() {
        System.out.println("C'est au tour du joueur de faire une proposition");
    }
    
    /**
     * La methode permet au joueur de saisir une combinaison de x chiffres et de
     * l'afficher la combinaison est parametré dans le fichier de propriete
     */

    public void saisieJoueur() {
        setChanceUtiliseeJoueur(getChanceUtiliseeJoueur() + 1);
        System.out.println("\n");
        System.out.println("Veuillez tenter votre essai n°" + getChanceUtiliseeJoueur());

        Scanner scan = new Scanner(System.in);
        Pattern combinaison = Pattern.compile("[0-9]{" + getNbCombinaison() + "}");
        System.out.println("Saisir " + getNbCombinaison() + " chiffres");
        while (!scan.hasNext(combinaison)) {
            if (scan.hasNext()) {
                System.out.println("Erreur vous avez saisi " + scan.next());
                System.out.println("Veuillez bien saisir " + getNbCombinaison() + " chiffres");

            }
        }

        String nb = scan.nextLine();
        setTab(nb.toCharArray());
        System.out.println("Vous avez saisi: " + nb);

    }
    
    /**
     * La methode compare la saisie du joueur a la combinaison aleatoire de l IA Un
     * signe + - ou = indique au joueur si il a trouvé ou non la combinaison de l IA
     */

    public void comparaisonDeCombinaison() {
        
        System.out.println("Le resultat est le suivant");
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

        }
    }
    
    public void resultatPourJoueur() {
        System.out.println("\n");
        if (getSaisieJoueur() == getCombinaisonAleatoire()) {
            System.out.print("Vous avez gagné, vous avez trouvé la combinaison ");
        } else if (getSaisieJoueur() < getCombinaisonAleatoire() || getSaisieJoueur() > getCombinaisonAleatoire())
            System.out.println("Vous n'avez pas trouvé la combinaison \n ");
        
    }
    
    public void nouveauTourPourIa() {

        System.out.println("\n");
        System.out.println("Essai n° " + getChanceUtiliseeIa() + " pour l'Ia");
    }
    
    
    public int generationNbreAletoire(int min, int max) {
        if (min == max)
            return min;

        Random random = new Random();
        return min + random.nextInt(max - min);

    }

    /**
     * Methode qui va generer une nouvelle combinaison aleatoire pour l IA en
     * fonction du resultat precedent Mode defenseur et mode duel
     */

    public void generationNouvelleCombinaisonIa() {
        setNouvelleCombinaisonIa("");

        char[] resultatDansTab = new char[getResultat().length()];
        setCombiIaTab(new char[getCombiIaConvertiEnString().length()]);
        for (int i = 0; i < getResultat().length(); i++) {
            resultatDansTab[i] = getResultat().charAt(i);
            getCombiIaTab()[i] += getCombiIaConvertiEnString().charAt(i);

        }
        for (int i = 0; i < resultatDansTab.length; i++) {
            if (Character.toString(resultatDansTab[i]).equals("+")) {
                setNouvelleCombinaisonIa(getNouvelleCombinaisonIa()
                        + this.generationNbreAletoire(Character.getNumericValue(getCombiIaTab()[i]), 9));

            } else if (Character.toString(resultatDansTab[i]).equals("-")) {

                setNouvelleCombinaisonIa(getNouvelleCombinaisonIa()
                        + this.generationNbreAletoire(0, Character.getNumericValue(getCombiIaTab()[i])));

            } else {

                setNouvelleCombinaisonIa(getNouvelleCombinaisonIa() + (Character.getNumericValue(getCombiIaTab()[i])));

            }
        }
    }

    /**
     * Methode affichant la nouvelle combinaison de l IA Mode defenseur et mode duel
     */

    public void nouvelleCombinaisonIa() {
        System.out.println("L'IA propose la nouvelle combinaison " + getNouvelleCombinaisonIa());
        setCombiIaConvertiEnString(getNouvelleCombinaisonIa());

    }
    
    /**
     * Methode permettant au joueur de saisir sous forme d operateur pour modifier a
     * la hausse ou la baisse la nouvelle combinaison de l IA Mode defenseur et mode
     * duel
     */

    public void saisieOperateur() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir le résultat sous forme d'opérateur");
        String result = sc.nextLine();
        System.out.println("Vous avez saisi " + result);
        setResultat(result);

    }

    /**
     * Methode affichant si l' IA a trouve ou non la combinaison du joueur Mode
     * defenseur et mode duel
     */

    public void nouvearesultat() {
        if (getNouvelleCombinaisonIa() == getCombinaisonSecreteJoueur()) {
            System.out.println("L'IA a trouvé la combinaison du joueur");
        } else if (getNouvelleCombinaisonIa() != getCombinaisonSecreteJoueur()) {
            System.out.println("L'IA n'a pas trouvé la combinaison du joueur ");
        }
    }


    
    public void TentativeDeTrouverLaCombinaisonEntreIaEtJoueur() {
        
           introduction();
           saisieCombinaisonSecreteJoueur();
           combinaisonIaSecrete(); 
           tourIa();
           propositionCombinaisonIa();
           comparaisonDeCombinaisonPourIa();
           resultatPourIa();
           tourJoueur();
           saisieJoueur();
           comparaisonDeCombinaison();
           resultatPourJoueur();
        
          if (getCombinaisonAleatoireIaAtt() == getSaisieJoueurDef() && getSaisieJoueur() == getCombinaisonAleatoire()) {
             propositionApresUneFinDePartie();
          }
             else { 
           /* début de do */
           do {
           nouveauTourPourIa();
           generationNouvelleCombinaisonIa();
           nouvelleCombinaisonIa();
           saisieOperateur();
           nouvearesultat();
           tourJoueur();
           saisieJoueur();
           comparaisonDeCombinaison();
           resultatPourJoueur(); 
           setNbretrs(getNbretrs() + 1);
           setChanceUtiliseeIa(getChanceUtiliseeIa() + 1);

        } while ( getNouvelleCombinaisonIa() != getCombinaisonSecreteJoueur() && getSaisieJoueur() != getCombinaisonAleatoire() && getNbretrs() != getNbEssai());
        }}
    
    /**
     * Methode demandant au joueur de saisir si il souhaite recommencer changer ou
     * quitter le jeu
     */

    public void propositionApresUneFinDePartie() {
        
        System.out.println();
        System.out.println("Pour poursuivre veuillez choisir entre les 3 modes ci-dessous:");
        System.out.println();
        System.out.println("1- Recommencer une partie ");
        System.out.println("2- Changer de mode de jeu");
        System.out.println("3- Quitter le jeu");

    }
    
    /**
     * Methode qui permet au joueur en fonction du choix proposé et de sa saisie de
     * faire une nouvelle partie , de retourner dans le menu de selection des modes
     * de jeux ou de sortir du jeu
     */
    public void choixApresUneFinDePartie() {
        Scanner sc = new Scanner(System.in);
        resetChanceUtiliseeJoueur();
        resetNbTour();
        resetChanceUtiliseeIa();
        resetnbTours();
        resetResultat();
        resetnouvelleCombinaisonIa();
        resetcombinaisonSecrete();
        try {
            int nbMode = sc.nextInt();
            switch (nbMode) {
            case 1:
                System.out.println("La partie va recommencer ");
                TentativeDeTrouverLaCombinaisonEntreIaEtJoueur();
                propositionApresUneFinDePartie();
                choixApresUneFinDePartie();

                break;

            case 2:
                System.out.println("Retour à l'accueil pour choisir un mode de jeux");
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
        
        TentativeDeTrouverLaCombinaisonEntreIaEtJoueur();
        propositionApresUneFinDePartie();
        choixApresUneFinDePartie();
      
       /* introduction();
     saisieCombinaisonSecreteJoueur();
       combinaisonIaSecrete(); 
        tourIa();
        propositionCombinaisonIa();
        comparaisonDeCombinaisonPourIa();
        resultatPourIa();
        tourJoueur();
        saisieJoueur();
        comparaisonDeCombinaison();
        resultatPourJoueur();*/
    }

}