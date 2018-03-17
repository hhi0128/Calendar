package DataManager;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

public class SaveData extends JFrame{
	
	// JTabbedPane�� �̿��Ͽ� home, music, image, text �г� ����
	private JTabbedPane tabbedPane = new JTabbedPane();
	
	// �̹��� ������
	public static ImageIcon loadImageIcon = new ImageIcon("./images/importIcon.png");
	public static ImageIcon deleteImageIcon = new ImageIcon("./images/deleteIcon.png");
	public static ImageIcon musicFileIcon = new ImageIcon("./images/musicIcon.png");
	public static ImageIcon imageFileIcon = new ImageIcon("./images/imageIcon.png");
	public static ImageIcon textFileIcon = new ImageIcon("./images/textIcon.png");
	
	// �� Ŭ������ ��ü ����
	public Home home = new Home();
	public Music music = new Music();
	public Image image = new Image();
	public Text text = new Text();
	
	public SaveData(){
		setTitle("Data Manage System");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		makeGUI();
		
		setSize(Main.Main_Width, Main.Main_Height);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public void makeGUI() {
		
		// JTabbedPane�� �г� ����
		tabbedPane.add("Home", home);
		tabbedPane.add("Music", music);
		tabbedPane.add("Image", image);
		tabbedPane.add("Text", text);
		
		add(tabbedPane);
		
	}
	
	
}
