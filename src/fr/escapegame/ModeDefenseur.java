package fr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import fr.escapegame.propriete.ChargerPropriete;

public class ModeDefenseur extends ModeDeJeux {
    public String resultat = " ";
    public String combiJoueur = " ";
    public String nouvelleCombinaison = " ";
    public int counter = 0;
    public int nbretrs=2;
    /**
     * Méthode pour présenter le mode de jeu defenseur
     */


    /**
     * Méthode pour présenter le mode de jeu choisi
     */

    public void introduction() {
        System.out.println("\n");
        System.out.println("Bienvenue dans le mode Challenger.");
        System.out.println("Tu vas devoir affronter une IA et deviner sa combinaison secrète de " + getNbCombinaison()
                + " chiffres. ");
        System.out.println("Tu as " + getNbEssai() + " essais. ");
        System.out.println("La partie commence ");

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
            getCombinaison()[i] = (int) (Math.random() * 9);
            if (getModeDev() == true) {
                System.out.print(getCombinaison()[i]);

            }
        }
        System.out.println();
    }
    /**
     * La methode permet au joueur de saisir une combinaison de x chiffres et de
     * l'afficher la combinaison est parametré dans le fichier de propriete
     */

    public void saisieJoueur() {
        setChanceUtilisee(getChanceUtilisee() + 1);
        System.out.println("\n");
        Scanner scan = new Scanner(System.in);
        Pattern combinaison = Pattern.compile("[0-9]{" + getNbCombinaison() + "}");
        System.out.println("Veuillez saisir " + getNbCombinaison() + " chiffres");
        while (!scan.hasNext(combinaison)) {
            if (scan.hasNext()) {
                System.out.println("Erreur vous avez saisi " + scan.next());
                System.out.println("Veuillez bien saisir " + getNbCombinaison() + " chiffres");

            }
        }

        String nb = scan.nextLine();
        setTab(nb.toCharArray());
        combiJoueur+=( nb);
        System.out.println("Vous avez saisi " + combiJoueur);

    }

    /**
     * La methode compare la saisie du joueur a la combinaison aleatoire de l IA Un
     * signe + - ou = indique au joueur si il a trouvé ou non la combinaison de l IA
     */
    /**
     * La methode affiche ou non la combinaison aleatoire de l IA si la variable
     * modeDev dans la methode combinaisonAleatoireIa est true
     */
    
    

    public void combinaisonVisibleIa() {
        System.out.println("\n");
        System.out.print("l'IA propose la combinaison :");
        this.combinaisonAleatoireIa();

    }

  

    public void comparaisonDeCombinaison() {
       
        System.out.println("\n");
        System.out.print("Le resultat est le suivant");
        for (int k = 0; k < getTab().length; k++) {
            setSaisieJoueur(Integer.parseInt(String.valueOf(getTab()[k])));
            setCombinaisonAleatoire(getCombinaisonIa()[k]);
            if (getCombinaisonAleatoire() == getSaisieJoueur()) {
                resultat+= ("=");
            } else if (getCombinaisonAleatoire() > getSaisieJoueur()) {
                resultat+= ("-");
            } else {
                resultat+= ("+");

            }
            

        }
        System.out.println(resultat);
    }
    
    public void resultat () {
        comparaisonDeCombinaison();
        if (getCombinaisonAleatoire() == getSaisieJoueur()) {
            System.out.println("L'IA a trouvé la combinaison du joueur");
        } else if (getCombinaisonAleatoire() > getSaisieJoueur()||getCombinaisonAleatoire() < getSaisieJoueur()) {
            System.out.println("L'ia n'a pas trouvé la combinaison du joueur ");
        
    }
        
    
   
    
    }
    
    
    
    public void tentativePourTrouverLaCombinaisonDuJoueur() {
        
        
        char[] resultatTab = new char[resultat.length()];
        char[] combiJoueurTab = new char[combiJoueur.length()];
        for (int i = 0; i < resultat.length(); i++) {
        resultatTab[i] = resultat.charAt(i);
        combiJoueurTab[i] = combiJoueur.charAt(i);
        }
   
    setChanceUtilisee(getChanceUtilisee() + 1);
        setMessagePerteIa(getMessagePerteIa() + 1);
        setNbEssai(getNbEssai());
        setNombreDeTours(getNombreDeTours() + 1);
        for (int i = 0; i < resultatTab.length; i++) {
            // System.out.println(Character.toString(resultatTab[i]).equals("+"));
            if (Character.toString(resultatTab[i]).equals("+")) {
                /**
                 * System.out.println("superieur"); System.out.println(combinaisonTab[i]);
                 */
                nouvelleCombinaison += ( Character.getNumericValue(combiJoueurTab[i] + 1
                        + (int) (Math.random() * (9 - (Character.getNumericValue(combiJoueurTab[i] + 1))))));  
               
        
            } else if (Character.toString(resultatTab[i]).equals("-")) {
                /**
                 * System.out.println("inferieur"); System.out.println(combinaisonTab[i]);
                 */
                nouvelleCombinaison+=(Character.getNumericValue(combiJoueurTab[i] - 1
                - (int) (Math.random() * (Character.getNumericValue(combiJoueurTab[i] - 0)))));
            } else {
                /** System.out.print("egalite "); */
                nouvelleCombinaison +=(combiJoueurTab[i]);
                
            }}}
        
    public void nouveauTour () {
        System.out.println("Essai n° " + nbretrs);
    }
 
    
    
    public void nouvelleCombinaison() {
        System.out.println("L'IA propose la nouvelle combinaison " + nouvelleCombinaison);
    }

    public void saisieReponse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir le résultat sous forme d'opérateur");
        String resultat = sc.nextLine();
        
        System.out.println("Vous avez saisi " + resultat);
        
    }

    public void nouvearesultat() {
        if (nouvelleCombinaison == resultat) {
            System.out.println("L'IA a trouvé la combinaison du joueur");
        } else if (getCombinaisonAleatoire() > getSaisieJoueur()||getCombinaisonAleatoire() < getSaisieJoueur()) {
            System.out.println("L'ia n'a pas trouvé la combinaison du joueur ");
    }
    }
    
    
    
    
    public void boucle () {
        saisieJoueur();
        combinaisonVisibleIa();
        resultat();
    do {
        setNbEssai(getNbEssai());
        tentativePourTrouverLaCombinaisonDuJoueur();
        nouveauTour();
        nouvelleCombinaison();
        saisieReponse();
        nouvearesultat();
        
    } while (nouvelleCombinaison != resultat && nbretrs++ != getNbEssai());
    }
    
    public void gagneOuDefaite() {
        if (nouvelleCombinaison == resultat) {
            System.out.println("Victoire pour l'IA");
        } else {
            System.out.println("Défaite pour l'IA");
        }
        
    }
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
        //*resetChanceUtilisee();
       //* resetNbTour();
        try {
            int nbMode = sc.nextInt();
            switch (nbMode) {
            case 1:
                System.out.println("La partie va recommencer ");
                introduction();
                boucle();
                gagneOuDefaite();
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
        introduction();
        boucle();
        gagneOuDefaite();
        choixApresUneFinDePartie();
     
        
      
        

    }

}
