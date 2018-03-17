package FinalCalendar;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;

//���� ���� Ŭ����
public class FileWriterTest {

	{
		for (int i = 1; i < CalendarMemo.date.length; i++)

		{
			if (CalendarMemo.Now == i) { //���õ� ��¥�� �̸����� ������ ����
				String txt = CalendarMemo.memo.getText();

				//��� ����
				String fileName = "."+"//memories//"+ 
						 Integer.toString((CalendarMemo.SellectYEAR % 1000) * 10000 + (CalendarMemo.MONTH * 100) + CalendarMemo.SellectDATE)+".txt";

				try {

					// BufferedWriter �� FileWriter�� �����Ͽ� ��� (�ӵ� ���)
					BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, false));

					// ���Ͼȿ� ���ڿ� ����
					fw.write(txt);
					fw.flush();

					CalendarMemo.dateSchedule[CalendarMemo.Now].setText(txt);
					CalendarMemo.dateSchedule[CalendarMemo.Now].setBackground(new Color(217,217,217));

					// ��ü �ݱ�
					fw.close();

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}

	}

}