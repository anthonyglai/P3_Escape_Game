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

    /**
     * Methode qui permet de generer la partie Une boucle do while est executee tant
     * que le joueur ou l IA n a pas trouve la combinaison de son adversaire et tant
     * que le nombre d essais maximum n est pas atteint
     */
    public void TentativeDeTrouverLaCombinaisonEntreIaEtJoueur() {
        this.saisieCombinaisonSecreteJoueur();
        this.combinaisonIaSecrete();
        this.tourIa();
        this.propositionCombinaisonIa();
        this.comparaisonDeCombinaisonPourIa();
        this.resultatPourIa();
        if (combinaisonPourIa != combinaisonJoueur) {                    
        this.tourJoueur();
        this.saisieJoueur();
        this.comparaisonDeCombinaison();
        this.resultatPourJoueur();
        if (saisieJoueur != combinaisonAleatoireIa) {               
            while (!nouvelleCombinaisonIa.equals(combinaisonSecreteJoueur) && saisieJoueur != combinaisonAleatoireIa && nbreDeTours != nbEssai) {           
            this.nouveauTourPourIa();
            this.generationNouvelleCombinaisonIa();
            this.nouvelleCombinaisonIa();
            if (!nouvelleCombinaisonIa.equals(combinaisonSecreteJoueur)){
            this.saisieOperateur();
            this.nouvearesultat();        
            this.tourJoueur();
            this.saisieJoueur();
            this.comparaisonDeCombinaison();
            this.resultatPourJoueur();
            this.defaiteIaEtJoueur();
            this.nbreDeTours++;         
            }
         }
      }
   }
}
        
    public void jouer() {
        this.TentativeDeTrouverLaCombinaisonEntreIaEtJoueur();
    }
}