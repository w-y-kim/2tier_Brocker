package broker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import broker.vo.*;
import broker.exception.*;

public class Database {

	/**[find]
	 * Customer 테이블에 SSN의 존재 유무를 확인
	 * 
	 * @return 매개변수로 주어진 ssn이 존재하면 true, else false
	 * @param ssn존재유무를 확인하고자 하는 고객의 ssn
	 */
	public boolean ssnExist(String ssn) {
		boolean result = false;
		// Customer cus = new Customer();

		Connection con = ConnectionManager.getConnection();
		String sql = "Select ssn from ? where ssn = ?";

		try {
			PreparedStatement pstat = con.prepareStatement(sql);
			pstat.setString(1, "customer");
			pstat.setString(2, ssn);//TODO ssn 그냥 넣어도 되던데 난 왜 

			ResultSet rs = pstat.executeQuery(sql);
			result = rs.next();

			// int row = pstat.executeUpdate();//반환값은 int이다
			// System.out.println(row +"개 레코드 삽입");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(con);
		}
		return result;

	}

	/**[show]
	 * 매개변수로 주어진 ssn에 해당하는 고객을 조회하여 반환
	 * @param ssn 조회하고자 하는 고객의 ssn
	 * @return 조회결과를 갖는 customer 객체
	 * @throws RecordNotFoundException 조회하고자 하는 고객의 ssn이 존재하지 않을 경우 발생 
	 */
	public Customer getCustomer(String ssn) throws RecordNotFoundException{
		if(!ssnExist(ssn)) throw new RecordNotFoundException(); 
		Customer cus = null;
		
		return cus;
	}
	
	
	
	public static void main(String[] args) {
		Database db = new Database();
		boolean result = db.ssnExist("ssn");
		System.out.println(result);
	}

}
