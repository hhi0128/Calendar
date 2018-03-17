package FinalCalendar;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;

//파일 쓰기 클래스
public class FileWriterTest {

	{
		for (int i = 1; i < CalendarMemo.date.length; i++)

		{
			if (CalendarMemo.Now == i) { //선택된 날짜를 이름으로 파일을 생성
				String txt = CalendarMemo.memo.getText();

				//경로 지정
				String fileName = "."+"//memories//"+ 
						 Integer.toString((CalendarMemo.SellectYEAR % 1000) * 10000 + (CalendarMemo.MONTH * 100) + CalendarMemo.SellectDATE)+".txt";

				try {

					// BufferedWriter 와 FileWriter를 조합하여 사용 (속도 향상)
					BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, false));

					// 파일안에 문자열 쓰기
					fw.write(txt);
					fw.flush();

					CalendarMemo.dateSchedule[CalendarMemo.Now].setText(txt);
					CalendarMemo.dateSchedule[CalendarMemo.Now].setBackground(new Color(217,217,217));

					// 객체 닫기
					fw.close();

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}

	}

}