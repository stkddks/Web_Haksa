package haksa.object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class List {
	private Connection conn;
	   private Statement stmt;
	   private String sql;
	   private ResultSet rs;
	   private int age;
	   private String name;
	   private int hakbun;
	   private String subject;
	   private String dept;
	   private int p_age;
	   private String p_name;
	   private String p_subject;
	   private int m_age;
	   private String m_name;
	   private String m_dept;

	   public List() {

	   }

	   public void listStudentDisp() {
//	      System.out.print("이름: " + name + "\t\t나이: " + age + "\t\t 학번: " + hakbun + "\n");
	      System.out.print("이름: "+name+"\t\t 나이: "+age+"\t\t 학번: "+hakbun+"\t\t");
	   }

	   public void listProfessorDisp() {
//	      System.out.print("이름: " + name + "\t\t나이: " + age + "\t\t 과목: " + subject + "\n");
	      System.out.print("이름: "+name+"\t\t 나이: "+age+"\t\t 과목: "+subject+"\t\t");
	   }

	   public void listManagerDisp() {
//	      System.out.print("이름: " + name + "\t\t나이: " + age + "\t\t 부서: " + part + "\n");
	      System.out.print("이름: "+name+"\t\t 나이: "+age+"\t\t 부서: "+dept+"\t\t");
	   }

	   public void listHaksaDisp() {
		   System.out.print("학생이름: "+name+"\t\t 학생나이: "+age+"\t\t 학번: "+hakbun+"\t\t 교수이름: "+p_name+"\t\t 교수나이: "+p_age+"\t\t 과목: "+p_subject+"\t\t 관리자이름: "+m_name+"\t\t 관리자나이: "+m_age+"\t\t 부서: "+m_dept+"\t\t");
	   }

	   public void listProcess() throws SQLException {
	      System.out.println();
	      
	      conn = Register.getConnection();
	      System.out.println("--------------학생 전체 출력---------------");
	      stmt = conn.createStatement();
	      sql = "select * from Student";
	      rs = stmt.executeQuery(sql);
	      while (rs.next()) {
	         age = rs.getInt("age");
	         name = rs.getString("name");
	         hakbun = rs.getInt("hakbun");

	         listStudentDisp();
	      }
	      System.out.println();
	      System.out.println("--------------교수 전체 출력---------------");
	      stmt = conn.createStatement();
	      sql = "select * from Professor";
	      rs = stmt.executeQuery(sql);
	      while (rs.next()) {
	         age = rs.getInt("age");
	         name = rs.getString("name");
	         subject = rs.getString("subject");

	         listProfessorDisp();
	      }
	      System.out.println();
	      System.out.println("--------------관리자 전체 출력--------------");
	      stmt = conn.createStatement();
	      sql = "select * from manager";
	      rs = stmt.executeQuery(sql);
	      while (rs.next()) {
	         age = rs.getInt("age");
	         name = rs.getString("name");
	         dept = rs.getString("part");

	         listManagerDisp();
	      }

	      System.out.println("--------------학사 전체 출력--------------");
			stmt = conn.createStatement();
			
			sql = "select s.no as 번호, s.age as 나이, s.name as 이름, s.hakbun as 학번, p.age as 교수나이, p.name as 교수이름, p.subject as 과목, m.age as 관리자나이, m.name as 관리자이름, m.dept as 부서 from (student s left join professor p on s.no=p.no) full outer join manager m on s.no=m.no ORDER BY 번호 ASC";		
			
			rs = stmt.executeQuery(sql);

	      while (rs.next()) {
	        age = rs.getInt("나이");
	         name = rs.getString("이름");
	         hakbun = rs.getInt("학번");

	         p_age = rs.getInt("교수나이");
	         p_name = rs.getString("교수이름");
	         p_subject = rs.getString("과목");

	         m_age = rs.getInt("관리자나이");
	         m_name = rs.getString("관리자이름");
	         m_dept = rs.getString("부서");

	         listHaksaDisp();
	      }
	   }
}
