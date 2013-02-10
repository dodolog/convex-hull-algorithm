import model.GridGUIModel;
import view.GridGUIView;
import control.*;

public class Main {
	
	// based on MVC pattern
	// http://home.cogeco.ca/~ve3ll/jatutorc.htm
	// http://csis.pace.edu/~bergin/mvc/mvcgui.html

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GridGUIModel model = new GridGUIModel();
	    GridGUIView view = new GridGUIView("Algorytm otoczka");
	    new GridGUIController(model,view);
	    
	}
}


