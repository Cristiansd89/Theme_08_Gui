package Grabar_Fichero;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.*;

public class GrabarTexto {
	
	private int seleccionado = -1;  
	private JFrame ventana; // Crear Ventana
	private JPanel panel1;  // Panel sobre el que van a ir los botones y el Area de texto
	private JButton select;  //Botones de seleccionar
	private JButton save;  //Botones de guardar
	private JTextArea areaTexto; //Area de texto 
	private Container contenedor; //Contenedor donde se añaden los componentes
	private File archivoElegido;
	
	public void crearComponentes() {
		
		
		
		ventana =new JFrame(); //Creamos la  ventana con el titulo
		contenedor = ventana.getContentPane(); //Creamos el Contenedor
		
		
		panel1 = new JPanel();//Inicializamos el panel
		
		select = new JButton();		//inicializar los botones
		
		save = new JButton();
		
		
		
		areaTexto = new JTextArea(10,40); //Inicializamos Areas Siempre hay que pasarle las medidas
		areaTexto.setLineWrap(true); //para que el texto se ajuste al area
		areaTexto.setWrapStyleWord(true); //permite que no queden palabras incompletas al hacer el salto de linea.
		
		
	
	}
	
	
	public  void colocar() {
		
		
		ventana.setTitle("Grabadora");  // Colocamos la ventana con su Titulo y medidas
		ventana.setSize(500,300);
		ventana.setResizable(true);
		
		select.setText("Ubicacion"); //Nombre Botones
		save.setText("Guardar");
		
		panel1.add(areaTexto,BorderLayout.NORTH ); //Colocamos TextArea
		panel1.add(select,BorderLayout.SOUTH);//Colocamos Botones 
		panel1.add(save,BorderLayout.SOUTH);
		
		contenedor.add(panel1);//Introducimos todo el panel en el contenedor
		
		ventana.setVisible(true); /*hacemos la ventana visible sin este metodo la ventana 
		non saldria
		*/

	}
	
	public void registrarEventos() {
		save.addActionListener(new ManejadorBoton());
		select.addActionListener(new ManejadorBoton());
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	class ManejadorBoton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
	 		
			//Este objeto es el que va a registrar el evento tranformamos el event e en un objeto boto
			//
			JButton boton = (JButton) e.getSource();
			
			//Para que nos muestre  la ventana de dialogo
			if(select== boton) {
				//Creamos el objeto JFileChooser
				JFileChooser fc=new JFileChooser();
				/*
				 * Con esta funcion abrimos el Cuadro de dialogo en el va aparacer 	"SAVE"
				 * y el parametro que le pasamos es donde queremos que nos salga
				 */
				 seleccionado = fc.showSaveDialog(areaTexto);
				
				/*
				 * Esto  va hacer como que reconoce la pulsacion
				 */
				if( seleccionado == JFileChooser.APPROVE_OPTION) {
					/*
					 * getSelect... no guarda la referencia del archivo en el que vamos a guardar
					 * el texto
					 */
					archivoElegido = fc.getSelectedFile();
				}
				
		
			}else if(save == boton){
				
				
					//Creamo el objeto FileWriter pasandole la referencia del archivo seleccionado
					if(seleccionado == JFileChooser.APPROVE_OPTION) {
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
		
		}
		
	}
	
	
	
	
	
	public GrabarTexto() {
		
		crearComponentes();
		//Como no es static solo te deja llamarlo en el constructor
		colocar();
		registrarEventos();
		
	}

	public static void main(String[] args) {
		
		new GrabarTexto();
	
		

	}

}

