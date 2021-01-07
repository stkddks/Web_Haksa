package haksa.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Function {

	public static void register(String protocol, BufferedReader input, Statement stmt, Connection conn, int cnt) throws IOException, SQLException{
		// 등록
		while(true) {
			System.out.println();
			System.out.println("==========메뉴선택=========");
			System.out.println("1. 학생");
			System.out.println("2. 교수");
			System.out.println("3. 관리자");
			System.out.println("4. 이전메뉴");
			System.out.println("=======================");
			System.out.printf("번호를 선택해주세요: ");

			protocol=input.readLine();
			System.out.println();
			
			if(protocol.equals("1")) {
				System.out.println("--------------학생 등록--------------");
				
				System.out.printf("나이: ");
				String age1 = input.readLine();		//나중에 형변환을 해줘야 한다.(db에서 NUMBER로 데이터타입을 지정해주었기 때문에)
				System.out.printf("이름: ");
				String name = input.readLine();
				System.out.printf("학번: ");
				String hakbun1 = input.readLine();		//나중에 형변환을 해줘야 한다.
				
				int age = Integer.parseInt(age1); 		// 문자열로 받아들인 것을 정수타입으로 바꿔준다. 
				int hakbun = Integer.parseInt(hakbun1); 
				
				stmt = conn.createStatement();
				String sql = "insert into Student(no, age, name, hakbun) values (student_no.nextval, "+age+", '"+name+"', "+hakbun+")";
				
				cnt = stmt.executeUpdate(sql);
				System.out.println(cnt + "명의 학생이 등록 되었습니다");
				System.out.println();	
			} 
			else if(protocol.equals("2")) {
				System.out.println("--------------교수 등록--------------");
				
				System.out.printf("나이: ");
				String age1 = input.readLine();		//나중에 형변환을 해줘야 한다.
				System.out.printf("이름: ");
				String name = input.readLine();
				System.out.printf("과목: ");
				String subject = input.readLine();
				int age = Integer.parseInt(age1); 		// 문자열로 받아들인 것을 정수타입으로 바꿔준다. 
				
				stmt = conn.createStatement();
				String sql = "insert into Professor(no, age, name, subject) values (professor_no.nextval, "+age+", '"+name+"', '"+subject+"')";
				cnt = stmt.executeUpdate(sql);
				System.out.println(cnt + "명의 교수님이 등록 되었습니다");
				System.out.println();
			} 
			
			else if(protocol.equals("3")) {
				System.out.println("--------------관리자 등록--------------");
				
				System.out.printf("나이: ");
				String age1 = input.readLine();//나중에 형변환을 해줘야 한다.
				System.out.printf("이름: ");
				String name = input.readLine();
				System.out.printf("부서: ");
				String dept = input.readLine();
				int age = Integer.parseInt(age1); 		// 문자열로 받아들인 것을 정수타입으로 바꿔준다. 
				
				stmt = conn.createStatement();
				String sql = "insert into manager(no, age, name, dept) values (manager_no.nextval, "+age+" , '"+name+"', '"+dept+"')";
				cnt = stmt.executeUpdate(sql);
				System.out.println(cnt + "명의 관리자가 등록 되었습니다");
				System.out.println();
				
			} 
			
			else if (protocol.equals("4")) {
				System.out.println();
				break;
			}
		}
	}

	public static void search(String protocol, BufferedReader input, Statement stmt, Connection conn, int cnt) throws IOException, SQLException{
		// 찾기
		
			System.out.println("찾을 사람의 직업을 입력해주세요(1. 학생 / 2.교수 / 3.관리자 ): ");
			String cho = input.readLine();{
				if(cho.equals("1")){
					System.out.println("찾으시려는 학생의 이름을 입력해주세요: ");
					String searchName = input.readLine();
				    stmt = conn.createStatement();
					String sql = "select * from Student where name='"+searchName+"'";
					ResultSet rs = stmt.executeQuery(sql);
					if(!rs.isBeforeFirst()) {	// true면 커서가 첫행앞에 있다 = 값이 있다.	//false면 커서가 그 외의 위치에 있거나 결과 집합에 행이 들어 있지 않다  = 값이 없다.		//이 isBeforeFirst 메서드는 java.sql.ResultSet 인터페이스의 isBeforeFirst 메서드에 의해 지정됩니다.
						System.out.println("이승철이 부릅니다. 그런사람 또 없없습니다~");
					}
					while(rs.next()) {
						int age = rs.getInt("age");
						String name = rs.getNString("name");
						int hakbun = rs.getInt("hakbun");
						System.out.print("이름: "+name+"\t\t 나이: "+age+"\t\t 학번: "+hakbun+"\t\t");
						System.out.println();
					} 
				}
				else if(cho.equals("2")) {
					System.out.println("찾으시려는 교수의 이름을 입력해주세요: ");
					String searchName = input.readLine();
					stmt = conn.createStatement();
					String sql = "select * from Professor where name='"+searchName+"'";
					cnt = stmt.executeUpdate(sql);
					ResultSet rs = stmt.executeQuery(sql);
					
					if(cnt==0) {
						System.out.println("이승철이 부릅니다. 그런사람 또 없없습니다~");
					}
					while(rs.next()) {
						int age = rs.getInt("age");
						String name = rs.getNString("name");
						String subject = rs.getString("subject");
						System.out.print("이름: "+name+"\t\t 나이: "+age+"\t\t 과목: "+subject+"\t\t");
						System.out.println();
						break;
					}
				}
				
				else if (cho.equals("3")) {
					System.out.println("찾으시려는 관리자의 이름을 입력해주세요: ");
					String searchName = input.readLine();
					stmt = conn.createStatement();
					String sql = "select * from manager where name='"+searchName+"'";
					ResultSet rs = stmt.executeQuery(sql);	
					if(!rs.isBeforeFirst()) {	// true면 커서가 첫행앞에 있다 = 값이 있다.	//false면 커서가 그 외의 위치에 있거나 결과 집합에 행이 들어 있지 않다  =  값이 없다.
						System.out.println("이승철이 부릅니다. 그런사람 또 없없습니다~");
					}
					while(rs.next()) {
						int no = rs.getInt("no");
						int age = rs.getInt("age");
						String name = rs.getNString("name");
						String dept = rs.getString("dept");
						System.out.print("이름: "+name+"\t\t 나이: "+age+"\t\t 부서: "+dept+"\t\t");
						System.out.println();
					}
				}
				else {
					System.out.println("정확한 번호를 입력해주세요");
					System.out.println("처음으로 돌아갑니다.");
				}
			
			}
	}

	public static void delete(String protocol, BufferedReader input, Statement stmt, Connection conn, int cnt) throws IOException, SQLException{
		// 삭제
	
			System.out.println("삭제할 사람의 직업을 입력해주세요(1. 학생 / 2.교수 / 3.관리자 ): ");
			String cho = input.readLine();
			if(cho.equals("1")){	
				
				System.out.println("삭제할 학생의 이름을 입력해주세요: ");
				String setName = input.readLine();
				
				stmt = conn.createStatement();
				String sql = "select * from Student where name='"+setName+"'";
				ResultSet rs = stmt.executeQuery(sql);
				if(!rs.isBeforeFirst()) {	// true면 커서가 첫행앞에 있다 = 값이 있다.	//false면 커서가 그 외의 위치에 있거나 결과 집합에 행이 들어 있지 않다  =  값이 없다.
					System.out.println("이승철이 부릅니다. 그런사람 또 없없습니다~");
				}
				while(rs.next()) {
					stmt = conn.createStatement();
					sql = "delete from Student where name='"+setName+"'";
					cnt = stmt.executeUpdate(sql);
					String delName = rs.getString("name");
					System.out.println(delName+"님의 정보를 삭제하였습니다.");
					System.out.println();
				}
			}
			else if(cho.equals("2")){
				System.out.println("삭제할 교수의 이름을 입력해주세요: ");
				String setName = input.readLine();
				
				stmt = conn.createStatement();
				String sql = "select * from Professor where name='"+setName+"'";
				ResultSet rs = stmt.executeQuery(sql);
				if(!rs.isBeforeFirst()) {	
					System.out.println("이승철이 부릅니다. 그런사람 또 없없습니다~");
				}
				while(rs.next()) {
					stmt = conn.createStatement();
					sql = "delete from Professor where name='"+setName+"'";
					cnt = stmt.executeUpdate(sql);
					String delName = rs.getString("name");
					System.out.println(delName+"님의 정보를 삭제하였습니다.");
					System.out.println();
				}
				
			}
			else if(cho.equals("3")){
				System.out.println("삭제할 관리자의 이름을 입력해주세요: ");
				String setName = input.readLine();
				
				stmt = conn.createStatement();
				String sql = "select * from manager where name='"+setName+"'";
				ResultSet rs = stmt.executeQuery(sql);
				if(!rs.isBeforeFirst()) {	
					System.out.println("이승철이 부릅니다. 그런사람 또 없없습니다~");
				}
				while(rs.next()) {
					stmt = conn.createStatement();
					sql = "delete from manager where name='"+setName+"'";
					cnt = stmt.executeUpdate(sql);
					String delName = rs.getString("name");
					System.out.println(delName+"님의 정보를 삭제하였습니다.");
					System.out.println();
				}
			}
			

	}
	
	
	public static void list(String protocol, Statement stmt, Connection conn) throws IOException, SQLException{
		
			System.out.println("--------------학생 전체 출력--------------");					
			stmt = conn.createStatement();
			String sql = "select * from Student";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int no = rs.getInt("no");
				int age = rs.getInt("age");
				String name = rs.getNString("name");
				int hakbun = rs.getInt("hakbun");
				System.out.print("이름: "+name+"\t\t 나이: "+age+"\t\t 학번: "+hakbun+"\t\t");
				System.out.println();
			}
			System.out.println();
			System.out.println("--------------교수 전체 출력--------------");
			stmt = conn.createStatement();
			sql = "select * from Professor";
			rs = stmt.executeQuery(sql);				
			while(rs.next()) {
				int no = rs.getInt("no");
				int age = rs.getInt("age");
				String name = rs.getNString("name");
			    String subject = rs.getString("subject");
				System.out.print("이름: "+name+"\t\t 나이: "+age+"\t\t 과목: "+subject+"\t\t");
				System.out.println();
			}
			System.out.println();
			System.out.println("--------------관리자 전체 출력--------------");
			stmt = conn.createStatement();
			sql = "select * from manager";
			rs = stmt.executeQuery(sql);				
			while(rs.next()) {
				int no = rs.getInt("no");
				int age = rs.getInt("age");
				String name = rs.getNString("name");
			    String dept = rs.getString("dept");
				System.out.print("이름: "+name+"\t\t 나이: "+age+"\t\t 부서: "+dept+"\t\t");
				System.out.println();
			}
			System.out.println();
			System.out.println("--------------학사 전체 출력--------------");
			stmt = conn.createStatement();
			
			sql = "select s.no as 번호, s.age as 나이, s.name as 이름, s.hakbun as 학번, p.age as 교수나이, p.name as 교수이름, p.subject as 과목, m.age as 관리자나이, m.name as 관리자이름, m.dept as 부서 from (student s left join professor p on s.no=p.no) full outer join manager m on s.no=m.no ORDER BY 번호 ASC";		
			
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int age = rs.getInt("나이");
				String name = rs.getNString("이름");
			    String hakbun = rs.getString("학번");
			    int p_age = rs.getInt("교수나이");
				String p_name = rs.getNString("교수이름");
			    String p_subject = rs.getString("과목");
			    int m_age = rs.getInt("관리자나이");
				String m_name = rs.getNString("관리자이름");
			    String m_dept = rs.getString("부서");
			    
			    System.out.print("학생이름: "+name+"\t\t 학생나이: "+age+"\t\t 학번: "+hakbun+"\t\t 교수이름: "+p_name+"\t\t 교수나이: "+p_age+"\t\t 과목: "+p_subject+"\t\t 관리자이름: "+m_name+"\t\t 관리자나이: "+m_age+"\t\t 부서: "+m_dept+"\t\t");
				System.out.println();
			}
		
		System.out.println();
		System.out.println();
	}
	
	
	public static void exit() {
		System.out.println("학사 프로그램을 종료합니다");
		System.exit(0);
	}
	
}
