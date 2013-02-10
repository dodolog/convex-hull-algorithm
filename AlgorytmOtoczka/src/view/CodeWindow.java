package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class CodeWindow extends JFrame {

	/**
	 *  Okno z kodem algorytmu wyznaczającej otoczkę wypukłą zbioru punktów w 2d
	 */
	private static final long serialVersionUID = 1L;

	private JScrollPane jScrollPanel;
	private JTextArea textArea;

	public CodeWindow() {
		
		setPreferredSize(new Dimension(800,600));
		setMinimumSize(new Dimension(800, 600));
		
		//Punkty.class.getProtectionDomain().getCodeSource().getLocation().getPath()
		try { // FIXME: write dynamic path
			textArea = new JTextArea(readFileToString("/home/mich/workspace/AlgorytmOtoczka/src/model/Punkty.java"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		textArea.setFont(new Font("Serif", Font.ITALIC, 10));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		jScrollPanel = new JScrollPane(textArea);

		BorderLayout layout = new BorderLayout();
		getContentPane().setLayout(layout);
		getContentPane().add(jScrollPanel, BorderLayout.CENTER);
		setVisible(true);
	}

	private  String readFileToString(String path) throws IOException {
		  FileInputStream stream = new FileInputStream(new File(path));
		  try {
		    FileChannel fc = stream.getChannel();
		    MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
		    /* Instead of using default, pass in a decoder. */
		    return Charset.defaultCharset().decode(bb).toString();
		  }
		  finally {
		    stream.close();
		  }
		}

}
