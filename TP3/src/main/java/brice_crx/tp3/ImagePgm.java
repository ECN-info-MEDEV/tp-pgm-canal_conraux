/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package brice_crx.tp3;

import java.io.FileNotFoundException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
     * crée une image pgm à partir des données contenu dans le fichier pgm
     *
     * @param url
     * @throws java.io.FileNotFoundException
     */
    public ImagePgm(String url) throws FileNotFoundException, IOException {
        highestGreyLevel = 255;
        image = new ArrayList();

        //String url = "/home/thomas/Documents/GitHub/tp-pgm-canal_conraux/TP3/images/baboon.pgm";
        url = System.getProperty("user.dir") + "/images/" + url;

        // Lecture du fichier pgm
        FileReader file = new FileReader(url);

        int k;

        k = file.read();
        k = file.read();

        width = file.read();
        height = file.read();

        k = file.read();

        List<Integer> list;
        for (int i = 0; i < height; i++) {
            list = new ArrayList();
            for (int j = 0; j < width; j++) {
                list.add(file.read());
            }
            image.add(list);
        }
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
     * modifie l'image en elle-même
     *
     * @param image
     */
    public void setImage(List<List<Integer>> image) {
        this.image = image;
    }

    //Fonctions de seuillage...
    /**
     * Modifie l'image en rendant noirs les pixels dont la valeur est
     * strictement inférieure à la valeur seuil et blancs les autres
     *
     * @param seuilValue
     */
    public void seuil(int seuilValue) {
        for (List<Integer> line : image) {
            for (Integer value : line) {
                if (value < seuilValue) {
                    value = 0;
                } else {
                    value = 255;
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

}
