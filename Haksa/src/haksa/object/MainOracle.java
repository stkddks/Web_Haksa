package haksa.object;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import haksa.function.Function;


public class MainOracle {
	
	public static void main(String[] args) {
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String protocol=null;
		Connection conn = null;
		
		Register rg = new Register();
		Search sc = new Search();
		Delete del = new Delete();
		List li = new List();
		
		while(true) {
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
				
				try {
					protocol =rg.setInput();
					conn = (Connection) Register.getConnection();
					rg.haksaProcess();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				finally {
					try {
//					stmt.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
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
			// 검색
			else if(protocol.equals("2")) {
				try {
					sc.setCho();
					sc.searchProcess();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				System.out.println("계속 하시려면1, 종료하시려면 0을 눌러주세요:");
				try {
					String cnt = input.readLine();
					if(cnt.equals("1")) {
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
					del.setCho();
					del.deleteProcess();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
			// 전체출력
			else if(protocol.equals("4")) {
				try {
					li.listProcess();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
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
