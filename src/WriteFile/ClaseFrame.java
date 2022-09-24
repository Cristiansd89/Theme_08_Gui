package WriteFile;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Clase que permite cargar una ventana con un area de texto
 * y las opciones de abrir o guardar un archivo
 * @author HENAO
 *
 */
public class ClaseFrame extends JFrame implements ActionListener {
	
		private Container contenedor;
		private JLabel labelTitulo;/*declaramos el objeto Label*/
		private JTextArea areaDeTexto;
		private JButton botonAbrir;/*declaramos el objeto Boton*/
		private JButton botonGuardar;/*declaramos el objeto Boton*/
		private JScrollPane scrollPaneArea;
		private JFileChooser fileChooser; /*Declaramos el objeto fileChooser*/
		private String texto;
    
		//constructor.
		public ClaseFrame() {
			
			contenedor = getContentPane();
			contenedor.setLayout(null);
			
			/*Inicializamos el objeto JFileChooser*/
			fileChooser=new JFileChooser();
			
			/*Propiedades del Label, lo instanciamos, posicionamos y
			 * activamos los eventos*/
			labelTitulo= new JLabel();
			labelTitulo.setText("COMPONENTE JFILECHOOSER"); // Mostramos t�tulo.
			labelTitulo.setBounds(110, 20, 180, 23); // Posicionamos el JLabel.
			
			areaDeTexto = new JTextArea();
			areaDeTexto.setLineWrap(true); //para que el texto se ajuste al area
			areaDeTexto.setWrapStyleWord(true); //permite que no queden palabras incompletas al hacer el salto de linea.
			
		   	scrollPaneArea = new JScrollPane();
			scrollPaneArea.setBounds(20, 50, 350, 270);
	        scrollPaneArea.setViewportView(areaDeTexto);
	       	
			/*Propiedades del bot�n, lo instanciamos, posicionamos y
			 * activamos los eventos*/
			botonAbrir= new JButton();
			botonAbrir.setText("Abrir"); //Asignamos el nombre del bot�n.
			botonAbrir.setBounds(110, 330, 80, 23); //Lo posicionamos.
			botonAbrir.addActionListener(this);
			
			botonGuardar= new JButton();
			botonGuardar.setText("Guardar");
			botonGuardar.setBounds(220, 330, 80, 23);
			botonGuardar.addActionListener(this);
			
			/*Agregamos los componentes al Contenedor*/
			contenedor.add(labelTitulo);
			contenedor.add(scrollPaneArea);
			contenedor.add(botonAbrir);
			contenedor.add(botonGuardar);
			
       		//Asigna un t�tulo a la barra de t�tulo
			setTitle("Ventana JFileChooser");
			
			//tama�o de la ventana
			setSize(400,400);
			
			//Pone la ventana en el Centro de la pantalla
			setLocationRelativeTo(null);
			
			// Cierra la aplicaci�n al pulsar la x.
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		@Override
		public void actionPerformed(ActionEvent evento) {
			if(evento.getSource() == botonAbrir){
				
				String archivo = abrirArchivo();
				areaDeTexto.setText(archivo);
			}
			
			if(evento.getSource() == botonGuardar){
				guardarArchivo();
			}
		
		}

		/**
		 * Permite mostrar la ventana que carga 
		 * archivos en el area de texto
		 * @return
		 */
		private String abrirArchivo() {
					
			String aux =""; 		
	 		texto ="";
		
	 		try {
	 			/*llamamos el m�todo que permite cargar la ventana*/
	    		fileChooser.showOpenDialog(areaDeTexto);
	    		
	    		/*abrimos el archivo seleccionado*/
	 			File abre = fileChooser.getSelectedFile();

	 			/*recorremos el archivo, lo leemos para plasmarlo
	 			 *en el area de texto*/
	 			if(abre!=null) { 	
	 				
	 				FileReader archivos = new FileReader(abre);
	 				BufferedReader lee = new BufferedReader(archivos);
	 				
	 				while((aux=lee.readLine())!=null) {
	 					
	 					 texto+= aux+ "\n";
	 				}
	 				
	 		  		lee.close();
	 			} 			
	 		}
	 		catch(IOException ex) {
	 			
			  JOptionPane.showMessageDialog(null,ex+"" +
			  		"\nNo se ha encontrado el archivo",
			  		"ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
			}
		    return texto;
		    
		}
		
		/**
		 * Guardamos el archivo del area 
		 * de texto en el equipo
		 */
		/*Para seleccionar un fichero para guardar datos, 
		 */
		private void guardarArchivo() {

	 		try {
	 			JFileChooser fileChooser = new JFileChooser();
	 			int seleccion = fileChooser.showSaveDialog(areaDeTexto);
	 			
	 			if (seleccion == JFileChooser.APPROVE_OPTION){
	 				
	 			   File fichero = fileChooser.getSelectedFile();
	 			   // Aqu� debemos abrir el fichero para escritura
	 			   // y salvar nuestros datos.
	 			   FileWriter save = new FileWriter(fichero);
		 		   save.write(areaDeTexto.getText());
		 		   save.close();

	 			   JOptionPane.showMessageDialog(null,
	 					"El archivo se a guardado Exitosamente",
	 					"Informaci�n",JOptionPane.INFORMATION_MESSAGE);
			    }
	 			/*String nombre ="";
	 			
				JFileChooser file = new JFileChooser();
				file.showSaveDialog(areaDeTexto);
				File guarda = file.getSelectedFile();
		
				if(guarda !=null) {
					
		 			nombre = file.getSelectedFile().getName();
		 			
		 			guardamos el archivo y le damos el formato directamente,
		 			 * si queremos que se guarde en formato doc lo definimos como .doc
		 			FileWriter save = new FileWriter(guarda);
		 			save.write(areaDeTexto.getText());
		 			save.close();
		 			*/
	 			
	 		 }
	 	     catch(IOException ex) {
	 	    	 
			 JOptionPane.showMessageDialog(null,
					 "Su archivo no se ha guardado",
					 "Advertencia",JOptionPane.WARNING_MESSAGE);
		    }
	 	}
		
		
}
