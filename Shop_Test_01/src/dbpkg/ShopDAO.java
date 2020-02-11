package dbpkg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ShopDAO extends DAOBase{
	private static ShopDAO instance = new ShopDAO();
	public static ShopDAO getInstance() {
		return instance;
	}
	
	private ShopDAO () {}
	
	//회원목록조회 memList.jsp
	public ArrayList<MemberVO> memList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<MemberVO> list = null;
		try {
			conn = getConnection();
			sql = "select custno, custname, phone, address,"
					+ " to_char(joindate, 'YYYY-MM-DD'), grade, city"
					+ " from member_tbl_01";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<MemberVO>();
				do {
					MemberVO vo = new MemberVO();
					vo.setCustno(rs.getInt(1));
					vo.setCustname(rs.getString(2));
					vo.setPhone(rs.getString(3));
					vo.setAddress(rs.getString(4));
					vo.setJoindate(rs.getString(5));
					if (rs.getString(6).equals("A")) {
						vo.setGrade("VIP");
					} else if (rs.getString(6).equals("B")) {
						vo.setGrade("일반");
					} else if (rs.getString(6).equals("C")) {
						vo.setGrade("직원");
					}
					vo.setCity(rs.getString(7));
					list.add(vo);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBResouce(rs, pstmt, conn);
		}
		return list;
	}
	
	
	//custno 자동생성번호 insert.jsp
	public int getCustno() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int custno = 0;
		try {
			conn = getConnection();
			sql = "select NVL(max(custno),0)+1 from member_tbl_01";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				custno = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBResouce(rs, pstmt, conn);
		}
		return custno;
		
	}
	
	
	//회원등록 insert.jsp
	public int insertMem(MemberVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int rs = 0;
		try {
			conn = getConnection();
			sql = "insert into member_tbl_01 values (?, ?, ?, ?,TO_DATE(?, 'YYYYMMdd'), ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getCustno());
			pstmt.setString(2, vo.getCustname());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getJoindate());
			pstmt.setString(6, vo.getGrade());
			pstmt.setString(7, vo.getCity());
			rs = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBResouce(pstmt, conn);
		}
		return rs;
	}
	
	//회원정보 1개 받아오기 update.jsp
	public MemberVO getCust(int custno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		MemberVO vo = null;
		try {
			conn = getConnection();
			sql = " SELECT CUSTNAME, PHONE, ADDRESS, TO_CHAR(JOINDATE,'YYYY-MM-dd'), GRADE, CITY "
					+ " FROM MEMBER_TBL_01 "
					+ " WHERE CUSTNO = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, custno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new MemberVO();
				vo.setCustname(rs.getString(1));
				vo.setPhone(rs.getString(2));
				vo.setAddress(rs.getString(3));
				vo.setJoindate(rs.getString(4));
				vo.setGrade(rs.getString(5));
				vo.setCity(rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	//회원정보 수정 updatePro.jsp
	public int updateMem(MemberVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int rs = 0;
		try {
			conn = getConnection();
			sql = "UPDATE MEMBER_TBL_01 SET "
					+ " CUSTNAME = ?, PHONE = ?, ADDRESS = ?, GRADE = ?, CITY = ? "
					+ " WHERE CUSTNO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getCustname());
			pstmt.setString(2, vo.getPhone());
			pstmt.setString(3, vo.getAddress());
			pstmt.setString(4, vo.getGrade());
			pstmt.setString(5, vo.getCity());
			pstmt.setInt(6, vo.getCustno());
			rs = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBResouce(pstmt, conn);
		}
		return rs;
	}
	
	public ArrayList<PriceVO> prList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<PriceVO> list = null;
		try {
			conn = getConnection();
			sql = "SELECT ME.CUSTNO, ME.CUSTNAME, ME.GRADE, SUM(MO.PRICE) AS TOTAL_PRICE" + 
					" FROM MEMBER_TBL_01 ME " + 
					" JOIN MONEY_TBL_01 MO " + 
					" ON (ME.CUSTNO = MO.CUSTNO) " + 
					" GROUP BY ME.CUSTNO, ME.CUSTNAME, ME.GRADE " + 
					" ORDER BY TOTAL_PRICE DESC ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<PriceVO>();
				do {
					PriceVO vo = new PriceVO();
					vo.setCustno(rs.getInt(1));
					vo.setCustname(rs.getString(2));
					if (rs.getString(3).equals("A")) {
						vo.setGrade("VIP");
					} else if (rs.getString(3).equals("B")) {
						vo.setGrade("일반");
					} else if (rs.getString(3).equals("C")) {
						vo.setGrade("직원");
					}
					vo.setTotal_price(rs.getInt(4));
					list.add(vo);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBResouce(rs, pstmt, conn);
		}
		return list;
	}
}




















