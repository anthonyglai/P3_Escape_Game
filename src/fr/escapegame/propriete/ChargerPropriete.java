package fr.escapegame.propriete;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ChargerPropriete {

	private static String NB_COMBINAISON_NAME = "application.nb.combinaison";
	private static String NB_ESSAI_NAME = "application.nb.nbreEssai";
	private static String MODE_DEV_NAME = "application.modeDev";

	public static int NB_COMBINAISON;
	public static int NB_ESSAI;
	public static boolean MODE_DEV;
	private static String FILE_NAME = "config.properties";

	static {

		Properties property = new Properties();
		FileInputStream fs;
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
