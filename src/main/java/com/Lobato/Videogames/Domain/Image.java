package com.Lobato.Videogames.Domain;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Image implements IResource {

    private  String videogameName ;
    private BufferedImage image2D;

    public Image(String videogameName, BufferedImage image2D) {
        this.videogameName = videogameName;
        this.image2D = image2D;
    }

    @Override
    public String  createUrl(String videogameName){
        return "../image/"+videogameName+".jpg";
    }


    @Override
    public boolean saveResource() {
        String format  = "jpg";
        File file = new File(createUrl(this.videogameName));
        try{
            ImageIO.write(this.image2D,format,file);
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String loadResource() {
        return "";
    }
}
