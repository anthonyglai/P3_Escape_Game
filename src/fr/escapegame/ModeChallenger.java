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
            if (saisieJoueur == combinaisonAleatoireIa) {
                System.out.print("Vous avez gagn�, vous avez trouv� la combinaison de l' IA\n");
            } else if (saisieJoueur < combinaisonAleatoireIa || saisieJoueur > combinaisonAleatoireIa) {
                System.out.println("Vous n'avez pas trouv� la combinaison de l' IA\n");
            }
            nombreDeTour++;
        } while (saisieJoueur != combinaisonAleatoireIa && nombreDeTour != nbEssai);
        if (saisieJoueur != combinaisonAleatoireIa) {
            System.out.print("Vous avez perdu, la combinaison de l' IA est ");
        }
        for (int z = 0; z < combinaisonIa.length; z++) {
            if (saisieJoueur != combinaisonAleatoireIa) {
                System.out.print(combinaisonIa[z]);
            }
        }
    }

    public void jouer() {
       this.combinaisonIaSecrete();
       this.tentativePourTrouverLaCombinaisonDeIa();
    }
}