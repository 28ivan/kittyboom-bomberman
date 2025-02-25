package viewController;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        // JFrame
        JFrame frame = new JFrame("BomberMan - Pantalla Classic");

        //meto la pantalla classic
        PantallaClassic pantallaClassic = new PantallaClassic();
        frame.add(pantallaClassic);

        // Configuración del JFrame
        frame.setSize(850, 550); // Tamaño de la ventana (ajustable)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana
        frame.setResizable(false); // Hacer que la ventana no sea redimensionable
        frame.setVisible(true); // Mostrar la ventana
    }
}