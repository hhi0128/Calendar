package FinalCalendar1;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//���� �б� Ŭ����
public class FileReaderTest {
	{
		File file;
		char[] ch;

		for (int i = 1; i < CalendarMemo.date.length; i++) {
			
			//���õ� ��¥�� ������ �ҷ���
		
			if (CalendarMemo.Now == i) { 
				String dfName = "."+"//memories//"+
						 Integer.toString((CalendarMemo.SellectYEAR % 1000) * 10000 + (CalendarMemo.MONTH * 100) + CalendarMemo.SellectDATE)+".txt";
				file = new File(dfName);
				ch = new char[(int) file.length()];
				
				//���ۿ� ���� ���� ����
				try {
					BufferedReader memoReader = new BufferedReader(new FileReader(file));
					try {
						memoReader.read(ch);

						memoReader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} catch (FileNotFoundException e1) {
					System.out.println("������ �������� �ʽ��ϴ�.");
					e1.printStackTrace();

				} catch (IOException e) {
					System.out.println("������ �б⸦ ������ �� �����ϴ�.");
					e.printStackTrace();
				}

				//���ۿ� ����� ������ �޸�� ���
				String s = new String(ch);

				CalendarMemo.memo.setText(s);
				CalendarMemo.dateSchedule[CalendarMemo.Now].setText(s);
				CalendarMemo.dateSchedule[CalendarMemo.Now].setBackground(new Color(217,217,217));

			}

		}
	}

}