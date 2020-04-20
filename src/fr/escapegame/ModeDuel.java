package fr.escapegame;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import fr.escapegame.propriete.ChargerPropriete;

/**
 * ModeDuel est la classe enfant de ModeDeJeux
 * 
 * @author Glairon Anthony
 * @version 1.0
 */
public class ModeDuel extends ModeDeJeux {

    public static final Logger LOGGER = Logger.getLogger(Launcher.class);

    /**
     * La methode compare la saisie du joueur a la combinaison aleatoire de l IA Un
     * signe + - ou = indique au joueur si il a trouve ou non la combinaison de l IA
     */


    /**
     * Methode qui indique si le joueur a gagne ou perdu
     */
 /*   public void resultatPourJoueur() {
        System.out.println("\n");
        if (getSaisieJoueur() == getCombinaisonAleatoire()) {
            System.out.print("Vous avez gagné, vous avez trouvé la combinaison de l' IA\n ");
            Launcher finDePartie = new Launcher();
            finDePartie.choixApresUneFinDePartie();
        } else if (getSaisieJoueur() < getCombinaisonAleatoire() || getSaisieJoueur() > getCombinaisonAleatoire())
            System.out.println("Vous n'avez pas trouvé la combinaison de l IA\n ");
    }*/

    /**
     * Methode indiquant le nombre de chance utilisees pour l IA
     */
  /*  public void nouveauTourPourIa() {
        System.out.println("\n");
        System.out.println("Essai n° " + getChanceUtiliseeIa() + " pour l'Ia");
    }*/

    /**
     * Methode generant une serie de nombre aleatoire compris dans un intervalle
     * maxi et min qui s integre dans la methode generationNouvelleCombinaisonIa
     * 
     * @param min
     * @param max
     * @return
     */
    /*public int generationNbreAletoire(int min, int max) {
        if (min == max)
            return min;
        Random random = new Random();
        return min + random.nextInt(max - min);
    }*/

    /**
     * Methode qui va generer une nouvelle combinaison aleatoire pour l IA en
     * fonction du resultat des operateurs saisi par le joueur dans la methode
     * saisiOperateur
     */
   /* public void generationNouvelleCombinaisonIa() {
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
    }*/

    /**
     * Methode affichant la nouvelle combinaison de l IA
     */
   /* public void nouvelleCombinaisonIa() {
        System.out.println("L'IA propose la nouvelle combinaison " + getNouvelleCombinaisonIa());
        setCombiIaConvertiEnString(getNouvelleCombinaisonIa());
    }*/

    /**
     * Methode permettant au joueur de saisir sous forme d operateur pour modifier a
     * la hausse ou la baisse la nouvelle combinaison de l IA Mode
     */
  /* public void saisieOperateur() {
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
    }*/

    /**
     * Methode affichant si l' IA a trouvee ou non la combinaison du joueur
     */
   /*public void nouvearesultat() {
        if (getNouvelleCombinaisonIa().equals(getCombinaisonSecreteJoueur())) {
            System.out.println("L'IA a trouvé la combinaison du joueur");
            System.out.println("Victoire pour l'IA");
            Launcher finDePartie = new Launcher();
            finDePartie.choixApresUneFinDePartie(); 
        } else if (!getNouvelleCombinaisonIa().equals(getCombinaisonSecreteJoueur())) {
            System.out.println("L'IA n'a pas trouvé la combinaison du joueur ");
        }
    }*/

    /**
     * Methode affichant que l'IA et le joueur ont perdus
     */
    public void defaiteIaEtJoueur() {
        if (!getNouvelleCombinaisonIa().equals(getCombinaisonSecreteJoueur()) && getNbretrs() != getNbEssai()) {
            System.out.println("Fin de partie, le joueur et l' IA ont perdus");
            System.out.print("La combinaison de l'IA est ");
            {
                for (int z = 0; z < getCombinaisonIa().length; z++)
                    if (getSaisieJoueur() != getCombinaisonAleatoire())
                        System.out.print(getCombinaisonIa()[z] + "\n");
            }
        }
    }

    /**
     * Methode qui permet de generer la partie Une boucle do while est executee tant
     * que le joueur ou l IA n a pas trouve la combinaison de son adversaire et tant
     * que le nombre d essais maximum n est pas atteint
     */
    public void TentativeDeTrouverLaCombinaisonEntreIaEtJoueur() {
        saisieCombinaisonSecreteJoueur();
        combinaisonIaSecrete();
        tourIa();
        propositionCombinaisonIa();
        comparaisonDeCombinaisonPourIa();
        resultatPourIa();
        tourJoueur();
        comparaisonDeCombinaison();
        resultatPourJoueur();
        do {
            nouveauTourPourIa();
            generationNouvelleCombinaisonIa();
            nouvelleCombinaisonIa();
            saisieOperateur();
            nouvearesultat();
            tourJoueur();
            comparaisonDeCombinaison();
            resultatPourJoueur();
            defaiteIaEtJoueur();
            nbretrs++;
           /* chanceUtiliseeIa++;*/
            /*getNbretrs();*/
        } while (!nouvelleCombinaisonIa.equals(combinaisonSecreteJoueur) && nbretrs != nbEssai);
    }

    public void jouer() {
        TentativeDeTrouverLaCombinaisonEntreIaEtJoueur();
    }
}