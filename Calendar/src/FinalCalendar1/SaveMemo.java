package FinalCalendar1;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

//메모저장 클래스
class SaveMemo implements ActionListener {

	public void actionPerformed(ActionEvent e) {

		JButton b = (JButton) e.getSource();

		if (b.getText().equals("Save")) {
			CalendarMemo.memo.getText();
			new FileWriterTest(); //파일 쓰기 클래스
			
		
			
					
				}

			}
			
			
		
	}




