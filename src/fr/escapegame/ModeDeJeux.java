package fr.escapegame;

/*import java.util.InputMismatchException;*/
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import fr.escapegame.propriete.ChargerPropriete;

/**
 * ModeDeJeux est la classe parent regroupant les methodes et variable pour les
 * 3 classes ModeChallenger ModeDefenseur et ModeDuel
 * @author Glairon Anthony
 * @version 1.0
 */
public abstract class ModeDeJeux {
    public static final Logger LOGGER = Logger.getLogger(Launcher.class);

    /*
     *  Variable du fichier de propriete
     */
    public Boolean modeDev = ChargerPropriete.MODE_DEV;
    public int nbCombinaison = ChargerPropriete.NB_COMBINAISON;
    public int nbEssai = ChargerPropriete.NB_ESSAI;

    /*
     * Variable des modes de jeux challenger, defenseur et duel
     */
    public int[] combinaisonIa; 
    public char[] tableauDeCombinaison;
    public int saisieJoueur = 0; 
    public int combinaisonAleatoireIa = 0; 
    public int chanceUtiliseeJoueur = 1;
    public String combinaisonSecreteJoueur = ""; 
    public int combinaisonPourIa = 0; 
    public int chanceUtiliseeIa = 2; 
    public int[] iaCombinaison;
    public String resultat = ""; 
    public int nbreDeTours = 1; 
    public int toursIa = 1;
    public String nouvelleCombinaisonIa = ""; 
    public int combinaisonJoueur = 0; 
    public char[] tabChar; 
    public String combiIaConvertiEnString = ""; 
    public char[] combiIaTab;   
    public Integer selected = null;
    
    /*
     * Methode d introduction du mode challenger
     */
    public void introductionModeChallenger() {
        if (nbCombinaison == 1 && nbEssai  == 1) {
           System.out.println("Vous avez choisi le mode challenger, vous allez devoir deviner la combinaison de l IA de "+ nbCombinaison + " chiffre en " + nbEssai + " essai. \n");
           System.out.println("La partie commence.\n");
        } else if (nbCombinaison == 1 && nbEssai  > 1) {
            System.out.println("Vous avez choisi le mode challenger, vous allez devoir deviner la combinaison de l IA de "+ nbCombinaison + " chiffre en " + nbEssai + " essais. \n");
            System.out.println("La partie commence.\n");
        } else if (nbCombinaison > 1 && nbEssai == 1) {
            System.out.println("Vous avez choisi le mode challenger, vous allez devoir deviner la combinaison de l IA de "+ nbCombinaison + " chiffres en " + nbEssai + " essai. \n");
            System.out.println("La partie commence.\n");
        } else {
            System.out.println("Vous avez choisi le mode challenger, vous allez devoir deviner la combinaison de l IA de "+ nbCombinaison + " chiffres en " + nbEssai + " essais. \n");
            System.out.println("La partie commence.\n");
        }        
    }
    
    /*
     * Methode d introduction du mode defenseur
     */
    public void introductionModeDefenseur() {
        if (nbCombinaison == 1 && nbEssai  == 1) {
            System.out.println("Vous avez choisi le mode Defenseur, l'IA doit deviner votre combinaison secr�te de "+ nbCombinaison + " chiffre en " + nbEssai + " essai. \n");
            System.out.println("La partie commence.\n");
        } else if (nbCombinaison == 1 && nbEssai  > 1) {
            System.out.println("Vous avez choisi le mode Defenseur, l'IA doit deviner votre combinaison secr�te de "+ nbCombinaison + " chiffre en " + nbEssai + " essais. \n");
            System.out.println("La partie commence.\n");
        } else if (nbCombinaison > 1 && nbEssai == 1) {
            System.out.println("Vous avez choisi le mode Defenseur, l'IA doit deviner votre combinaison secr�te de "+ nbCombinaison + " chiffres en " + nbEssai + " essai. \n");
            System.out.println("La partie commence.\n");
        } else {
            System.out.println("Vous avez choisi le mode Defenseur, l'IA doit deviner votre combinaison secr�te de "+ nbCombinaison + " chiffres en " + nbEssai + " essais. \n");
            System.out.println("La partie commence.\n");
        }        
    }
    
