package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Punkty;
import control.Config;

public class Panel extends JPanel implements Config {

	private static final long serialVersionUID = 508369234136358064L;

	public void paintComponent(Graphics g) {
		
		if (Punkty.size() != 0) {
			for (int i = 0; i < Punkty.size(); i++){
				
				if(  i == Punkty.getExtrema(0) || i == Punkty.getExtrema(1) || i == Punkty.getExtrema(2)  || i == Punkty.getExtrema(3) )
					g.setColor(Color.RED);
				else
					if(Punkty.isInPath(i))
						g.setColor(Color.GREEN);
					else	
						g.setColor(Color.BLACK);
					
				g.fillOval(Punkty.getX(i)-4, Punkty.getY(i)-4, 8, 8);
			
				
				if(DEBUG && Punkty.getExtrema(0)>0)
				{
					Punkty.calculateAngleFor(Punkty.pointReference<0 ? Punkty.getExtrema(0) : Punkty.pointReference);
					g.drawString(String.format("%d  %2.1f", i,Punkty.getAngle(i))  ,Punkty.getX(i), Punkty.getY(i));
				}
				
			}

			int n,m;
			for(int j=0;j<Punkty.sizePath()-1;j++)
			{
				try{
				n = Punkty.getPath(j);
				m = Punkty.getPath(j+1);
				
				System.out.print("n i m:" + n + " " + m + "\n");
				
				g.setColor(Color.BLUE);
				g.drawLine(Punkty.getX(n), Punkty.getY(n), Punkty.getX(m), Punkty.getY(m));
				}
				catch(ArrayIndexOutOfBoundsException e){
					
					System.out.print("Wyszło poza!!! ");
					return;
				}
			}
		}
	}
}
