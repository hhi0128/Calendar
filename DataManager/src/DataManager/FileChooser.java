package DataManager;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileChooser {

	
	public FileChooser() {

		JFileChooser chooser = new JFileChooser(); // ��ü ����

		int ret = chooser.showOpenDialog(null); // ����â ����

		if (ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "Not Choose File Path", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}

		String filePath = chooser.getSelectedFile().getPath(); // ���ϰ�θ� ������
		System.out.println(filePath); // ���

//		String outFolder = "."+"//"+"home";
		String outFolder = Home.directoryList.filePath;

		String ChoosedFile = chooser.getSelectedFile().getName();
		
		fileCopy(filePath,  outFolder+"\\"+ChoosedFile); //�ҷ��� ���� Database�� ����
	
	
		Home.homePanel.removeAll();
		
		Home.setHome();
		
		Home.homePanel.repaint();
		Home.homePanel.revalidate();

	}
//���� �޼ҵ�
	void fileCopy(String filePath, String outFolder) {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			FileOutputStream fos = new FileOutputStream(outFolder);

			int data = 0;
			while ((data = fis.read()) != -1) {
				fos.write(data);
			}
			fis.close();
			fos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
