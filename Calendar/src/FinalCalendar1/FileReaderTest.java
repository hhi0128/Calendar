package FinalCalendar1;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//파일 읽기 클래스
public class FileReaderTest {
	{
		File file;
		char[] ch;

		for (int i = 1; i < CalendarMemo.date.length; i++) {
			
			//선택된 날짜의 파일을 불러옴
		
			if (CalendarMemo.Now == i) { 
				String dfName = "."+"//memories//"+
						 Integer.toString((CalendarMemo.SellectYEAR % 1000) * 10000 + (CalendarMemo.MONTH * 100) + CalendarMemo.SellectDATE)+".txt";
				file = new File(dfName);
				ch = new char[(int) file.length()];
				
				//버퍼에 파일 내용 저장
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
					System.out.println("파일이 존재하지 않습니다.");
					e1.printStackTrace();

				} catch (IOException e) {
					System.out.println("파일의 읽기를 수행할 수 없습니다.");
					e.printStackTrace();
				}

				//버퍼에 저장된 내용을 메모로 출력
				String s = new String(ch);

				CalendarMemo.memo.setText(s);
				CalendarMemo.dateSchedule[CalendarMemo.Now].setText(s);
				CalendarMemo.dateSchedule[CalendarMemo.Now].setBackground(new Color(217,217,217));

			}

		}
	}

}