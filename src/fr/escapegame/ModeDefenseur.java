package fr.escapegame;

import java.util.InputMismatchException;
import java.util.Random;
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
        System.out.println("Une IA va devoir deviner ta combinaison secrète de " + getNbCombinaison() + " chiffres. ");
        System.out.println("L'IA a " + getNbEssai() + " essais. ");
        System.out.println("La partie commence ");

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
     * Méthode qui affiche la combinaison aleatoire de l IA Mode defenseur
     */

    public void propositionCombinaisonIa() {
        System.out.println("\n");
        System.out.println("Essai n° 1");
        System.out.print("l'IA propose la combinaison :");
        this.creationCombinaisonAleatoireIa();

    }

    /**
     * Methode pour que le joueur saisisse une combinaison de x chiffres en du
     * parametrage de la variable nbCombinaison
     */

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
     * Methode affichant le numero d essai pour l IA Mode defenseur et mode duel
     */

    public void nouveauTourPourIa() {

        System.out.println("\n");
        System.out.println("Essai n° " + getChanceUtiliseeIa() + " pour l'Ia");
    }

    /**
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
        if (getNouvelleCombinaisonIa().equals(getCombinaisonSecreteJoueur())) {
            System.out.println("L'IA a trouvé la combinaison du joueur");
            System.out.println("Victoire pour l'IA");
            choixApresUneFinDePartie();
        } else if (!getNouvelleCombinaisonIa().equals(getCombinaisonSecreteJoueur())) {
            System.out.println("L'IA n'a pas trouvé la combinaison du joueur ");

        }

    }

    /**
     * Methode regroupant les methodes ci-dessus dans une boucle do While qui
     * bouclera tant que la combinaison aleatoire de l IA est differente de la
     * combinaison secrete du joueur et tant que la nouvelle combinaison de l IA est
     * differente de la combinasion secrete du joueur et tant que le nombre de tour
     * est different du nombre d essai parametre
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
        if (!getNouvelleCombinaisonIa().equals(getCombinaisonSecreteJoueur())) {
            System.out.print("Défaite pour l IA");
        }
    }
    
     /** Methode demandant au joueur de saisir si il souhaite recommencer changer ou
     * quitter le jeu
     */

    public void propositionApresUneFinDePartie() {
        defaiteIa();
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

    /** Methode generant le deroulement du jeu */

    public void jouer() {

        tentativePourTrouverLaCombinaisonDuJoueur();
        choixApresUneFinDePartie();
    }

}