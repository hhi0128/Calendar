package DataManager;

import java.awt.*;
import javax.swing.*;

public class Text extends JPanel{
	
	private JPanel textPanel = new JPanel();
	
	public JButton textLoadFileButton = new JButton(SaveData.loadImageIcon);
	public JButton textDeleteFileButton = new JButton(SaveData.deleteImageIcon);

	public Text(){
		
		setLayout(new BorderLayout());
		
		textPanel.setBackground(Color.WHITE);
		
		add(new FileButton(), BorderLayout.EAST);
		add(textPanel, BorderLayout.CENTER);	
			
	}
}
