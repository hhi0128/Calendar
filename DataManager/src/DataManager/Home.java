package DataManager;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.*;

public class Home extends JPanel {

	
	// ���� �� ������ ��� �г�
	public static JPanel homePanel = new JPanel();
	public static JPanel folderPanel[] = new JPanel[15];
	// �̹��� ��ü
	public static ImageIcon folderIcon = new ImageIcon(".\\images\\folderIcon.png");

	// home �� �ִ� ������ �����ִ� ��ư
	public static JButton homeDirectoryButton[];
	public static String fileName;

	// DirectoryList ��ü ����
	static DirectoryList directoryList = new DirectoryList();

	public Home() {

		setLayout(new BorderLayout());

		homePanel.setLayout(new GridLayout(3, 5));

		homePanel.setBackground(Color.WHITE);

		homeDirectoryButton = new JButton[directoryList.directoryNum];
		// home ���丮�� ���� ���丮 ���� ��ŭ ��ư ����

		setHome();

		// �ҷ����� �� ���� ��ư ����
		add(new FileButton(), BorderLayout.EAST);
		add(homePanel, BorderLayout.CENTER);
	}

	public static void setHome() {
		
		directoryList = new DirectoryList();
		homeDirectoryButton = new JButton[directoryList.directoryNum];

		for (int i = 0; i < 15; i++) {
			folderPanel[i] = new JPanel();
			folderPanel[i].setBackground(Color.WHITE);
			homePanel.add(folderPanel[i]);
		}

		for (int i = 0; i < directoryList.directoryNum; i++) {
			homeDirectoryButton[i] = new JButton(folderIcon);

			// ��ư�� TitledBorder�� ������ ��ư �Ʒ��� �̸� ���
			fileName = directoryList.list[i];

			if (fileName.substring(fileName.length() - 3).equals("txt"))
				homeDirectoryButton[i].setIcon(SaveData.textFileIcon);
			else if(fileName.substring(fileName.length()-3).equals("png")) {
				ImageIcon tmp = new ImageIcon(DirectoryList.filePath + "\\" + fileName);
				java.awt.Image origin = tmp.getImage();
				java.awt.Image change = origin.getScaledInstance(75, 75, Image.UNDEFINED_CONDITION);
				ImageIcon result = new ImageIcon(change);
				homeDirectoryButton[i].setIcon(result);							

			}
			else if (fileName.substring(fileName.length() - 3).equals("mp3"))
				homeDirectoryButton[i].setIcon(SaveData.musicFileIcon);
			
			TitledBorder titledBorder = new TitledBorder(directoryList.list[i]);
			titledBorder.setTitlePosition(TitledBorder.BELOW_BOTTOM);
			titledBorder.setTitleJustification(TitledBorder.CENTER);

			homeDirectoryButton[i].setBorder(titledBorder);
			homeDirectoryButton[i].setBackground(Color.WHITE);
			homeDirectoryButton[i].setActionCommand(fileName);

			// �׼� ������ ����
			homeDirectoryButton[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton btn = (JButton) e.getSource();
					String s = btn.getActionCommand();

					File f = new File(DirectoryList.filePath + s );
					
					if(f.isDirectory()) {
						System.out.println(DirectoryList.filePath);
						
						DirectoryList.BeforefilePath=DirectoryList.filePath;
						DirectoryList.filePath = DirectoryList.filePath + s + "\\";

						System.out.println(DirectoryList.filePath);

						Home.homePanel.removeAll();

						Home.setHome();
					}
				}
			});

			// �гο� ��ư ����
			folderPanel[i].add(homeDirectoryButton[i]);

		}
	}
}
