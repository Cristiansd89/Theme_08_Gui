package ReadFile;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ExtraerTexto {
	
	/*
	 * Atributos
	 */
	
	private Container contenedor; // Declaramos el Contenedor
	private JFrame ventana; //// Declaramos el ventana
	private JPanel panel1; // Declaramos el panel
	private JButton abrir; // Declaramos los botones
	private JButton mostrar; 
	private JTextArea areaTexto; // Declaramos el area de Texto
	private int selecciona = -1; //Declaramos la variable de confirmacion
	private File archivoElegido; 	// Declaramos el el archivo que vamos abrir

	public ExtraerTexto() {
		crearComponentes();
		colocar();
		registarEventos();
	}
	
	/*
	 * Metodo para crear componentes
	 */
	
	public void crearComponentes() {
		
		contenedor = new Container();
		ventana = new JFrame();
	
		panel1 = new JPanel();
		
		abrir = new JButton();
		mostrar = new JButton();
		
		areaTexto = new JTextArea(10, 40); // Inicializamos Areas Siempre hay que pasarle las medidas
		areaTexto.setLineWrap(true); // para que el texto se ajuste al area
		areaTexto.setWrapStyleWord(true); //permite que no queden palabras incompletas al hacer el salto de linea
		
		
	
		
	}
	public void colocar() {
		contenedor= ventana.getContentPane();
		
		ventana.setTitle("Extraer Ficheros"); // colocamos un titulo a la ventana
		ventana.setSize(500,300); // Tmanaño de la ventana
		ventana.setResizable(true);
		
		abrir.setText("Abrir"); // añadimos el nombre al boton
		mostrar.setText("Mostrar");
		
		panel1.add(areaTexto,BorderLayout.NORTH); // Colocamos en el panel los componentes
		panel1.add(abrir , BorderLayout.SOUTH);
		panel1.add(mostrar, BorderLayout.SOUTH);
		
		
		contenedor.add(panel1); // introducimo dentro del panel el Contenedor
		
		ventana.setVisible(true); // Metedo para hacer visible la ventana
		
		
	}
	/*
	 * Metodo que escuchardor le pasamos un objeto de la clase escuchadorBoton por la interfaz implements
	 */
	public void registarEventos() {
		abrir.addActionListener(new escuchadorBoton());// 
		mostrar.addActionListener(new escuchadorBoton());
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/*
	 * Esto es el escuchador por la interfaz implementada Action
	 */
	class escuchadorBoton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
						
			
			JButton boton = (JButton) e.getSource(); // Este objeto es el que va a registrar el evento tranformamos el event e en un objeto boto
						
			if(abrir == boton) {
				
				/*
				 * Objeto FileChooose para poder abrir ventana de dialogo
				 */
				JFileChooser fc = new JFileChooser();
				
				/*
				 *Con esta funcion abrirmos el cuadro de dialogo en el va aparecer
				 * 
				 */
				selecciona = fc.showOpenDialog(areaTexto);
			
			if(selecciona == JFileChooser.APPROVE_OPTION) { // fucion que equivale a aceptar
					
					archivoElegido = fc.getSelectedFile(); //Almacenamos la referencia del archivo selecionado
				}
				
		}else if(mostrar == boton) {
			//Te falta la chicha
			if(selecciona == JFileChooser.APPROVE_OPTION) {
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
			
			
			
			}
		}
		
		
	}

	public static void main(String[] args) {
		new	ExtraerTexto();
		
	}

}
