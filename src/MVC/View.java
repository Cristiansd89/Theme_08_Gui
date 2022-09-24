package MVC;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
	 * En este clase vamos a crear y posicionar todos los componentes gr�ficos de la aplicaci�n
	 * @author Victor Sanchez
	 *
	 */
			public class View extends JFrame{
			
				/*
				 * Declaramos todos los componentes graficos necesarios.
				 */
			JFrame window;
			
			private Container cp;
			
			private JPanel buttonContainer;
			private JPanel dataContainer;
			
			private JLabel labelOp1;
			private JLabel labelOp2;
			private JLabel labelResult;
			
			JTextField fieldOp1;
			JTextField fieldOp2;
			JTextField fieldResult;
			
			JButton additionButton;
			JButton susbstrButton;
			JButton multButton;
			JButton divisionButton;
			private JButton deleteButton;
				
			/*
			 * Constructor que asigna contenido a cada componente y lo coloca en la ventana
			 */
			public View()
			{
				////////////////////////////////////////////////////////
				//Creamos componentes
				//window
				window = new JFrame();
				
				//content pane
				cp = window.getContentPane();
						
				//containers
				buttonContainer = new JPanel();
				dataContainer = new JPanel();
				
				//labels
				labelOp1 = new JLabel("Operator 1");
				labelOp2 = new JLabel("Operator 2");
				labelResult = new JLabel("Result");
				
				//text fields
				fieldOp1 = new JTextField(1);
				fieldOp2 = new JTextField(1);
				fieldResult = new JTextField(1);
				
				//buttons
				additionButton = new JButton("+");
				susbstrButton = new JButton("-");
				multButton = new JButton("*");
				divisionButton = new JButton("/");
				deleteButton = new JButton("Delete");
				////////////////////////////////////////77
				//Posicionamos
				//window
				window.setSize(500,200);
				window.setLocation(700, 350);
				window.setTitle("Graphic Calculator");
				
				//add elements to data container
				dataContainer.setLayout(new GridLayout(3,2,5,10));
				dataContainer.add(labelOp1);
				dataContainer.add(fieldOp1);
				dataContainer.add(labelOp2);
				dataContainer.add(fieldOp2);
				dataContainer.add(labelResult);
				dataContainer.add(fieldResult);
				
				//add elements to button container
				buttonContainer.setLayout(new FlowLayout());
				buttonContainer.add(additionButton);
				buttonContainer.add(susbstrButton);
				buttonContainer.add(multButton);
				buttonContainer.add(divisionButton);
				buttonContainer.add(deleteButton);

				//content pane
				cp.add(buttonContainer, BorderLayout.SOUTH);
		        cp.add(dataContainer, BorderLayout.CENTER);
				
				//window
				window.setVisible(true);
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
			
			/*
			 * Metodo que se encarga de a�adir los escuchadores a cada componente.
			 * Este metodo recibe una instancia de la clase Controller
			 */
			public void conectaControlador( Controller c )
			{
				additionButton.addActionListener(c);
				
				susbstrButton.addActionListener(c);
				multButton.addActionListener(c);
				divisionButton.addActionListener(c);
				deleteButton.addActionListener(c);
			}
}
