package fr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import fr.escapegame.propriete.ChargerPropriete;

public class ModeDefenseur extends ModeDeJeux {
    
    
    /**
     * Methode d introduction au mode defenseur
     */
   
    public void introduction() {
        System.out.println("\n");
        System.out.println("Bienvenue dans le mode defenseur.");
        System.out.println("Une IA va devoir deviner ta combinaison secrète de " + nbCombinaison + " chiffres. ");
        System.out.println("L'IA a " + nbEssai + " essais. ");
        System.out.println("La partie commence ");

    }

     
    /**
     * La methode creee une combinaison aleatoire pour l IA entre 0 et 9 chiffres.
     * Mode defenseur La variable nbCombinaison determinera le nombre de chiffre que
     * comportera la combinaison.
     */
    public void creationCombinaisonAleatoireIa() {

        this.combinaisonIaAtt = new int[nbCombinaison];
        for (int i = 0; i < nbCombinaison; i++) {
            combinaisonIaAtt[i] = (int) (Math.random() * 9);

            System.out.print(combinaisonIaAtt[i]);

        }
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
    
    /**
     * Methode pour que le joueur saisisse une combinaison 
     * de x chiffres en du parametrage de la variable nbCombinaison
     */
    
    public void saisieCombinaisonSecreteJoueur() {
        System.out.println("\n");
        Scanner scanner = new Scanner(System.in);
        Pattern combinaisonSecrete = Pattern.compile("[0-9]{" + nbCombinaison + "}");
        System.out.println("Veuillez saisir une combinaison secrète de " + nbCombinaison + " chiffres");
        while (!scanner.hasNext(combinaisonSecrete)) {
            if (scanner.hasNext()) {
                System.out.println("Erreur vous avez saisi " + scanner.next());
                System.out.println("Veuillez bien saisir " + nbCombinaison + " chiffres");

            }
        }

        String nbre = scanner.nextLine();
        tabDef = nbre.toCharArray();
        combinaisonSecreteJoueur += (nbre);
        System.out.println("Vous avez saisi la combinaison secrète " + combinaisonSecreteJoueur);

    }

    
    /**
     * Cette methode compare la combinaison aleatoire de l IA  a la 
     * combinaison secrete du joueur et affiche un resultat
     */
    

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
    
    
    /**
     * Methode affichant que l Ia a trouve ou non la combinaison du joueur
     */
    

    public void resultat() {
        if (combinaisonAleatoireIaAtt == saisieJoueurDef) {
            System.out.println("L'IA a trouvé la combinaison du joueur");
        } else if (combinaisonAleatoireIaAtt > saisieJoueurDef || combinaisonAleatoireIaAtt < saisieJoueurDef) {
            System.out.println("L'ia n'a pas trouvé la combinaison du joueur ");

        }

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
    
    
    /**
     * Methode regroupant les methodes ci-dessus dans une boucle do While 
     * qui bouclera tant que la combinaison aleatoire de l IA est differente de la combinaison secrete du joueur
     * et tant que la nouvelle combinaison de l IA est differente de la combinasion secrete du joueur
     * et tant que le nombre de tour est different du nombre d essai parametre
     */
    
    
    
    public void tentativePourTrouverLaCombinaisonDuJoueur()  {
        introduction();
        saisieCombinaisonSecreteJoueur();
        propositionCombinaisonIa();
        comparaisonDeCombinaisonPourIa();
        resultat(); 
        do {   
      //* nbEssai;
       nouveauTourPourIa();
       generationNouvelleCombinaisonIa();
       nouvelleCombinaisonIa();
       saisieOperateur();
       nouvearesultat();
            nbretrs++;
            
     //*       System.out.println(chanceUtiliseeIa);
     //*       System.out.println(getChanceUtiliseeJoueur());
       

      } while (combinaisonAleatoireIaAtt != saisieJoueurDef && nouvelleCombinaisonIa != combinaisonSecreteJoueur && nbretrs != nbEssai );
       
}  
  
    /**
     * Methode affichant si l Ia a gagnee ou perdue
     */
  
    
    public void victoireOuDefaite() {
        if (nouvelleCombinaisonIa == combinaisonSecreteJoueur) {
            System.out.println("Victoire pour l'IA");
        } else {
            System.out.println("Défaite pour l'IA");
        }

    }
    
    
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
        propositionApresUneFinDePartie();
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
                tentativePourTrouverLaCombinaisonDuJoueur();
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
    
    
    public void jouer() {
      
        tentativePourTrouverLaCombinaisonDuJoueur();
        victoireOuDefaite();
        choixApresUneFinDePartie();
    }

}