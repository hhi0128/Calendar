package FinalCalendar;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;


//날짜 선택 클래스
class ClickDate implements ActionListener {

	int firstDay = CalendarMemo.cal2.get(Calendar.DAY_OF_WEEK);

	public void actionPerformed(ActionEvent e) {

		JButton c = (JButton) e.getSource();


		for (int i = 1; i < CalendarMemo.date.length; i++) {
			if (c.getText().equals("" + (i - firstDay + 1))) {

				CalendarMemo.datePanel[i].setBackground(Color.GREEN);
				CalendarMemo.date[i].setBackground(Color.GREEN);

				CalendarMemo.SellectDATE = i - firstDay + 1;

				CalendarMemo.cal4 = new GregorianCalendar(CalendarMemo.SellectYEAR, CalendarMemo.SellectMONTH - 1, CalendarMemo.SellectDATE);

				CalendarMemo.Now = i; //현재 선택된 날짜를 now에 저장해준다

				CalendarMemo.Memotitle.setText(CalendarMemo.MONTH + "월 " + CalendarMemo.SellectDATE + "일 " + "MEMO");

				System.out.println(CalendarMemo.Now);

				new FileReaderTest();

			} else{
				CalendarMemo.datePanel[i].setBackground(Color.WHITE);
				CalendarMemo.date[i].setBackground(Color.WHITE);
				
			}

		}


	}
}
