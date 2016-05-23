package broker.client.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import broker.dao.Database;
import broker.exception.RecordNotFoundException;
import broker.vo.Customer;
import broker.vo.Shares;
import broker.vo.Stock;

import javax.swing.JFormattedTextField;
import net.miginfocom.swing.MigLayout;

import java.awt.Font;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.JTextArea;

public class BrockerUI implements ActionListener {

	private JFrame frame;

	// 상단광고
	private JPanel top_panel;

	// 거래가능주식목록
	private JPanel stock_area;
	private JButton 거래주;
	private JScrollPane scrollPane;

	// 전체고객명단, 포트폴리오
	private JPanel body_panel;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JList list_1; 
	
	private JTextField 등록번호필드;
	private JTextField 성명필드;
	private JTextField 주식명필드;
	private JTextField 거래가필드;
	private JTextField 거래주필드;
	private JTextField 거래수량필드;
	private JButton 선택;
	private JButton 취소;

	// 사용자조작부
	private JPanel conrol_area;
	private JButton 추가;
	private JButton 수정;
	private JButton 삭제;
	private JButton 매입;
	private JButton 매수;

	// DB객체(임시클라매니저대용)
	Database db = new Database();

	private DefaultListModel<Stock> dlm;//주식리스트담는GUI객체
	private DefaultListModel<Customer> dlm2;
	private DefaultListModel<Shares> dlm3;
	private JList list_2;

	private ArrayList<Customer> cusList;

	private JTextArea 주소명필드;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrockerUI window = new BrockerUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BrockerUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setType(Type.UTILITY);
		frame.setTitle("주식매매프로그램");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 743, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		top_panel = new JPanel();
		top_panel.setBackground(Color.ORANGE);
		top_panel.setForeground(Color.DARK_GRAY);
		top_panel.setBounds(0, 0, 727, 26);
		frame.getContentPane().add(top_panel);

		body_panel = new JPanel();
		body_panel.setBackground(Color.GRAY);
		body_panel.setBounds(0, 19, 727, 307);
		frame.getContentPane().add(body_panel);
		body_panel.setLayout(null);

