package DataManager;

import java.awt.*;
import javax.swing.*;

public class Music extends JPanel{

	private JPanel musicPanel = new JPanel();
	
	public Music(){
		
		setLayout(new BorderLayout());
		
		musicPanel.setBackground(Color.WHITE);
		
		add(new FileButton(), BorderLayout.EAST);
		add(musicPanel, BorderLayout.CENTER);		
	}

}
