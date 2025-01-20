package test;

import dao.AccountsDAO;
import model.Account;

public class FindAccountTest {
	public static void main(String[] args) {
		testFindAccountTestOK();
		testFindAccountTestNG();
	}
	public static void testFindAccountTestOK() {
		Account account = new Account("minato2", "1234", "yusuke.minato@miyabilink.jp", "湊 雄輔", 23);
		AccountsDAO dao = new AccountsDAO();
		Account result = dao.findAccount(account);
		if (result == null) {
			System.out.println("testFindAccountTestOK：成功しました");
		} else {
			System.out.println("testFindAccountTestOK：失敗しました");
			
		}
	}
	public static void testFindAccountTestNG() {
		Account account = new Account("minato", "1234", "yusuke.minato@miyabilink.jp", "湊 雄輔", 23);
		AccountsDAO dao = new AccountsDAO();
		Account result = dao.findAccount(account);
		if (result != null &&
			result.getUserId().equals("minato") &&
			result.getPass().equals("1234") && 
			result.getMail().equals("yusuke.minato@miyabilink.jp") &&
			result.getName().equals("湊 雄輔") &&
			result.getAge() == 23) {
			System.out.println("testFindAccountTestNG：成功しました");
		} else {
			System.out.println("testFindAccountTestNG：失敗しました");			
		}
	}
}
