package barraMenu2;

import java.awt.BorderLayout;
import java.awt.Container;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Menu {
	
	private Container contenedor; // Declaramos el Contenedor
	private JFrame ventana;//// Declaramos el ventana
	private JPanel panel1;
	private JMenuBar mb; // Barra de Menus
	private JMenu archivo; // Elemento del menu
	private JMenuItem abrir; // submenus
	private JMenuItem guardar;
	private JMenuItem salir;	
	private JTextArea areaTexto; // Area de texto
	private int selecciona = -1; 
	
	public void crearElementos() {
		
		contenedor = new Container(); // Creamos contenedor
		ventana = new JFrame(); //creamos la ventana
		
		panel1 = new JPanel(); // se Creael el JPanel
		
		mb = new JMenuBar(); // se Crea la Barra de Menus
		mb.setFont(new Font ( "Arial", Font.PLAIN , 20) );//Estilo
		
		
		archivo = new JMenu("Archivo");// se Crea Elemento del menu
		archivo.setFont(new Font ( "Arial", Font.PLAIN , 16) ); // Estilo de fuente
		guardar = new JMenuItem("Guardar");
		guardar.setFont(new Font ( "Arial", Font.PLAIN , 16) );
		abrir = new JMenuItem("Abrir");
		abrir.setFont(new Font ( "Arial", Font.PLAIN , 16) );
		salir = new JMenuItem("salir");
		salir.setFont(new Font ( "Arial", Font.PLAIN , 16) );
		
		
		areaTexto = new JTextArea(10, 40); // Inicializamos Areas Siempre hay que pasarle las medidas
		areaTexto.setLineWrap(true); // para que el texto se ajuste al area
		areaTexto.setWrapStyleWord(true); //permite que no queden palabras incompletas al hacer el salto de linea
		 
	}
	
	public void colocar() {
		
		contenedor= ventana.getContentPane(); // Insertamos  la ventana dentro del contenedor

		archivo.add(abrir); // se añaden los items al menu
		archivo.add(guardar);
		archivo.add(salir);
		
		mb.add(archivo); // Se añade a la barra de menu el menu de archivos
		mb.setVisible(true);
		
		
		
		ventana.setTitle("Menu"); // colocamos un titulo a la ventana
		ventana.setSize(500,300); // Tmanaño de la ventana
		ventana.setResizable(true);
		ventana.setJMenuBar(mb); // para insertar la barra de menu
		ventana.setVisible(true); // Metodo para hacer visible la ventana
		ventana.add(mb, BorderLayout.NORTH); // Añadimos a la ventana
		
		panel1.add(areaTexto, BorderLayout.CENTER); // Se añade el area de texto al panel 
		
		
		contenedor.add(panel1); // Se añade el panel al contenedor
		
		
		/*
		 * Para un swicht hay que utilizar el e.getActionCommand();
		 */
		
	}
	/*
	 * Registrador de eventos
	 */
	public void registraEventos () {
		abrir.addActionListener(new Eventos());
		guardar.addActionListener(new Eventos());
		salir.addActionListener(new Eventos());
	}
	
	
	class Eventos implements ActionListener {
		JFileChooser fc = new JFileChooser();
		File archivoElegido;
		
		/*
		 * Ni puta idea
		 */
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			
			String  pulsar = e.getActionCommand(); 
			
			switch(pulsar) {
			
			case "abrir":
				abrirArchivo();
				break;
				
			case "guardar":
				guardarArchivo();
				break;
				
			case "salir":
				System.exit(0); // Cerrar ventana
				
				break;
	
				
			}
				
		}
		public void abrirArchivo() {

			/*
			 *Con esta funcion abrirmos el cuadro de dialogo en el va aparecer
			 * 
			 */
			selecciona = fc.showOpenDialog(areaTexto);
			if(selecciona == JFileChooser.APPROVE_OPTION) {
				 File archivoElegido = fc.getSelectedFile();
				 try {
						
						String texto = "";
						/*
						 * Buffer para leer linea a linea dentro le pasamos la referencia del archivo
						 */
						
						BufferedReader recuperar = new BufferedReader(new FileReader(archivoElegido));
						
						texto = recuperar.readLine(); // Leemos y lo almacenamos en la variable
						areaTexto.append(texto); // Metodo añade al areaTexto
						while(texto != null) { // Mientras en la variable haya contenido
							texto = recuperar.readLine();
							areaTexto.append(texto); // Append añade unaa cadena alfinal  del area de texto
						}
						
						
						recuperar.close(); // Cerramos la conexion
						
						
						
					}catch (Exception e1) {
						JOptionPane.showMessageDialog(null,
								 "Su archivo no se ha guardado",
								 "Advertencia",JOptionPane.WARNING_MESSAGE);
					}
			}
	
		} /// abrir archivo
		/*
		 * Guarda lo que hay escrito en el area de texto en un fichero
		 */
		public void guardarArchivo() {
			 selecciona = fc.showSaveDialog(areaTexto);
			 if( selecciona == JFileChooser.APPROVE_OPTION) {
					/*
					 * getSelect... no guarda la referencia del archivo en el que vamos a guardar
					 * el texto
					 */
				archivoElegido = fc.getSelectedFile();
				//Creamo el objeto FileWriter pasandole la referencia del archivo seleccionado
				if(selecciona == JFileChooser.APPROVE_OPTION) {
					try {
						//Creamos el FileWriter para poder Escribir el fichero
						FileWriter escribir = null;
						
						
						escribir = new FileWriter(archivoElegido);
						//Escribimos en archivo archivo
						escribir.write(areaTexto.getText());
						
						
						//Cerramos el flujo
						escribir.close();
						
						 JOptionPane.showMessageDialog(null,
				 					"El archivo se a guardado Exitosamente",
				 					"Informaci�n",JOptionPane.INFORMATION_MESSAGE);
						
					}catch (IOException e1) {
						JOptionPane.showMessageDialog(null,
								 "Su archivo no se ha guardado",
								 "Advertencia",JOptionPane.WARNING_MESSAGE);
				}
				
			}

			}
		}//guardarArchivos
	
	}/// Eventos
	public Menu() {
		crearElementos();
		colocar();
		registraEventos();
		
	}
	
	

	public static void main(String[] args) {
		new Menu();
	}

}
