package broker.client.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JScrollPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
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
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import java.awt.Window.Type;
import java.awt.Component;
import javax.swing.Box;

public class BrockerUI {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

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
		frame.setBounds(100, 100, 599, 365);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel top_panel = new JPanel();
		top_panel.setBackground(Color.ORANGE);
		top_panel.setForeground(Color.DARK_GRAY);
		top_panel.setBounds(0, 0, 684, 26);
		frame.getContentPane().add(top_panel);
		
		JPanel body_panel = new JPanel();
		body_panel.setBackground(Color.GRAY);
		body_panel.setBounds(-9, 24, 592, 307);
		frame.getContentPane().add(body_panel);
		body_panel.setLayout(null);
		
		JPanel stock_area = new JPanel();
		stock_area.setBounds(12, 10, 141, 283);
		body_panel.add(stock_area);
		stock_area.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 24, 117, 156);
		stock_area.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JLabel label = new JLabel("거래 가능 주식목록");
		label.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 12));
		label.setBounds(20, 5, 118, 15);
		stock_area.add(label);
		
		JButton btnNewButton = new JButton("거래주 선택");
		btnNewButton.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(12, 190, 117, 23);
		stock_area.add(btnNewButton);
		
		JLabel 주식명 = new JLabel("주식명");
		주식명.setBounds(12, 223, 36, 21);
		stock_area.add(주식명);
		
		textField_2 = new JTextField();
		textField_2.setBounds(51, 223, 78, 21);
		stock_area.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(51, 254, 78, 21);
		stock_area.add(textField_3);
		
		JLabel 거래가 = new JLabel("거래가");
		거래가.setBounds(12, 254, 36, 21);
		stock_area.add(거래가);
		
		JPanel customer_area = new JPanel();
		customer_area.setBounds(159, 10, 351, 283);
		body_panel.add(customer_area);
		customer_area.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 26, 201, 153);
		customer_area.add(scrollPane_1);
		
		JList list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(216, 26, 117, 153);
		customer_area.add(scrollPane_2);
		
		JList list_2 = new JList();
		scrollPane_2.setViewportView(list_2);
		
		JLabel 등록번호 = new JLabel("등록번호");
		등록번호.setBounds(12, 197, 48, 15);
		customer_area.add(등록번호);
		등록번호.setHorizontalAlignment(SwingConstants.LEFT);
		
		textField = new JTextField();
		textField.setBounds(68, 194, 131, 21);
		customer_area.add(textField);
		textField.setColumns(10);
		
		JLabel 성명 = new JLabel("성명");
		성명.setBounds(12, 228, 24, 15);
		customer_area.add(성명);
		
		textField_1 = new JTextField();
		textField_1.setBounds(68, 225, 131, 21);
		customer_area.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel 주소 = new JLabel("주소");
		주소.setBounds(12, 259, 24, 15);
		customer_area.add(주소);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(68, 256, 131, 21);
		customer_area.add(formattedTextField);
		
		JLabel 매입주 = new JLabel("매입주");
		매입주.setHorizontalAlignment(SwingConstants.LEFT);
		매입주.setBounds(216, 197, 48, 15);
		customer_area.add(매입주);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(260, 194, 73, 21);
		customer_area.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(260, 225, 73, 21);
		customer_area.add(textField_5);
		
		JLabel 매수주 = new JLabel("매수주");
		매수주.setHorizontalAlignment(SwingConstants.LEFT);
		매수주.setBounds(216, 228, 48, 15);
		customer_area.add(매수주);
		
		JButton 선택 = new JButton("선택");
		선택.setBounds(214, 255, 57, 23);
		customer_area.add(선택);
		
		JButton 취소 = new JButton("취소");
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
		
		JPanel display_area = new JPanel();
		display_area.setBounds(516, 10, 72, 177);
		body_panel.add(display_area);
		display_area.setLayout(new MigLayout("", "[57px][57px][57px][57px]", "[23px][][][][][][][][][][][][][][][]"));
		
		JButton 추가 = new JButton("추가");
		display_area.add(추가, "cell 0 4,alignx left,aligny top");
		
		JButton 수정 = new JButton("수정");
		display_area.add(수정, "cell 0 7,alignx left,aligny top");
		
		JButton 삭제 = new JButton("삭제");
		display_area.add(삭제, "cell 0 10");
		
		JButton 매입 = new JButton("매입");
		매입.setBounds(522, 204, 57, 23);
		body_panel.add(매입);
		
		JButton 매수 = new JButton("매수");
		매수.setBounds(522, 237, 57, 23);
		body_panel.add(매수);
	}
}
