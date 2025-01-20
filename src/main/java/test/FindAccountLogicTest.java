package test;

import model.Account;
import model.FindAccountLogic;

public class FindAccountLogicTest {
	public static void main(String[] args) {
		testExecuteOK();
		testExecuteNG();
	}
	public static void testExecuteOK() {
		Account account = new Account("minato2", "1234", "yusuke.minato@miyabilink.jp", "湊 雄輔", 23);
		FindAccountLogic bo = new FindAccountLogic();
		boolean result = bo.execute(account);
		if (result) {
			System.out.println("testExecuteOK：成功しました");
		} else {
			System.out.println("testExecuteOK：失敗しました");			
		}
	}
	public static void testExecuteNG() {
		Account account = new Account("minato", "1234", "yusuke.minato@miyabilink.jp", "湊 雄輔", 23);
		FindAccountLogic bo = new FindAccountLogic();
		boolean result = bo.execute(account);
		if (!result) {
			System.out.println("testExecuteNG：成功しました");
		} else {
			System.out.println("testExecuteNG：失敗しました");			
		}
	}
}