    /*
     * Methode d introduction du mode duel
     */
    public void introductionModeDuel() {
        if (nbCombinaison == 1 && nbEssai  == 1) { 
           System.out.println("Vous avez choisi le mode Duel, le premier entre l'IA ou le joueur qui trouve la combinaison de "+ nbCombinaison + " chiffre a gagn�. ");
           System.out.println("Chaque participant � " + nbEssai + " essai. \n");
           System.out.println("La partie commence. \n ");
        } else if (nbCombinaison == 1 && nbEssai  > 1) { 
            System.out.println("Vous avez choisi le mode Duel, le premier entre l'IA ou le joueur qui trouve la combinaison de "+ nbCombinaison + " chiffre a gagn�. ");
            System.out.println("Chaque participant � " + nbEssai + " essais. \n");
            System.out.println("La partie commence. \n ");
        } else if (nbCombinaison > 1 && nbEssai == 1) { 
            System.out.println("Vous avez choisi le mode Duel, le premier entre l'IA ou le joueur qui trouve la combinaison de "+ nbCombinaison + " chiffres a gagn�. ");
            System.out.println("Chaque participant � " + nbEssai + " essai. \n");
            System.out.println("La partie commence. \n ");
        } else { 
            System.out.println("Vous avez choisi le mode Duel, le premier entre l'IA ou le joueur qui trouve la combinaison de "+ nbCombinaison + " chiffres a gagn�. ");
            System.out.println("Chaque participant � " + nbEssai + " essais. \n");
            System.out.println("La partie commence. \n ");
        }        
    }
    
    /**
     * La methode genere une combinaison aleatoire pour l IA entre 0 et 9 chiffres
     * La variable nbCombinaison determinera le nombre de chiffre que comportera la
     * combinaison La variable modeDev permet l'affichage de la combinaison si elle
     * est = a true Mode challenger et mode duel
     */
    public void combinaisonAleatoireIa() {
        combinaisonIa = new int[nbCombinaison];
        for (int i = 0; i < nbCombinaison; i++) {
            combinaisonIa[i] = (int) (Math.random() * 9);
            if (modeDev == true) {
                System.out.print(combinaisonIa[i]);
            } else {
                System.out.println("?");
            }
        }
    }

    /**
     * La methode affiche ou non la combinaison aleatoire de l IA si la variable
     * modeDev dans la methode combinaisonAleatoireIa est true Mode challenger et
     * mode duel
     */
    public void combinaisonIaSecrete() {
        System.out.println("\n");
        System.out.print("La combinaison de l'IA � deviner est ");
        this.combinaisonAleatoireIa();
    }

    /**
     * Methode affichant chiffre au singulier ou au pluriel en 
     * fonction du nombre de chiffre � saisir dans la combinaison 
     * par le joueur
     */    
    public void nombreCombinaisonSaisiParJoueur() {
        if (nbCombinaison == 1) {
            System.out.println("Saisir une combinaison de " + nbCombinaison + " chiffre");
        } else {
            System.out.println("Saisir une combinaison de " + nbCombinaison + " chiffres");
        }      
    }
     
    /**
     * La methode permet au joueur de saisir une combinaison de x chiffres et de l
     * afficher mode chalenger et duel
     */
    public void saisieJoueur() {     
        System.out.println("\n");
        System.out.println("Veuillez tenter votre essai n�" + chanceUtiliseeJoueur++ + "\n");
        Scanner scan = new Scanner(System.in);
        Pattern combinaison = Pattern.compile("[0-9]{" + nbCombinaison + "}");
        nombreCombinaisonSaisiParJoueur();
        while (!scan.hasNext(combinaison)) {
            if (scan.hasNext()) {
                System.out.println("Erreur vous avez saisi " + scan.next());
                nombreCombinaisonSaisiParJoueur();
                String nb = scan.nextLine();
                tableauDeCombinaison = nb.toCharArray();
                System.out.println("Vous proposez la combinaison " + nb + "\n");
            }
        }
        String nb = scan.nextLine();
        tableauDeCombinaison = nb.toCharArray();
        System.out.println("Vous proposez la combinaison " + nb + "\n");
    }

    /**
     * La methode compare la saisie du joueur a la combinaison aleatoire de l IA Un
     * signe + - ou = indique au joueur si il a trouve ou non la combinaison de l IA
     * Mode challenger et duel
     */
    public void comparaisonDeCombinaison() {
        System.out.print("Le resultat est ");
        for (int k = 0; k < tableauDeCombinaison.length; k++) {
            saisieJoueur = (Integer.parseInt(String.valueOf(tableauDeCombinaison[k])));
            combinaisonAleatoireIa = (combinaisonIa[k]);
            if (saisieJoueur == combinaisonAleatoireIa) {
                System.out.print("=");
            } else if (saisieJoueur < combinaisonAleatoireIa) {
                System.out.print("+");
            } else {
                System.out.print("-");
            }
            System.out.println("\n");
        }
    }

    /**
     * Methode creee une combinaison aleatoire pour l IA entre 0 et 9 chiffres La
     * variable nbCombinaison determinera le nombre de chiffre que comportera la
     * combinaison Mode defenseur et mode duel
     */
    public void creationCombinaisonAleatoireIa() {
        this.iaCombinaison = new int[nbCombinaison];
        for (int i = 0; i < nbCombinaison; i++) {
            iaCombinaison[i] = (int) (Math.random() * 9);
            System.out.print(iaCombinaison[i]);
            combiIaConvertiEnString = combiIaConvertiEnString + iaCombinaison[i];
        }
    }

