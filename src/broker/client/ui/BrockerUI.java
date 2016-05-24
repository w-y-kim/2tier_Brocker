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
import broker.exception.DuplicateIDException;
import broker.exception.InvalidTransactionException;
import broker.exception.RecordNotFoundException;
import broker.vo.Customer;
import broker.vo.Shares;
import broker.vo.Stock;

import net.miginfocom.swing.MigLayout;

import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	private JList stockJList;

	// 전체고객명단, 포트폴리오
	private JPanel body_panel;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JList customerJList;

	private JTextField 등록번호필드;
	private JTextField 성명필드;
	private JTextField 주식명필드;
	private JTextField 거래가필드;
	private JTextField 거래주필드;
	private JTextField 거래수량필드;
	private JButton 확인;
	private JButton 취소;

	// 사용자조작부
	private JPanel conrol_area;
	private JButton 신규;
	private JButton 수정;
	private JButton 삭제;

	// DB객체(임시클라매니저대용)
	Database db = new Database();

	private DefaultListModel<Stock> dlm;// 주식리스트담는GUI객체
	private DefaultListModel<Customer> dlm2;
	private DefaultListModel<Shares> dlm3;
	private JList portJList;


	private JTextArea 주소명필드;

	private ArrayList<Stock> stockList;

	private String menu;
	private JButton 매수;
	private JButton 매도;

	private Customer cus;

	private Shares shares;

	private JPanel customer_area;

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
		// 주식리스트
		dlm = new DefaultListModel<Stock>();
		try {
			stockList = db.getAllStock();
			for (Stock e : stockList) {
				dlm.addElement(e);
			}
		} catch (RecordNotFoundException e1) {
			e1.printStackTrace();
		}
		stockJList = new JList(dlm);
		stockJList.setFont(new Font("나눔바른고딕", Font.PLAIN, 11));
		scrollPane.setViewportView(stockJList);

		JLabel label = new JLabel("거래가능 주식목록");
		label.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 12));
		label.setBounds(41, 6, 118, 15);
		stock_area.add(label);

		거래주 = new JButton("거래주 선택");
		거래주.setFont(new Font("나눔고딕", Font.BOLD, 11));
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

		customer_area = new JPanel();
		customer_area.setBounds(212, 10, 426, 283);
		body_panel.add(customer_area);
		customer_area.setLayout(null);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 26, 252, 153);
		customer_area.add(scrollPane_1);

		// 고객리스트
		dlm2 = new DefaultListModel<Customer>();
		try {//TODO 멤버변수였던걸 로컬로 바꾸니까 갑자기 dlm2가 갱신 잘됨 뭐지 ?? 
			ArrayList<Customer> cusList = db.getAllCustomer();
			for (Customer e : cusList) {
				dlm2.addElement(e);
			}
		} catch (RecordNotFoundException e1) {
			e1.printStackTrace();
		}
		customerJList = new JList(dlm2);
		customerJList.setFont(new Font("나눔바른고딕", Font.PLAIN, 11));
		scrollPane_1.setViewportView(customerJList);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(276, 26, 138, 153);
		customer_area.add(scrollPane_2);

		// 포트폴리오리스트
		dlm3 = new DefaultListModel<Shares>();
