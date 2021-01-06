
//얘는 Mysql

package haksa.mysql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HaksaProjectMysql {

@SuppressWarnings("resource")
public static void main(String[] args) throws InterruptedException {
   BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
   // 숫자로 프로토콜에 대한 약속을 잡았다.
   String protocol=null;

   Connection conn = null;
   Statement stmt = null;
   try {
      Class.forName("com.mysql.jdbc.Driver");      
   } catch (ClassNotFoundException e1) {
      e1.printStackTrace();
   } 
   while(true) {
      try {
         conn = DriverManager.getConnection("jdbc:mysql://bbr123.cafe24.com:3306/bbr123?characterEncoding=utf8", "bbr123","alstjr95!");
      } catch (SQLException e1) {
         e1.printStackTrace();
      }
      System.out.println("========학사관리=========");
      System.out.println("1. 등록");
      System.out.println("2. 검색");
      System.out.println("3. 삭제");
      System.out.println("4. 전체출력");
      System.out.println("0. 종료");
      System.out.println("=======================");
      System.out.print("번호를 선택해주세요: ");
      try {
         protocol = input.readLine();
      } catch (IOException e) {
         e.printStackTrace();
      }
      if(protocol.equals("1")) {
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
            try {
               protocol=input.readLine();
               System.out.println();
            } catch (IOException e) {
               e.printStackTrace();
               
            }
            if(protocol.equals("1")) {
               System.out.println("--------------학생 등록--------------");
               try {
                  System.out.printf("나이: ");
                  String age1 = input.readLine();      //나중에 형변환을 해줘야 한다.(db에서 NUMBER로 데이터타입을 지정해주었기 때문에)
                  System.out.printf("이름: ");
                  String name = input.readLine();
                  System.out.printf("학번: ");
                  String hakbun1 = input.readLine();      //나중에 형변환을 해줘야 한다.
                  
                  int age = Integer.parseInt(age1);       // 문자열로 받아들인 것을 정수타입으로 바꿔준다. 
                  int hakbun = Integer.parseInt(hakbun1); 
                  
                  // 쿼리준비
                  try {
                     stmt = conn.createStatement();
                     String sql = "insert into StudentA(age, name, hakbun) values ("+age+", '"+name+"', "+hakbun+")";
                     
                     int cnt = stmt.executeUpdate(sql);
                     System.out.println(cnt + "명의 학생이 등록 되었습니다");
                     System.out.println();
                  } catch (SQLException e) {
                     e.printStackTrace();
                  }
                  
               } catch (IOException e) {
                  e.printStackTrace();
               }
//               finally {
//                  try {
//                     stmt.close();
//                     conn.close();
//                  } catch (SQLException e) {
//                     e.printStackTrace();
//                  }
//               }
               
            } 
            else if(protocol.equals("2")) {
               System.out.println("--------------교수 등록--------------");
               try {
                  System.out.printf("나이: ");
                  String age1 = input.readLine();      //나중에 형변환을 해줘야 한다.
                  System.out.printf("이름: ");
                  String name = input.readLine();
                  System.out.printf("과목: ");
                  String subject = input.readLine();
                  int age = Integer.parseInt(age1);       // 문자열로 받아들인 것을 정수타입으로 바꿔준다. 
                  
                  try {
                     stmt = conn.createStatement();
                     String sql = "insert into ProfessorA(age, name, subject) values ("+age+", '"+name+"', '"+subject+"')";
                     int cnt = stmt.executeUpdate(sql);
                     System.out.println(cnt + "명의 교수님이 등록 되었습니다");
                     System.out.println();
                  } catch (SQLException e) {
                     e.printStackTrace();
                  }
                  
                  
               } catch (IOException e) {
                  e.printStackTrace();
               }finally {
                  try {
                     stmt.close();
                     conn.close();
                  } catch (SQLException e) {
                     e.printStackTrace();
                  }
               }
               
            } 
            else if(protocol.equals("3")) {
               System.out.println("--------------관리자 등록--------------");
               try {
                  System.out.printf("나이: ");
                  String age1 = input.readLine();//나중에 형변환을 해줘야 한다.
                  System.out.printf("이름: ");
                  String name = input.readLine();
                  System.out.printf("부서: ");
                  String dept = input.readLine();
                  int age = Integer.parseInt(age1);       // 문자열로 받아들인 것을 정수타입으로 바꿔준다. 
                  try {
                     stmt = conn.createStatement();
                     String sql = "insert into ManagerA(age, name, dept) values ("+age+" , '"+name+"', '"+dept+"')";
                     int cnt = stmt.executeUpdate(sql);
                     System.out.println(cnt + "명의 관리자가 등록 되었습니다");
                     System.out.println();
                  } catch (SQLException e) {
                     e.printStackTrace();
                  }
                  
               } catch (IOException e) {
                  e.printStackTrace();
               }finally {
                  try {
                     stmt.close();
                     conn.close();
                  } catch (SQLException e) {
                     e.printStackTrace();
                  }
               }
            } 
            else if (protocol.equals("4")) {
               System.out.println();
               break;
            }
            
            System.out.println("계속하시려면 1, 종료하시려면 0 을 입력해주세요: ");
            try {
               String cnt = input.readLine();
               if (cnt.equals("1")) {
                  continue;
               } else {
                  System.out.println("시스템이 종료됩니다.");
                  System.exit(0);
               }
            } catch (IOException e) {
               e.printStackTrace();
            }
            
         }
      }
     
      else if(protocol.equals("2")) {
         // 찾기
         try {
            System.out.println("찾을 사람의 직업을 입력해주세요(1. 학생 / 2.교수 / 3.관리자 ): ");
            String cho = input.readLine();{
               if(cho.equals("1")){
                  System.out.println("찾으시려는 학생의 이름을 입력해주세요: ");
                  String searchName = input.readLine();
                   stmt = conn.createStatement();
                  String sql = "select * from StudentA where name='"+searchName+"'";
                  ResultSet rs = stmt.executeQuery(sql);
                  if(!rs.isBeforeFirst()) {   // true면 커서가 첫행앞에 있다 = 값이 있다.   //false면 커서가 그 외의 위치에 있거나 결과 집합에 행이 들어 있지 않다  = 값이 없다.      //이 isBeforeFirst 메서드는 java.sql.ResultSet 인터페이스의 isBeforeFirst 메서드에 의해 지정됩니다.
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
                  String sql = "select * from ProfessorA where name='"+searchName+"'";
                  int cnt = stmt.executeUpdate(sql);
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
                  String sql = "select * from ManagerA where name='"+searchName+"'";
                  ResultSet rs = stmt.executeQuery(sql);   
                  if(!rs.isBeforeFirst()) {   // true면 커서가 첫행앞에 있다 = 값이 있다.   //false면 커서가 그 외의 위치에 있거나 결과 집합에 행이 들어 있지 않다  =  값이 없다.
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
                  continue;
               }
            
            }
            
            System.out.println("계속 하시려면1, 종료하시려면 0을 눌러주세요:");
            String choice = input.readLine();
            if(choice.equals("1")) {
               continue;
            } else {
               System.out.println("시스템이 종료됩니다.");
               System.exit(0);
            }

         } catch (IOException e) {
            e.printStackTrace();
         } catch (SQLException e) {
            e.printStackTrace();
         } finally {
            try {
               stmt.close();
               conn.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
      } 
      
      else if (protocol.equals("3")) {
         // 삭제
         try {
            System.out.println("삭제할 사람의 직업을 입력해주세요(1. 학생 / 2.교수 / 3.관리자 ): ");
            String cho = input.readLine();
            if(cho.equals("1")){   
               
               System.out.println("삭제할 학생의 이름을 입력해주세요: ");
               String setName = input.readLine();
               
               stmt = conn.createStatement();
               String sql = "select * from StudentA where name='"+setName+"'";
               ResultSet rs = stmt.executeQuery(sql);
               if(!rs.isBeforeFirst()) {   // true면 커서가 첫행앞에 있다 = 값이 있다.   //false면 커서가 그 외의 위치에 있거나 결과 집합에 행이 들어 있지 않다  =  값이 없다.
                  System.out.println("이승철이 부릅니다. 그런사람 또 없없습니다~");
               }
               while(rs.next()) {
                  stmt = conn.createStatement();
                  sql = "delete from StudentA where name='"+setName+"'";
                  int cnt = stmt.executeUpdate(sql);
                  String delName = rs.getString("name");
                  System.out.println(delName+"님의 정보를 삭제하였습니다.");
                  System.out.println();
               }
            }
            else if(cho.equals("2")){
               System.out.println("삭제할 교수의 이름을 입력해주세요: ");
               String setName = input.readLine();
               
               stmt = conn.createStatement();
               String sql = "select * from ProfessorA where name='"+setName+"'";
               ResultSet rs = stmt.executeQuery(sql);
               if(!rs.isBeforeFirst()) {   
                  System.out.println("이승철이 부릅니다. 그런사람 또 없없습니다~");
               }
               while(rs.next()) {
                  stmt = conn.createStatement();
                  sql = "delete from ProfessorA where name='"+setName+"'";
                  int cnt = stmt.executeUpdate(sql);
                  String delName = rs.getString("name");
                  System.out.println(delName+"님의 정보를 삭제하였습니다.");
                  System.out.println();
               }
               
            }
            else if(cho.equals("3")){
               System.out.println("삭제할 관리자의 이름을 입력해주세요: ");
               String setName = input.readLine();
               
               stmt = conn.createStatement();
               String sql = "select * from ManagerA where name='"+setName+"'";
               ResultSet rs = stmt.executeQuery(sql);
               if(!rs.isBeforeFirst()) {   
                  System.out.println("이승철이 부릅니다. 그런사람 또 없없습니다~");
               }
               while(rs.next()) {
                  stmt = conn.createStatement();
                  sql = "delete from ManagerA where name='"+setName+"'";
                  int cnt = stmt.executeUpdate(sql);
                  String delName = rs.getString("name");
                  System.out.println(delName+"님의 정보를 삭제하였습니다.");
                  System.out.println();
               }
            }
            
   
         } catch (IOException e) {
            e.printStackTrace();
         } catch (SQLException e) {
            e.printStackTrace();
         }
         finally {
            try {
               stmt.close();
               conn.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
      }

      
      else if(protocol.equals("4")) {
         System.out.println();
         try {
            System.out.println("--------------학생 전체 출력--------------");               
            stmt = conn.createStatement();
            String sql = "select * from StudentA";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
               int no = rs.getInt("no");
               int age = rs.getInt("age");
               String name = rs.getString("name");
               int hakbun = rs.getInt("hakbun");
               System.out.print("이름: "+name+"\t\t 나이: "+age+"\t\t 학번: "+hakbun+"\t\t");
               System.out.println();
            }
            System.out.println();
            System.out.println("--------------교수 전체 출력--------------");
            stmt = conn.createStatement();
            sql = "select * from ProfessorA";
            rs = stmt.executeQuery(sql);            
            while(rs.next()) {
               int no = rs.getInt("no");
               int age = rs.getInt("age");
               String name = rs.getString("name");
                String subject = rs.getString("subject");
               System.out.print("이름: "+name+"\t\t 나이: "+age+"\t\t 과목: "+subject+"\t\t");
               System.out.println();
            }
            System.out.println();
            System.out.println("--------------관리자 전체 출력--------------");
            stmt = conn.createStatement();
            sql = "select * from ManagerA";
            rs = stmt.executeQuery(sql);            
            while(rs.next()) {
               int no = rs.getInt("no");
               int age = rs.getInt("age");
               String name = rs.getString("name");
                String dept = rs.getString("dept");
               System.out.print("이름: "+name+"\t\t 나이: "+age+"\t\t 부서: "+dept+"\t\t");
               System.out.println();
            }
            System.out.println();
            // mysql로 돌릴때는 학사전체출력 주석처리하기! 문법이 달라!!
            
            System.out.println("--------------학사 전체 출력--------------");
            stmt = conn.createStatement();
            
//            sql = "select s.no as 번호, s.age as 나이, s.name as 이름, s.hakbun as 학번, p.age as 교수나이, p.name as 교수이름, p.subject as 과목, m.age as 관리자나이, m.name as 관리자이름, m.dept as 부서 from (student s left join professor p on s.no=p.no) left join manager m on s.no=m.no ORDER BY 번호 ASC";   
            //left join - oracle
//            sql = "select s.no as 번호, s.age as 나이, s.name as 이름, s.hakbun as 학번, p.age as 교수나이, p.name as 교수이름, p.subject as 과목, m.age as 관리자나이, m.name as 관리자이름, m.dept as 부서 from (student s left join professor p on s.no=p.no) full outer join manager m on s.no=m.no ORDER BY 번호 ASC";      
            //full outer join - oracle
            
            sql = "SELECT s.no as 번호, s.age as 나이, s.name as 이름, s.hakbun as 학번, p.age as 교수나이, p.name as 교수이름, p.subject as 과목, m.age as 관리자나이, m.name as 관리자이름, m.dept as 부서  FROM StudentA as s LEFT JOIN ProfessorA as p ON StudentA.no = ProfessorA.no LEFT JOIN ManagerA as m ON StudentA.no = ManagerA.no UNION ALL SELECT * FROM ProfessorA LEFT JOIN StudentA ON StudentA.no = ProfessorA.no LEFT JOIN ManagerA ON ProfessorA.no = ManagerA.no";
            
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
            
            
         } catch (SQLException e) {
            e.printStackTrace();
         } finally {
            try {
               stmt.close();
               conn.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }

         System.out.println();
         System.out.println();
         
      }
      
      else if(protocol.equals("0")) {
         System.out.println("학사 프로그램을 종료합니다");
         System.exit(0);
      }
      
   }// 반복문
}
}