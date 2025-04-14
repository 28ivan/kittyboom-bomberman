package viewController;
import java.awt.Color;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import model.Seleccion;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PantallaInicio extends JFrame implements Observer{

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panel;
    private JPanel panel_1;
    private JPanel panel_2;
    private JTextArea txt;
    private Image backgroundImage;
    private Controlador controlador;
    private Map<String, Component> panelMap = new HashMap<>();
    private boolean notificationsEnabled=true;
    
    public PantallaInicio() {
    	initialize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Seleccion.getSeleccion().addObserver(this);   
        getControlador().actionPerformed(null);
        
    }
    private void initialize() {
    	setBounds(100, 100, 840, 520);
        setResizable(false);
        setLocationRelativeTo(null);
        addKeyListener(getControlador());
        setFocusable(true);
        requestFocus();
        setContentPane(getContentPane());
        setVisible(true);
        }

        
    public JPanel getContentPane() {
    	// Cargar imagen de fondo
        try {
            backgroundImage = ImageIO.read(getClass().getClassLoader().getResource("viewController/imagenes/back.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        contentPane = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        /////////////////////////////
        contentPane.add(getPanel_Bomberman(20)); // más arriba
        contentPane.add(getPanel_Bomberman(10));
        contentPane.add(getPanel()); // más al fondo
        
        
        contentPane.add(getTxt());
        panelMap.put("panel_1", panel_1);
        panelMap.put("panel_2", panel_2);
        panelMap.put("panel", panel);
        clearPane(10);
        clearPane(20);
        contentPane.setComponentZOrder((JPanel) panelMap.get("panel"), 2); // más al fondo
        contentPane.setComponentZOrder((JPanel) panelMap.get("panel_1"), 1);
        contentPane.setComponentZOrder((JPanel) panelMap.get("panel_2"), 0); // más arriba
        contentPane.revalidate();
        contentPane.repaint();
        return contentPane;
    }
    private JPanel getPanel_Bomberman(int BMSelec) {
    	int BM = BMSelec;
    	if (BMSelec==1||BMSelec==10) {
    		if (panel_1 == null) {
                panel_1 = new JPanel();
                panel_1.setOpaque(false);
                panel_1.setLayout(null); // Permite posicionar manualmente
                JLabel label;
                panel_1.setBounds(400, 155, 350, 327);
                label = crearImagen(BM,300, 327);
                if (label!=null) {
                	panel_1.add(label);
                }
                
            }
            return panel_1;
    	} else if(BMSelec==2||BMSelec==20) {
    		if (panel_2 == null) {
                panel_2 = new JPanel();
                panel_2.setOpaque(false);
                panel_2.setLayout(null); // Permite posicionar manualmente
                panel_2.setBounds(154, 155, 238, 327);
                JLabel label = crearImagen(BM,238, 327);
                //añadir gif?
                panel_2.add(label);
            }
            return panel_2;
    	}
		return null;
        
    }
    private JLabel crearImagen(int BM, int dir1, int dir2) {
    	// Cargar imagen
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("viewController/imagenes/bomber"+BM+".png"));
        Image image = icon.getImage().getScaledInstance(dir1, dir2, Image.SCALE_SMOOTH);
        // Crear JLabel con el tamaño original de la imagen
        JLabel label = new JLabel(new ImageIcon(image));
        label.setBounds(0, 0, dir1, dir2); // Ajustar tamaño
        return label;
    }

    private JPanel getPanel() {
        if (panel == null) {
            panel = new JPanel();
            panel.setOpaque(false);
            panel.setLayout(null); // Permite posicionar manualmente
            panel.setBounds(117, 20, 576, 160);
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("viewController/imagenes/title.png"));
            Image image = icon.getImage().getScaledInstance(576, 160, Image.SCALE_SMOOTH);
            // Crear JLabel con el tamaño original de la imagen
            JLabel label = new JLabel(new ImageIcon(image));
            label.setBounds(0, 0, 576, 160); // Ajustar tamaño
            panel.add(label);
            
        }
        return panel;
    }
	private JTextArea getTxt() {
		if (txt == null) {
			txt = new JTextArea();
	        txt.setText("Selecciona con ← y →\n Presiona <o>pciones");
	        txt.setForeground(Color.MAGENTA);
	        txt.setEditable(false); 
	        txt.setOpaque(false);
	        txt.setFocusable(false);
	        txt.setBorder(null);
	        txt.setFont(new Font("SansSerif", Font.PLAIN, 12));
	        txt.setBounds(686, 428, 160, 50); 
	        txt.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
	        txt.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
		}
		return txt;
	}

	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(o instanceof Seleccion) { //ESOBJETO CAMBIARLO------------------
			int BMSelec = (int) arg;
			if (notificationsEnabled) {
				switch (BMSelec) {
					case 0:
						setVisible(false);
						@SuppressWarnings("unused")
				    	Pantalla pantalla = new Pantalla(1,1);
			        case 1:
			        	setPane(BMSelec+1); 
			        	clearPane((BMSelec)*10);
			        	break;
			        case 2:
			        	setPane(BMSelec-1);
			        	clearPane((BMSelec)*10);
			        	break;
			        case 3:
			        	setVisible(false);
			        	@SuppressWarnings("unused")
				    	Pantalla pantalla1 = new Pantalla(2,1);
			        	break;
			        case 4:
			        	setEnabled(false);
			        	notificationsEnabled = false;
			        	@SuppressWarnings("unused")
				    	PantallaSeleccionTipoTablero pantalla2 = new PantallaSeleccionTipoTablero();
			        	break;
			        default:
				}
			} else {
				switch (BMSelec) {
					case 5:
						notificationsEnabled = true;
						setEnabled(true);
						setVisible(true); //esto lo hago para actualizar la pantalla de seleccion de personaje y que no se ponga la pantalla del eclipse
		        	break;
				}
			}
			
		}
	}
	private void setPane(int BMSelec) {
		JPanel panel = (JPanel) panelMap.get("panel_" + BMSelec);
		if(panel!=null) {
			for (Component comp : panel.getComponents()) {
			    if (comp instanceof JLabel) {
			        JLabel label = (JLabel) comp;
			        
			        // Cargar la nueva imagen
			        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("viewController/imagenes/bomber" + BMSelec + ".png"));
			        Image image = icon.getImage().getScaledInstance(238, 327, Image.SCALE_SMOOTH);
			        
			        // Cambiar la imagen del JLabel
			        label.setIcon(new ImageIcon(image));
			        break; // Termina el bucle después de encontrar el JLabel
			    }
			}
			// Añadir GIF
			ImageIcon originalGif = new ImageIcon(getClass().getClassLoader().getResource("viewController/imagenes/gif.gif"));
			Image scaledGif = originalGif.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
			ImageIcon gifIcon = new ImageIcon(scaledGif);
			JLabel gifLabel = new JLabel(gifIcon);
	        gifLabel.setName("gif");

	        // Centrar el gif horizontalmente respecto al ancho del panel
	        int gifWidth = gifIcon.getIconWidth();
	        int gifHeight = gifIcon.getIconHeight();
	        int x = (238 - gifWidth) / 2;
	        int y = 50 ;
	        gifLabel.setBounds(x, y, gifWidth, gifHeight);
	        panel.add(gifLabel);
	        panel.setComponentZOrder(gifLabel, panel.getComponentCount() - 1);
		}
		contentPane.revalidate();
        contentPane.repaint();
	}
	private void clearPane(int BMSelec) {
		JPanel panel = (JPanel) panelMap.get("panel_" + (BMSelec)/10);
		if(panel!=null) {
			for (Component comp : panel.getComponents()) {
			    if (comp instanceof JLabel) {
			        JLabel label = (JLabel) comp;
			        
			        // Cargar la nueva imagen
			        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("viewController/imagenes/bomber" + BMSelec + ".png"));
			        Image image = icon.getImage().getScaledInstance(180, 246, Image.SCALE_SMOOTH);
			        
			        // Cambiar la imagen del JLabel
			        
			        label.setIcon(new ImageIcon(image));
			        break; // Termina el bucle después de encontrar el JLabel
			    }
			}
			 for (Component comp : panel.getComponents()) {
		            if (comp.getName() != null && comp.getName().equals("gif")) {
		                panel.remove(comp);
		                
		                break;
		            }
	        }
		}
		contentPane.revalidate();
        contentPane.repaint();
	}	
		//CONTROLADOR
		private Controlador getControlador() {
			if (controlador == null) {
				controlador = new Controlador();
			}
			return controlador;
			
		}

		private class Controlador implements ActionListener,KeyListener {
			
			public void handleKeyPressed(int pKey) {
				if (pKey==KeyEvent.VK_LEFT) {
					Seleccion.getSeleccion().seleccionarBomberMan('l');
		        }
				else if (pKey==KeyEvent.VK_RIGHT) {
					Seleccion.getSeleccion().seleccionarBomberMan('r');
		        }
				else if (pKey==KeyEvent.VK_ENTER || pKey==KeyEvent.VK_SPACE) {
						Seleccion.getSeleccion().inicializarTablero();
				}
				else if (pKey==KeyEvent.VK_O) {
					Seleccion.getSeleccion().seleccionarTipoTablero();
				}
		    }

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				getControlador().handleKeyPressed(key);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		}
	}