//		try {
//			ArrayList<Shares> portList;
//			portList = db.getAllPortfolio();
//			for (Shares e : portList) {
//				dlm3.addElement(e);
//			}
//		} catch (RecordNotFoundException e1) {
//			e1.printStackTrace();
//		}
		portJList = new JList(dlm3);
		portJList.setFont(new Font("나눔바른고딕", Font.PLAIN, 11));
		scrollPane_2.setViewportView(portJList);

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

		JLabel 수정수량 = new JLabel("수정수량");
		수정수량.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		수정수량.setHorizontalAlignment(SwingConstants.LEFT);
		수정수량.setBounds(276, 228, 48, 15);
		customer_area.add(수정수량);

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
		conrol_area.setLayout(new MigLayout("", "[57px][57px][57px][57px]",
				"[23px][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));

		신규 = new JButton("신규");
		신규.setFont(new Font("나눔고딕", Font.BOLD, 11));
		conrol_area.add(신규, "cell 0 4,alignx left,aligny top");

		수정 = new JButton("수정");
		수정.setFont(new Font("나눔고딕", Font.BOLD, 11));
		conrol_area.add(수정, "cell 0 7,alignx left,aligny top");

		삭제 = new JButton("삭제");
		삭제.setFont(new Font("나눔고딕", Font.BOLD, 11));
		conrol_area.add(삭제, "cell 0 10");

		매수 = new JButton("매수");
		매수.setFont(new Font("나눔고딕", Font.BOLD, 11));
		매수.setEnabled(true);
		conrol_area.add(매수, "cell 0 23");

		매도 = new JButton("매도");
		매도.setFont(new Font("나눔고딕", Font.BOLD, 11));
		매도.setEnabled(true);
		conrol_area.add(매도, "cell 0 29");

		확인 = new JButton("확인");
		확인.setBounds(276, 255, 64, 23);
		확인.setFont(new Font("나눔고딕", Font.BOLD, 11));
		customer_area.add(확인);

		취소 = new JButton("취소");
		취소.setBounds(350, 255, 64, 23);
		취소.setFont(new Font("나눔고딕", Font.BOLD, 11));
		customer_area.add(취소);

		//////////////////////////////////////////////////////////////////////////////////////////////
		{
			// 프레임항상 위
			frame.setAlwaysOnTop(true);
			// 초기버튼설정
			initCustomerButton(true);

			// 사용자수정불가필드
			주식명필드.setEditable(false);
			거래가필드.setEditable(false);
			거래주필드.setEditable(false);
			거래수량필드.setEditable(false);

			주소명필드 = new JTextArea();
			주소명필드.setFont(new Font("나눔고딕", Font.PLAIN, 13));
			주소명필드.setBounds(58, 222, 206, 51);
			customer_area.add(주소명필드);

			// 일괄처리_이벤트리스너&초기디자인
			JButton[] allBtn = { 신규, 수정, 삭제, 확인, 취소, 매수, 매도, 거래주 };
			for (int i = 0; i < allBtn.length; i++) {

				// 버튼안에 포커스 없애기
				allBtn[i].setFocusable(false);
				// 버튼 색변경
				allBtn[i].setBackground(Color.WHITE);
				allBtn[i].setForeground(Color.BLACK);
				// 이벤트리스너
				allBtn[i].addActionListener(this);
			}

			stockJList.addMouseListener(new MouseHandler());
			customerJList.addMouseListener(new MouseHandler());
			portJList.addMouseListener(new MouseHandler());
		}

	}

	/**
	 * 액션 이벤트에서 각 분기점 전후로 초기화 할 필요 시 사용
	 * 
	 * @param li
	 */
	// public void clearAll(JList li) {
	// if (li.equals(stockJList)) {
	// 주식명필드.setText("");
	// 거래가필드.setText("");
	// } else if (li.equals(customerJList)) {
	// 등록번호필드.setText("");
	// 성명필드.setText("");
	// 주소명필드.setText("");
	// } else if (li.equals(portJList)) {
	// 거래주필드.setText("");
	// 거래수량필드.setText("");
	// }
	//
	// }

	/**
	 * 액션 이벤트에서 각 분기점 전후로 초기화 할 필요 시 사용
	 */
	public void clearAll() {
		등록번호필드.setEditable(true);
		주식명필드.setText("");
		거래가필드.setText("");
		등록번호필드.setText("");
		성명필드.setText("");
		주소명필드.setText("");
		거래주필드.setText("");
		거래수량필드.setText("");
		JButton[] allBtn = { 신규, 수정, 삭제, 확인, 취소, 매수, 매도, 거래주 };
		for (int i = 0; i < allBtn.length; i++) {
			// 버튼 색변경
			allBtn[i].setBackground(Color.WHITE);
			allBtn[i].setForeground(Color.BLACK);
		}
	}

	/**
	 * 버튼 토글 메소드
	 * 
	 * @param status
	 */
	public void initCustomerButton(boolean status) {
		신규.setEnabled(status);
		삭제.setEnabled(status);
		수정.setEnabled(status);
		매수.setEnabled(status);
		매도.setEnabled(status);
		거래주.setEnabled(!status);
		확인.setEnabled(!status);
		취소.setEnabled(!status);
	}

	/**
	 * 리스트에서 클릭 시 텍스트 필드로 들어가는 메소드
	 * 
	 * @param stockJList
	 */
	private void setTextFieldValue(Object stockJList) {
		if (stockJList instanceof Customer) {
			Customer customer = (Customer) stockJList;
			등록번호필드.setText(customer.getSsn());
			성명필드.setText(customer.getCust_name());
			주소명필드.setText(customer.getAddress());
		} else if (stockJList instanceof Stock) {
			Stock stock = (Stock) stockJList;
			주식명필드.setText(stock.getSymbol());
			거래가필드.setText(Integer.toString(stock.getPrice()));
		} else {
			Shares shares = (Shares) stockJList;
			거래주필드.setText(shares.getSymbol());
			거래수량필드.setText(Integer.toString(shares.getQuantity()));
		}

	}

	/**
	 * 마우스리스너를 처리하기 위한 마우스핸들러 이너클래스 형식의 adapter(파워자바p414)
	 * 
	 * @author user
	 *
	 */
	private class MouseHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent me) {
			if (me.getSource() == customerJList) {
				Object selectedValue = null;
				selectedValue = (Customer) customerJList.getSelectedValue();
				// clearAll(customerJList);//필요없는거 같음
				setTextFieldValue((Customer) selectedValue);// 선택된 객체를 필드에 넣음

				Customer cus = (Customer) selectedValue;// 선택한 객체의 주민번호 사용하려고

				try {
					dlm3.removeAllElements();// 초기화하는 것은 고객별 포트폴리오
					ArrayList<Shares> portList = db.getPortfolio(cus.getSsn());
					for (Shares e : portList) {
						dlm3.addElement(e);
					}

					//TODO 실행은 되는데 빠진게 반영이 안됨,dlm3는 반영되는데 
					dlm2.removeAllElements();
					ArrayList<Customer> cusList;
					cusList = db.getAllCustomer();
					for (Customer e : cusList) {
						dlm2.addElement(e);
					}
				} catch (RecordNotFoundException e) {
					e.printStackTrace();

				}

			} else if (me.getSource() == stockJList) {
				Object selectedValue = null;
				selectedValue = (Stock) stockJList.getSelectedValue();
				// clearAll(stockJList);//필요없는거 같음
				setTextFieldValue((Stock) selectedValue);
			} else if (me.getSource() == portJList) {
				Object selectedValue = null;
				selectedValue = (Shares) portJList.getSelectedValue();
				// clearAll(portJList);//필요없는거 같음
				setTextFieldValue((Shares) selectedValue);
			}

		}
	}

