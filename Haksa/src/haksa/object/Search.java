package haksa.object;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Search {
	private static BufferedReader input;
	private String cho;
	private String name;
	private int age;
	private int hakbun;
	private String searchName;
	private String sql;
	private ResultSet rs;
	private String subject;
	private String dept;
	private Statement stmt;
	private int cnt;
	
	public Search(){
		
	}
	public void setCho() throws IOException {
		System.out.println("찾을 사람의 직업을 입력해주세요(1. 학생 / 2.교수 / 3.관리자 ): ");
		cho = input.readLine();
		
	}
	
	public void setSearchName(String name) throws IOException {
		System.out.println("찾으시려는 학생의 이름을 입력해주세요: ");
		searchName = Register.input.readLine();
//      System.out.println("찾을 "+name+"이름 입력");
//      searchName = Register.input.readLine();
	}
	public void studentDisp() throws IOException {
		System.out.print("이름: "+name+"\t\t 나이: "+age+"\t\t 학번: "+hakbun+"\t\t");
	}
	public void professorDisp() throws IOException {
		System.out.print("이름: "+name+"\t\t 나이: "+age+"\t\t 과목: "+subject+"\t\t");
	}
	public void managerDisp() throws IOException {
		System.out.print("이름: "+name+"\t\t 나이: "+age+"\t\t 부서: "+dept+"\t\t");
	}
	
	
	public void searchProcess() throws SQLException, IOException {
//		searchProcess();
		Connection conn = Register.getConnection();
		if(cho.equals("1")){
			System.out.println("찾으시려는 학생의 이름을 입력해주세요: ");
			searchName = input.readLine();
			stmt = conn.createStatement();
			sql = "select * from Student where name='"+searchName+"'";
			rs = stmt.executeQuery(sql);
			if(!rs.isBeforeFirst()) {	// true면 커서가 첫행앞에 있다 = 값이 있다.	//false면 커서가 그 외의 위치에 있거나 결과 집합에 행이 들어 있지 않다  = 값이 없다.		//이 isBeforeFirst 메서드는 java.sql.ResultSet 인터페이스의 isBeforeFirst 메서드에 의해 지정됩니다.
				System.out.println("이승철이 부릅니다. 그런사람 또 없없습니다~");
			}
			while(rs.next()) {
				int age = rs.getInt("age");
				String name = rs.getNString("name");
				int hakbun = rs.getInt("hakbun");
				System.out.println();
			} 
		}
		else if(cho.equals("2")) {
			System.out.println("찾으시려는 교수의 이름을 입력해주세요: ");
			searchName = input.readLine();
			stmt = conn.createStatement();
			sql = "select * from Professor where name='"+searchName+"'";
			cnt = stmt.executeUpdate(sql);
			rs = stmt.executeQuery(sql);
			
			if(cnt==0) {
				System.out.println("이승철이 부릅니다. 그런사람 또 없없습니다~");
			}
			while(rs.next()) {
				int age = rs.getInt("age");
				String name = rs.getNString("name");
				String subject = rs.getString("subject");
				System.out.println();
				break;
			}
		}
		
		else if (cho.equals("3")) {
			System.out.println("찾으시려는 관리자의 이름을 입력해주세요: ");
			searchName = input.readLine();
			stmt = conn.createStatement();
			sql = "select * from manager where name='"+searchName+"'";
			rs = stmt.executeQuery(sql);	
			if(!rs.isBeforeFirst()) {	// true면 커서가 첫행앞에 있다 = 값이 있다.	//false면 커서가 그 외의 위치에 있거나 결과 집합에 행이 들어 있지 않다  =  값이 없다.
				System.out.println("이승철이 부릅니다. 그런사람 또 없없습니다~");
			}
			while(rs.next()) {
				int no = rs.getInt("no");
				int age = rs.getInt("age");
				String name = rs.getNString("name");
				String dept = rs.getString("dept");
				System.out.println();
			}
		}
	}
   /*
   public static void main(String[] args) throws SQLException {
      Search search = new Search();
      try {
         search.searchProcess();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
   }
   */
}





