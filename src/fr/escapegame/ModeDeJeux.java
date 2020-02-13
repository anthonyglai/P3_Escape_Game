package fr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
import fr.escapegame.propriete.ChargerPropriete;

/**
 * ModeDeJeux est la classe parent regroupant les methodes et variable pour les
 * 3 classes ModeChallenger ModeDefenseur et ModeDuel
 * 
 * @author Glairon Anthony
 * @version 1.0
 */
public abstract class ModeDeJeux {
    
    //* Variable mode challenger

    private Boolean modeDev = ChargerPropriete.MODE_DEV;
    private int nbCombinaison = ChargerPropriete.NB_COMBINAISON;
    private int nbEssai = ChargerPropriete.NB_ESSAI;
    private int[] combinaisonIa;
    private char[] tab;
    private int saisieJoueur = 0;
    private int combinaisonAleatoire = 0;
    private int nombreDeTours = 0;
    private int chanceUtilisee = 0;
    private int messagePerteDuJoueur = 1;
    private int[] combinaisonJoueur;
    private int messagePerteIa = 1;
    
    //*Variable mode defenseur
    
    private String resultat = "";
    private String combiJoueur = "";
    private String nouvelleCombinaison = "";
    private int nbretrs = 1;
    private String resetNouvelleCombi = "";
    private int chanceUtiliseeIa = 1;

    /**
     * La methode genere une combinaison aleatoire pour l IA entre 0 et 9 chiffres.
     * La variable nbCombinaison determinera le nombre de chiffre que comportera la
     * combinaison La variable modeDev permet l'affichage de la combinaison si elle
     * est = a true .
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
     * La methode affiche ou non la combinaison aleatoire de l IA si la variable
     * modeDev dans la methode combinaisonAleatoireIa est true
     */
    public void combinaisonVisibleIa() {
        System.out.println("\n");
        System.out.println("La combinaison à deviner est :");
        this.combinaisonAleatoireIa();

    }

    /**
     * La methode permet au joueur de saisir une combinaison de x chiffres et de
     * l'afficher la combinaison est parametré dans le fichier de propriete
     */
    public void saisieJoueurA() {

        System.out.println("\n");
        System.out.println("Veuillez tenter votre essai n°" + chanceUtilisee++);

        Scanner scan = new Scanner(System.in);
        Pattern combinaison = Pattern.compile("[0-9]{" + nbCombinaison + "}");
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
     * La methode compare la saisie du joueur a la combinaison aleatoire de l IA Un
     * signe + - ou = indique au joueur si il a trouvé ou non la combinaison de l IA
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
     * Methode retournant au joueur en fonction de sa saisie si il a gagne si il
     * doit ressaisir une combinaison ou si il a perdu cette methode est repetee
     * tant que le joueur trouve un resultat different de la combinaison de l IA et
     * dans la limite du nombre d essai parametre dans la variable nbEssai du
     * fichier de propriete
     */
    public void tentativePourTrouverLaCombinaisonDeIa() {

        do {
            comparaisonDeCombinaison();

            System.out.println();
            if (saisieJoueur == combinaisonAleatoire) {
                System.out.print("Vous avez gagné, vous avez trouvé la combinaison ");
            } else if (saisieJoueur < combinaisonAleatoire || saisieJoueur > combinaisonAleatoire)
                System.out.println("Vous n'avez pas trouvé la combinaison \n ");
            if (messagePerteDuJoueur++ == nbEssai) {
                System.out.print("Vous avez utilisé vos " + nbEssai + " essais, vous avez perdu. la combinaison était ");
            }

            nombreDeTours++;

        } while (saisieJoueur != combinaisonAleatoire && nombreDeTours != nbEssai);

    }

    /**
     * Methode qui affiche le résultat de la combinaison aléatoire de l IA à la
     * quand la partie est terminee
     */

    public void affichageDeLaCombinaison() {
        tentativePourTrouverLaCombinaisonDeIa();
        for (int z = 0; z < combinaisonIa.length; z++)
            System.out.print(combinaisonIa[z]);
        System.out.print(".");
        System.out.println("\n");

    }
    
    
    public void creationCombinaisonAleatoireIa() {

        this.setCombinaisonIa(new int[getNbCombinaison()]);
        for (int i = 0; i < getNbCombinaison(); i++) {
            getCombinaison()[i] = (int) (Math.random() * 9);
            
                System.out.print(getCombinaison()[i]);

            }
        }

    

    public void GenerationCombinaisonAleatoireIa() {
        System.out.println("\n");
        System.out.print("l'IA propose la combinaison :");
        this.creationCombinaisonAleatoireIa();

    }

    public void saisieJoueur() {
        System.out.println("\n");
        Scanner scan = new Scanner(System.in);
        Pattern combinaison = Pattern.compile("[0-9]{" + nbCombinaison  + "}");
        System.out.println("Veuillez saisir " + nbCombinaison + " chiffres");
        while (!scan.hasNext(combinaison)) {
            if (scan.hasNext()) {
                System.out.println("Erreur vous avez saisi " + scan.next());
                System.out.println("Veuillez bien saisir " + nbCombinaison  + " chiffres");
            }
        }

        String nb = scan.nextLine();
        setTab(nb.toCharArray());
        combiJoueur += (nb);
        System.out.println("Vous avez saisi " + combiJoueur);

    }

    public void comparaisonDeCombinaisonpourIa() {

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
        System.out.println(resultat);
    }

    public void resultat() {
        comparaisonDeCombinaisonpourIa();
        if (combinaisonAleatoire == saisieJoueur) {
            System.out.println("L'IA a trouvé la combinaison du joueur");
        } else if (combinaisonAleatoire > saisieJoueur || combinaisonAleatoire < saisieJoueur) {
            System.out.println("L'ia n'a pas trouvé la combinaison du joueur ");

        }

    }
                
    public void creationNouvelleCombinaisonIa() {
        nouvelleCombinaison = "";
        char[] resultatTab = new char[resultat.length()];
        char[] combiJoueurTab = new char[combiJoueur.length()];
        for (int i = 0; i < resultat.length(); i++) {
            resultatTab[i] = resultat.charAt(i);
            combiJoueurTab[i] = combiJoueur.charAt(i);
        }

        for (int i = 0; i < resultatTab.length; i++) {
            // System.out.println(Character.toString(resultatTab[i]).equals("+"));
            if (Character.toString(resultatTab[i]).equals("+")) {
                /**
                 * System.out.println("superieur"); System.out.println(combinaisonTab[i]);
                 */
                nouvelleCombinaison += (Character.getNumericValue(combiJoueurTab[i] + 1
                        + (int) (Math.random() * (9 - (Character.getNumericValue(combiJoueurTab[i] + 1))))));

            } else if (Character.toString(resultatTab[i]).equals("-")) {
                /**
                 * System.out.println("inferieur"); System.out.println(combinaisonTab[i]);
                 */
                nouvelleCombinaison += (Character.getNumericValue(combiJoueurTab[i] - 1
                        - (int) (Math.random() * (Character.getNumericValue(combiJoueurTab[i] - 0)))));
            } else {
                /** System.out.print("egalite "); */
                nouvelleCombinaison += (combiJoueurTab[i]);

            }
        }
    }

    public void nouveauTour() {
        
        System.out.println("Essai n° " + chanceUtilisee);
    }

    public void nouvelleCombinaisonIa() {
        System.out.println("L'IA propose la nouvelle combinaison " + nouvelleCombinaison);
    }

    public void saisieIndicePourIa() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir le résultat sous forme d'opérateur");
        String resultat = sc.nextLine();

        System.out.println("Vous avez saisi " + resultat);

    }

