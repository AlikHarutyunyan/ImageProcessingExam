package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class GradientPanel extends JPanel {
    public GradientPanel(){
        this.setLayout(null);
        File url = new File("src/main/resources/image.jpg");
        try {
            BufferedImage image = ImageIO.read(url);
            this.setBounds(0,0,image.getWidth(),image.getHeight());
            LinkedHashMap<Integer,Integer> pixelColor = new LinkedHashMap<>();
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                   int color = image.getRGB(i,j);
                   if(pixelColor.containsKey(color)){
                       pixelColor.put(color,pixelColor.get(color)+1);
                   }else{
                       pixelColor.put(color,1);
                   }
                }
            }

            for (int i = 0; i < 100; i++) {
                Map.Entry<Integer, Integer> entryWithMaxValue = null;
                for (Map.Entry<Integer, Integer> entry : pixelColor.entrySet()) {
                    if (entryWithMaxValue == null || entry.getValue() > entryWithMaxValue.getValue()) {
                        entryWithMaxValue = entry;
                    }
                }
                
                if (entryWithMaxValue != null) {
                    int keyToRemove = entryWithMaxValue.getKey();
                    for (int j = 0; j < image.getWidth(); j++) {
                        for (int k = 0; k < image.getHeight(); k++) {
                            if(image.getRGB(j,k) == keyToRemove){
                                image.setRGB(j,k,new Color(255,255,255).getRGB());
                            }
                        }
                    }

                    pixelColor.remove(keyToRemove);
                    System.out.println("Removed entry with key: " + keyToRemove);
                }
            }
            JLabel label = new JLabel(new ImageIcon((resizeImage(image,1200,800))));
            label.setBounds(0,0,1200,800);
            this.add(label);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        this.setVisible(true);
    }

   public  BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }
}
