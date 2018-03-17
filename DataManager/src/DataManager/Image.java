package DataManager;

import java.awt.*;
import javax.swing.*;

public class Image extends JPanel{
	
	private JPanel imagePanel = new JPanel();
	
	public Image(){
		
		setLayout(new BorderLayout());
		
		imagePanel.setBackground(Color.WHITE);
		
		add(new FileButton(), BorderLayout.EAST);
		add(imagePanel, BorderLayout.CENTER);	
		
	}
}
