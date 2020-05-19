package fr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

/**
 * ModeChallenger est la classe enfant de ModeDeJeux
 */
public class ModeChallenger extends ModeDeJeux {

    public void tentativePourTrouverLaCombinaisonDeIa() {
        int nombreDeTour = 0;
        do {
            saisieJoueur();
            comparaisonDeCombinaison();
            if (saisieJoueur == combiIaComparesaisieJoueur) {
                System.out.print("Vous avez gagné, vous avez trouvé la combinaison de l' IA\n");
            } else if (saisieJoueur < combiIaComparesaisieJoueur || saisieJoueur > combiIaComparesaisieJoueur) {
                System.out.println("Vous n'avez pas trouvé la combinaison de l' IA\n");
            }
            nombreDeTour++;
        } while (saisieJoueur != combiIaComparesaisieJoueur && nombreDeTour != nbEssai);
        if (saisieJoueur != combiIaComparesaisieJoueur) {
            System.out.print("Vous avez perdu, la combinaison de l' IA est ");
        }
        for (int z = 0; z < combinaisonIaSecrete.length; z++) {
            if (saisieJoueur != combiIaComparesaisieJoueur) {
                System.out.print(combinaisonIaSecrete[z]);
            }
        }
    }

    public void jouer() {
        this.combinaisonIaSecrete();
        this.tentativePourTrouverLaCombinaisonDeIa();
    }
}