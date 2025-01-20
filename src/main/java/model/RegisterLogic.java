package model;

import dao.AccountsDAO;

public class RegisterLogic {
	public boolean execute(Account account) {
		AccountsDAO dao = new AccountsDAO();
		boolean accountResult = dao.registerAccount(account);
		return accountResult;
	}
}