    /**
     * Methode qui affiche la combinaison aleatoire de l IA Mode defenseur et mode
     * duel
     */
    public void propositionCombinaisonIa() {
        System.out.println("Essai n� 1\n");
        System.out.print("l'IA propose la combinaison ");
        this.creationCombinaisonAleatoireIa();
    }
    
    /**
     * Methode affichant le mot chiffre au singulier ou au pluriel en fonction
     * du nombre de chiffre dans la variable nbCombinaison
     * Classe Launcher
     */
    public void nombreChiffreCombinaisonSecreteJoueur() {
    if (nbCombinaison == 1) {
        System.out.println("Veuillez saisir une combinaison secr�te de " + nbCombinaison + " chiffre");
    } else {
        System.out.println("Veuillez saisir une combinaison secr�te de " + nbCombinaison + " chiffres");
    }      
}
        
    /**
     * Methode pour que le joueur saisisse une combinaison de x chiffres 
     * Mode defenseur et mode duel
     */
    public void saisieCombinaisonSecreteJoueur() {
        Scanner scanner = new Scanner(System.in);
        Pattern combinaisonSecrete = Pattern.compile("[0-9]{" + nbCombinaison + "}");
        nombreChiffreCombinaisonSecreteJoueur();
        while (!scanner.hasNext(combinaisonSecrete)) {
            if (scanner.hasNext()) {
                System.out.println("Erreur vous avez saisi " + scanner.next());
                System.out.println("Veuillez bien saisir " + nbCombinaison + " chiffres");
                String nbre = scanner.nextLine();
                tabChar = nbre.toCharArray();
                combinaisonSecreteJoueur = combinaisonSecreteJoueur + (nbre);
                nombreChiffreCombinaisonSecreteJoueur();
            }
        }
        String nbre = scanner.nextLine();
        tabChar = nbre.toCharArray();
        combinaisonSecreteJoueur = combinaisonSecreteJoueur + (nbre);
        System.out.println("Vous avez saisi la combinaison secr�te " + combinaisonSecreteJoueur + "\n");
    }

    /**
     * Methode qui compare la combinaison aleatoire de l IA a la combinaison secrete
     * du joueur et affiche un resultat sous forme d'operateur 
     * Mode defenseur et mode duel
     */
    public void comparaisonDeCombinaisonPourIa() {
        System.out.println("\n");
        System.out.print("Le resultat est ");
        for (int k = 0; k < tabChar.length; k++) {
            Integer.parseInt(String.valueOf(tabChar[k]));
            combinaisonPourIa = iaCombinaison[k];
            if (combinaisonPourIa == combinaisonJoueur) {
                resultat = resultat + ("=");
            } else if (combinaisonPourIa > combinaisonJoueur) {
                resultat = resultat + ("-");
            } else {
                resultat = resultat + ("+");
            }
        }
        System.out.println(resultat + "\n");
    }

    /**
     * Methode affichant que l Ia a trouve ou non la combinaison du joueur 
     * Mode defenseur et mode duel
     */
    public void resultatPourIa() {
        if (combinaisonPourIa == combinaisonJoueur) {
            System.out.println("Victoire de l IA, la combinaison a �t� devin�e ");
        } else if (combinaisonPourIa > combinaisonJoueur || combinaisonPourIa < combinaisonJoueur) {
            System.out.println("L'IA n'a pas trouv� la combinaison du joueur");
        }
    }

    /**
     * Methode qui indique si le joueur a gagne ou perdu
     * Mode duel
     */
    public void resultatPourJoueur() {
        System.out.println("\n");
        if (saisieJoueur == combinaisonAleatoireIa) {
            System.out.print("Vous avez gagn�, vous avez trouv� la combinaison de l' IA\n ");
        } else if (saisieJoueur < combinaisonAleatoireIa || saisieJoueur > combinaisonAleatoireIa)
            System.out.println("Vous n'avez pas trouv� la combinaison de l IA\n ");
    }

    /**
     * Methode indiquant le nombre de chance utilisees pour l IA
     * Mode duel
     */
    public void nouveauTourPourIa() {
        System.out.println("\n");
        System.out.println("Essai n� " + chanceUtiliseeIa++ + " pour l'Ia\n");
    }

    /**
     * Methode generant une serie de nombre aleatoire compris dans un intervalle
     * maxi et min qui s integre dans la methode generationNouvelleCombinaisonIa
     * 
     * @param min
     * @param max
     * @return defenseur et mode duel
     */
    public int generationNbreAletoire(int min, int max) {
        if (min == max)
            return min;
        Random random = new Random();
        return min + random.nextInt(max - min);
    }

