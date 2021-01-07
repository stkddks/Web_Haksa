package haksa.object;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Register {
   static BufferedReader input;
   private static Connection conn;
   private String protocol;
   private Statement stmt;
   private int cnt;
   private String age1;
   private String name;
   private String hakbun1;
   private String subject;
   private String dept;

   static {		 // static이 있으면 생성자가 아니라 static필드에서 생성해준다
      input = new BufferedReader(new InputStreamReader(System.in)); // BufferedReader로 키보드를 설정한다
   }
  


   public Register() {
	      try {
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      }
	   }

	   public static Connection getConnection() throws SQLException {
	      conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "khbclass", "dkdlxl");
	      return conn;
	   }

   public String setInput() throws IOException {
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
      return protocol;
   }

   public void setAge() throws IOException {
      System.out.println("나이 : ");
      age1 = input.readLine();
   }

   public void setName() throws IOException {
      System.out.println("이름 : ");
      name = input.readLine();
   }

   public void setHakbun() throws IOException {
      System.out.println("학번 : ");
      hakbun1 = input.readLine();
   }

   public void setSubject() throws IOException {
      System.out.println("과목 : ");
      subject = input.readLine();
   }

   public void setDept() throws IOException {
      System.out.println("부서 : ");
      dept = input.readLine();
   }

   public String haksaProcess() throws IOException, SQLException {
      if (protocol.equals("1")) {
    	  System.out.println("--------------학생 등록--------------");
         setAge();
         setName();
         setHakbun();
         int age = Integer.parseInt(age1); 		// 문자열로 받아들인 것을 정수타입으로 바꿔준다. 
			int hakbun = Integer.parseInt(hakbun1); 
			
			stmt = conn.createStatement();
			String sql = "insert into Student(no, age, name, hakbun) values (student_no.nextval, "+age+", '"+name+"', "+hakbun+")";
			
			cnt = stmt.executeUpdate(sql);
			registerDisp(cnt, "학생");
      } else if (protocol.equals("2")) {
         setAge();
         setName();
         setSubject();
         int age = Integer.parseInt(age1); 		// 문자열로 받아들인 것을 정수타입으로 바꿔준다. 
			
			stmt = conn.createStatement();
			String sql = "insert into Professor(no, age, name, subject) values (professor_no.nextval, "+age+", '"+name+"', '"+subject+"')";
			cnt = stmt.executeUpdate(sql);
			registerDisp(cnt, "교수");
      } else if (protocol.equals("3")) {
         setAge();
         setName();
         setDept();
         int age = Integer.parseInt(age1); 		// 문자열로 받아들인 것을 정수타입으로 바꿔준다. 
			
			stmt = conn.createStatement();
			String sql = "insert into manager(no, age, name, dept) values (manager_no.nextval, "+age+" , '"+name+"', '"+dept+"')";
			cnt = stmt.executeUpdate(sql);
			
			registerDisp(cnt, "매니저");
      }
//      else if (protocol.equals("4")) {
//			System.out.println();
//			break;
//		}
      return protocol; // 중요허다
   }

   public void registerDisp(int cnt, String position) {
      System.out.println(cnt + "건 " + position + "이 등록 되었습니다.");
   }


   
   /*
   public static void main(String[] args) throws SQLException {
      Register rg = new Register();
      try {
          conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "khbclass", "dkdlxl");
          rg.setInput();
          rg.haksaProcess();
       } catch (SQLException e1) {
          e1.printStackTrace();
       } catch (IOException e) {
         e.printStackTrace();
      }
   }*/
}




