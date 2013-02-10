package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.*;
import view.*;

//the controller listens for actions and reacts
public class GridGUIController implements ActionListener, ChangeListener, MouseListener,MouseMotionListener, Config {
	
	GridGUIModel model;
	GridGUIView view;

	public GridGUIController(GridGUIModel model, GridGUIView view) {
		// create the model and the GUI view
		this.model = new GridGUIModel(); // :P
		this.view = view;
		
		// Add action listener from this class to view buttons
		view.theActionListeners(this);
		view.theChangeListeners(this);
		view.addMouseListener(this);
		view.theMouseListeners(this);
		
		Punkty.losujPunkty(NUM_INIT_POINT, SIZE_W_H);
		Punkty.computePath();
		
		view.setLabel(String.valueOf(NUM_INIT_POINT));
		view.setValueSlider(NUM_INIT_POINT);
		
		view.repaint();
	}

	// Provide interactions for actions performed in the view
	public void actionPerformed(ActionEvent ae) {
		
		String action_com = ae.getActionCommand();
		char c = action_com.charAt(0);
		
		switch (c) {
		case 'r':
			model.run();  
			break;
		case 'e':
			model.exit();
			break;
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {

		// model. wylosuj punkty okreslona ilosc
		
		JSlider source = (JSlider) e.getSource();		
		
		if (!source.getValueIsAdjusting()) {
			Integer iloscPunktow = (Integer) source.getValue();

			view.setLabel(iloscPunktow.toString());
			
			System.out.println("iloscPunktow "+iloscPunktow);
			
			Punkty.losujPunkty(iloscPunktow, SIZE_W_H );
			Punkty.computePath();
			
			view.repaint();
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// wleczenie kursora z włączonym przyciskiem
	
		int i = Punkty.pointReference;
		
	    Punkty.setX(i, e.getX() );
	    Punkty.setY(i, e.getY() );

	                //--- Don't move the ball off the screen sides
	    Punkty.setX(i, Math.max(Punkty.getX(i), 0));
	    Punkty.setX(i, Math.min(Punkty.getX(i), view.getWidth() - 4));

	                //--- Don't move the ball off top or bottom
	    Punkty.setY(i, Math.max(Punkty.getY(i), 0));
	    Punkty.setY(i, Math.min(Punkty.getY(i), view.getHeight() - 4));
	                
	    Punkty.calculateExtremaPoint();
	    Punkty.computePath();
	                
	    view.repaint(); // Repaint because position changed.
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	     
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		 for (int i = 0; i < Punkty.size(); i++) {
			 Punkty.setCanDrag(i, false);
		 }
	}	 

	@Override
	public void mousePressed(MouseEvent e) {
		
		 int x = e.getX();   // Save the x coord of the click
	     int y = e.getY();   // Save the y coord of the click
	     
	      for (int i = 0; i < Punkty.size(); i++) {
	    	  
	            if (x >= (Punkty.getX(i)-5) && x <= (Punkty.getX(i) + 5) && y >= (Punkty.getY(i)-5) && y <= (Punkty.getY(i) + 5)) {
	            	
	            	// TODO: canDrag NIEPOTRZEBNE ????????????????
	                Punkty.setCanDrag(i, true);
	                
	                Punkty.pointReference = i;

	            } else {
	            	Punkty.setCanDrag(i, false);
	            }
	      }
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}