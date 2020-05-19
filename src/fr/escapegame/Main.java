package fr.escapegame;


import org.apache.log4j.PropertyConfigurator;

public class Main{



	public static void main(String[] args) {
		PropertyConfigurator.configure("src/main/ressources/log4j.properties");
		Launcher jeu = new Launcher();
		jeu.parametrage();
		jeu.presentationDesModes();
		jeu.ModeDeJeux();
	}
}
