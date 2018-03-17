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

	// 월 이동 버튼을 눌렀을 때 변하는 날짜( 년, 월, 일, 요일)
	public static int YEAR = caln.get(Calendar.YEAR);
	public static int MONTH = caln.get(Calendar.MONTH) + 1;
	public static int DATE = caln.get(Calendar.DATE);
	public static int DAY_OF_WEEK = caln.get(Calendar.DAY_OF_WEEK);

	// 선택된 달의 마지막 날짜(그 달의 일수)
	public static int lastDay = caln.getActualMaximum(Calendar.DAY_OF_MONTH);

	// 현재 날짜(년, 월, 일, 요일, 시간, 분)
	public static int CorrentYEAR = cal1.get(Calendar.YEAR);
	public static int CorrentMONTH = cal1.get(Calendar.MONTH) + 1;
	public static int CorrentDATE = cal1.get(Calendar.DATE);
	public static int CorrentHOUR = cal1.get(Calendar.HOUR_OF_DAY);
	public static int CorrentMINUTE = cal1.get(Calendar.MINUTE);

	// 마우스로 선택한 날짜관련(년, 월, 일)
	public static int SellectYEAR = cal4.get(Calendar.YEAR);
	public static int SellectMONTH = cal4.get(Calendar.MONTH) + 1;
	public static int SellectDATE = cal4.get(Calendar.DATE);

	public static JPanel today = new JPanel(); // 지금 시간 표시용
	public static JPanel top = new JPanel(); // 년, 월 표시용
	public static JPanel midPanel = new JPanel(); // 달력 출력
	public static JPanel dayOfWeekPanel = new JPanel(); // 요일 출력
	public static JPanel datePanel[] = new JPanel[35];
	public static JPanel mid = new JPanel(); // 일 출력
	public static JPanel bot = new JPanel(); // 메모 출력

	// 월 이동 버튼
	public JButton up = new JButton(upNormalButtonImage);
	public JButton down = new JButton(downNormalButtonImage);

	// 달력 생성 버튼
	public static JButton[] date = new JButton[35];

	// 메모 부분
	public static JLabel dateSchedule[] = new JLabel[35];
	public static JLabel Memotitle = new JLabel("날짜를 클릭하면 MEMO 가 나와요!");
	public static JTextArea memo = new JTextArea();
	public static JButton save = new JButton("메모 저장");

	public static int Now = 0; // 지금이 며칠인지

	// 파일 이름 생성용
	public static int name = (YEAR % 1000) * 10000 + (MONTH * 100) + DATE;

	public static String[] txt = null;

	// 요일 출력
	public static String days[] = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

	// 윤곽선 정의
	public static TitledBorder tborder = new TitledBorder("");
	public static EtchedBorder eborder = new EtchedBorder();

	CalendarMemo() {

		setTitle("CALENDAR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setLayout(null);

		setCalendar(); // 달력 출력

		// 현재 선택된 달의 날짜(년, 월, 일)
		caln = new GregorianCalendar(YEAR, MONTH, DATE);

		// 현재 날짜
		JLabel b = new JLabel("TODAY  " + CorrentYEAR + "-" + CorrentMONTH + "-" + CorrentDATE);
		b.setFont(new Font("Arial", Font.BOLD, 14));
		b.setHorizontalAlignment(JLabel.CENTER);
		b.setForeground(Color.GRAY);

		b.setBounds(20, 0, 160, 30);

		today.setLayout(null);
		today.add(b);
		today.setBackground(Color.WHITE);
		today.setBounds(190, 0, 240, 30);

		// 시간 확인용
		System.out.println(YEAR);
		System.out.println(MONTH);
		System.out.println(DATE);

		// 달력 배치

		mid.setLayout(new GridLayout(5, 7, 0, 0));

		midPanel.add(dayOfWeekPanel);
		midPanel.add(mid);

		// 관련 파넬 c에 추가
		c.add(today);
		c.add(top);
		c.add(midPanel);
		c.add(bot);
		c.setBackground(Color.WHITE);

		// 선택된 year, month
		top.setLayout(new FlowLayout());
		top.setBackground(Color.WHITE);
		top.setBounds(220, 25, 150, 30);

		// 달력 이동 버튼
		up.setBackground(Color.WHITE);
		up.setBorder(null);
		up.setRolloverIcon(upRolloverButtonImage);
		up.setLocation(420, 0);
		up.setSize(60, 40);
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// top과 mid를 지움

				CalendarMemo.top.removeAll();
				CalendarMemo.mid.removeAll();

				CalendarMemo.MONTH++;

				if (CalendarMemo.MONTH > 12) {
					CalendarMemo.MONTH = 1;
					CalendarMemo.YEAR++;

				}

				// 변동된 날짜로 다시 top과 mid를 출력
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

		// 요일 배치용
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

		// 달력 출력
		midPanel.setBackground(Color.WHITE);
		midPanel.setLayout(null);
		midPanel.setBounds(40, 70, 500, 430);

		// 요일 출력
		dayOfWeekPanel.setBackground(Color.WHITE);
		dayOfWeekPanel.setLayout(new GridLayout(1, 7));
		dayOfWeekPanel.setBounds(0, 0, 500, 25);

		// 일 출력용
		mid.setBackground(Color.WHITE);
		mid.setBounds(0, 25, 500, 400);

		tborder.setTitle("일정을 입력하세요.");
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

		// 창 크기
		setSize(600, 700);
		setVisible(true);

	}

	// 달력 출력 캡슐화
	public static void setCalendar() {

		// 선택된 달 관련
		caln = new GregorianCalendar(YEAR, MONTH - 1, DATE);
		int lastDay = caln.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println(lastDay);

		date = new JButton[35];

		////
		// 그 달의 첫째날 확인용
		cal2 = new GregorianCalendar(YEAR, MONTH - 1, 1);
		int firstDay = cal2.get(Calendar.DAY_OF_WEEK);
		System.out.println(firstDay);

		// 선택된 해와 달 출력
		JLabel yearMonth = new JLabel("" + YEAR + " / " + MONTH + "  ");

		yearMonth.setBackground(Color.WHITE);
		yearMonth.setFont(new Font("고딕체", Font.BOLD, 20));
		top.add(yearMonth);

		for (int i = 0; i < 35; i++) {
			datePanel[i] = new JPanel();
			datePanel[i].setBorder(eborder);
			datePanel[i].setBackground(Color.WHITE);
			datePanel[i].setLayout(null);
		}
		// 달력 만들기
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

				// 버퍼에 파일 내용 저장
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

				// 버퍼에 저장된 내용을 메모로 출력
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

		// 일요일은 빨간색으로
		for (

				int i = 1; i < date.length; i++) {

			if (i < firstDay) {
				continue;
			} else if (i >= firstDay) {

				cal3 = new GregorianCalendar(YEAR, MONTH - 1, i - firstDay + 1);

				// 첫번째 일요일 찾기
				int firstSunday = cal3.get(Calendar.DAY_OF_WEEK);

				// DAY_OF_WEEK 가 1 이면 일요일
				if (firstSunday == 1) {
					date[i].setForeground(Color.RED);
				}
			}
		}
	}

}
