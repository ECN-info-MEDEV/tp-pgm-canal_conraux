/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package brice_crx.tp3;

import java.io.FileNotFoundException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Représentation d'une image au format pgm
 *
 * @author conra
 */
public class ImagePgm {

    //Attributs
    /**
     * largeur de l'image
     */
    private int width;

    /**
     * hauteur de l'image
     */
    private int height;

    /**
     * niveau de gris maximum
     */
    private int highestGreyLevel;

    /**
     * représentation de l'image
     */
    private List<List<Integer>> image;

    //Méthodes
    /**
     * crée une image pgm noire de taille w et h
     *
     * @param w
     * @param h
     */
    public ImagePgm(int w, int h) {
        width = w;
        height = h;
        highestGreyLevel = 255;
        image = new ArrayList();
        List<Integer> list;
        for (int i = 0; i < height; i++) {
            list = new ArrayList();
            for (int j = 0; j < width; j++) {
                list.add(0);
            }
            image.add(list);
        }
    }

    /**
     * crée une image pgm noire à partir des données contenu dans le fichier pgm
     *
     * @param path
     */
    public ImagePgm(String path) {
        // Recupère le chemin du fichier
        path = System.getProperty("user.dir") + "/images/" + path;
        path = setPgmExtension(path);

        // Lecture du fichier pgm
        File file = new File(path);
        String line;
        String[] lineSplitted = null;
        try (Scanner myReader = new Scanner(file)) {
            // Traitement de l'entête
            myReader.nextLine();
            myReader.nextLine();

            lineSplitted = myReader.nextLine().split("\\s+");
            this.width = Integer.parseInt(lineSplitted[0]);
            this.height = Integer.parseInt(lineSplitted[1]);
            lineSplitted = myReader.nextLine().split("\\s+");
            this.highestGreyLevel = Integer.parseInt(lineSplitted[0]);
            ////////////////////////

            // Traitement des valeurs des pixels
            line = "";

            while (myReader.hasNextLine()) {
                line += myReader.nextLine() + " ";
                if (line.charAt(0) == ' ') {
                    line = line.substring(1, line.length());
                }
            }

            lineSplitted = line.split("\\s+");
            ////////////////////////////////////////////////////////
        
        }catch (IOException e){
            System.out.println("Le chemin du fichier ne mène à rien");
        }
        

        // Recupération des pixels dans le tableau
        int k = 0;

        this.image = new ArrayList();

        List<Integer> list;
        for (int i = 0; i < height; i++) {
            list = new ArrayList();
            for (int j = 0; j < width; j++) {
                list.add(Integer.parseInt(lineSplitted[k]));
                k++;
            }
            image.add(list);
        }
        ////////////////////////////
    }

    /**
     * renvoie la largeur de l'image
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * modifie la largeur de l'image
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * renvoie la hauteur de l'image
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * modifie la hauteur de l'image
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * renvoie le niveau de gris maximum de l'image (toujours 255)
     *
     * @return
     */
    public int getHighestGreyLevel() {
        return highestGreyLevel;
    }

    /**
     * modifie le niveau de gris maximum de l'image (toujours 255)
     *
     * @param highestGreyLevel
     */
    public void setHighestGreyLevel(int highestGreyLevel) {
        this.highestGreyLevel = highestGreyLevel;
    }

