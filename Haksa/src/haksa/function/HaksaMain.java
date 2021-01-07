package haksa.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HaksaMain {
	
	public static void main(String[] args) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		String protocol=null;
		Connection conn = null;
		Statement stmt = null;
		int cnt = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");		// 오라클을 자바에게 인식한다(로드)
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} 
		
		while(true) {
			try {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "khbclass", "dkdlxl");	// 연결한다.	// cafe24를 쓰면 연결할 필요 없다(빌드패스X)
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
			
			// 등록
			if(protocol.equals("1")) {
				
				try {
					Function.register(protocol, input, stmt, conn, cnt);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				finally {
					try {
//						stmt.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				System.out.println("계속하시려면 1, 종료하시려면 0 을 입력해주세요: ");
				try {
					String cnt1 = input.readLine();
					if (cnt1.equals("1")) {
						continue;
					} else {
						System.out.println("시스템이 종료됩니다.");
						System.exit(0);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
			
			// 검색
			else if(protocol.equals("2")) {
				try {
					Function.search(protocol, input, stmt, conn, cnt);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally {
					try {
//						stmt.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				System.out.println("계속 하시려면1, 종료하시려면 0을 눌러주세요:");
				try {
					String choice = input.readLine();
					if(choice.equals("1")) {
						continue;
					} else {
						System.out.println("시스템이 종료됩니다.");
						System.exit(0);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			// 삭제
			else if(protocol.equals("3")) {
				try {
					Function.delete(protocol, input, stmt, conn, cnt);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
//						stmt.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
			// 전체출력
			else if(protocol.equals("4")) {
				try {
					Function.list(protocol, stmt, conn);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
//						stmt.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} 
			
			// 종료
			else if(protocol.equals("0")) {
				Function.exit();
			}
			
		}
	}
	
}
