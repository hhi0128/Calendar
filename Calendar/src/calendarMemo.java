import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;


// ���� �б�, ����
import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class calendarMemo extends JFrame {

	Container c = getContentPane();

	Color b = new Color(180, 180, 180);


	public static Calendar caln = Calendar.getInstance();
	static Calendar cal4 = Calendar.getInstance();
	static Calendar cal1 = Calendar.getInstance();

	private GregorianCalendar cal2, cal3;

	//�� �̵� ��ư�� ������ �� ���ϴ� ��¥( ��, ��, ��, ����)
	static int YEAR = caln.get(Calendar.YEAR);
	static int MONTH = caln.get(Calendar.MONTH) + 1;
	static int DATE = caln.get(Calendar.DATE);
	static int DAY_OF_WEEK = caln.get(Calendar.DAY_OF_WEEK);

	

	//���õ� ���� ������ ��¥(�� ���� �ϼ�)
	static int lastDay = caln.getActualMaximum(Calendar.DAY_OF_MONTH);


	//���� ��¥(��, ��, ��, ����, �ð�, ��)
	static int CorrentYEAR = cal1.get(Calendar.YEAR);
	static int CorrentMONTH = cal1.get(Calendar.MONTH) + 1;
	static int CorrentDATE = cal1.get(Calendar.DATE);
	static int CorrentHOUR = cal1.get(Calendar.HOUR_OF_DAY);
	static int CorrentMINUTE = cal1.get(Calendar.MINUTE);

	
	//���콺�� ������ ��¥����(��, ��, ��)
	static int SellectYEAR = cal4.get(Calendar.YEAR);
	static int SellectMONTH = cal4.get(Calendar.MONTH) + 1;
	static int SellectDATE = cal4.get(Calendar.DATE);

	
	JPanel today = new JPanel(); //���� �ð� ǥ�ÿ�
	JPanel top = new JPanel(); // ��, �� ǥ�ÿ�
	JPanel mid = new JPanel(); // �޷� ���
	JPanel bot = new JPanel(); // �޸� ���

	
	//�� �̵� ��ư
	static JButton up = new JButton("UP"); 
	static JButton down = new JButton("DOWN");

	//�޷� ���� ��ư
	JButton[] date = new JButton[35];

	//�޸� �κ�
	JLabel Memotitle = new JLabel("��¥�� Ŭ���ϸ� MEMO �� ���Ϳ�!");
	JTextArea memo = new JTextArea();
	JButton save = new JButton("�޸� ����");

	
	int Now = 0; // ������ ��ĥ����

	//���� �̸� ������
	int name = (YEAR % 1000) * 10000 + (MONTH * 100) + DATE;

	
	public String[] txt = null;

	
	//���� ���
	private String days[] = { "��", "��", "ȭ", "��", "��", "��", "��" };

	
	
	
	calendarMemo() {

		setTitle("CALENDAR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setLayout(null);

		
		//////////////////
		
		
		setCalendar();  //�޷� ���
	

		//////////////////

		
		//���� ���õ� ���� ��¥(��, ��, ��)
		caln = new GregorianCalendar(YEAR, MONTH, DATE);
		
		
		
		//���� �ð�
		JLabel a = new JLabel("TODAY  " + CorrentYEAR + "�� " + CorrentMONTH + "�� " + CorrentDATE + "�� " + CorrentHOUR
				+ "�� " + CorrentMINUTE + "�� ");

		today.add(a);
		today.setBackground(b);
		today.setLocation(200, 0);
		today.setSize(210, 40);

		
		// �ð� Ȯ�ο�

		System.out.println(YEAR);
		System.out.println(MONTH);
		System.out.println(DATE);

		//�޷� ��ġ
		mid.setLayout(new GridLayout(7, 7, 0, 0));
		
		//���� �ĳ� c�� �߰�
		c.add(today);
		c.add(top);
		c.add(mid);
		c.add(bot);
		c.setBackground(b);

		//���õ� year, month
		top.setLayout(new GridLayout(1, 2, 0, 0));
		top.setBackground(b);
		top.setLocation(0, 40);
		top.setSize(150, 30);

		
		//////////////////////////////                   �޷� �̵� ��ư
		up.setBackground(b);
		up.setLocation(270, 40);
		up.setSize(60, 40);
		up.addActionListener(new updown());
		c.add(up);

		down.setBackground(b);
		down.setLocation(330, 40);
		down.setSize(80, 40);
		down.addActionListener(new updown());
		c.add(down);

		//////////////////////////////
		
		
		//�޷� ��¿� 
		mid.setBackground(b);
		mid.setLocation(0, 90);
		mid.setSize(400, 300);

		
		//�޸� ��¿�
		Memotitle.setSize(200, 30);
		Memotitle.setLocation(10, 20);
		Memotitle.setVisible(true);

		memo = new JTextArea("�޸� �ۼ��ϼ���  ");

		memo.setSize(400, 250);
		memo.setLocation(0, 50);
		memo.setVisible(true);

		JButton save = new JButton("�޸� ����");
		save.addActionListener(new saveMemo());
		save.setSize(100, 30);
		save.setLocation(300, 300);
		save.setVisible(true);
		save.setBackground(new Color(200, 200, 200));
		bot.setLayout(null);

		bot.add(Memotitle);
		bot.add(memo);
		bot.add(save);

		bot.setBackground(b);
		bot.setLocation(0, 400);
		bot.setVisible(true);
		bot.setSize(400, 500);

		
		//â ũ��
		setSize(420, 800);
		setVisible(true);

	}

	//�޷� �̵� ��ư ���� Ŭ����
	class updown implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {

			JButton btn = (JButton) arg0.getSource();
			if (btn.getText().equals("UP")) {
				
				//top�� mid�� ����
				
				top.removeAll();       
				mid.removeAll();
				
				MONTH++;
				
				if(MONTH>12) {
					MONTH=1;
					YEAR++;
					
					
				}
				
				//������ ��¥�� �ٽ� top�� mid�� ���
				setCalendar();
				revalidate();
				System.out.println(MONTH);
				
				

			}

			else {
				
				top.removeAll();
				mid.removeAll();
				
				MONTH--;
				
				if(MONTH<1) {
					MONTH=12;
					YEAR--;
				}
				setCalendar();
				revalidate();
				System.out.println(MONTH);
			}

		}

	}

	
	//�޸����� Ŭ����
	class saveMemo implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			JButton b = (JButton) e.getSource();

			if (b.getText().equals("�޸� ����")) {
				memo.getText();
				new FileWriterTest(); //���� ���� Ŭ����
			}

		}

	}
	
	
	//��¥ ���� Ŭ����
	class clickdate implements ActionListener {

		int firstDay = cal2.get(Calendar.DAY_OF_WEEK);

		public void actionPerformed(ActionEvent e) {

			JButton c = (JButton) e.getSource();

			for (int i = 1; i < date.length; i++) {
				if (c.getText().equals("" + (i - firstDay + 1))) {

					date[i].setBackground(Color.GREEN);

					SellectDATE = i - firstDay + 1;

					cal4 = new GregorianCalendar(SellectYEAR, SellectMONTH - 1, SellectDATE);

					Now = i; //���� ���õ� ��¥�� now�� �������ش�
					
					Memotitle.setText(MONTH + "�� " + SellectDATE + "�� " + "MEMO");

					System.out.println(Now);

					new FileReaderTest();

				} else if (i - firstDay + 1 == DATE) {

					date[i].setBackground(new Color(150, 150, 220));

				} else
					date[i].setBackground(b);

			}
		}
	}

	
	//���� ���� Ŭ����
	class FileWriterTest {

		{
			for (int i = 1; i < date.length; i++)

			{
				if (Now == i) { //���õ� ��¥�� �̸����� ������ ����
					String txt = memo.getText();

					//��� ����
					String fileName = "."+"//memories//"+ 
							 Integer.toString((SellectYEAR % 1000) * 10000 + (MONTH * 100) + SellectDATE)+".txt";

					try {

						// BufferedWriter �� FileWriter�� �����Ͽ� ��� (�ӵ� ���)
						BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, false));

						// ���Ͼȿ� ���ڿ� ����
						fw.write(txt);
						fw.flush();

						// ��ü �ݱ�
						fw.close();

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}

		}

	}

	
	//���� �б� Ŭ����
	class FileReaderTest {
		{
			File file;
			char[] ch;

			for (int i = 1; i < date.length; i++) {
				
				
				//���õ� ��¥�� ������ �ҷ���
			
				if (Now == i) { 
					String dfName = "."+"//memories//"+
							 Integer.toString((SellectYEAR % 1000) * 10000 + (MONTH * 100) + SellectDATE)+".txt";
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
						System.out.println("sample.txt ������ �������� �ʽ��ϴ�.");
						e1.printStackTrace();

					} catch (IOException e) {
						System.out.println("sample.txt ������ �б⸦ ������ �� �����ϴ�.");
						e.printStackTrace();
					}

					//���ۿ� ����� ������ �޸�� ���
					String s = new String(ch);

					memo.setText(s);

				}

			}
		}

	}
	
	//�޷� ��� ĸ��ȭ
	void setCalendar() { 

		//���õ� �� ����
		caln = new GregorianCalendar(YEAR, MONTH-1, DATE);
		int lastDay = caln.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println(lastDay);
		
		//�޷� ������
		date = new JButton[42];
		
		////
		//�� ���� ù°�� Ȯ�ο�
		cal2 = new GregorianCalendar(YEAR, MONTH - 1, 1);
		int firstDay = cal2.get(Calendar.DAY_OF_WEEK);
		System.out.println(firstDay);
		
		//���õ� �ؿ� �� ���
		JButton year = new JButton("" + YEAR + "��");
		JButton month = new JButton("" + MONTH + "��");
		
		year.setBackground(b);
		month.setBackground(b);
		top.add(year);
		top.add(month);
		
		//���� ��ġ��
		JButton[] day = new JButton[7];

		for (int i = 0; i < day.length; i++) {
			day[i] = new JButton(days[i]);
			mid.add(day[i]);
			day[i].setEnabled(false);
			day[i].setBackground(new Color(10, 50, 100));

		}

		
		//�޷� �����
		for (int i = 1; i < date.length; i++) {

			if (i < firstDay) {
				date[i] = new JButton("");
				date[i].setBackground(b);

			} else if (i >= firstDay) {
				date[i] = new JButton("" + (i - firstDay + 1));
				date[i].setSize(20, 20);
				date[i].setBackground(b);
				date[i].addActionListener(new clickdate());

				if (i - firstDay + 1 == DATE) {
					Font font = new Font("����ü", Font.ITALIC, 18);
					date[i].setForeground(Color.BLUE);
					date[i].setBackground(new Color(150, 150, 220));
					date[i].setFont(font);
				}

				else if (i - firstDay + 1 > lastDay) {
					date[i] = new JButton("");
					date[i].setBackground(b);
				}
			}
			mid.add(date[i]);

		}

		//�Ͽ����� ����������
		for (int i = 1; i < date.length; i++) {

			if (i < firstDay) {
				continue;
			} else if (i >= firstDay) {

				cal3 = new GregorianCalendar(YEAR, MONTH - 1, i - firstDay + 1);

				//ù��° �Ͽ��� ã��
				int firstSunday = cal3.get(Calendar.DAY_OF_WEEK);

				//DAY_OF_WEEK �� 1 �̸� �Ͽ���
				if (firstSunday == 1) {
					date[i].setForeground(Color.RED);
				}
			}
		}
	}
		
		

	public static void main(String[] srgs) {
		new calendarMemo();

	}
}




	

