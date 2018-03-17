import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;


// 파일 읽기, 쓰기
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

	//월 이동 버튼을 눌렀을 때 변하는 날짜( 년, 월, 일, 요일)
	static int YEAR = caln.get(Calendar.YEAR);
	static int MONTH = caln.get(Calendar.MONTH) + 1;
	static int DATE = caln.get(Calendar.DATE);
	static int DAY_OF_WEEK = caln.get(Calendar.DAY_OF_WEEK);

	

	//선택된 달의 마지막 날짜(그 달의 일수)
	static int lastDay = caln.getActualMaximum(Calendar.DAY_OF_MONTH);


	//현재 날짜(년, 월, 일, 요일, 시간, 분)
	static int CorrentYEAR = cal1.get(Calendar.YEAR);
	static int CorrentMONTH = cal1.get(Calendar.MONTH) + 1;
	static int CorrentDATE = cal1.get(Calendar.DATE);
	static int CorrentHOUR = cal1.get(Calendar.HOUR_OF_DAY);
	static int CorrentMINUTE = cal1.get(Calendar.MINUTE);

	
	//마우스로 선택한 날짜관련(년, 월, 일)
	static int SellectYEAR = cal4.get(Calendar.YEAR);
	static int SellectMONTH = cal4.get(Calendar.MONTH) + 1;
	static int SellectDATE = cal4.get(Calendar.DATE);

	
	JPanel today = new JPanel(); //지금 시간 표시용
	JPanel top = new JPanel(); // 년, 월 표시용
	JPanel mid = new JPanel(); // 달력 출력
	JPanel bot = new JPanel(); // 메모 출력

	
	//월 이동 버튼
	static JButton up = new JButton("UP"); 
	static JButton down = new JButton("DOWN");

	//달력 생성 버튼
	JButton[] date = new JButton[35];

	//메모 부분
	JLabel Memotitle = new JLabel("날짜를 클릭하면 MEMO 가 나와요!");
	JTextArea memo = new JTextArea();
	JButton save = new JButton("메모 저장");

	
	int Now = 0; // 지금이 며칠인지

	//파일 이름 생성용
	int name = (YEAR % 1000) * 10000 + (MONTH * 100) + DATE;

	
	public String[] txt = null;

	
	//요일 출력
	private String days[] = { "일", "월", "화", "수", "목", "금", "토" };

	
	
	
	calendarMemo() {

		setTitle("CALENDAR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setLayout(null);

		
		//////////////////
		
		
		setCalendar();  //달력 출력
	

		//////////////////

		
		//현재 선택된 달의 날짜(년, 월, 일)
		caln = new GregorianCalendar(YEAR, MONTH, DATE);
		
		
		
		//현재 시간
		JLabel a = new JLabel("TODAY  " + CorrentYEAR + "년 " + CorrentMONTH + "월 " + CorrentDATE + "일 " + CorrentHOUR
				+ "시 " + CorrentMINUTE + "분 ");

		today.add(a);
		today.setBackground(b);
		today.setLocation(200, 0);
		today.setSize(210, 40);

		
		// 시간 확인용

		System.out.println(YEAR);
		System.out.println(MONTH);
		System.out.println(DATE);

		//달력 배치
		mid.setLayout(new GridLayout(7, 7, 0, 0));
		
		//관련 파넬 c에 추가
		c.add(today);
		c.add(top);
		c.add(mid);
		c.add(bot);
		c.setBackground(b);

		//선택된 year, month
		top.setLayout(new GridLayout(1, 2, 0, 0));
		top.setBackground(b);
		top.setLocation(0, 40);
		top.setSize(150, 30);

		
		//////////////////////////////                   달력 이동 버튼
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
		
		
		//달력 출력용 
		mid.setBackground(b);
		mid.setLocation(0, 90);
		mid.setSize(400, 300);

		
		//메모 출력용
		Memotitle.setSize(200, 30);
		Memotitle.setLocation(10, 20);
		Memotitle.setVisible(true);

		memo = new JTextArea("메모를 작성하세요  ");

		memo.setSize(400, 250);
		memo.setLocation(0, 50);
		memo.setVisible(true);

		JButton save = new JButton("메모 저장");
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

		
		//창 크기
		setSize(420, 800);
		setVisible(true);

	}

	//달력 이동 버튼 관련 클래스
	class updown implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {

			JButton btn = (JButton) arg0.getSource();
			if (btn.getText().equals("UP")) {
				
				//top과 mid를 지움
				
				top.removeAll();       
				mid.removeAll();
				
				MONTH++;
				
				if(MONTH>12) {
					MONTH=1;
					YEAR++;
					
					
				}
				
				//변동된 날짜로 다시 top과 mid를 출력
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

	
	//메모저장 클래스
	class saveMemo implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			JButton b = (JButton) e.getSource();

			if (b.getText().equals("메모 저장")) {
				memo.getText();
				new FileWriterTest(); //파일 쓰기 클래스
			}

		}

	}
	
	
	//날짜 선택 클래스
	class clickdate implements ActionListener {

		int firstDay = cal2.get(Calendar.DAY_OF_WEEK);

		public void actionPerformed(ActionEvent e) {

			JButton c = (JButton) e.getSource();

			for (int i = 1; i < date.length; i++) {
				if (c.getText().equals("" + (i - firstDay + 1))) {

					date[i].setBackground(Color.GREEN);

					SellectDATE = i - firstDay + 1;

					cal4 = new GregorianCalendar(SellectYEAR, SellectMONTH - 1, SellectDATE);

					Now = i; //현재 선택된 날짜를 now에 저장해준다
					
					Memotitle.setText(MONTH + "월 " + SellectDATE + "일 " + "MEMO");

					System.out.println(Now);

					new FileReaderTest();

				} else if (i - firstDay + 1 == DATE) {

					date[i].setBackground(new Color(150, 150, 220));

				} else
					date[i].setBackground(b);

			}
		}
	}

	
	//파일 쓰기 클래스
	class FileWriterTest {

		{
			for (int i = 1; i < date.length; i++)

			{
				if (Now == i) { //선택된 날짜를 이름으로 파일을 생성
					String txt = memo.getText();

					//경로 지정
					String fileName = "."+"//memories//"+ 
							 Integer.toString((SellectYEAR % 1000) * 10000 + (MONTH * 100) + SellectDATE)+".txt";

					try {

						// BufferedWriter 와 FileWriter를 조합하여 사용 (속도 향상)
						BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, false));

						// 파일안에 문자열 쓰기
						fw.write(txt);
						fw.flush();

						// 객체 닫기
						fw.close();

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}

		}

	}

	
	//파일 읽기 클래스
	class FileReaderTest {
		{
			File file;
			char[] ch;

			for (int i = 1; i < date.length; i++) {
				
				
				//선택된 날짜의 파일을 불러옴
			
				if (Now == i) { 
					String dfName = "."+"//memories//"+
							 Integer.toString((SellectYEAR % 1000) * 10000 + (MONTH * 100) + SellectDATE)+".txt";
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
						System.out.println("sample.txt 파일이 존재하지 않습니다.");
						e1.printStackTrace();

					} catch (IOException e) {
						System.out.println("sample.txt 파일의 읽기를 수행할 수 없습니다.");
						e.printStackTrace();
					}

					//버퍼에 저장된 내용을 메모로 출력
					String s = new String(ch);

					memo.setText(s);

				}

			}
		}

	}
	
	//달력 출력 캡슐화
	void setCalendar() { 

		//선택된 달 관련
		caln = new GregorianCalendar(YEAR, MONTH-1, DATE);
		int lastDay = caln.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println(lastDay);
		
		//달력 생성용
		date = new JButton[42];
		
		////
		//그 달의 첫째날 확인용
		cal2 = new GregorianCalendar(YEAR, MONTH - 1, 1);
		int firstDay = cal2.get(Calendar.DAY_OF_WEEK);
		System.out.println(firstDay);
		
		//선택된 해와 달 출력
		JButton year = new JButton("" + YEAR + "년");
		JButton month = new JButton("" + MONTH + "월");
		
		year.setBackground(b);
		month.setBackground(b);
		top.add(year);
		top.add(month);
		
		//요일 배치용
		JButton[] day = new JButton[7];

		for (int i = 0; i < day.length; i++) {
			day[i] = new JButton(days[i]);
			mid.add(day[i]);
			day[i].setEnabled(false);
			day[i].setBackground(new Color(10, 50, 100));

		}

		
		//달력 만들기
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
					Font font = new Font("굴림체", Font.ITALIC, 18);
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

		//일요일은 빨간색으로
		for (int i = 1; i < date.length; i++) {

			if (i < firstDay) {
				continue;
			} else if (i >= firstDay) {

				cal3 = new GregorianCalendar(YEAR, MONTH - 1, i - firstDay + 1);

				//첫번째 일요일 찾기
				int firstSunday = cal3.get(Calendar.DAY_OF_WEEK);

				//DAY_OF_WEEK 가 1 이면 일요일
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




	

