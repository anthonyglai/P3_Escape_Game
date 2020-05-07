package fr.escapegame;

public class ModeDefenseur extends ModeDeJeux {

    /**
     * Methode regroupant differentes methode dans une boucle do While qui bouclera
     * tant que la nouvelle combinaison aleatoire de l IA est differente de la
     * combinaison secrete du joueur et tant que le nombre de tour est different du
     * nombre d essai
     */
    public void tentativePourTrouverLaCombinaisonDuJoueur() {
        this.saisieCombinaisonSecreteJoueur();
        this.propositionCombinaisonIa();
        this.comparaisonDeCombinaisonPourIa();
        this.resultatPourIa();
        if (combinaisonPourIa != combinaisonJoueur) { 
        do {
            this.nouveauTourPourIa();
            this.generationNouvelleCombinaisonIa();
            this.nouvelleCombinaisonIa();
            this.saisieOperateur();
            this.nouvearesultat();
            this.nbreDeTours++;
        } while (!nouvelleCombinaisonIa.equals(combinaisonSecreteJoueur) && nbreDeTours != nbEssai);
    }
}
       
    /** Methode generant le deroulement du jeu */
    public void jouer() {
        this.tentativePourTrouverLaCombinaisonDuJoueur();
        
    }
}