    public void nouvearesultat() {
        if (nouvelleCombinaison == resultat) {
            System.out.println("L'IA a trouvé la combinaison du joueur");
        } else if (combinaisonAleatoire > saisieJoueur || combinaisonAleatoire < saisieJoueur) {
            System.out.println("L'ia n'a pas trouvé la combinaison du joueur ");
        }
    }

    public void tentativePourTrouverLaCombinaisonDeJoueur() {
        saisieJoueur();
        GenerationCombinaisonAleatoireIa();
        resultat();
        do {           
            creationNouvelleCombinaisonIa();
            nouveauTour();
            nouvelleCombinaisonIa();
            saisieIndicePourIa();
            nouvearesultat();

            nbretrs++;

        } while (nouvelleCombinaison != resultat && nbretrs != getNbEssai());
    }

    public void victoireOuDefaite() {
        if (nouvelleCombinaison == resultat) {
            System.out.println("Victoire pour l'IA");
        } else {
            System.out.println("Défaite pour l'IA");
        }

    }

    public void resetResultat() {

        this.resultat = "";

    }

    public void resetnbTours() {

        this.nbretrs = 1;

    }
    
    public void resetnouveauTour() {
        this.chanceUtilisee = 0;
    }
    
    
    
    

    /**
     * Methode demandant au joueur de saisir si il souhaite recommencer changer ou
     * quitter le jeu
     */

    public void propositionApresUneFinDePartie() {
        tentativePourTrouverLaCombinaisonDeIa();
        System.out.println();
        System.out.println("Pour poursuivre veuillez choisir entre les 3 modes ci-dessous");
        System.out.println();
        System.out.println("1- Recommencer une partie ");
        System.out.println("2- Changer de mode de jeu");
        System.out.println("3- Quitter le jeu");

    }

    /**
     * Methode reinitialisant la variable du nombre d essai a 0 en fin de partie
     */

    public void resetChanceUtilisee() {
        this.chanceUtilisee = 0;

    }

    /**
     * @description Methode reinitialisant la variable du nombre de tours a 0 en fin
     *              de partie
     */

    public void resetNbTour() {

        this.nombreDeTours = 0;

    }
    public void resetChanceUtiliseeIa() {
        this.chanceUtiliseeIa = 1;

    }
    
    /**
     * Methode qui permet au joueur en fonction du choix proposé et de sa saisie de
     * faire une nouvelle partie , de retourner dans le menu de selection des modes
     * de jeux ou de sortir du jeu
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
            sc.close();
        }

    }

    public String getResultat() {
        return resultat;
    }

    public String getCombiJoueur() {
        return combiJoueur;
    }

    public String getNouvelleCombinaison() {
        return nouvelleCombinaison;
    }

    public int getNbretrs() {
        return nbretrs;
    }

    public String getResetNouvelleCombi() {
        return resetNouvelleCombi;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public void setCombiJoueur(String combiJoueur) {
        this.combiJoueur = combiJoueur;
    }

    public void setNouvelleCombinaison(String nouvelleCombinaison) {
        this.nouvelleCombinaison = nouvelleCombinaison;
    }

    public void setNbretrs(int nbretrs) {
        this.nbretrs = nbretrs;
    }

    public void setResetNouvelleCombi(String resetNouvelleCombi) {
        this.resetNouvelleCombi = resetNouvelleCombi;
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
    }

    public int getChanceUtiliseeIa() {
        return chanceUtiliseeIa;
    }

    public void setChanceUtiliseeIa(int chanceUtiliseeIa) {
        this.chanceUtiliseeIa = chanceUtiliseeIa;
    }
}