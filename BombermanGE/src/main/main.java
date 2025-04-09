package main;

import javax.swing.JFrame;

import model.Seleccion;
import model.Tablero;
import viewController.Pantalla;
import viewController.PantallaInicio;

public class main {
    public static void main(String[] args) {
    	//MODELO//
    	Seleccion.getSeleccion();
    	//VISTA//
    	@SuppressWarnings("unused")
    	PantallaInicio pantalla = new PantallaInicio();
    }
}