package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

//View is where the GUI is built
public class GridGUIView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	Panel panel;
	JPanel kontrola;
	JPanel panelGlowny;

	JLabel licznikJLabel;
	JSlider iloscSlider;
	JButton pokazButton;
	JButton exitButton;

	public GridGUIView(String title) // the constructor
	{
		super(title);
		setSize(450, 480);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		panel = new Panel();
		kontrola = new JPanel();
		panelGlowny = new JPanel();

		licznikJLabel = new JLabel();
		iloscSlider = new JSlider();
		pokazButton = new JButton("Opis");
		exitButton = new JButton("Wyjdź");

		kontrola.setLayout(new FlowLayout());
		kontrola.add(licznikJLabel);
		kontrola.add(iloscSlider);
		kontrola.add(pokazButton);
		kontrola.add(exitButton);

		panelGlowny.setLayout(new BorderLayout());
		panelGlowny.add(kontrola, BorderLayout.PAGE_START);
		panelGlowny.add(panel,BorderLayout.CENTER);
		

		getContentPane().add(panelGlowny);
		setVisible(true);

	}
	public void rysuj(){	
		panel.repaint();
	}
	
	public void setLabel(String opis){
		licznikJLabel.setText(opis);
	}
	public void setValueSlider(int v){
		iloscSlider.setValue(v);
	}
	// method to add ActionListener passed by Controller to panel
	public void theActionListeners(ActionListener al) {
		pokazButton.setActionCommand("run");
		pokazButton.addActionListener(al);
		exitButton.setActionCommand("exit");
		exitButton.addActionListener(al);
	}

	public void theMouseListeners(MouseListener m){
		panel.addMouseListener(m);
		panel.addMouseMotionListener((MouseMotionListener) m);
	}
	//this.addMouseListener(this);
   // this.addMouseMotionListener(this);
	

	public void theChangeListeners(ChangeListener ch) {
		iloscSlider.addChangeListener(ch);
	}
	public void setLabelForPoint(int n, String value) {
		//panel
	}
}