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
        do {
            saisieJoueur();
            comparaisonDeCombinaison();
            if (saisieJoueur == combinaisonAleatoire) {
                System.out.print("Vous avez gagné, vous avez trouvé la combinaison de l' IA\n");
            } else if (saisieJoueur < combinaisonAleatoire || saisieJoueur > combinaisonAleatoire) {
                System.out.println("Vous n'avez pas trouvé la combinaison de l' IA\n");
            }
            nombreDeTours++;
        } while (saisieJoueur != combinaisonAleatoire && nombreDeTours != nbEssai);
        if (saisieJoueur != combinaisonAleatoire) {
            System.out.print("Vous avez perdu, la combinaison de l' IA est ");
        }
        for (int z = 0; z < combinaisonIa.length; z++) {
            if (saisieJoueur != combinaisonAleatoire) {
                System.out.print(combinaisonIa[z]);
            }
        }
    }

    public void jouer() {
       this.combinaisonIaSecrete();
       this.tentativePourTrouverLaCombinaisonDeIa();
    }
}