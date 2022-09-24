package fruteria;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
/**
 * 
 * @author cristian
 *
 */


public class ClaseFruteria {
	/*
	 * Siempre se añade el contenedor a la ventana
	 */
	private JFrame ventana; //// Declaramos el ventana 
	private Container contenedor; // Declaramos el Contenedor
	
	
	private JPanel panelNort;
	private JPanel panelCent;
	private JPanel panelShurt;
	
	private JTextArea areaPeso;
	private JTextArea areaCoste;
	
	private JButton botonesProducto [] ; // Array de los Botones
	private JButton peso;
	private JButton imprimir;
	private JButton cerrar;
	
	private JLabel etiqueta1;// Para añadir etiquetas
	private JLabel etiqueta2;
	private JLabel etiqueta3;
	private JLabel etiqueta4;
	
	String productoCadena = null;
	Map<String , Integer> PrecioProductos = new HashMap<String , Integer>(); //Diccionario
	private String  productos [] = {"Manzana","Peras","Naranjas","Patatas","Limones", "Melones", "Cebollas","Ajo","Platanos"};
	private int precios []= {1,2,3,2,1,4,3,1,5};
	
	
	
	
	
	

	public void crearElementos() {
		ventana = new JFrame("fruteria");
		
		contenedor = new Container(); // Creamos contenedor
		
		panelNort = new JPanel(); // Crear paneles
		panelCent = new JPanel();
		panelShurt = new JPanel();
		botonesProducto = new JButton[productos.length]; // Tiene la longitud de  productos
		
		for(int i = 0; i < productos.length ; i++) { // for para ir creando botones
			botonesProducto[i] = new JButton(productos[i]);
		}
		
		peso = new JButton("peso");
		imprimir = new JButton("imprimir");
		cerrar = new JButton("cerrar");
		
		areaPeso = new JTextArea(1,10); // Area de texto
		areaCoste = new JTextArea(1,10);
		
		etiqueta1 = new JLabel();
		etiqueta2 = new JLabel();
		etiqueta3 = new JLabel();
		etiqueta4 = new JLabel();
		
		ventana.setVisible(true); // siempre tiene que estar esto que no se te olvide
		
		
		
	}
	
	public void añadirElementos() {
		
		contenedor= ventana.getContentPane(); // Te da la referencia  del contenedor
		ventana.setSize(500,400); // tamaño de la ventana
		
		areaPeso.setEnabled(false); // desactivar  area de texto
		
		etiqueta1.setText("peso");
		etiqueta2.setText("Pulsa Intro despues de pesar algo");
		etiqueta3.setText("coste");
		etiqueta4.setText("€");
		
		panelNort.add(etiqueta1); // Primera etiqueta
		panelNort.add(areaPeso, BorderLayout.CENTER); // area de texto
		panelNort.add(etiqueta2); // Segunda etiqueta
		
		panelCent.setLayout(new GridLayout(4, 3, 3, 3));
		
		panelShurt.add(etiqueta3);
		panelShurt.add(areaCoste);
		panelShurt.add(etiqueta4);
		
		for (int i = 0;i<botonesProducto.length;i++) { // Añadir los productos a gridlayaut
			panelCent.add(botonesProducto[i]);
		}
		panelCent.add(peso); // Añadiendo los botones
		panelCent.add(imprimir);
		panelCent.add(cerrar);
		
		
		contenedor.add(panelNort, BorderLayout.NORTH); // Se añade los paneles al contenedor
		contenedor.add(panelCent, BorderLayout.CENTER);
		contenedor.add(panelShurt, BorderLayout.SOUTH);
		
		 for (int i = 0; i<productos.length; i++) { // Introducimos los precios dentro del diccionario
				String	producto = productos[i]; // Pasamos a una variable individual para poder introducirlo en
				int precio = precios[i]; // el diccionario
				
				PrecioProductos.put(producto,precio); //
			}
	
	}
	/*
	 * Registrador de los eventos
	 */
	public void registrarEventos() {
		
		for(int i = 0; i < botonesProducto.length ; i++) { // registro de acciones producto
			botonesProducto[i].addActionListener(new Eventos());
		}
		peso.addActionListener(new Eventos());
		imprimir.addActionListener(new Eventos());
		cerrar.addActionListener(new Eventos());
	}
	
	/*
	 * Escuchador
	 */
	class Eventos implements ActionListener {
		
		String producto;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			String  pulsar = e.getActionCommand();// Leer las cadenas del boton
			
			
			if(pulsar != "peso" && pulsar != "imprimir" && pulsar != "cerrar") {
				productoCadena = pulsar;
			}
			
			switch(pulsar) {
							
				case "peso":
					areaPeso.setEnabled(true);
					break;
					
				case "imprimir":
					
					imprimir(productoCadena);
					break;
								
				case "cerrar":
					System.exit(0); // Cerrar ventana
					break;
					
								
			}//switch
			
			
	}// ActionPerformed
		
	
	/*
	 * Este metodo recorre el diccionario buscando equivalencias si el boton pulsado coindice con 
	 * alguna clave del diccionario debera hacer el cualculo y subirlo al are de texto
	 */
	//parece que aqui anda el problema(Diccionario
		public void imprimir(String productoCadena) {
			
					try {
						int peso= Integer.parseInt(areaPeso.getText());// Para transformar  el texto del area en un numero entero
						int precioProducto = PrecioProductos.get(productoCadena); 
						
						int precioTotal =  peso * precioProducto;
						String precioCadena = String.valueOf(precioTotal); // pasar de numero entero a cadena
					
						areaCoste.setText(precioCadena); // Metodo para añadir a de abajo el resultado
					
					}catch (Exception e){
						JOptionPane.showMessageDialog(null, "El campo de peso esta vacio");
					}
			
					
		}
	
		
	}// Eventos

	
	 public ClaseFruteria() {
		 crearElementos();
		 añadirElementos();
		 registrarEventos();
		 
		
		 
	 }

	public static void main(String[] args) {
		
		new	ClaseFruteria();
	}

}
