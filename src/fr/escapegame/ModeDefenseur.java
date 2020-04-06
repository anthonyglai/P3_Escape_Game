package fr.escapegame;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import fr.escapegame.propriete.ChargerPropriete;

    public class ModeDefenseur extends ModeDeJeux {
        
    public static final Logger LOGGER = Logger.getLogger(Launcher.class);

    /**
     * Introduction du mode defenseur
     */
    public void introduction() {
        System.out.println("Dans le mode defenseur, l'IA doit deviner votre combinaison secrète de " + getNbCombinaison() + " chiffre(s). ");
        System.out.println("L'IA a " + getNbEssai() + " essais. "+"\n");
        System.out.println("La partie commence\n");
    }

    /**
     * La methode creee une combinaison aleatoire pour l IA entre 0 et 9 chiffres.
     * La variable nbCombinaison determinera le nombre de chiffre que
     * comportera la combinaison
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
     * Methode qui affiche la combinaison aleatoire de l IA Mode defenseur
     */
    public void propositionCombinaisonIa() {
        System.out.println("Essai n° 1\n");
        System.out.print("l'IA propose la combinaison ");
        this.creationCombinaisonAleatoireIa();
    }

    /**
     * Methode pour que le joueur saisisse une combinaison
     * de x chiffres 
     */
    public void saisieCombinaisonSecreteJoueur() {
        Scanner scanner = new Scanner(System.in);
        Pattern combinaisonSecrete = Pattern.compile("[0-9]{" + getNbCombinaison() + "}");
        System.out.println("Veuillez saisir une combinaison secrète de " + getNbCombinaison() + " chiffres");
        while (!scanner.hasNext(combinaisonSecrete)) {
            if (scanner.hasNext()) {
                System.out.println("Erreur vous avez saisi " + scanner.next());
                System.out.println("Veuillez bien saisir " + getNbCombinaison() + " chiffres");
                String nbre = scanner.nextLine();
                setTabDef(nbre.toCharArray());
                setCombinaisonSecreteJoueur(getCombinaisonSecreteJoueur() + (nbre));
                System.out.println("Vous avez saisi la combinaison secrète " + getCombinaisonSecreteJoueur() +"\n");
            }
        }
        String nbre = scanner.nextLine();
        setTabDef(nbre.toCharArray());
        setCombinaisonSecreteJoueur(getCombinaisonSecreteJoueur() + (nbre));
        System.out.println("Vous avez saisi la combinaison secrète " + getCombinaisonSecreteJoueur() +"\n");
    }
    
    /**
     * Cette methode compare la combinaison aleatoire de l IA a la combinaison
     * secrete du joueur et affiche un resultat sous forme d operateur
     */
    public void comparaisonDeCombinaisonPourIa() {
        System.out.println("\n");
        System.out.print("Le resultat est ");
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
        System.out.println(getResultat() + "\n");
    }

    /**
     * Methode indiquant le nombre de chance utilisees pour l IA
     */
    public void resultatPourIa() {
        if (getCombinaisonAleatoireIaAtt() == getSaisieJoueurDef()) {
            System.out.println("L'IA a trouvé la combinaison du joueur");
        } else if (getCombinaisonAleatoireIaAtt() > getSaisieJoueurDef()
                || getCombinaisonAleatoireIaAtt() < getSaisieJoueurDef()) {
            System.out.println("L'IA n'a pas trouvé la combinaison du joueur");
        }
    }
    
    /**
     * Methode qui indique si le joueur a gagne ou perdu
     */
    public void resultatPourJoueur() {
        System.out.println("\n");
        if (getSaisieJoueur() == getCombinaisonAleatoire()) {
            System.out.print("Vous avez gagné, vous avez trouvé la combinaison ");
            choixApresUneFinDePartie();
        } else if (getSaisieJoueur() < getCombinaisonAleatoire() || getSaisieJoueur() > getCombinaisonAleatoire())
            System.out.println("Vous n'avez pas trouvé la combinaison \n ");        
    }

    /**
     * Methode indiquant le nombre de chance utilisees pour l IA
     */
    public void nouveauTourPourIa() {
        System.out.println("\n");
        System.out.println("Essai n° " + getChanceUtiliseeIa() + " pour l'Ia\n");
    }

    /**
     * Methode generant une serie de nombre aleatoire compris dans un intervalle maxi et min
     * qui s integre dans la methode generationNouvelleCombinaisonIa
     * @param min
     * @param max
     * @return
     */    
    public int generationNbreAletoire(int min, int max) {
        if (min == max)
            return min;
        Random random = new Random();
        return min + random.nextInt(max - min);
    }

    /**
     * Methode qui va generer une nouvelle combinaison aleatoire pour l IA en
     * fonction du resultat des operateurs saisi par le joueur dans la methode 
     * saisiOperateur
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
     * Methode affichant la nouvelle combinaison de l IA 
     */
    public void nouvelleCombinaisonIa() {
        System.out.println("L'IA propose la nouvelle combinaison " + getNouvelleCombinaisonIa());
        setCombiIaConvertiEnString(getNouvelleCombinaisonIa() +"\n");
    }

    /**
     * Methode permettant au joueur de saisir sous forme d operateur pour modifier a
     * la hausse ou la baisse la nouvelle combinaison de l IA Mode 
     */
    public void saisieOperateur() {
        Scanner scan = new Scanner(System.in);
        Pattern operateur = Pattern.compile("[+---=]");
        System.out.println("Veuillez saisir le résultat sous forme d'opérateur + - ou =");
        while (!scan.hasNext(operateur)) {
        if (scan.hasNext()) {
            System.out.println("Vous n'avez pas saisi des opérateurs ");
            System.out.println("Veuillez à nouveau saisir le résultat sous forme d'opérateur + - ou = ");
            String result = scan.nextLine();                     
            setResultat(result);
           }             
       }
            String result = scan.nextLine();                     
            System.out.println("Vous avez saisi " + result);
            setResultat(result);
        }

    /**
     * Methode affichant si l' IA a trouvee ou non la combinaison du joueur 
     */
    public void nouvearesultat() {
        if (getNouvelleCombinaisonIa().equals(getCombinaisonSecreteJoueur())) {
            System.out.println("L'IA a trouvé la combinaison du joueur");
            System.out.println("Victoire pour l'IA");
            choixApresUneFinDePartie();
        } else if (!getNouvelleCombinaisonIa().equals(getCombinaisonSecreteJoueur())) {
            System.out.println("L'IA n'a pas trouvé la combinaison du joueur");
        }
    }

    /**
     * Methode regroupant differentes methode dans une boucle do While qui
     * bouclera tant que la nouvelle combinaison aleatoire de l IA est differente de la
     * combinaison secrete du joueur et tant que le nombre de tour
     * est different du nombre d essai
     */
    public void tentativePourTrouverLaCombinaisonDuJoueur() {
        introduction();
        saisieCombinaisonSecreteJoueur();
        propositionCombinaisonIa();
        comparaisonDeCombinaisonPourIa();
        resultatPourIa();
        if(getCombinaisonAleatoireIaAtt() == getSaisieJoueurDef()) {
            choixApresUneFinDePartie();
        }     
        do {
            nouveauTourPourIa();
            generationNouvelleCombinaisonIa();
            nouvelleCombinaisonIa();
            saisieOperateur();
            nouvearesultat();
            setNbretrs(getNbretrs() + 1);
            setChanceUtiliseeIa(getChanceUtiliseeIa() + 1);
            getNbretrs();
        }  while (!getNouvelleCombinaisonIa().equals(getCombinaisonSecreteJoueur()) && getNbretrs() != getNbEssai());
    }

    /**
     * Methode qui affiche quand l IA a perdu
     */
    public void defaiteIa() {
        tentativePourTrouverLaCombinaisonDuJoueur();
        if (!getNouvelleCombinaisonIa().equals(getCombinaisonSecreteJoueur())) {
            System.out.print("Défaite pour l IA\n");
        }
    }
    
    /** Methode generant le deroulement du jeu */
    public void jouer() {
        tentativePourTrouverLaCombinaisonDuJoueur();
    }
}