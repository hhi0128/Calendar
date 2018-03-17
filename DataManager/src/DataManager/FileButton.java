package DataManager;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;



// �ҷ�����, ���� ��ư ��� �г�
public class FileButton extends JPanel{

	// �̹���
	private ImageIcon loadImageIcon = new ImageIcon("./images/importIcon.png");
	private ImageIcon deleteImageIcon = new ImageIcon("./images/deleteIcon.png");
	private ImageIcon backImageIcon = new ImageIcon("./images/backIcon.png");
	// �ҷ�����, ���� ��ư
	public JButton fileLoadButton = new JButton(loadImageIcon);
	public JButton fileDeleteButton = new JButton(deleteImageIcon);
	public JButton backButton = new JButton(backImageIcon);

	public FileButton(){

		// FlowLayout ����
		setLayout(new FlowLayout());
		// Background �Ͼ��
		setBackground(Color.WHITE);

		// ��ư Background �Ͼ��
		fileLoadButton.setBackground(Color.WHITE);
		fileDeleteButton.setBackground(Color.WHITE);
		backButton.setBackground(Color.WHITE);

		// ��ư ������ ����
		fileLoadButton.setBorder(null);
		fileDeleteButton.setBorder(null);
		backButton.setBorder(null);

		// �ҷ����� ��ư�� �׼Ǹ����� ����
		fileLoadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// FileSave ��ü ����
				new FileChooser();


			}
		});

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = DirectoryList.filePath;


				if(name== ".\\home\\" ) 

				{
					Home.homePanel.removeAll();

					DirectoryList.filePath= DirectoryList.HomeDirectory;
					Home.setHome();

					Home.homePanel.repaint();
					Home.homePanel.revalidate();


				}

				else {
					if(name.length() > ".\\home\\".length()) {
						Home.homePanel.removeAll();

						File f2 = new File(DirectoryList.filePath);	

						String fname = f2.getParent();
						DirectoryList.filePath=fname+"\\";

						Home.setHome();

						Home.homePanel.repaint();
						Home.homePanel.revalidate();

						System.out.println(DirectoryList.filePath);						
					}

				}
			}
		});

		// �гο� ��ư ����
		add(fileLoadButton);
		add(fileDeleteButton);
		add(backButton);

	}


}
