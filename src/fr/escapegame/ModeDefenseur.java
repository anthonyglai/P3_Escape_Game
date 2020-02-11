package fr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import fr.escapegame.propriete.ChargerPropriete;

public class ModeDefenseur extends ModeDeJeux {
    public String resultat = "";
    public String combiJoueur = "";
    public String nouvelleCombinaison = "";
    public int nbretrs = 1;
    public String resetNouvelleCombi = "";

    public void introduction() {
        System.out.println("\n");
        System.out.println("Bienvenue dans le mode Challenger.");
        System.out.println("Tu vas devoir affronter une IA et deviner sa combinaison secrète de " + getNbCombinaison()
                + " chiffres. ");
        System.out.println("Tu as " + getNbEssai() + " essais. ");
        System.out.println("La partie commence ");

    }

    public void creationCombinaisonAleatoireIa() {

        this.setCombinaisonIa(new int[getNbCombinaison()]);
        for (int i = 0; i < getNbCombinaison(); i++) {
            getCombinaison()[i] = (int) (Math.random() * 9);
            
                System.out.print(getCombinaison()[i]);

            }
        }

    

    public void combinaisonAleatoireIa() {
        System.out.println("\n");
        System.out.print("l'IA propose la combinaison :");
        this.creationCombinaisonAleatoireIa();

    }

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
        combiJoueur += (nb);
        System.out.println("Vous avez saisi " + combiJoueur);

    }

    public void comparaisonDeCombinaison() {

        System.out.println("\n");
        System.out.print("Le resultat est le suivant");
        for (int k = 0; k < getTab().length; k++) {
            setSaisieJoueur(Integer.parseInt(String.valueOf(getTab()[k])));
            setCombinaisonAleatoire(getCombinaisonIa()[k]);
            if (getCombinaisonAleatoire() == getSaisieJoueur()) {
                resultat += ("=");
            } else if (getCombinaisonAleatoire() > getSaisieJoueur()) {
                resultat += ("-");
            } else {
                resultat += ("+");

            }

        }
        System.out.println(resultat);
    }

    public void resultat() {
        comparaisonDeCombinaison();
        if (getCombinaisonAleatoire() == getSaisieJoueur()) {
            System.out.println("L'IA a trouvé la combinaison du joueur");
        } else if (getCombinaisonAleatoire() > getSaisieJoueur() || getCombinaisonAleatoire() < getSaisieJoueur()) {
            System.out.println("L'ia n'a pas trouvé la combinaison du joueur ");

        }

    }

    public void tentativePourTrouverLaCombinaisonDuJoueur() {
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
        setChanceUtilisee(getChanceUtilisee() + 1);
        System.out.println("Essai n° " + getChanceUtilisee());
    }

    public void nouvelleCombinaison() {
        System.out.println("L'IA propose la nouvelle combinaison " + nouvelleCombinaison);
    }

    public void saisieReponsePourIa() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir le résultat sous forme d'opérateur");
        String resultat = sc.nextLine();

        System.out.println("Vous avez saisi " + resultat);

    }

    public void nouvearesultat() {
        if (nouvelleCombinaison == resultat) {
            System.out.println("L'IA a trouvé la combinaison du joueur");
        } else if (getCombinaisonAleatoire() > getSaisieJoueur() || getCombinaisonAleatoire() < getSaisieJoueur()) {
            System.out.println("L'ia n'a pas trouvé la combinaison du joueur ");
        }
    }

    public void boucle() {
        saisieJoueur();
        combinaisonAleatoireIa();
        resultat();
        do {
            setNbEssai(getNbEssai());
            tentativePourTrouverLaCombinaisonDuJoueur();
            nouveauTour();
            nouvelleCombinaison();
            saisieReponsePourIa();
            nouvearesultat();

            nbretrs++;

        } while (nouvelleCombinaison != resultat && nbretrs != getNbEssai());
    }

    public void gagneOuDefaite() {
        if (nouvelleCombinaison == resultat) {
            System.out.println("Victoire pour l'IA");
        } else {
            System.out.println("Défaite pour l'IA");
        }

    }

    public void resetResultat() {

        resultat = "";

    }

    public void nbTours() {

        nbretrs = 0;

    }

    public void propositionApresUneFinDePartie() {
        System.out.println();
        System.out.println("Pour poursuivre veuillez choisir entre les 3 modes ci-dessous:");
        System.out.println();
        System.out.println("1- Recommencer une partie ");
        System.out.println("2- Changer de mode de jeu");
        System.out.println("3- Quitter le jeu");

    }

    public void choixApresUneFinDePartie() {
        Scanner sc = new Scanner(System.in);
        propositionApresUneFinDePartie();
        resetChanceUtilisee();
        resetResultat();
        nbTours();

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