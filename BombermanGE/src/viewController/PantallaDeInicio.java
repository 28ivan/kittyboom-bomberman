
	package viewController;

	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	public class PantallaDeInicio extends JFrame {

	    private String tipoPantalla;
	    private String colorBomberMan; 

	    public PantallaDeInicio() {
	        setTitle("BomberMan - Configuraci�n");
	        setSize(400, 300);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(new GridLayout(5, 1)); 

	       //TIPO DE PANTALLA
	        JLabel lblPantalla = new JLabel("Selecciona el tipo de pantalla:");
	        String[] tiposPantalla = {"Classic", "Soft", "Empty"};
	        JComboBox<String> comboPantalla = new JComboBox<>(tiposPantalla);
	        comboPantalla.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                tipoPantalla = (String) comboPantalla.getSelectedItem();
	            }
	        });

	        // color de BomberMan
	        JLabel lblColor = new JLabel("Selecciona el color de BomberMan:");
	        String[] coloresBomberMan = {"Blanco", "Negro"};
	        JComboBox<String> comboColor = new JComboBox<>(coloresBomberMan);
	        comboColor.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                colorBomberMan = (String) comboColor.getSelectedItem();
	            }
	        });

	        // Bot�n para iniciar el juego
	        JButton btnIniciar = new JButton("Iniciar Partida");
	        btnIniciar.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JOptionPane.showMessageDialog(null, "Has seleccionado:\n"
	                        + "Pantalla: " + tipoPantalla + "\n"
	                        + "BomberMan: " + colorBomberMan);

	                // Cierra la pantalla de inicio
	                dispose();

	                // Inicia el juego con las opciones seleccionadas
	                iniciarJuego(tipoPantalla, colorBomberMan);
	            }
	        });

	        
	        add(lblPantalla);
	        add(comboPantalla);
	        add(lblColor);
	        add(comboColor);
	        add(btnIniciar);

	        setVisible(true);
	    }

	    private void iniciarJuego(String tipoPantalla, String colorBomberMan) {
	        System.out.println("Iniciando partida...");
	        System.out.println("Pantalla seleccionada: " + tipoPantalla);
	        System.out.println("BomberMan seleccionado: " + colorBomberMan);

	       
//	        if (tipoPantalla.equals("Classic")) {
//	            new ClassicScreen(colorBomberMan);
//	        } else if (tipoPantalla.equals("Soft")) {
//	            new SoftScreen(colorBomberMan);
//	        } else if (tipoPantalla.equals("Empty")) {
//	            new EmptyScreen(colorBomberMan); 
//	        }
	    }

	    public static void main(String[] args) {
	        EventQueue.invokeLater(() -> {
	            new PantallaDeInicio();
	        });
	    }
	}

