package fr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

/**
 * ModeChallenger est la classe enfant de ModeDeJeux
 * 
 * @author Glairon Anthony
 * @version 1.0
 */

public class ModeChallenger extends ModeDeJeux {
    public static final Logger LOGGER = Logger.getLogger(Launcher.class);

    public void jouer() {

        /**
         * La methode affiche ou non la combinaison aleatoire de l IA si la variable
         * modeDev dans la methode combinaisonAleatoireIa est true Mode challenger et
         * mode duel
         */
        this.combinaisonIaSecrete();

        /**
         * Methode retournant au joueur en fonction de sa saisie si il a trouve ou non
         * la combinaison Cette methode est repetee tant que le joueur trouve un
         * resultat different de la combinaison de l IA et dans la limite du nombre d
         * essai mode chalenger et duel
         */
        this.messageFinDePartie();

    }
}
