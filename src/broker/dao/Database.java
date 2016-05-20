package broker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import broker.vo.*;
import broker.exception.*;

public class Database {

	/**
	 * [find] Customer 테이블에 SSN의 존재 유무를 확인
	 * 
	 * @return 매개변수로 주어진 ssn이 존재하면 true, else false
	 * @param ssn존재유무확인하고자
	 *            하는 고객의 ssn
	 */

	public boolean ssnExist(String ssn) {
		boolean result = false;

		// Statement 방식을 쓰면 setString 못쓰고, sql도 executeQuery에 넣어줘야함
		// String sql = "select * from Customer where ssn =" + "'" + ssn + "'";
		// Statement stat = con.createStatement();
		// ResultSet rs = stat.executeQuery(sql);

		Connection con = ConnectionManager.getConnection();
		String sql = "select * from Customer where ssn = ?";

		try {
			PreparedStatement pstat = con.prepareStatement(sql);
			pstat.setString(1, ssn);
			ResultSet rs = pstat.executeQuery();

			// System.out.println(rs.next());//한번 쓸때마다 줄이 바뀌기 때문에 주의할것
			if (rs.next()) {// TODO rs.next()를 쓰면 안됨, 어떻게하지?해결 일단 DB문제였음
				System.out.println("레코드가 true");
				result = true;
			} else {
				System.out.println("레코드 false");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(con);
		}
		return result;
	}

	/**
	 * [show] 매개변수로 주어진 ssn에 해당하는 고객을 조회하여 반환
	 * 
	 * @param ssn조회하고자
	 *            하는 고객의 ssn
	 * @return 조회결과를 갖는 customer 객체
	 * @throws RecordNotFoundException조회하고자
	 *             하는 고객의 ssn이 존재하지 않을 경우 발생
	 */

	public Customer getCustomer(String ssn) throws RecordNotFoundException {

		if (!ssnExist(ssn))
			throw new RecordNotFoundException(); // 존재하지 않는 경우 예외처리

		Customer cus = null;
		Connection con = ConnectionManager.getConnection();
		String sql = "SELECT * FROM customer WHERE ssn = ?";

		try {
			PreparedStatement pstat = con.prepareStatement(sql);
			pstat.setString(1, ssn);
			ResultSet rs = pstat.executeQuery();
			if (rs.next()) {
				String cust_name = rs.getString("cust_name");
				String address = rs.getString("address");
				cus = new Customer(ssn, cust_name, address);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(con);
		}

		return cus;
	}

	/**
	 * [showAll]Customer 테이블에 등록된 모든 고객정보를 조회한다.
	 * 
	 * @return 등록되어 있는 모든 고객정보 목록
	 * @throws RecordNotFoundException
	 */
	public ArrayList<Customer> getAllCustomer() throws RecordNotFoundException {
		ArrayList<Customer> list = new ArrayList<Customer>();

		Connection con = ConnectionManager.getConnection();
		String sql = "SELECT * FROM CUSTOMER";
		try {
			PreparedStatement pstat = con.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				System.out.println("여기까지실행");
				String ssn = rs.getString("ssn");
				String cust_name = rs.getString("cust_name");
				String address = rs.getString("address");
				Customer cus = new Customer(ssn, cust_name, address);
				list.add(cus);

			}

		} catch (SQLException e) {

		} finally {
			ConnectionManager.close(con);
		}
		if (list.isEmpty()) {
			System.out.println("레코드가 비어있습니다.");
		}

		return list;
	}

	/**
	 * [insert] 매개변수로 주어진 새로운 고객정보를 등록한다.
	 * 
	 * @param c등록하고자
	 *            하는 새로운 고객정보를 가지고 있는 Customer 객체
	 * @throws DuplicateIDException등록하고자
	 *             하는 고객의 ssn이 이미 존재할 경우 발생
	 */
	public void addCustomer(Customer c) throws DuplicateIDException {

		if (!ssnExist(c.getSsn()))
			throw new DuplicateIDException(); // 존재하지 않는 경우 예외처리

		Connection con = ConnectionManager.getConnection();
		String sql = "INSERT INTO CUSTOMER VALUES(?,?,?)";

		try {
			PreparedStatement pstat = con.prepareStatement(sql);
			pstat.setString(1, c.getSsn());
			pstat.setString(2, c.getCust_name());
			pstat.setString(3, c.getAddress());
			int result = pstat.executeUpdate();
			if (result == 1) {
				System.out.println("등록완료");
			} else {
				System.out.println("등록안됨");
			}

		} catch (SQLException e) {

		} finally {
			ConnectionManager.close(con);
		}

	}

	/**
	 * [delete] 매개변수로 주어진 ssn에 해당하는 고객정보 & 보유주식정보 삭제 여러개의 작업이 있으면 transcation
	 * 처리를 해주어야 함
	 * 
	 * @param ssn
	 *            삭제하고자 하는 고객의 ssn
	 * @throws RecordNotFoundException
	 *             조회하고자 하는 고객의 ssn이 존재하지 않을 경우 발생
	 */
	public void deleteCustomer(String ssn) throws RecordNotFoundException {
		if (!ssnExist(ssn))
			throw new RecordNotFoundException(); // 존재하지 않는 경우 예외처리

		Connection con = ConnectionManager.getConnection();
		String sql = "DELETE FROM CUSTOMER  WHERE SSN = ?";
		String sql2 = "DELETE FROM SHARES WHERE SSN = ?";

		try {
			con.setAutoCommit(false);

			// 외래키-참조키 관계의 테이블은 커밋 순서도 SQL에서 처럼해줘야함
			// 그것이 복잡하니까 DB생성시 외래키를 사용하는 constraint에 on delete cascade를 해줌
			// 대신 이렇게하면 int row2 = pstat2.executeUpdate();의 결과는 0이 된다.
			PreparedStatement pstat2 = con.prepareStatement(sql2);
			pstat2.setString(1, ssn);
			int row2 = pstat2.executeUpdate();
			con.commit();

			PreparedStatement pstat = con.prepareStatement(sql);
			pstat.setString(1, ssn);
			int row = pstat.executeUpdate();

			// if(true) throw new SQLException();//확인용

			if (row == 1 && row2 == 1) {
				System.out.println("삭제완료");
			} else
				System.out.println("삭제안됨");

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			ConnectionManager.close(con);
		}

	}

	/**
	 * [update] 매개변수로 주어진 새로운 고객정보를 갱신한다.
	 * 
	 * @param 새로운
	 *            고객정볼르 가지고 있는 Customer 객체
	 * @throws 갱신하고자
	 *             하는 고객의 ssn이 존재하지 않을 경우 발생
	 */
	public void updateCustomer(Customer c) throws RecordNotFoundException {
		if (!ssnExist(c.getSsn()))
			throw new RecordNotFoundException();
		Connection con = ConnectionManager.getConnection();
		String sql = "UPDATE CUSTOMER set cust_name=?, address=? where ssn=?";

		try {
			PreparedStatement pstat = con.prepareStatement(sql);
			pstat.setString(1, c.getCust_name());
			pstat.setString(2, c.getAddress());
			pstat.setString(3, c.getSsn());
			System.out.println(pstat);
			int result = pstat.executeUpdate();// TODO 쿼리문 틀리면 여기서 멈춰있는 문제
			if (result == 1) {
				System.out.println("업뎃완료");
			} else {
				System.out.println("업뎃안됨");// TODO 업뎃안되는걸 체크하는 방법?
			}

		} catch (SQLException e) {

		} finally {
			ConnectionManager.close(con);
		}

	}

	/**
	 * 매개변수로 주어진 ssn의 고객이 보유하고 있느 주식 목록을 조회하여 반환
	 * 
	 * @param ssn
	 *            조회하고자 하는 고객의 ssn
	 * @return 특정 고객이 보유하고 있는 주식정보 목록
	 * @throws RecordNotFoundException
	 */
	public ArrayList<Shares> getPortfolio(String ssn) throws RecordNotFoundException {
		ArrayList<Shares> list = new ArrayList<>();

		if (!ssnExist(ssn)) {
			throw new RecordNotFoundException();
		}

		Connection con = ConnectionManager.getConnection();
		// String sql = "SELECT * FROM customer c, shares s where c.? = s.?";
		String sql = "SELECT * FROM shares WHERE ssn =?";

		try {
			PreparedStatement pstat = con.prepareStatement(sql);
			pstat.setString(1, ssn);
			// pstat.setString(2, ssn);
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				
				String symbol = rs.getString("symbol");
				int quantity = rs.getInt("quantity");
				Shares s = new Shares(ssn, symbol, quantity);
				list.add(s);
			}
			//TOOD 분기처리하려면 if안의 조건에 rs.next()를 쓰게되는데 그러면 또 한줄건너뜀
//			if (result) {
//				System.out.println("고객의 주식자료 조회시작");
//
//
//			} else {
//				System.out.println("해당 고객은 주식자료가 없음");
//			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

	
	public ArrayList<Stock> getStock(String ssn) throws RecordNotFoundException {
		ArrayList<Stock> list = new ArrayList<>();

		if (!ssnExist(ssn)) {
			throw new RecordNotFoundException();
		}

		Connection con = ConnectionManager.getConnection();
		// String sql = "SELECT * FROM customer c, shares s where c.? = s.?";
		String sql = "SELECT * FROM shares WHERE ssn =?";

		try {
			PreparedStatement pstat = con.prepareStatement(sql);
			pstat.setString(1, ssn);
			// pstat.setString(2, ssn);
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				
				String symbol = rs.getString("symbol");
				int quantity = rs.getInt("price");
				Stock c = new Stock(symbol, price);
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}
	public static void main(String[] args) {
		Database db = new Database();

		// 찾기(중복검사)

		// boolean result = db.ssnExist("111-112");
		// System.out.println(result);

		System.out.println("=====================================");

		// 컬럼하나출력

		// try {
		// Customer c = db.getCustomer("111-112");
		// String str = c.toString();
		// System.out.println(str);
		// // System.out.println(c);//toString생략
		// } catch (RecordNotFoundException e) {
		// e.printStackTrace();
		// }

		System.out.println("=====================================");

		// 전체출력

		// try {
		// ArrayList<Customer> result = db.getAllCustomer();
		//
		// for (int i = 0; i < result.size(); i++) {
		// Customer c = result.get(i);
		// System.out.println(c);
		// String str = c.toString();
		// System.out.println(str);
		// }
		// } catch (RecordNotFoundException e) {
		// e.printStackTrace();
		// }

		System.out.println("=====================================");

		// 추가

		// Customer c_insert = new Customer("111-111", "김사람", "강남");
		// try {
		// db.addCustomer(c_insert);
		// } catch (DuplicateIDException e) {
		// e.printStackTrace();
		// }

		System.out.println("=====================================");

		// 삭제

		// try {
		// db.deleteCustomer("111-119");
		// } catch (RecordNotFoundException e) {
		// e.printStackTrace();
		// }

		System.out.println("=====================================");

		// 업데이트

		// Customer c_update = new Customer("111-111", "김사람", "강남");
		// try {
		// db.updateCustomer(c_update);
		// } catch (RecordNotFoundException e) {
		// e.printStackTrace();
		// }

		System.out.println("=====================================");
		
		//get_shares테이블 
		
		ArrayList<Shares> result;
		try {
			result = db.getPortfolio("111-111");
			for (int i = 0; i < result.size(); i++) {
				Shares s = result.get(i);
				System.out.println(s);
			}
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//get_stocks주식정보 테이블
		
		
		
	}
}
