package fr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import fr.escapegame.propriete.ChargerPropriete;

public class ModeDuel extends ModeDeJeux {
    public String resultat = "";
    public String combinaisonSecreteJoueur = "";
    public String nouvelleCombinaisonIa = "";
    public int nbretrs = 1; // *saisieCombinaisonSecreteJoueur()
    public String resetNouvelleCombi = "";
    private int saisieJoueurDef = 0;

    public int[] combinaisonIaAtt; // *creationCombinaisonAleatoireIa();
    public int combinaisonAleatoireIaAtt = 0;
    public int chanceUtiliseeIa =2;
    private char[] tabDef; // *saisieCombinaisonSecreteJoueur()

    public void introduction() {
        System.out.println("\n");
        System.out.println("Bienvenue dans le mode Challenger.");
        System.out.println("Tu vas devoir affronter une IA et deviner sa combinaison secrète de " + getNbCombinaison()
                + " chiffres. ");
        System.out.println("Tu as " + getNbEssai() + " essais. ");
        System.out.println("La partie commence ");

    }

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
     * Mode challenger et mode duel modeDev dans la methode combinaisonAleatoireIa
     * est true
     */
    public void combinaisonIaSecrete() {
        System.out.println("\n");
        System.out.print("La combinaison de l'Ia à deviner est :");
        this.combinaisonAleatoireIa();
        

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
        tabDef = nbre.toCharArray();
        combinaisonSecreteJoueur += (nbre);
        System.out.println("Vous avez saisi la combinaison secrète " + combinaisonSecreteJoueur);

    }

    /**
     * La methode creee une combinaison aleatoire pour l IA entre 0 et 9 chiffres.
     * Mode defenseur La variable nbCombinaison determinera le nombre de chiffre que
     * comportera la combinaison.
     */
    public void creationCombinaisonAleatoireIa() {

        this.combinaisonIaAtt = new int[getNbCombinaison()];
        for (int i = 0; i < getNbCombinaison(); i++) {
            combinaisonIaAtt[i] = (int) (Math.random() * 9);

            System.out.print(combinaisonIaAtt[i]);

        }
    }
   
    public void tourIa() {
        System.out.println("C'est au tour de l' IA de faire un proposition");
    }

    /**
     * Méthode qui affiche la combinaison aleatoire de l IA Mode defenseur
     */

    public void propositionCombinaisonIa() {
        System.out.println("\n");
        System.out.println("Essai n° 1");
        System.out.print("l'IA propose la combinaison :");
        this.creationCombinaisonAleatoireIa();
        
        

        

    }

    public void comparaisonDeCombinaisonPourIa() {

        System.out.println("\n");
        System.out.print("Le resultat est le suivant ");
        for (int k = 0; k < tabDef.length; k++) {
            saisieJoueurDef = Integer.parseInt(String.valueOf(tabDef[k]));
            combinaisonAleatoireIaAtt = combinaisonIaAtt[k];
            // * setSaisieJoueur(Integer.parseInt(String.valueOf(getTab()[k])));
            // * setCombinaisonAleatoire(getCombinaisonIa()[k]);
            if (combinaisonAleatoireIaAtt == saisieJoueurDef) {
                resultat += ("=");
            } else if (combinaisonAleatoireIaAtt > saisieJoueurDef) {
                resultat += ("-");
            } else {
                resultat += ("+");

            }

        }
        System.out.println(resultat);
    }

    public void resultat() {
        if (combinaisonAleatoireIaAtt == saisieJoueurDef) {
            System.out.println("L'IA a trouvé la combinaison du joueur");
        } else if (combinaisonAleatoireIaAtt > saisieJoueurDef || combinaisonAleatoireIaAtt < saisieJoueurDef) {
            System.out.println("L'ia n'a pas trouvé la combinaison du joueur ");

        }

    }

    public void tourJoueur() {
        System.out.println("C'est au tour du joueur de faire une proposition");
    }
    
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

        String saisieJoueur = scan.nextLine();
        setTab(saisieJoueur.toCharArray());
        System.out.println("Vous avez saisi: " + saisieJoueur);

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
    System.out.println();
    if (getSaisieJoueur() == getCombinaisonAleatoire()) {
        System.out.print("Vous avez gagné, vous avez trouvé la combinaison ");
    } else if (getSaisieJoueur() < getCombinaisonAleatoire() || getSaisieJoueur() > getCombinaisonAleatoire())
        System.out.println("Vous n'avez pas trouvé la combinaison \n ");
    
    }
    
    public void nouveauTourPourIa() {          
        System.out.println("\n");
        System.out.println("Essai n° " + chanceUtiliseeIa++ + " pour l'Ia");
    }
    
    public void generationNouvelleCombinaisonIa() {
        nouvelleCombinaisonIa = "";

        char[] resultatDansTab = new char[resultat.length()];
        char[] combiJoueurDansTab = new char[combinaisonSecreteJoueur.length()];
        for (int i = 0; i < resultat.length(); i++) {
            resultatDansTab[i] = resultat.charAt(i);
            combiJoueurDansTab[i] = combinaisonSecreteJoueur.charAt(i);
        }

        for (int i = 0; i < resultatDansTab.length; i++) {
            // System.out.println(Character.toString(resultatTab[i]).equals("+"));
            if (Character.toString(resultatDansTab[i]).equals("+")) {
                /**
                 * System.out.println("superieur"); System.out.println(combinaisonTab[i]);
                 */
                nouvelleCombinaisonIa += (Character.getNumericValue(combiJoueurDansTab[i] + 1
                        + (int) (Math.random() * (9 - (Character.getNumericValue(combiJoueurDansTab[i] + 1))))));

            } else if (Character.toString(resultatDansTab[i]).equals("-")) {
                /**
                 * System.out.println("inferieur"); System.out.println(combinaisonTab[i]);
                 */
                nouvelleCombinaisonIa += (Character.getNumericValue(combiJoueurDansTab[i] - 1
                        - (int) (Math.random() * (Character.getNumericValue(combiJoueurDansTab[i] - 0)))));
            } else {
                /** System.out.print("egalite "); */
                nouvelleCombinaisonIa += (combiJoueurDansTab[i]);

            }
        }
    }
    
    
    public void nouvelleCombinaisonIa() {
        System.out.println("L'IA propose la nouvelle combinaison " + nouvelleCombinaisonIa);
    }
    
    
    public void saisieOperateur() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir le résultat sous forme d'opérateur");
        String resultat = sc.nextLine();
        System.out.println("Vous avez saisi " + resultat);

    }
    public void nouvearesultat() {
        if (nouvelleCombinaisonIa == combinaisonSecreteJoueur) {
            System.out.println("L'IA a trouvé la combinaison du joueur");
        } else if (nouvelleCombinaisonIa  != combinaisonSecreteJoueur) {
            System.out.println("L'IA n'a pas trouvé la combinaison du joueur ");
        }
    }
    
    public void TentativeDeTrouverLaCombinaisonEntreIaEtJoueur() {
        introduction();
        combinaisonIaSecrete();
        saisieCombinaisonSecreteJoueur();
        tourIa();
        propositionCombinaisonIa();
        comparaisonDeCombinaisonPourIa();
        resultat();
        tourJoueur();
        saisieJoueur();
        comparaisonDeCombinaison();
        resultatPourJoueur();
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

            nbretrs++;
            
       

        } while ( nouvelleCombinaisonIa != combinaisonSecreteJoueur && nbretrs != nbEssai );
    }
    


    public void jouer() {
      
        TentativeDeTrouverLaCombinaisonEntreIaEtJoueur();
    }

}