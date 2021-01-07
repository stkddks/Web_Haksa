package haksa;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HaksaProjectMysql {
   public static void main(String[] args) {
      BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
      String protocol = null;
      Connection conn = null;

      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
      } catch (ClassNotFoundException e1) {
         e1.printStackTrace();
      }
      Statement stmt = null;
      while (true) {
         try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "khbclass", "dkdlxl");
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
         if (protocol.equals("1")) { // 등록

            System.out.println();
            System.out.println("==========메뉴선택=========");
            System.out.println("1. 학생");
            System.out.println("2. 교수");
            System.out.println("3. 관리자");
            System.out.println("4. 이전메뉴");
            System.out.print("번호를 선택해주세요: ");

            try {
               protocol = input.readLine();
            } catch (IOException e) {
               e.printStackTrace();

            }
            if (protocol.equals("1")) {
               System.out.println("학생등록");
               try {
                  System.out.println("나이: ");
                  String age1 = input.readLine();
                  System.out.println("이름: ");
                  String name = input.readLine();
                  System.out.println("학번: ");
                  String hakbun1 = input.readLine();

                  int age = Integer.parseInt(age1);
                  int hakbun = Integer.parseInt(hakbun1);

                  stmt = conn.createStatement();
                  String sql = "INSERT INTO STUDENT(NO, AGE, NAME, HAKBUN) VALUES (student_no.nextval, " + age
                        + ", '" + name + "', " + hakbun + ")";

                  int cnt = stmt.executeUpdate(sql);
                  System.out.println(cnt + "명의 학생이 등록 되었습니다");

               } catch (IOException e) {
                  e.printStackTrace();
               } catch (SQLException e) {
                  e.printStackTrace();
               } finally {
                  try {
                     conn.close();
                     stmt.close();
                  } catch (SQLException e) {
                     e.printStackTrace();
                  }
               }
            } else if (protocol.equals("2")) {
               System.out.println("교수등록");
               try {
                  System.out.println("나이: ");
                  String age1 = input.readLine();
                  System.out.println("이름: ");
                  String name = input.readLine();
                  System.out.println("과목: ");
                  String subject = input.readLine();
                  int age = Integer.parseInt(age1);

                  try {
                     stmt = conn.createStatement();
                     String sql = "INSERT INTO PROFESSOR(NO, AGE, NAME, SUBJECT) VALUES (professor_no.nextval, "
                           + age + ", '" + name + "', '" + subject + "')";
                     int cnt = stmt.executeUpdate(sql);
                     System.out.println(cnt + "명의 교수님이 등록 되었습니다");
                  } catch (SQLException e) {
                     e.printStackTrace();
                  }

               } catch (IOException e) {
                  e.printStackTrace();
               } finally {
                  try {
                     conn.close();
                     stmt.close();
                  } catch (SQLException e) {
                     e.printStackTrace();
                  }
               }
            } else if (protocol.equals("3")) {
               System.out.println("관리자등록");
               try {
                  System.out.println("나이: ");
                  String age1 = input.readLine();
                  System.out.println("이름: ");
                  String name = input.readLine();
                  System.out.println("부서: ");
                  String dept = input.readLine();
                  int age = Integer.parseInt(age1);
                  try {
                     stmt = conn.createStatement();
                     String sql = "INSERT INTO MANAGER(NO, AGE, NAME, PART) VALUES (manager_no.nextval, " + age
                           + " , '" + name + "', '" + dept + "')";
                     int cnt = stmt.executeUpdate(sql);
                     System.out.println(cnt + "명의 관리자가 등록 되었습니다");
                  } catch (SQLException e) {
                     e.printStackTrace();
                  }

               } catch (IOException e) {
                  e.printStackTrace();
               } finally {
                  try {
                     conn.close();
                     stmt.close();
                  } catch (SQLException e) {
                     e.printStackTrace();
                  }
               }
            } else if (protocol.equals("4")) {
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

         else if (protocol.equals("2")) { // 찾기
            System.out.println("찾기");
            System.out.println("찾을대상:");
            System.out.println("1.학생 2.교수 3.관리자");
            try {
               String select = input.readLine();

               if (select.equals("1")) {
                  System.out.println("찾을 학생 이름을 입력해주세요: ");
                  String searchName = input.readLine();

                  stmt = conn.createStatement();
                  String sql = "SELECT * FROM STUDENT WHERE NAME ='" + searchName + "'";
                  ResultSet rs = stmt.executeQuery(sql);

                  int age = 0;
                  String name = null;
                  int hakbun = 0;

                  if (!rs.isBeforeFirst()) {
                     System.out.println("찾는 학생이 없습니다.");
                  }

                  while (rs.next()) {
                     age = rs.getInt("age");
                     name = rs.getString("name");
                     hakbun = rs.getInt("hakbun");
                     System.out.print("이름: " + name + "\t\t 나이: " + age + "\t\t 학번: " + hakbun + "\n");
                  }

               } else if (select.equals("2")) {
                  System.out.println("찾을 교수 이름을 입력해주세요: ");
                  String searchName = input.readLine();

                  stmt = conn.createStatement();
                  String sql = "SELECT * FROM PROFESSOR WHERE NAME = '" + searchName + "'";
                  ResultSet rs = stmt.executeQuery(sql);

                  int age = 0;
                  String name = null;
                  String subject = null;

                  while (rs.next()) {
                     age = rs.getInt("age");
                     name = rs.getString("name");
                     subject = rs.getString("subject");
                  }
                  if (!rs.next()) {
                     System.out.println("찾는 교수가 없습니다.");
                  }
                  System.out.print("이름: " + name + "\t\t 나이: " + age + "\t\t 학번: " + subject + "\n");
               } else if (select.equals("3")) {
                  System.out.println("찾을 관리자 이름을 입력해주세요:");
                  String searchName = input.readLine();

                  stmt = conn.createStatement();
                  String sql = "SELECT * FROM MANAGER WHERE NAME ='" + searchName + "'";
                  ResultSet rs = stmt.executeQuery(sql);

                  int age = 0;
                  String name = null;
                  String part = null;

                  while (rs.next()) {
                     age = rs.getInt("age");
                     name = rs.getString("name");
                     part = rs.getString("subject");
                  }
                  if (!rs.next()) {
                     System.out.println("찾는 관리자가 없습니다.");
                  }
                  System.out.print("이름: " + name + "\t\t 나이: " + age + "\t\t 학번: " + part + "\n");
               }

            } catch (IOException e) {
               e.printStackTrace();
            } catch (SQLException e) {
               e.printStackTrace();
            } finally {
               try {
                  conn.close();
                  stmt.close();
               } catch (SQLException e) {
                  e.printStackTrace();
               }
            }
         }

         else if (protocol.equals("3")) { // 삭제
            System.out.println("삭제");
            System.out.println("삭제할 대상:");
            System.out.println("1.학생 2.교수 3.관리자");

            try {
               String select = input.readLine();

               if (select.equals("1")) {
                  System.out.println("삭제할 학생 이름을 입력해주세요:");
                  String deleteName = input.readLine();

                  stmt = conn.createStatement();
                  String sql = "DELETE FROM STUDENT WHERE NAME = '" + deleteName + "'";
                  int cnt = stmt.executeUpdate(sql);

                  if (cnt == 0) {
                     System.out.println("삭제할 학생이 존재하지 않습니다.");
                  } else {
                     System.out.println(deleteName + "님이 삭제되었습니다.");
                  }

               } else if (select.equals("2")) {
                  System.out.println("삭제할 교수 이름을 입력해주세요:");
                  String deleteName = input.readLine();

                  stmt = conn.createStatement();
                  String sql = "DELETE FROM PROFESSOR WHERE NAME = '" + deleteName + "'";
                  int cnt = stmt.executeUpdate(sql);

                  if (cnt == 0) {
                     System.out.println("삭제할 교수가 존재하지 않습니다.");
                  } else {
                     System.out.println(deleteName + "님이 삭제되었습니다.");
                  }
               } else if (select.equals("3")) {
                  System.out.println("삭제할 관리자 이름을 입력해주세요:");
                  String deleteName = input.readLine();

                  stmt = conn.createStatement();
                  String sql = "DELETE FROM MANAGER WHERE NAME = '" + deleteName + "'";
                  int cnt = stmt.executeUpdate(sql);

                  if (cnt == 0) {
                     System.out.println("삭제할 관리자가 존재하지 않습니다.");
                  } else {
                     System.out.println(deleteName + "님이 삭제되었습니다.");
                  }
               }
            } catch (IOException e) {
               e.printStackTrace();
            } catch (SQLException e) {
               e.printStackTrace();
            } finally {
               try {
                  conn.close();
                  stmt.close();
               } catch (SQLException e) {
                  e.printStackTrace();
               }
            }
         }

         else if (protocol.equals("4")) { // 전체 출력
            System.out.println();
            try {
               System.out.println("--------------학생 전체 출력---------------");
               stmt = conn.createStatement();
               String sql = "select * from Student";
               ResultSet rs = stmt.executeQuery(sql);
               while (rs.next()) {
                  int age = rs.getInt("age");
                  String name = rs.getString("name");
                  int hakbun = rs.getInt("hakbun");
                  System.out.print("이름: " + name + "\t\t 나이: " + age + "\t\t 학번: " + hakbun + "\t\t");
                  System.out.println();
               }
               System.out.println();
               System.out.println("--------------교수 전체 출력---------------");
               stmt = conn.createStatement();
               sql = "select * from Professor";
               rs = stmt.executeQuery(sql);
               while (rs.next()) {
                  int age = rs.getInt("age");
                  String name = rs.getString("name");
                  String subject = rs.getString("subject");
                  System.out.print("이름: " + name + "\t\t 나이: " + age + "\t\t 과목: " + subject + "\t\t");
                  System.out.println();
               }
               System.out.println();
               System.out.println("--------------관리자 전체 출력--------------");
               stmt = conn.createStatement();
               sql = "select * from manager";
               rs = stmt.executeQuery(sql);
               while (rs.next()) {
                  int age = rs.getInt("age");
                  String name = rs.getString("name");
                  String part = rs.getString("part");
                  System.out.print("이름: " + name + "\t\t 나이: " + age + "\t\t 부서: " + part + "\t\t");
                  System.out.println();
               }

               stmt = conn.createStatement();
               sql = "SELECT S.NO 번호, S.AGE AS 나이, S.NAME AS 이름, S.HAKBUN AS 학번, P.AGE AS 교수나이, P.NAME AS 교수이름, P.SUBJECT AS 과목, M.AGE AS 관리자나이, M.NAME AS 관리자이름, M.PART AS 부서 FROM (STUDENT S FULL OUTER JOIN PROFESSOR P ON S.NO = P.NO) FULL OUTER JOIN MANAGER M on S.NO = M.NO ORDER BY 번호 ASC";
               rs = stmt.executeQuery(sql);

               System.out.println("학사 전체 출력");

               while (rs.next()) {
                  int sage = rs.getInt("나이");
                  String sname = rs.getString("이름");
                  int hakbun = rs.getInt("학번");

                  int page = rs.getInt("교수나이");
                  String pname = rs.getString("교수이름");
                  String subject = rs.getString("과목");

                  int mage = rs.getInt("관리자나이");
                  String mname = rs.getString("관리자이름");
                  String part = rs.getString("부서");

                  System.out.print(sage + "\t" + sname + "\t" + hakbun + "\t" + page + "\t" + pname + "\t"
                        + subject + "\t" + mage + "\t" + mname + "\t" + part + "\n");
               }

            } catch (SQLException e) {
               e.printStackTrace();
            } finally {
               try {
                  conn.close();
                  stmt.close();
               } catch (SQLException e) {
                  e.printStackTrace();
               }
            }
            System.out.println();

         } else if (protocol.equals("0")) {
            System.out.println("학사 프로그램을 종료합니다");
            System.exit(0);
         }

      } // 반복문
   }
}