    /**
     * Methode qui va generer une nouvelle combinaison aleatoire pour l IA 
     * Mode defenseur et mode duel
     */
    public void generationNouvelleCombinaisonIa() {
        nouvelleCombinaisonIa = "";
        char[] resultatDansTab = new char[resultat.length()];
        combiIaTab = new char[combiIaConvertiEnString.length()];
        for (int i = 0; i < resultat.length(); i++) {
            resultatDansTab[i] = resultat.charAt(i);
            combiIaTab[i] += combiIaConvertiEnString.charAt(i);
        }
        for (int i = 0; i < resultatDansTab.length; i++) {
            if (Character.toString(resultatDansTab[i]).equals("+")) {
                nouvelleCombinaisonIa = nouvelleCombinaisonIa
                        + this.generationNbreAletoire(Character.getNumericValue(combiIaTab[i]), 9);
            } else if (Character.toString(resultatDansTab[i]).equals("-")) {

                nouvelleCombinaisonIa = nouvelleCombinaisonIa
                        + this.generationNbreAletoire(0, Character.getNumericValue(combiIaTab[i]));
            } else {
                nouvelleCombinaisonIa = nouvelleCombinaisonIa + (Character.getNumericValue(combiIaTab[i]));
            }
        }
    }

    /**
     * Methode affichant la nouvelle combinaison de l IA 
     * Mode defenseur et mode duel
     */
    public void nouvelleCombinaisonIa() {
        System.out.println("L'IA propose la nouvelle combinaison " + nouvelleCombinaisonIa);
        combiIaConvertiEnString = nouvelleCombinaisonIa + "\n";
        if (nouvelleCombinaisonIa.equals(combinaisonSecreteJoueur)) {
            System.out.println("Victoire de l IA, la combinaison a �t� devin�e");
        }
     }

    /**
     * Methode permettant au joueur de saisir sous forme d operateur pour modifier a
     * la hausse ou la baisse la nouvelle combinaison de l IA 
     * Mode defenseur et mode duel
     */
    public void saisieOperateur() {
        Scanner scan = new Scanner(System.in);
        Pattern operateur = Pattern.compile("[+---=]");
        System.out.println("Veuillez saisir le r�sultat sous forme d'op�rateur + - ou =");
        while (!scan.hasNext(operateur)) {
            if (scan.hasNext()) {
                System.out.println("Vous n'avez pas saisi des op�rateurs ");
                System.out.println("Veuillez � nouveau saisir le r�sultat sous forme d'op�rateur + - ou = ");
                String result = scan.nextLine();
                resultat = result;
            }
        }
        String result = scan.nextLine();
        System.out.println("Vous avez saisi " + result);
        resultat = result;       
    }

    /**
     * Methode affichant si l' IA a trouvee ou non la combinaison du joueur 
     * Mode defenseur et mode duel
     */
   public void nouvearesultat() {
        toursIa++;
        if (!nouvelleCombinaisonIa.equals(combinaisonSecreteJoueur) && (toursIa == nbEssai)) {
            System.out.print("D�faite pour l IA, la combinaison du joueur n'a pas �t� trouv�e");
        } else if (!nouvelleCombinaisonIa.equals(combinaisonSecreteJoueur)) {
            System.out.println("L'IA n'a pas trouv� la combinaison du joueur");
        } else {
            System.out.println("Victoire de l IA, la combinaison a �t� devin�e ");           
        }
    }

    /**
     * Methode pour annoncer que c est au tour du joueur de jouer
     * Mode duel
     */
    public void tourJoueur() {
        System.out.println("\n");
        System.out.println("C'est au tour du joueur de faire une proposition");
    }

    /**
     * Methode pour indiquer que c est au tour de l IA de jouer
     * Mode duel
     */
    public void tourIa() {
        System.out.println("\n");
        System.out.println("C'est au tour de l' IA de faire une proposition \n ");
    }

    /**
     * Methode affichant que l'IA et le joueur ont perdus
     * Mode duel
     */
    public void defaiteIaEtJoueur() {
        if (!nouvelleCombinaisonIa.equals(combinaisonSecreteJoueur) &&  saisieJoueur < combinaisonAleatoireIa && (toursIa == nbEssai)) {
            System.out.println("Fin de partie, le joueur et l'IA ont perdus");
            System.out.print("La combinaison de l'IA est ");
            {
                for (int z = 0; z < combinaisonIa.length; z++)
                    if (saisieJoueur != combinaisonAleatoireIa)
                        System.out.print(combinaisonIa[z] + "\n");
            }
        }
    }
}