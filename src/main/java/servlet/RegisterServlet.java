package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.FindAccountLogic;
import model.RegisterLogic;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerInput.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		String mail = request.getParameter("mail");
		String name = request.getParameter("name");
		String ageString = request.getParameter("age");
		
		if (ageString != null && !ageString.trim().isEmpty()) {
		    try {
		        int age = Integer.parseInt(ageString);
		    	
				Account account = new Account(userId, pass, mail, name, age);
				FindAccountLogic findBo = new FindAccountLogic();
				boolean findResult = findBo.execute(account);

				if (findResult) {
					RegisterLogic bo = new RegisterLogic();
					boolean result = bo.execute(account);
				
					if (result) {
						request.setAttribute("account", account);
						RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerResult.jsp");
						dispatcher.forward(request, response);
					} else {
						request.setAttribute("errormsg", "このユーザーIDは既に登録されています");
						RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerInput.jsp");
						dispatcher.forward(request, response);	
					}
				} else {
					request.setAttribute("errormsg", "このユーザーIDは既に登録されています");
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerInput.jsp");
					dispatcher.forward(request, response);
				}	
		    } catch (NumberFormatException e) {
		    	request.setAttribute("errormsg", "無効な年齢が入力されました");
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerInput.jsp");
				dispatcher.forward(request, response);
		    }
		} else {
			request.setAttribute("errormsg", "年齢が指定されていません");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerInput.jsp");
			dispatcher.forward(request, response);
		}
	}
}
