package FinalCalendar1;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

//�޸����� Ŭ����
class SaveMemo implements ActionListener {

	public void actionPerformed(ActionEvent e) {

		JButton b = (JButton) e.getSource();

		if (b.getText().equals("Save")) {
			CalendarMemo.memo.getText();
			new FileWriterTest(); //���� ���� Ŭ����
			
		
			
					
				}

			}
			
			
		
	}




