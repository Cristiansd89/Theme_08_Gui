package MVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
	/**
	 * Clase encargada de manejar cada evento que se origine en nuestra aplicacion.
	 * @author Victor
	 *
	 */
public class Controller implements ActionListener{

	private View view;
	private double op1;
	private double op2;
	
	public Controller( View view ){
	        this.view   = view;
	 }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton sourceButton = null;
		String source = e.getActionCommand();

		try
		{	
			if(view.fieldOp1.getText().equals("") ||  view.fieldOp2.getText().equals(""))
				throw new BlankErr();
		
			else if (source.equals("Delete"))
			{
				view.fieldOp1.setText("");
				view.fieldOp2.setText("");
				view.fieldResult.setText("");
			}			
			else
			{
				 op1 = Double.parseDouble(view.fieldOp1.getText());
				 op2 = Double.parseDouble(view.fieldOp2.getText());
				sourceButton = (JButton) e.getSource();
				//e.getActionCommand()
				if (sourceButton == view.additionButton)
					view.fieldResult.setText( Double.toString(op1 + op2) );
				else if (sourceButton == view.susbstrButton)
					view.fieldResult.setText( Double.toString(op1 - op2) );
				else if (sourceButton == view.multButton)
					view.fieldResult.setText( Double.toString(op1 * op2) );
				else if (sourceButton == view.divisionButton)
				{
					if (op2 != 0)
						view.fieldResult.setText( Double.toString(op1 / op2) );
					else
						JOptionPane.showMessageDialog(view.window, "Cannot divide by zero.");
				}
			}
		}
		catch (BlankErr err)
		{
			JOptionPane.showMessageDialog(view.window, "Field value(s) empty.");
		}
		catch (NumberFormatException nfe)
		{
			view.fieldResult.setText("Field values are not valid.");
		}
		
	}

	
}
		class BlankErr extends Exception
		{
			public BlankErr()
			{
				super();
			}
		}