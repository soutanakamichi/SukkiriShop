package model;

import dao.AccountsDAO;

public class FindAccountLogic {
	public boolean execute(Account account) {
		AccountsDAO dao = new AccountsDAO();
		Account accountResult = dao.findAccount(account);
		return accountResult == null;
	}
}
