/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package brice_crx.tp3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author conra
 */
public class ImagePgm {
    private int width;
    private int height;
    private int highestGreyLevel;
    private List<List<Integer>> image;
    
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHighestGreyLevel() {
        return highestGreyLevel;
    }

    public void setHighestGreyLevel(int highestGreyLevel) {
        this.highestGreyLevel = highestGreyLevel;
    }

    public List<List<Integer>> getImage() {
        return image;
    }

    public void setImage(List<List<Integer>> image) {
        this.image = image;
    }
    
    
}