		stock_area = new JPanel();
		stock_area.setBounds(12, 10, 188, 283);
		body_panel.add(stock_area);
		stock_area.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 24, 164, 156);
		stock_area.add(scrollPane);
		//주식리스트 
		dlm = new DefaultListModel<Stock>();
		ArrayList<Stock> stockList;
		try {
			stockList = db.getAllStock();
			for (Stock e : stockList) {
				dlm.addElement(e);
			}
		} catch (RecordNotFoundException e1) {
			e1.printStackTrace();
		}
		JList list = new JList(dlm);
		list.setFont(new Font("나눔바른고딕", Font.PLAIN, 11));
		scrollPane.setViewportView(list);

		JLabel label = new JLabel("거래 가능 주식목록");
		label.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 12));
		label.setBounds(41, 6, 118, 15);
		stock_area.add(label);

		거래주 = new JButton("거래주 선택");
		거래주.setFont(new Font("나눔고딕", Font.BOLD, 11));
		거래주.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		거래주.setBounds(12, 190, 164, 23);
		stock_area.add(거래주);

		JLabel 주식명 = new JLabel("주식명");
		주식명.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		주식명.setBounds(12, 223, 36, 21);
		stock_area.add(주식명);

		주식명필드 = new JTextField();
		주식명필드.setBounds(51, 223, 125, 21);
		stock_area.add(주식명필드);
		주식명필드.setColumns(10);

		거래가필드 = new JTextField();
		거래가필드.setColumns(10);
		거래가필드.setBounds(51, 254, 125, 21);
		stock_area.add(거래가필드);

		JLabel 거래가 = new JLabel("거래가");
		거래가.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		거래가.setBounds(12, 254, 36, 21);
		stock_area.add(거래가);

		JPanel customer_area = new JPanel();
		customer_area.setBounds(212, 10, 426, 283);
		body_panel.add(customer_area);
		customer_area.setLayout(null);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 26, 252, 153);
		customer_area.add(scrollPane_1);

		// 고객리스트
		dlm2 = new DefaultListModel<Customer>();
		try {
			cusList = db.getAllCustomer();
			for (Customer e : cusList) {
				dlm2.addElement(e);
			}
		} catch (RecordNotFoundException e1) {
			e1.printStackTrace();
		}
		list_1 = new JList(dlm2);
		list_1.addMouseListener(new MouseHandler());
		list_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 11));
		scrollPane_1.setViewportView(list_1);

		

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(276, 26, 138, 153);
		customer_area.add(scrollPane_2);

		// 포트폴리오리스트
		dlm3 = new DefaultListModel<Shares>();
		ArrayList<Shares> portList;
		try {
			portList = db.getAllPortfolio();
			for (Shares e : portList) {
				dlm3.addElement(e);
			}
		} catch (RecordNotFoundException e1) {
			e1.printStackTrace();
		}
		list_2 = new JList(dlm3);
		list_2.setFont(new Font("나눔바른고딕", Font.PLAIN, 11));
		scrollPane_2.setViewportView(list_2);

		JLabel 등록번호 = new JLabel("등록번호");
		등록번호.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		등록번호.setBounds(12, 197, 48, 15);
		customer_area.add(등록번호);
		등록번호.setHorizontalAlignment(SwingConstants.LEFT);

		등록번호필드 = new JTextField();
		등록번호필드.setBounds(58, 193, 80, 21);
		customer_area.add(등록번호필드);
		등록번호필드.setColumns(10);

		JLabel 성명 = new JLabel("성명");
		성명.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		성명.setBounds(150, 197, 24, 15);
		customer_area.add(성명);

		성명필드 = new JTextField();
		성명필드.setBounds(180, 193, 84, 21);
		customer_area.add(성명필드);
		성명필드.setColumns(10);

		JLabel 주소 = new JLabel("주소");
		주소.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		주소.setBounds(12, 228, 24, 15);
		customer_area.add(주소);

		JLabel 거래주명 = new JLabel("거래주명");
		거래주명.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		거래주명.setHorizontalAlignment(SwingConstants.LEFT);
		거래주명.setBounds(276, 197, 48, 15);
		customer_area.add(거래주명);

		거래주필드 = new JTextField();
		거래주필드.setColumns(10);
		거래주필드.setBounds(329, 193, 85, 21);
		customer_area.add(거래주필드);

		거래수량필드 = new JTextField();
		거래수량필드.setColumns(10);
		거래수량필드.setBounds(329, 224, 85, 21);
		customer_area.add(거래수량필드);

		JLabel 거래수량 = new JLabel("거래수량");
		거래수량.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		거래수량.setHorizontalAlignment(SwingConstants.LEFT);
		거래수량.setBounds(276, 228, 48, 15);
		customer_area.add(거래수량);

		JLabel label_1 = new JLabel("전체고객명단");
		label_1.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 12));
		label_1.setBounds(99, 5, 99, 15);
		customer_area.add(label_1);

		JLabel label_2 = new JLabel("포트폴리오");
		label_2.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 12));
		label_2.setBounds(306, 5, 73, 15);
		customer_area.add(label_2);

		conrol_area = new JPanel();
		conrol_area.setBounds(650, 10, 67, 283);
		body_panel.add(conrol_area);
		conrol_area.setLayout(new MigLayout("", "[57px][57px][57px][57px]", "[23px][][][][][][][][][][][][][][][][][][][][][][][][][]"));

		추가 = new JButton("추가");
		추가.setFont(new Font("나눔고딕", Font.BOLD, 11));
		conrol_area.add(추가, "cell 0 4,alignx left,aligny top");

		수정 = new JButton("수정");
		수정.setFont(new Font("나눔고딕", Font.BOLD, 11));
		conrol_area.add(수정, "cell 0 7,alignx left,aligny top");

		삭제 = new JButton("삭제");
		삭제.setFont(new Font("나눔고딕", Font.BOLD, 11));
		conrol_area.add(삭제, "cell 0 10");
								
										선택 = new JButton("선택");
										conrol_area.add(선택, "cell 0 18");
										선택.setFont(new Font("나눔고딕", Font.BOLD, 11));
								
										취소 = new JButton("취소");
										conrol_area.add(취소, "cell 0 21");
										취소.setFont(new Font("나눔고딕", Font.BOLD, 11));

		{// 사용자수정불가필드
			주식명필드.setEditable(false);
			거래가필드.setEditable(false);
			거래주필드.setEditable(false);
			거래수량필드.setEditable(false);
			
			주소명필드 = new JTextArea();
			주소명필드.setFont(new Font("나눔고딕", Font.PLAIN, 13));
			주소명필드.setBounds(58, 222, 206, 51);
			customer_area.add(주소명필드);
			
					매입 = new JButton("매입");
					매입.setBounds(276, 255, 65, 23);
					customer_area.add(매입);
					매입.setFont(new Font("나눔고딕", Font.BOLD, 11));
					매입.setEnabled(false);
					
							매수 = new JButton("매수");
							매수.setBounds(349, 255, 65, 23);
							customer_area.add(매수);
							매수.setFont(new Font("나눔고딕", Font.BOLD, 11));
							매수.setEnabled(false);

			JButton[] allBtn = { 추가, 수정, 삭제, 선택, 취소, 매입, 매수, 거래주 };
			for (int i = 0; i < allBtn.length; i++) {

				// 버튼안에 포커스 없애기
				allBtn[i].setFocusable(false);
				// 버튼 색변경
				allBtn[i].setBackground(Color.WHITE);
			}
		}

	}

	public void clearAll() {
		주식명필드.setText("");
		거래가필드.setText("");
		등록번호필드.setText("");
		성명필드.setText("");

	}

	public void initCustomerButton(boolean status) {
		추가.setEnabled(status);
		삭제.setEnabled(status);
		수정.setEnabled(status);
		선택.setEnabled(!status);
		취소.setEnabled(!status);

	}

	public void initShareButton(boolean status) {
		매입.setEnabled(status);
		매수.setEnabled(status);
		// 주식명필드.is
		거래수량필드.setEditable(status);
	}

	private void setTextFieldValue(Customer c){
		등록번호필드.setText(c.getSsn());
		성명필드.setText(c.getCust_name());
		주소명필드.setText(c.getAddress());
	}
	
	private class MouseHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent me){
			Object selectedValue = null;
			selectedValue = (Customer)list_1.getSelectedValue();
			clearAll();
			setTextFieldValue((Customer) selectedValue);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
