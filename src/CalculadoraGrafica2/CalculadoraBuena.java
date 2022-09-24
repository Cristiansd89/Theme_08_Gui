package CalculadoraGrafica2;
/**
 * 
 */
//package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author madrid
 *
 */

public class CalculadoraBuena {

	/**
	 * @param args
	 */
			
	private JFrame window;
	
	private Container cp;
	
	private JPanel buttonContainer;
	private JPanel dataContainer;
	
	private JLabel labelOp1;
	private JLabel labelOp2;
	private JLabel labelResult;
	
	private JTextField fieldOp1;
	private JTextField fieldOp2;
	private JTextField fieldResult;
	
	private JButton additionButton;
	private JButton susbstrButton;
	private JButton multButton;
	private JButton divisionButton;
	private JButton deleteButton;
	
	public CalculadoraBuena()
	{
		createComponents();
		positionComponents();
		registerEvents();
	}
	
	public void createComponents()
	{
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
		
	}
	
	public void positionComponents()
	{
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
	
	public void registerEvents()
	{
		additionButton.addActionListener(new ButtonOperations());
		susbstrButton.addActionListener(new ButtonOperations());
		multButton.addActionListener(new ButtonOperations());
		divisionButton.addActionListener(new ButtonOperations());
		deleteButton.addActionListener(new DeleteButton());
	}
	
	class ButtonOperations implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
    	{
			JButton sourceButton = null;
			try
			{	
				if(fieldOp1.getText().equals("") ||  fieldOp2.getText().equals(""))
					throw new BlankErr();
				
				double op1 = Double.parseDouble(fieldOp1.getText());
				double op2 = Double.parseDouble(fieldOp2.getText());
				sourceButton = (JButton) e.getSource();
				//e.getActionCommand()
				if (sourceButton == additionButton)
					fieldResult.setText( Double.toString(op1 + op2) );
				else if (sourceButton == susbstrButton)
					fieldResult.setText( Double.toString(op1 - op2) );
				else if (sourceButton == multButton)
					fieldResult.setText( Double.toString(op1 * op2) );
				else if (sourceButton == divisionButton)
				{
					if (op2 != 0)
						fieldResult.setText( Double.toString(op1 / op2) );
					else
						JOptionPane.showMessageDialog(window, "Cannot divide by zero.");
				}
				
			}
			catch (BlankErr err)
			{
				JOptionPane.showMessageDialog(window, "Field value(s) empty.");
			}
			catch (NumberFormatException nfe)
			{
				fieldResult.setText("Field values are not valid.");
			}

    	}
	}
	
	class DeleteButton implements ActionListener
	{

		public void actionPerformed(ActionEvent e) 
		{
			String source = e.getActionCommand();
		
			if (source.equals("Delete"))
			{
				fieldOp1.setText("");
				fieldOp2.setText("");
				fieldResult.setText("");
			}
		}
		
	}
	
	public static void main(String[] args) 
	{
		new CalculadoraBuena();
	}
}

class BlankErr extends Exception
{
	public BlankErr()
	{
		super();
	}
}

