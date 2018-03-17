package FinalCalendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

//달력 이동 버튼 관련 클래스
class UpDown implements ActionListener {

	public void actionPerformed(ActionEvent arg0) {

		JButton btn = (JButton) arg0.getSource();
		if (btn.getText().equals("UP")) {
			
			//top과 mid를 지움
			
			CalendarMemo.top.removeAll();       
			CalendarMemo.mid.removeAll();
			
			CalendarMemo.MONTH++;
			
			if(CalendarMemo.MONTH>12) {
				CalendarMemo.MONTH=1;
				CalendarMemo.YEAR++;
				
			}
			
			//변동된 날짜로 다시 top과 mid를 출력
			CalendarMemo.setCalendar();
			btn.revalidate();
			System.out.println(CalendarMemo.MONTH);
			

		}

		else {
			
			CalendarMemo.top.removeAll();
			CalendarMemo.mid.removeAll();
			
			CalendarMemo.MONTH--;
			
			if(CalendarMemo.MONTH<1) {
				CalendarMemo.MONTH=12;
				CalendarMemo.YEAR--;
			}
			CalendarMemo.setCalendar();
			btn.revalidate();
			System.out.println(CalendarMemo.MONTH);
		}

	}

}
