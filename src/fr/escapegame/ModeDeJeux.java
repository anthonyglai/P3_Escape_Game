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

    

    public Boolean modeDev = ChargerPropriete.MODE_DEV;
    public int nbCombinaison = ChargerPropriete.NB_COMBINAISON;
    public int nbEssai = ChargerPropriete.NB_ESSAI;
   
 // Variable mode challenger
    
    public int[] combinaisonIa;
    public char[] tab;
    public int saisieJoueur = 0;
    public int combinaisonAleatoire = 0;
    public int nombreDeTours = 0;
    public int chanceUtiliseeJoueur = 0;
    public int messagePerteDuJoueur = 1;
    public int[] combinaisonJoueur;
    public int messagePerteIa = 1;
    
   
    //* VARIABLE MODE DEFENSEUR
    
public String combinaisonSecreteJoueur = "";            
public int[] combinaisonIaModedef;           
public int combinaisonAleatoireIaAtt = 0;           
public int chanceUtiliseeIa = 2;            
public int combinaisonAleatoireModedef = 0;         
public int[] combinaisonIaAtt;            
public String combiJoueur = "";         
public String resultat = "";            
public String resetNouvelleCombi = "";          
public int nbretrs = 1;         
public String nouvelleCombinaisonIa = "";           
public int saisieJoueurDef = 0;            
public char[] tabDef;          
    


  

    /**
     * La methode genere une combinaison aleatoire pour l IA entre 0 et 9 chiffres.                  
     * La variable nbCombinaison determinera le nombre de chiffre que comportera la
     * combinaison La variable modeDev permet l'affichage de la combinaison si elle
     * est = a true .
     * Mode challenger et mode duel
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
     * Mode challenger et mode duel 
     */
    public void combinaisonVisibleIa() {
        System.out.println("\n");
        System.out.println("La combinaison de l'Ia à deviner est :");
        this.combinaisonAleatoireIa();

    }

    /**
     * La methode permet au joueur de saisir une combinaison de x chiffres et                       
     * d afficher la combinaison 
     * Mode challenger et mode duel
     */
    public void saisieJoueur() {

        System.out.println("\n");
        System.out.println("Veuillez tenter votre essai n°" + chanceUtiliseeJoueur++);

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
     * La methode compare la saisie du joueur a la combinaison aleatoire de l IA                          
     * Affichage des signes + - ou = indique au joueur si il a trouvé ou non la combinaison de l IA
     * Mode challenger et mode duel
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
     * doit ressaisir une combinaison ou si il a perdu. Cette methode est repetee
     * tant que le joueur trouve un resultat different de la combinaison de l IA et
     * dans la limite du nombre d essai parametre dans la variable nbEssai du                              
     * fichier de propriete
     * Mode challenger et mode duel 
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
     * Methode qui affiche le résultat de la combinaison aléatoire de l IA                             
     * quand la partie est terminee
     * Mode challenger et mode duel
     */

    public void affichageDeLaCombinaisonIa() {
        tentativePourTrouverLaCombinaisonDeIa();
        for (int z = 0; z < combinaisonIa.length; z++)
            System.out.print(combinaisonIa[z]);
        System.out.print(".");
        System.out.println("\n");

    }
 

    /**
     * La methode creee une combinaison aleatoire pour l IA entre 0 et 9 chiffres.
     * La variable nbCombinaison determinera le nombre de chiffre que
     * comportera la combinaison.
     * Mode defenseur et mode duel
     */
    public void creationCombinaisonAleatoireIa() {

        this.combinaisonIaAtt = new int[nbCombinaison];
        for (int i = 0; i < nbCombinaison; i++) {
            combinaisonIaAtt[i] = (int) (Math.random() * 9);

            System.out.print(combinaisonIaAtt[i]);

        }
    }
   
    /**
     * Méthode qui affiche la combinaison aleatoire de l IA 
     * Mode defenseur et mode duel
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
     * Mode defenseur et mode duel
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
     * Methode qui compare la combinaison aleatoire de l IA  a la 
     * combinaison secrete du joueur et affiche un resultat sous forme d'opérateur
     * Mode challenger et mode duel
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
     * Mode defenseur et mode duel
     */
    
    public void resultat() {
        if (combinaisonAleatoireIaAtt == saisieJoueurDef) {
            System.out.println("L'IA a trouvé la combinaison du joueur");
        } else if (combinaisonAleatoireIaAtt > saisieJoueurDef || combinaisonAleatoireIaAtt < saisieJoueurDef) {
            System.out.println("L'ia n'a pas trouvé la combinaison du joueur ");

        }

    }


    /**
     * Methode affichant le numero d essai pour l IA
     * Mode defenseur et mode duel
     */
    public void nouveauTourPourIa() {   
        
        System.out.println("\n");
        System.out.println("Essai n° " + chanceUtiliseeIa++ + " pour l'Ia");
    }
    
    /**
     * Methode qui va generer une nouvelle combinaison aleatoire pour l IA 
     * en fonction du resultat precedent
     * Mode defenseur et mode duel
     */
    
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
    
    /**
     * Methode affichant la nouvelle combinaison de l IA
     * Mode defenseur et mode duel
     */
    
    public void nouvelleCombinaisonIa() {
        System.out.println("L'IA propose la nouvelle combinaison " + nouvelleCombinaisonIa);
    }
    
    /**
     * Methode permettant au joueur de saisir sous forme d operateur pour modifier a la hausse ou
     * la baisse la nouvelle combinaison de l IA 
     * Mode defenseur et mode duel
     */
    
    public void saisieOperateur() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir le résultat sous forme d'opérateur");
        String resultat = sc.nextLine();
        System.out.println("Vous avez saisi " + resultat);

    }
    
    /**
     * Methode affichant si l' IA a trouve ou non la combinaison
     * du joueur
     * Mode defenseur et mode duel
     */
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
     * Mode defenseur 
     */
    
    
    public void tentativePourTrouverLaCombinaisonDuJoueur() {
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
            
    

      } while (combinaisonAleatoireIaAtt != saisieJoueurDef && nouvelleCombinaisonIa != combinaisonSecreteJoueur && nbretrs != nbEssai );
       
}  
    
    /**
     * Methode affichant si l IA a gagnee ou perdue
     * Mode defenseur et mode duel
     */
  
    
    public void victoireOuDefaite() {
        if (nouvelleCombinaisonIa == combinaisonSecreteJoueur) {
            System.out.println("Victoire pour l'IA");
        } else {
            System.out.println("Défaite pour l'IA");
        }

    }
    
    
   /**
    * Methode qui reinitialise les opérateurs saisies par le joueur dans la 
    * methode saisie operateur
    * Mode defenseur et mode duel
    */
   

    public void resetResultat() {

        this.resultat = "";

    }
    
    /**
     * Methode qui reinitialise la variable du nombre de tour a 1 
     * Mode defenseur et mode duel
     */

    public void resetnbTours() {

        this.nbretrs = 1;

    }
    
    /**
     * Methode qui reinitialise la variable de la combinaison secrete saisie par 
     * le joueur 
     * Mode defenseur et mode duel
     */
    
    public void resetcombinaisonSecrete() {
    combinaisonSecreteJoueur = "";
    }
    /**
     * Methode qui reinitialise la variable correspondant au nombre d essais qu'a utilises le joueur
     * Mode defenseur et mode duel
     */

    public void resetnouveauTour() {
        this.chanceUtiliseeJoueur = 0;
    }
    
    /**
     * Methode qui reinitialise la nouvelle combinaison aleatoire de l IA
     * Mode defenseur et mode duel
     */
    public void resetnouvelleCombinaisonIa() {
        nouvelleCombinaisonIa = "";  
    }
   
    /**
     * Methode reinitialisant la variable du nombre de chance utilisee pour le joueur
     * a 0 en fin de partie
     * Mode defenseur et mode duel
     */

    public void resetChanceUtiliseeJoueur() {
        this.chanceUtiliseeJoueur = 0;

    }

    /**
     * Methode reinitialisant la variable du nombre de tours pour le joueur a 0 en fin
     * de partie
     * Mode defenseur et mode duel
     */

    public void resetNbTour() {

        this.nombreDeTours = 0;

    }
    
    /**
     * Methode reinitialisant la variable a 1 pour l Ia en fin
     * de partie
     * Mode defenseur et mode duel
     */

    public void resetChanceUtiliseeIa() {
        this.chanceUtiliseeIa = 2;
        

    }


    /**
     * Methode demandant au joueur de saisir si il souhaite recommencer changer ou
     * quitter le jeu
     * Mode defenseur et mode duel
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
     * Methode qui permet au joueur en fonction du choix proposé et de sa saisie de
     * faire une nouvelle partie , de retourner dans le menu de selection des modes
     * de jeux ou de sortir du jeu
     * Mode defenseur et mode duel
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

    public Boolean getModeDev() {
        return modeDev;
    }

    public int getNbCombinaison() {
        return nbCombinaison;
    }

    public int getNbEssai() {
        return nbEssai;
    }

    public int[] getCombinaisonIa() {
        return combinaisonIa;
    }

    public char[] getTab() {
        return tab;
    }

    public int getSaisieJoueur() {
        return saisieJoueur;
    }

    public int getCombinaisonAleatoire() {
        return combinaisonAleatoire;
    }

    public int getNombreDeTours() {
        return nombreDeTours;
    }

    public int getChanceUtiliseeJoueur() {
        return chanceUtiliseeJoueur;
    }

    public int getMessagePerteDuJoueur() {
        return messagePerteDuJoueur;
    }

    public int[] getCombinaisonJoueur() {
        return combinaisonJoueur;
    }

    public int getMessagePerteIa() {
        return messagePerteIa;
    }

    public String getCombinaisonSecreteJoueur() {
        return combinaisonSecreteJoueur;
    }

    public int[] getCombinaisonIaModedef() {
        return combinaisonIaModedef;
    }

    public int getCombinaisonAleatoireIaDef() {
        return combinaisonAleatoireIaAtt;
    }

    public int getChanceUtiliseeIa() {
        return chanceUtiliseeIa;
    }

    public int getCombinaisonAleatoireModedef() {
        return combinaisonAleatoireModedef;
    }

    public int[] getCombinaisonIadef() {
        return combinaisonIaAtt;
    }

    public String getCombiJoueur() {
        return combiJoueur;
    }

    public String getResultat() {
        return resultat;
    }

    public String getResetNouvelleCombi() {
        return resetNouvelleCombi;
    }

    public int getNbretrs() {
        return nbretrs;
    }

    public String getNouvelleCombinaisonIa() {
        return nouvelleCombinaisonIa;
    }

    public int getSaisieJoueurDef() {
        return saisieJoueurDef;
    }

    public char[] getTabDef() {
        return tabDef;
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

    public void setCombinaisonIa(int[] combinaisonIa) {
        this.combinaisonIa = combinaisonIa;
    }

    public void setTab(char[] tab) {
        this.tab = tab;
    }

    public void setSaisieJoueur(int saisieJoueur) {
        this.saisieJoueur = saisieJoueur;
    }

    public void setCombinaisonAleatoire(int combinaisonAleatoire) {
        this.combinaisonAleatoire = combinaisonAleatoire;
    }

    public void setNombreDeTours(int nombreDeTours) {
        this.nombreDeTours = nombreDeTours;
    }

    public void setChanceUtiliseeJoueur(int chanceUtiliseeJoueur) {
        this.chanceUtiliseeJoueur = chanceUtiliseeJoueur;
    }

    public void setMessagePerteDuJoueur(int messagePerteDuJoueur) {
        this.messagePerteDuJoueur = messagePerteDuJoueur;
    }

    public void setCombinaisonJoueur(int[] combinaisonJoueur) {
        this.combinaisonJoueur = combinaisonJoueur;
    }

    public void setMessagePerteIa(int messagePerteIa) {
        this.messagePerteIa = messagePerteIa;
    }

    public void setCombinaisonSecreteJoueur(String combinaisonSecreteJoueur) {
        this.combinaisonSecreteJoueur = combinaisonSecreteJoueur;
    }

    public void setCombinaisonIaModedef(int[] combinaisonIaModedef) {
        this.combinaisonIaModedef = combinaisonIaModedef;
    }

    public void setCombinaisonAleatoireIaDef(int combinaisonAleatoireIaDef) {
        this.combinaisonAleatoireIaAtt = combinaisonAleatoireIaDef;
    }

    public void setChanceUtiliseeIa(int chanceUtiliseeIa) {
        this.chanceUtiliseeIa = chanceUtiliseeIa;
    }

    public void setCombinaisonAleatoireModedef(int combinaisonAleatoireModedef) {
        this.combinaisonAleatoireModedef = combinaisonAleatoireModedef;
    }

    public void setCombinaisonIadef(int[] combinaisonIadef) {
        this.combinaisonIaAtt = combinaisonIadef;
    }

    public void setCombiJoueur(String combiJoueur) {
        this.combiJoueur = combiJoueur;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public void setResetNouvelleCombi(String resetNouvelleCombi) {
        this.resetNouvelleCombi = resetNouvelleCombi;
    }

    public void setNbretrs(int nbretrs) {
        this.nbretrs = nbretrs;
    }

    public void setNouvelleCombinaisonIa(String nouvelleCombinaisonIa) {
        this.nouvelleCombinaisonIa = nouvelleCombinaisonIa;
    }

    public void setSaisieJoueurDef(int saisieJoueurDef) {
        this.saisieJoueurDef = saisieJoueurDef;
    }

    public void setTabDef(char[] tabDef) {
        this.tabDef = tabDef;
    }}
