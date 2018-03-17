package DataManager;

import java.io.File;
import java.io.IOException;

public class DirectoryList {

	public static String HomeDirectory = ".\\home\\";
	// ���丮 or ���� ���
	public static String filePath =  HomeDirectory;
	public static String BeforefilePath;
	// ���� ��ü ����
	public File f1 = new File(filePath);
	
	// ���� ���� ���丮�� ������ �����ϴ� �迭
	public String[] list = f1.list();
	// ���丮�� ���� ����
	public static int directoryNum;
	
	public DirectoryList() {
		
		// ���丮�� ���� ���� ����
		directoryNum = list.length;
		// ���� ���� ���丮�� ���� ��� ���
		for(int i = 0 ; i < list.length ;i++) {
			File f2 = new File(filePath, list[i]);
			if(f2.isDirectory())
				System.out.println("directory : " + list[i]);
			else
				System.out.printf("file : %s %, dbyte %n", list[i], f2.length());

		}
	}
}
