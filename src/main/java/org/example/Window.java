package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class Window extends JFrame {

    public Window() {

        try {
            File url = new File("src/main/resources/image.jpg");
             BufferedImage image = null;
            {
                try {
                    image = ImageIO.read(url);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            this.setBounds(0,0,image.getWidth(),image.getHeight());

            this.setLocationRelativeTo(null);
            this.setLayout(null);


           // this.setResizable(false);
            this.add(new GradientPanel());
            this.setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



//    public BufferedImage process (BufferedImage bufferedImage){
//        BufferedImage processed = null;
//        try {
//             processed = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
//            for (int i = 0; i < bufferedImage.getHeight()-1; i++) {
//                for (int j = 0; j < bufferedImage.getWidth() - 1; j++) {
//                    processed.setRGB(j, i, bufferedImage.getRGB(bufferedImage.getWidth() - j - 1 , i));
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            System.out.println("s");
//        }
//        return  processed;
//    }

}
