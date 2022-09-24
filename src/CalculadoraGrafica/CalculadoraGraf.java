package CalculadoraGrafica;
import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculadoraGraf 
{
	//Ventana
	private JFrame ventana;

	//Paneles
	private JPanel panel1;
	private JPanel panel2;

	//Botones
	private JButton suma;
	private JButton resta;
	private JButton multiplicacion;
	private JButton division;
	private JButton borrar;
	private JButton salir;

	//Campos de texto 
	private JTextField numero1;
	private JTextField numero2;
	private JTextField resultado;

	//Label
	private JLabel labelNum1;
	private JLabel labelNum2;
	private JLabel labelResul;

	//Contenedor
	private Container contenedor;


	public CalculadoraGraf()
	{
		crearComponentes();
		colocar();
		registrarEventos();
	}

	public void crearComponentes()
	{
		//Inicializamos la ventana
		ventana= new JFrame("Calculadora");

		contenedor=ventana.getContentPane();
		
		//Inicializamos los botones
		suma = new JButton("+");
		resta= new JButton("-");
		multiplicacion = new JButton("*");
		division = new JButton("/");
		borrar = new JButton("borrar");
		salir = new JButton ("salir");

		//Inicializamos los paneles
		panel1 = new JPanel();
		panel2 = new JPanel();	
		panel2.setLayout(new FlowLayout());

		//Inicializamos los campos de texto y etiquetas
		numero1 = new JTextField(15);
		labelNum1 = new JLabel("Primer Numero");
		numero2 = new JTextField(15);
		labelNum2 = new JLabel("Segundo Numero");
		resultado = new JTextField(15);
		labelResul = new JLabel("Resultado");
	}
	public void colocar()
	{
		//Colocamos la ventana
		ventana.setTitle("Calculadora");
		ventana.setSize(400,200);
		ventana.setResizable(true);
			
		//Colocamos los componentes en el primer panel
		panel1.setLayout(new GridLayout(3,2,5,10));
		panel1.add(labelNum1);
		panel1.add(numero1);
		panel1.add(labelNum2);
		panel1.add(numero2);
		panel1.add(labelResul);
		panel1.add(resultado);

		//Colocamos los componentes en el segundo panel
		panel2.add(suma);
		panel2.add(resta);
		panel2.add(multiplicacion);
		panel2.add(division);
		panel2.add(borrar);
		panel2.add(salir);
		
		//Colocamos los paneles en el contenedor
		contenedor.add(panel1, BorderLayout.NORTH);
		contenedor.add(panel2, BorderLayout.SOUTH);
		
		//hacemos la ventana visible
		ventana.setVisible(true);
		
			

	}
	public void registrarEventos()
	{
		//Sintanxis objeto fuente.addActionListener(new Referencia del eschuchador());
		suma.addActionListener(new ManejadorBoton());
		resta.addActionListener(new ManejadorBoton());
		multiplicacion.addActionListener(new ManejadorBoton());
		division.addActionListener(new ManejadorBoton());
		borrar.addActionListener(new ManejadorBoton());
		salir.addActionListener(new ManejadorBoton());
		ventana.setDefaultCloseOperation(ventana.EXIT_ON_CLOSE);
	}

	
		//ESto es el escuchador SIempre es igual
	class ManejadorBoton implements ActionListener {

		public void actionPerformed(ActionEvent evento)
		{

			JButton Fuente = (JButton) evento.getSource();

			if (Fuente == borrar)
			{
				numero1.setText("");
				numero2.setText("");
				resultado.setText("");
			}
			else if( Fuente == salir)
			{
				int respuestaSalir = JOptionPane.showConfirmDialog(ventana, "Seguro que desea salir", "salir", 0, 1);
				
				if (respuestaSalir == 0) {
					System.exit(0);
				}
			}
			else if (verificarNumero(numero1.getText()) && verificarNumero(numero2.getText())) {

				double num1 = Double.parseDouble(numero1.getText());

				double num2 = Double.parseDouble(numero2.getText());

				operacion(Fuente, num1, num2);
			}
		}
	}
	public boolean verificarNumero(String num) {

		try {

			if (num == "") {

				throw new Error2Numeros();
			}

			else {

				Double.parseDouble(num);

				return true;
			}
		} 

		catch (Error2Numeros e) {
			JOptionPane.showMessageDialog(ventana, "Inserte 2 numeros");
			return false;
		}
		
		catch (NumberFormatException e) {

			JOptionPane.showMessageDialog(ventana, "Numero 1 y Numero 2 deben ser numericos");

			return false;
		}
		
	}
	public void operacion(JButton boton, double num1, double num2)
	{
		double resultadoOp=0;
		
		if(boton == suma)
		{
			resultadoOp=num1+num2;
			resultado.setText(Double.toString(resultadoOp));
		}
		else if(boton ==resta)
		{
			resultadoOp=num1 - num2;
			resultado.setText(Double.toString(resultadoOp));
		}
		else if(boton == multiplicacion)
		{
			resultadoOp=num1 * num2;
			resultado.setText(Double.toString(resultadoOp));
		}
		else if(boton==division)
		{
			resultadoOp=num1 / num2;
			resultado.setText(Double.toString(resultadoOp));
		}
		
	}
	public static void main(String[] args) {
		new CalculadoraGraf();
	}
}
class Error2Numeros extends Exception {
	
	public Error2Numeros() {
		super();
	}
	
}