//	public void refreshCustomer() {
//		try {
//			cusList = db.getAllCustomer();
//		} catch (RecordNotFoundException e1) {
//			e1.printStackTrace();
//		}
//		dlm2 = new DefaultListModel<Customer>();
//		try {
//			cusList = db.getAllCustomer();
//			for (Customer e : cusList) {
//				dlm2.addElement(e);
//			}
//		} catch (RecordNotFoundException e1) {
//			e1.printStackTrace();
//		}
//
//	}

	public void buttonColorChange(JButton btn) {
		btn.setBackground(Color.GRAY);
		btn.setForeground(Color.WHITE);
		btn.setEnabled(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// clearAll();
//		this.refreshCustomer();
		if (e.getSource() == 거래주) {// 확인>거래주명넘어감>매입열림

			// 주식명필드.setText(t);
			매수.setEnabled(true);
			거래주필드.setText(주식명필드.getText());
			거래수량필드.setEditable(true);
			portJList.setEnabled(false);
			// 매수.setEnabled(true);

		} else if (e.getSource() == 신규) {
			menu = "신규메뉴";
			initCustomerButton(false);
			customerJList.setEnabled(false);
			portJList.setEnabled(false);
			buttonColorChange(신규);

		} else if (e.getSource() == 수정) {
			menu = "수정메뉴";
			initCustomerButton(false);
			거래주.setEnabled(true);
			portJList.setEnabled(true);
			매수.setEnabled(true);
			매도.setEnabled(true);
			등록번호필드.setEditable(false);
			buttonColorChange(수정);

		} else if (e.getSource() == 삭제) {
			menu = "삭제메뉴";
			initCustomerButton(false);
			거래주.setEnabled(false);
			buttonColorChange(삭제);

		} else if (e.getSource() == 매수) {// 산다
			menu = "매수메뉴";
			initCustomerButton(false);
			// customerJList.setEnabled(false);
			// portJList.setEnabled(false);
			buttonColorChange(매수);
		} else if (e.getSource() == 매도) {// 판다
			menu = "매도메뉴";
			initCustomerButton(false);
			// customerJList.setEnabled(false);
			// portJList.setEnabled(false);
			buttonColorChange(매도);

		} else if (e.getSource() == 확인) {
			initCustomerButton(true);
			cus = new Customer(등록번호필드.getText(), 성명필드.getText(), 주소명필드.getText());
			if (거래주필드.getText().equals(null)) {
				shares = new Shares(등록번호필드.getText(), 거래주필드.getText(), Integer.parseInt(거래수량필드.getText()));
			}

			if (menu == "신규메뉴") {
				try {
					db.addCustomer(cus);
					JOptionPane.showMessageDialog(null, "신규등록완료");
				} catch (DuplicateIDException e1) {
					e1.printStackTrace();

				}
			} else if (menu == "수정메뉴") {
				try {
					db.updateCustomer(cus);
				} catch (RecordNotFoundException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "수정완료");
			} else if (menu == "삭제메뉴") {
				try {
					db.deleteCustomer(등록번호필드.getText());
//					Object selectedValue = (Customer) customerJList.getSelectedValue();
//					dlm2.removeElement(selectedValue);//갑자기 잘됨 , 초기화메소드 고객리스트주석볼것
				} catch (RecordNotFoundException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "삭제완료");

			} else if (menu == "매수메뉴") {
				db.buyShares(shares);
				JOptionPane.showMessageDialog(null, "매입완료");

			} else if (menu == "매도메뉴") {
				try {
					db.sellShares(shares);
				} catch (RecordNotFoundException | InvalidTransactionException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "판매완료");
			}

			initCustomerButton(true);// TODO 꼭 2번 해줘야하나?

			clearAll();// TODO 이걸 합칠 순 없을까?

		} else if (e.getSource() == 취소) {
			clearAll();
			initCustomerButton(true);
			stockJList.setEnabled(true);
			customerJList.setEnabled(true);
			portJList.setEnabled(true);
			// 매수.setEnabled(false);
			// 매도.setEnabled(false);
		}
//		this.refreshCustomer();
	}
}
