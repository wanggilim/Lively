package com.quadcore.lively.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.quadcore.lively.util.OracleDBUtil;


public class DashboardDAO {
	private Connection conn;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;
	
	public DashboardDAO() {
		
	}
	
	
	/**
	 * 단어 검색
	 * @param word::String
	 * @return result::List/ArrayList<WordVO>
	 * 
	 * (결정 필요)
	 * 정확히 입력한 단어만 나와야하는 것인가,
	 * 입력 단어와 유사한 단어를 보여줄 것인가
	 * (*만약에 정확히 입력한 단어만 나와야한다면 메서드 이름도 ..words 가 아니고 word)
	 */
	public List<WordVO> searchWords(String word) {
		List<WordVO> list = new ArrayList<>(1);
		
		try {
			// DB 연결
			conn = OracleDBUtil.dbConnect();
			
			// SQL + Statement
			String sql = "SELECT * FROM tokentest WHERE lower(token) LIKE '%" + word.toLowerCase() + "%'";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			
			// WordVO 로 변환 후 List에 넣기
			while (rs.next()) {
				list.add(new WordVO (rs.getInt(1),	// tokenNo
								rs.getString(2), 	// token
								rs.getString(3), 	// mean
								rs.getString(4), 	// tokenTag
								rs.getString(5), 	// tokenType
								rs.getInt(6)) 		// stmtNo
						);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	/**
	 * SNS (Twitter 트윗, YouTube 영상 댓글) 예문 검색
	 * @param word::String
	 * @return result::List/ArrayList<StatementVO>
	 * 
	 * (결정 필요)
	 * 정확히 입력한 단어만 나와야하는 것인가,
	 * 입력 단어와 유사한 단어를 보여줄 것인가
	 * (*만약에 정확히 입력한 단어만 나와야한다면 메서드 이름도 ..words 가 아니고 word)
	 * 
	 * (2019.03.18 추가)
	 * profileURL 추가
	 * 
	 */
	public List<StmtVO> searchStatements(String word) {
		
		List<StmtVO> list = new ArrayList<>(1);
		
		try {
			// DB 연결
			conn = OracleDBUtil.dbConnect();
			
			// SQL + Statement
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT * FROM")
			.append(" stmt st JOIN tokentest tt USING (stmtno) ")
			.append(" JOIN profile p ON substr(st.stmturl, 21, instr(substr(st.stmturl, 21), '/')-1) = substr(p.accounts,2)")
			.append(" WHERE lower(tt.token) = '").append(word.toLowerCase()).append("'");
			String sql = sb.toString();
			
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			// WordVO 로 변환 후 List에 넣기
			while (rs.next()) {
				list.add(new StmtVO(
						rs.getInt("STMTNO"), 		// stmtNo
						rs.getString("STMT"),	// stmt
						rs.getLong("STMTTS"),		// stmtTs
						rs.getString("STMTTYPE"),	// stmtType
						rs.getString("LOCATION"),	// location
						rs.getInt("LIKES"), 		// likes
						rs.getInt("SHARING"), 		// sharing
						rs.getInt("CELLEBNO"), 		// cellbNo
						rs.getString("STMTURL"), 	// stmtURL
						rs.getString("ACCOUNTS"),	// screenName
						rs.getString("IMAGE"))	// profileURL
				);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
			
			
	/**
	 * 기타 확인사항
	 * 1. 단어 추가
	 * 2. 단어 수정
	 * 3. 단어 삭제
	 * 4. 단어 complain 
	 */
			

}
