package model;

import java.awt.Graphics;

import view.CodeWindow;
import control.Config;

//Model contains the functionality (properties and methods)
public class GridGUIModel implements Config {

	Punkty points = null;
/*	
	Integer[] x = {0,20,30,40,50};
	Integer[] y = {0,30,40,20,50};
*/
	Boolean[] canDrag;


	public void run() {// pokazuje kod algorytmu
		new CodeWindow();
	}

	public GridGUIModel() {

		Punkty.losujPunkty(NUM_INIT_POINT , SIZE_W_H);
		Punkty.computePath();
		
		Punkty.setCanDrag(canDrag);
	}

	public void exit() {
		System.exit(0);
	}

	public int sizeOfPoints(){
		return Punkty.size();
	}
	
	
	public void rysuj(Graphics g) {
	}

}