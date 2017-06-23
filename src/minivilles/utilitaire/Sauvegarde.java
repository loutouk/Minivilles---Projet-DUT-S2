package minivilles.utilitaire;

import minivilles.metier.Metier;

import java.io.*;

/**
 * Created by louis on 23/06/17.
 */
public class Sauvegarde {
    private static Sauvegarde ourInstance = new Sauvegarde();

    public static Sauvegarde getInstance() {
        return ourInstance;
    }

    private Sauvegarde() {
    }


    public Metier charger(){

        ObjectInputStream ois = null;

        try {

            final FileInputStream fichier = new FileInputStream("sauvegarde/save");
            ois = new ObjectInputStream(fichier);
            Metier m = (Metier) ois.readObject();
            return m;

        } catch (final java.io.IOException e) {

            // Fichier non present
            //e.printStackTrace();
            //System.out.println("Aucun fichier de sauvegarde");

        } catch (final ClassNotFoundException e) {

            //e.printStackTrace();

        } finally {

            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                //ex.printStackTrace();
            }
        }

        return null;
    }

    public boolean sauvegarder(Metier metier){

        File dossier = new File("sauvegarde");
        // creation du dossier si il n'existe pas
        if (!dossier.exists()) {
            // System.out.println("creation répertoire: " + theDir.getName());
            try {
                dossier.mkdir();
            } catch (SecurityException se) {
                //Debogage
            }
        }

        // Sérialisation
        File fichier = new File("sauvegarde/save");

        // ouverture d'un flux sur un fichier
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(fichier));
        } catch (IOException e) {
            //e.printStackTrace();
        }

        // sérialization de l'objet, sauvegarde
        try {
            oos.writeObject(metier);
            return true;
        } catch (IOException e) {
            //e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (final IOException ex) {
                //ex.printStackTrace();
            }
        }

        return false;
    }
}