    /**
     * renvoie l'image en elle-même
     *
     * @return
     */
    public List<List<Integer>> getImage() {
        return image;
    }

   
    /**
     * Crée une nouvelle image pgm à partir de l'objet courant
     *
     * @param nom
     */
    public void WritePgmImage(String nom) {

        // Définition du chemin
        String path = System.getProperty("user.dir") + "/images/" + nom;
        path = setPgmExtension(path);

        // Création du fichier pgm
        try {
            File newImage = new File(path);
            if (newImage.createNewFile()) {
                System.out.println("Fichier créé : " + newImage.getName());
            } else {
                System.out.println("Le fichier existe déjà, on écrit par dessus.");
            }
        } catch (IOException e) {
            System.out.println("Une erreur a eut lieu lors de la création du fichier");
        }

        // Symbole de retour a la ligne selon l'os
        String retourAlaLigne;
        if (System.getProperty("os.name").equals("Linux")) {
            retourAlaLigne = "\n";
        } else {
            retourAlaLigne = "\r\n";
        }

        // Ecriture dans le fichier
        try {
            // Ecriture entête
            FileWriter imageWriter = new FileWriter(path);
            imageWriter.write("P2" + retourAlaLigne);
            imageWriter.write("#" + retourAlaLigne);
            imageWriter.write(this.width + "  " + this.height + retourAlaLigne);
            imageWriter.write("255" + retourAlaLigne);

            // Ecriture pixels
            String line = "";
            int i, j;

            for (int k = 0; k < this.width * this.height; k++) {
                i = k / this.width;
                j = k % this.width;

                if (line.length() > 45) {   // On rentre la ligne dans le fichier avant quelle ne soit trop longue
                    line += retourAlaLigne;
                    imageWriter.write(line);
                    line = "";
                }
                // On ajoute le prochain pixel à la ligne
                line += Integer.toString(this.getImage().get(i).get(j)) + "  ";

            }
            imageWriter.close();
        } catch (IOException e) {
            System.out.println("Une erreur a eu lieu lors de l'écriture dans le fichier");
        }
    }

    
    //Fonctions de seuillage...
    /**
     * Modifie l'image en rendant noirs les pixels dont la valeur est
     * strictement inférieure à la valeur seuil et blancs les autres
     *
     * @param seuilValue
     */
    public void seuil(int seuilValue) {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (this.image.get(i).get(j) < seuilValue) {
                    this.image.get(i).set(j, 0);
                } else {
                    this.image.get(i).set(j, 255);
                }
            }
        }
    }

    /**
     * Réalise la différence entre deux images de même dimension
     *
     * @param img
     */
    public void difference(ImagePgm img) {
        if (width == img.width && height == img.height) {
            List<List<Integer>> newImg = new ArrayList();
            for (int i = 0; i < height; i++) {
                List<Integer> ligne = new ArrayList();
                for (int j = 0; j < width; j++) {
                    int value = image.get(i).get(j) - img.image.get(i).get(j);
                    if (value < 0) {
                        ligne.add(0);
                    } else {
                        ligne.add(value);
                    }
                }
                newImg.add(ligne);
            }
            image = newImg;
        } else {
            System.out.println("Les tailles des images ne correspondent pas.");
        }
    }

    /**
     * Agrandit l'image d'un facteur multiplicateur
     * @param multiplicateur
     */
    public void agrandissement(int multiplicateur) {
        List<List<Integer>> newImg = new ArrayList();
        
        //Pour chaque ligne de l'image de base
        for (int i = 0; i < height; i++) {
            //On crée multiplicateur * la même ligne
            for (int l = 0; l < multiplicateur; l++) {
                List<Integer> row = new ArrayList();
                //Pour chaque colonne de l'image de base
                for (int j = 0; j < width; j++) {
                    //On crée multiplicateur * la même colonne
                    for (int k = 0; k < multiplicateur; k++) {
                        row.add(image.get(i).get(j));
                    }
                }
                newImg.add(row);
            }
        }
        
        //On modifie maintenant correctement l'image
        width *= multiplicateur;
        height *= multiplicateur;
        image = newImg;
    }
    
    /**
     *  Retrecit l'image d'un diviseur diviseur
     * @param diviseur
     */
    public void retrecissement(int diviseur) {
        List<List<Integer>> newImg = new ArrayList();
        
        //Création de la nouvelle image
        for (int i = 0; i<height; i+= diviseur) {
            List<Integer> row = new ArrayList();
            for (int j=0; j< width; j+=diviseur) {
                row.add(image.get(i).get(j));
            }
            newImg.add(row);
        }
        
        //Modification de l'image avec les bonnes valeurs
        height = (int)(height/diviseur);
        width = (int)(width/diviseur);
        image = newImg;
    }

    /**
     * Fonction statique qui vérifie que l'extension est bien .pgm
     *
     * @param url
     * @return
     */
    static String setPgmExtension(String url) {
        String troisDerniersChars = url.substring(url.length() - 3, url.length());

        if (!troisDerniersChars.equalsIgnoreCase("pgm")) {
            int indexPoint = url.indexOf(".");
            if (indexPoint != -1) {
                url = url.substring(0, indexPoint);
            }
            url += ".pgm";
        }

        return url;
    }
}
