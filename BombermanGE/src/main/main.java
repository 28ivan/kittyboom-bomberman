package main;

import javax.swing.JFrame;

public class main {
    public static void main(String[] args) {
        // JFrame
        JFrame frame = new JFrame("BomberMan - Pantalla Classic");

        //meto la pantalla classic

        // Configuraci�n del JFrame
        frame.setSize(850, 550); // Tama�o de la ventana (ajustable)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicaci�n al cerrar la ventana
        frame.setResizable(false); // Hacer que la ventana no sea redimensionable
        frame.setVisible(true); // Mostrar la ventana
    }
}