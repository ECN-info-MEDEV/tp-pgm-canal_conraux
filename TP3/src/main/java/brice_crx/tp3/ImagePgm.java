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
     * @param w
     * @param h
     */
    public ImagePgm(int w,int h) {
        width = w;
        height = h;
        highestGreyLevel = 255;
        image = new ArrayList();
        List<Integer> list;
        for (int i = 0;i<height;i++) {
            list = new ArrayList();
            for (int j=0;j<width;j++) {
                list.add(0);
            }
            image.add(list);
        }
    }
    
    /**
     * crée une image pgm noire à partir des données contenu dans le fichier pgm
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
        for (int i = 0;i<height;i++) {
            list = new ArrayList();
            for (int j=0;j<width;j++) {
                list.add(file.read());
            }
            image.add(list);
        }
    }

    /**
     * renvoie la largeur de l'image
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * modifie la largeur de l'image
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * renvoie la hauteur de l'image
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * modifie la hauteur de l'image
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * renvoie le niveau de gris maximum de l'image (toujours 255)
     * @return
     */
    public int getHighestGreyLevel() {
        return highestGreyLevel;
    }

    /**
     * modifie le niveau de gris maximum de l'image (toujours 255)
     * @param highestGreyLevel
     */
    public void setHighestGreyLevel(int highestGreyLevel) {
        this.highestGreyLevel = highestGreyLevel;
    }

    /**
     * renvoie l'image en elle-même
     * @return
     */
    public List<List<Integer>> getImage() {
        return image;
    }

    /**
     * modifie l'image en elle-même
     * @param image
     */
    public void setImage(List<List<Integer>> image) {
        this.image = image;
    }
    
    
}
