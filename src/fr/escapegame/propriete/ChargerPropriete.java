package fr.escapegame.propriete;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class ChargerPropriete {
	

	/*
	 * Definition des constantes du fichier de propriete
	 */
	final static String NB_COMBINAISON_NAME = "application.longueur.combinaison";
	final static String NB_ESSAI_NAME = "application.nb.nbreEssai";
	final static String MODE_DEV_NAME = "application.modeDev";

	/*
	 * Definition des constantes
	 */
	public static int NB_COMBINAISON;
	public static int NB_ESSAI;
	public static boolean MODE_DEV;
	private static String FILE_NAME = "config.properties";

	static {
		/*
		 * 1) Importation de la classe Properties (creation du fichier vide)
		 * 2) Importation de la FileInputStream (ouvre une connexion e un fichier)
		 * 3) Property.load(fs) Lit la liste de propriete e partir de FileInputStream
		 * 4) 3 constante = getproperty va rechercher les proprietes de la constante 
		 */
		Properties property = new Properties();	FileInputStream fs;
		try {
			System.out.println("Chargement du fichier: " + System.getProperty("user.dir") + File.separator + FILE_NAME);
			fs = new FileInputStream(System.getProperty("user.dir") + File.separator + FILE_NAME);
			property.load(fs);
			NB_COMBINAISON = Integer.valueOf(property.getProperty(NB_COMBINAISON_NAME));
			NB_ESSAI = Integer.valueOf(property.getProperty(NB_ESSAI_NAME));
			MODE_DEV = Boolean.valueOf(property.getProperty(MODE_DEV_NAME));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
