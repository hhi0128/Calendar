package FinalCalendar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class CalendarMemo extends JFrame {

	Container c = getContentPane();

	private ImageIcon upNormalButtonImage = new ImageIcon("images/upNormalButton.png");
	private ImageIcon upRolloverButtonImage = new ImageIcon("images/upRolloverButton.png");
	private ImageIcon downNormalButtonImage = new ImageIcon("images/downNormalButton.png");
	private ImageIcon downRolloverButtonImage = new ImageIcon("images/downRolloverButton.png");

	public static Calendar caln = Calendar.getInstance();
	public static Calendar cal4 = Calendar.getInstance();
	public static Calendar cal1 = Calendar.getInstance();

	public static GregorianCalendar cal2, cal3;

	// �� �̵� ��ư�� ������ �� ���ϴ� ��¥( ��, ��, ��, ����)
	public static int YEAR = caln.get(Calendar.YEAR);
	public static int MONTH = caln.get(Calendar.MONTH) + 1;
	public static int DATE = caln.get(Calendar.DATE);
	public static int DAY_OF_WEEK = caln.get(Calendar.DAY_OF_WEEK);

	// ���õ� ���� ������ ��¥(�� ���� �ϼ�)
	public static int lastDay = caln.getActualMaximum(Calendar.DAY_OF_MONTH);

	// ���� ��¥(��, ��, ��, ����, �ð�, ��)
	public static int CorrentYEAR = cal1.get(Calendar.YEAR);
	public static int CorrentMONTH = cal1.get(Calendar.MONTH) + 1;
	public static int CorrentDATE = cal1.get(Calendar.DATE);
	public static int CorrentHOUR = cal1.get(Calendar.HOUR_OF_DAY);
	public static int CorrentMINUTE = cal1.get(Calendar.MINUTE);

	// ���콺�� ������ ��¥����(��, ��, ��)
	public static int SellectYEAR = cal4.get(Calendar.YEAR);
	public static int SellectMONTH = cal4.get(Calendar.MONTH) + 1;
	public static int SellectDATE = cal4.get(Calendar.DATE);

	public static JPanel today = new JPanel(); // ���� �ð� ǥ�ÿ�
	public static JPanel top = new JPanel(); // ��, �� ǥ�ÿ�
	public static JPanel midPanel = new JPanel(); // �޷� ���
	public static JPanel dayOfWeekPanel = new JPanel(); // ���� ���
	public static JPanel datePanel[] = new JPanel[35];
	public static JPanel mid = new JPanel(); // �� ���
	public static JPanel bot = new JPanel(); // �޸� ���

	// �� �̵� ��ư
	public JButton up = new JButton(upNormalButtonImage);
	public JButton down = new JButton(downNormalButtonImage);

	// �޷� ���� ��ư
	public static JButton[] date = new JButton[35];

	// �޸� �κ�
	public static JLabel dateSchedule[] = new JLabel[35];
	public static JLabel Memotitle = new JLabel("��¥�� Ŭ���ϸ� MEMO �� ���Ϳ�!");
	public static JTextArea memo = new JTextArea();
	public static JButton save = new JButton("�޸� ����");

	public static int Now = 0; // ������ ��ĥ����

	// ���� �̸� ������
	public static int name = (YEAR % 1000) * 10000 + (MONTH * 100) + DATE;

	public static String[] txt = null;

	// ���� ���
	public static String days[] = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

	// ������ ����
	public static TitledBorder tborder = new TitledBorder("");
	public static EtchedBorder eborder = new EtchedBorder();

	CalendarMemo() {

		setTitle("CALENDAR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setLayout(null);

		setCalendar(); // �޷� ���

		// ���� ���õ� ���� ��¥(��, ��, ��)
		caln = new GregorianCalendar(YEAR, MONTH, DATE);

		// ���� ��¥
		JLabel b = new JLabel("TODAY  " + CorrentYEAR + "-" + CorrentMONTH + "-" + CorrentDATE);
		b.setFont(new Font("Arial", Font.BOLD, 14));
		b.setHorizontalAlignment(JLabel.CENTER);
		b.setForeground(Color.GRAY);

		b.setBounds(20, 0, 160, 30);

		today.setLayout(null);
		today.add(b);
		today.setBackground(Color.WHITE);
		today.setBounds(190, 0, 240, 30);

		// �ð� Ȯ�ο�
		System.out.println(YEAR);
		System.out.println(MONTH);
		System.out.println(DATE);

		// �޷� ��ġ

		mid.setLayout(new GridLayout(5, 7, 0, 0));

		midPanel.add(dayOfWeekPanel);
		midPanel.add(mid);

		// ���� �ĳ� c�� �߰�
		c.add(today);
		c.add(top);
		c.add(midPanel);
		c.add(bot);
		c.setBackground(Color.WHITE);

		// ���õ� year, month
		top.setLayout(new FlowLayout());
		top.setBackground(Color.WHITE);
		top.setBounds(220, 25, 150, 30);

		// �޷� �̵� ��ư
		up.setBackground(Color.WHITE);
		up.setBorder(null);
		up.setRolloverIcon(upRolloverButtonImage);
		up.setLocation(420, 0);
		up.setSize(60, 40);
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// top�� mid�� ����

				CalendarMemo.top.removeAll();
				CalendarMemo.mid.removeAll();

				CalendarMemo.MONTH++;

				if (CalendarMemo.MONTH > 12) {
					CalendarMemo.MONTH = 1;
					CalendarMemo.YEAR++;

				}

				// ������ ��¥�� �ٽ� top�� mid�� ���
				CalendarMemo.setCalendar();
				revalidate();
				System.out.println(CalendarMemo.MONTH);
			}
		});
		c.add(up);

		down.setBackground(Color.WHITE);
		down.setBackground(Color.WHITE);
		down.setBorder(null);
		down.setRolloverIcon(downRolloverButtonImage);
		down.setLocation(110, 0);
		down.setSize(40, 40);
		down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalendarMemo.top.removeAll();
				CalendarMemo.mid.removeAll();

				CalendarMemo.MONTH--;

				if (CalendarMemo.MONTH < 1) {
					CalendarMemo.MONTH = 12;
					CalendarMemo.YEAR--;
				}
				CalendarMemo.setCalendar();
				revalidate();
				System.out.println(CalendarMemo.MONTH);
			}
		});
		c.add(down);

		// ���� ��ġ��
		JLabel[] day = new JLabel[7];

		for (int i = 0; i < day.length; i++) {
			day[i] = new JLabel(days[i]);
			dayOfWeekPanel.add(day[i]);

			day[i].setSize(100, 10);
			day[i].setBackground(Color.WHITE);
			day[i].setOpaque(true);
			day[i].setForeground(Color.BLACK);
			day[i].setHorizontalAlignment(JLabel.CENTER);
			day[i].setVerticalAlignment(JLabel.TOP);
			day[i].setFont(new Font("Arial", Font.BOLD, 15));

			if (i == 0)
				day[i].setForeground(Color.RED);
		}

		// �޷� ���
		midPanel.setBackground(Color.WHITE);
		midPanel.setLayout(null);
		midPanel.setBounds(40, 70, 500, 430);

		// ���� ���
		dayOfWeekPanel.setBackground(Color.WHITE);
		dayOfWeekPanel.setLayout(new GridLayout(1, 7));
		dayOfWeekPanel.setBounds(0, 0, 500, 25);

		// �� ��¿�
		mid.setBackground(Color.WHITE);
		mid.setBounds(0, 25, 500, 400);

		tborder.setTitle("������ �Է��ϼ���.");
		tborder.setTitlePosition(TitledBorder.ABOVE_TOP);
		tborder.setTitleJustification(TitledBorder.CENTER);

		memo.setSize(500, 100);
		memo.setLocation(0, 50);
		memo.setBorder(tborder);
		memo.setVisible(true);

		JButton save = new JButton("Save");
		save.addActionListener(new SaveMemo());
		save.setBounds(195, 150, 100, 30);
		save.setVisible(true);
		save.setBackground(new Color(200, 200, 200));

		bot.setLayout(null);
		bot.add(memo);
		bot.add(save);

		bot.setBackground(Color.WHITE);
		bot.setBounds(45, 460, 500, 500);
		bot.setVisible(true);

		// â ũ��
		setSize(600, 700);
		setVisible(true);

	}

	// �޷� ��� ĸ��ȭ
	public static void setCalendar() {

		// ���õ� �� ����
		caln = new GregorianCalendar(YEAR, MONTH - 1, DATE);
		int lastDay = caln.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println(lastDay);

		date = new JButton[35];

		////
		// �� ���� ù°�� Ȯ�ο�
		cal2 = new GregorianCalendar(YEAR, MONTH - 1, 1);
		int firstDay = cal2.get(Calendar.DAY_OF_WEEK);
		System.out.println(firstDay);

		// ���õ� �ؿ� �� ���
		JLabel yearMonth = new JLabel("" + YEAR + " / " + MONTH + "  ");

		yearMonth.setBackground(Color.WHITE);
		yearMonth.setFont(new Font("���ü", Font.BOLD, 20));
		top.add(yearMonth);

		for (int i = 0; i < 35; i++) {
			datePanel[i] = new JPanel();
			datePanel[i].setBorder(eborder);
			datePanel[i].setBackground(Color.WHITE);
			datePanel[i].setLayout(null);
		}
		// �޷� �����
		for (int i = 1; i < date.length; i++) {

			if (i < firstDay) {
				date[i] = new JButton("");
				date[i].setBorder(eborder);
				date[i].setBackground(Color.WHITE);

				dateSchedule[i] = new JLabel();
				dateSchedule[i].setBounds(5, 55, 60, 20);
				dateSchedule[i].setForeground(Color.BLACK);
				dateSchedule[i].setBackground(Color.WHITE);
				dateSchedule[i].setHorizontalAlignment(JLabel.CENTER);
				dateSchedule[i].setFont(new Font("Arail", Font.BOLD, 10));
				dateSchedule[i].setOpaque(true);

			} else if (i >= firstDay) {
				date[i] = new JButton("" + (i - firstDay + 1));
				date[i].setBounds(5, 5, 60, 50);
				date[i].setBorder(null);
				date[i].setHorizontalAlignment(JButton.LEFT);
				date[i].setVerticalAlignment(JButton.TOP);
				date[i].setBackground(Color.WHITE);
				date[i].addActionListener(new ClickDate());
				
				
				File file;
				char[] ch;
				
				String dfName = "." + "//memories//" + Integer.toString((CalendarMemo.YEAR % 1000) * 10000
						+ (CalendarMemo.MONTH * 100) + i - firstDay + 1) + ".txt";
				file = new File(dfName);
				ch = new char[(int) file.length()];

				// ���ۿ� ���� ���� ����
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

				// ���ۿ� ����� ������ �޸�� ���
				String s = new String(ch);

				
				

				dateSchedule[i] = new JLabel("");
				dateSchedule[i].setBounds(5, 55, 60, 20);
				dateSchedule[i].setForeground(Color.BLACK);
				dateSchedule[i].setBackground(Color.WHITE);
				dateSchedule[i].setHorizontalAlignment(JLabel.CENTER);
				dateSchedule[i].setFont(new Font("Arail", Font.BOLD, 10));
				dateSchedule[i].setOpaque(true);
				dateSchedule[i].setText(s);
				dateSchedule[i].setBackground(new Color(217, 217, 217));

				if (i - firstDay + 1 == DATE && CorrentMONTH == MONTH) {
					Font font = new Font("Arial", Font.BOLD, 20);
					date[i].setForeground(Color.BLUE);
					date[i].setFont(font);
				}

				else if (i - firstDay + 1 > lastDay) {
					date[i] = new JButton("");
					date[i].setBackground(Color.WHITE);
				}
			}

			datePanel[i].add(date[i]);
			datePanel[i].add(dateSchedule[i]);
			mid.add(datePanel[i]);

		}

		// �Ͽ����� ����������
		for (

				int i = 1; i < date.length; i++) {

			if (i < firstDay) {
				continue;
			} else if (i >= firstDay) {

				cal3 = new GregorianCalendar(YEAR, MONTH - 1, i - firstDay + 1);

				// ù��° �Ͽ��� ã��
				int firstSunday = cal3.get(Calendar.DAY_OF_WEEK);

				// DAY_OF_WEEK �� 1 �̸� �Ͽ���
				if (firstSunday == 1) {
					date[i].setForeground(Color.RED);
				}
			}
		}
	}

}
