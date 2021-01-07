package haksa.object;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {

	private static BufferedReader input;
	private Statement stmt;
	private Connection conn;
	private ResultSet rs;
	private String cho;
	private String sql;
	private String deleteName;
//	private int cnt;
	
	public Delete() {
		
	}
	public void setCho() throws IOException{
		
		System.out.println("삭제할 사람의 직업을 입력해주세요(1. 학생 / 2.교수 / 3.관리자 ): ");
		cho = input.readLine();
	}
	
	public void setDelete() throws IOException{
		System.out.println("삭제할 학생의 이름을 입력해주세요: ");
		deleteName = Register.input.readLine();
	}
	
	 public void deleteDisp(String delName) throws IOException {
	      System.out.println(delName+"님의 정보를 삭제하였습니다.");
	      System.out.println();
	   }

	
	public void deleteProcess() throws IOException, SQLException {
		conn = Register.getConnection();
		
		if(cho.equals("1")){	
			setDelete();
			stmt = conn.createStatement();
			sql = "select * from Student where name='"+deleteName+"'";
			rs = stmt.executeQuery(sql);
			if(!rs.isBeforeFirst()) {	// true면 커서가 첫행앞에 있다 = 값이 있다.	//false면 커서가 그 외의 위치에 있거나 결과 집합에 행이 들어 있지 않다  =  값이 없다.
				System.out.println("이승철이 부릅니다. 그런사람 또 없없습니다~");
			}
			while(rs.next()) {
				stmt = conn.createStatement();
				sql = "delete from Student where name='"+deleteName+"'";
				stmt.executeUpdate(sql);
				deleteName = rs.getString("name");
				deleteDisp(deleteName);
				
			}
		}
		else if(cho.equals("2")){
		
			setDelete();
			stmt = conn.createStatement();
			sql = "select * from Professor where name='"+deleteName+"'";
			rs = stmt.executeQuery(sql);
			if(!rs.isBeforeFirst()) {	
				System.out.println("이승철이 부릅니다. 그런사람 또 없없습니다~");
			}
			while(rs.next()) {
				stmt = conn.createStatement();
				sql = "delete from Professor where name='"+deleteName+"'";
				stmt.executeUpdate(sql);
				deleteName = rs.getString("name");
				deleteDisp(deleteName);
			}
		}
		else if(cho.equals("3")){
			setDelete();
			stmt = conn.createStatement();
			sql = "select * from manager where name='"+deleteName+"'";
			rs = stmt.executeQuery(sql);
			if(!rs.isBeforeFirst()) {	
				System.out.println("이승철이 부릅니다. 그런사람 또 없없습니다~");
			}
			while(rs.next()) {
				stmt = conn.createStatement();
				sql = "delete from manager where name='"+deleteName+"'";
				stmt.executeUpdate(sql);
				deleteName = rs.getString("name");
				deleteDisp(deleteName);
			}
		}
	}
}

