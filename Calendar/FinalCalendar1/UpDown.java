package FinalCalendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

//�޷� �̵� ��ư ���� Ŭ����
class UpDown implements ActionListener {

	public void actionPerformed(ActionEvent arg0) {

		JButton btn = (JButton) arg0.getSource();
		if (btn.getText().equals("UP")) {
			
			//top�� mid�� ����
			
			CalendarMemo.top.removeAll();       
			CalendarMemo.mid.removeAll();
			
			CalendarMemo.MONTH++;
			
			if(CalendarMemo.MONTH>12) {
				CalendarMemo.MONTH=1;
				CalendarMemo.YEAR++;
				
			}
			
			//������ ��¥�� �ٽ� top�� mid�� ���
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
