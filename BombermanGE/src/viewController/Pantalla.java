package viewController;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Pantalla extends JFrame {

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Pantalla frame = new Pantalla();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Pantalla() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        // FONDO
        JPanel contentPane = new JPanel() {
            private Image backgroundImage;

            {
                try {
                    //  viewController.imagenes
                    backgroundImage = ImageIO.read(getClass().getClassLoader().getResource("viewController/imagenes/stageBack3.png"));
                } catch (IOException e) {
                   
                    e.printStackTrace();
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Dibujar la imagen de fondo 
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

     
        contentPane.setLayout(new GridLayout(11, 17, 0, 0));
        setContentPane(contentPane);

      
        for (int i = 0; i < 11; i++) { // Filas
            for (int j = 0; j < 17; j++) { 
            	contentPane.add(new JLabel());            	
            }
        }
    }
}