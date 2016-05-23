package broker.client.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class BrockerUI {

	private JFrame frame;
	private JTextField 등록번호필드;
	private JTextField 성명필드;
	private JTextField 주식명필드;
	private JTextField 거래가필드;
	private JTextField 거래주필드;
	private JTextField 거래수량필드;
	private JPanel top_panel;
	private JPanel body_panel;
	private JPanel stock_area;
	private JScrollPane scrollPane;
	private JButton 거래주;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JFormattedTextField 주소포맷필드;
	private JButton 선택;
	private JButton 취소;
	private JPanel display_area;
	private JButton 추가;
	private JButton 수정;
	private JButton 삭제;
	private JButton 매입;
	private JButton 매수;

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
		frame.setBounds(100, 100, 599, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		top_panel = new JPanel();
		top_panel.setBackground(Color.ORANGE);
		top_panel.setForeground(Color.DARK_GRAY);
		top_panel.setBounds(0, 0, 684, 26);
		frame.getContentPane().add(top_panel);
		
		body_panel = new JPanel();
		body_panel.setBackground(Color.GRAY);
		body_panel.setBounds(-9, 24, 592, 307);
		frame.getContentPane().add(body_panel);
		body_panel.setLayout(null);
		
		stock_area = new JPanel();
		stock_area.setBounds(12, 10, 141, 283);
		body_panel.add(stock_area);
		stock_area.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 24, 117, 156);
		stock_area.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JLabel label = new JLabel("거래 가능 주식목록");
		label.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 12));
		label.setBounds(20, 5, 118, 15);
		stock_area.add(label);
		
		거래주 = new JButton("거래주 선택");
		거래주.setFont(new Font("나눔고딕", Font.BOLD, 11));
		거래주.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		거래주.setBounds(12, 190, 117, 23);
		stock_area.add(거래주);
		
		JLabel 주식명 = new JLabel("주식명");
		주식명.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		주식명.setBounds(12, 223, 36, 21);
		stock_area.add(주식명);
		
		주식명필드 = new JTextField();
		주식명필드.setBounds(51, 223, 78, 21);
		stock_area.add(주식명필드);
		주식명필드.setColumns(10);
		
		거래가필드 = new JTextField();
		거래가필드.setColumns(10);
		거래가필드.setBounds(51, 254, 78, 21);
		stock_area.add(거래가필드);
		
		JLabel 거래가 = new JLabel("거래가");
		거래가.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		거래가.setBounds(12, 254, 36, 21);
		stock_area.add(거래가);
		
		JPanel customer_area = new JPanel();
		customer_area.setBounds(159, 10, 351, 283);
		body_panel.add(customer_area);
		customer_area.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 26, 201, 153);
		customer_area.add(scrollPane_1);
		
		JList list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(216, 26, 117, 153);
		customer_area.add(scrollPane_2);
		
		JList list_2 = new JList();
		scrollPane_2.setViewportView(list_2);
		
		JLabel 등록번호 = new JLabel("등록번호");
		등록번호.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		등록번호.setBounds(12, 197, 48, 15);
		customer_area.add(등록번호);
		등록번호.setHorizontalAlignment(SwingConstants.LEFT);
		
		등록번호필드 = new JTextField();
		등록번호필드.setBounds(68, 194, 131, 21);
		customer_area.add(등록번호필드);
		등록번호필드.setColumns(10);
		
		JLabel 성명 = new JLabel("성명");
		성명.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		성명.setBounds(12, 228, 24, 15);
		customer_area.add(성명);
		
		성명필드 = new JTextField();
		성명필드.setBounds(68, 225, 131, 21);
		customer_area.add(성명필드);
		성명필드.setColumns(10);
		
		JLabel 주소 = new JLabel("주소");
		주소.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		주소.setBounds(12, 259, 24, 15);
		customer_area.add(주소);
		
		주소포맷필드 = new JFormattedTextField();
		주소포맷필드.setBounds(68, 256, 131, 21);
		customer_area.add(주소포맷필드);
		
		JLabel 거래주명 = new JLabel("거래주명");
		거래주명.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		거래주명.setHorizontalAlignment(SwingConstants.LEFT);
		거래주명.setBounds(216, 197, 48, 15);
		customer_area.add(거래주명);
		
		거래주필드 = new JTextField();
		거래주필드.setColumns(10);
		거래주필드.setBounds(260, 194, 73, 21);
		customer_area.add(거래주필드);
		
		거래수량필드 = new JTextField();
		거래수량필드.setColumns(10);
		거래수량필드.setBounds(260, 225, 73, 21);
		customer_area.add(거래수량필드);
		
		JLabel 거래수량 = new JLabel("거래수량");
		거래수량.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		거래수량.setHorizontalAlignment(SwingConstants.LEFT);
		거래수량.setBounds(216, 228, 48, 15);
		customer_area.add(거래수량);
		
		선택 = new JButton("선택");
		선택.setFont(new Font("나눔고딕", Font.BOLD, 11));
		선택.setBounds(214, 255, 57, 23);
		customer_area.add(선택);
		
		취소 = new JButton("취소");
		취소.setFont(new Font("나눔고딕", Font.BOLD, 11));
		취소.setBounds(274, 255, 61, 23);
		customer_area.add(취소);
		
		JLabel label_1 = new JLabel("전체고객명단");
		label_1.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 12));
		label_1.setBounds(84, 5, 99, 15);
		customer_area.add(label_1);
		
		JLabel label_2 = new JLabel("포트폴리오");
		label_2.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 12));
		label_2.setBounds(246, 5, 73, 15);
		customer_area.add(label_2);
		
		display_area = new JPanel();
		display_area.setBounds(516, 10, 72, 177);
		body_panel.add(display_area);
		display_area.setLayout(new MigLayout("", "[57px][57px][57px][57px]", "[23px][][][][][][][][][][][][][][][]"));
		
		추가 = new JButton("추가");
		추가.setFont(new Font("나눔고딕", Font.BOLD, 11));
		display_area.add(추가, "cell 0 4,alignx left,aligny top");
		
		수정 = new JButton("수정");
		수정.setFont(new Font("나눔고딕", Font.BOLD, 11));
		display_area.add(수정, "cell 0 7,alignx left,aligny top");
		
		삭제 = new JButton("삭제");
		삭제.setFont(new Font("나눔고딕", Font.BOLD, 11));
		display_area.add(삭제, "cell 0 10");
		
		매입 = new JButton("매입");
		매입.setFont(new Font("나눔고딕", Font.BOLD, 11));
		매입.setBounds(522, 204, 57, 23);
		body_panel.add(매입);
		
		매수 = new JButton("매수");
		매수.setFont(new Font("나눔고딕", Font.BOLD, 11));
		매수.setBounds(522, 237, 57, 23);
		body_panel.add(매수);
	
		{//사용자수정불가필드 
			주식명필드.setEditable(false);
			거래가필드.setEditable(false);
			거래주필드.setEditable(false);
			거래수량필드.setEditable(false);
			매입.setEnabled(false);
			매수.setEnabled(false);
			
			

			
			JButton[] allBtn = {추가,수정,삭제,선택,취소,매입,매수,거래주};
			for (int i = 0; i < allBtn.length; i++) {
			
				//버튼안에 포커스 없애기
				allBtn[i].setFocusable(false);
				//버튼 색변경
				allBtn[i].setBackground(Color.WHITE);
			}
		}

	}

	public void clearAll(){
		주식명필드.setText("");
		거래가필드.setText("");
		등록번호필드.setText("");
		성명필드.setText("");
		
		
	}
	
	public void initCustomerButton(boolean status){
		추가.setEnabled(status);
		삭제.setEnabled(status);
		수정.setEnabled(status);
		선택.setEnabled(!status);
		취소.setEnabled(!status);
		
	}
	
	public void initShareButton(boolean status){
		매입.setEnabled(status);
		매수.setEnabled(status);
//		주식명필드.is
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
