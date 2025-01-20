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

//ユーザー登録機能のサーブレット
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	//ユーザー登録入力画面にフォワード
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//ユーザ登録入力画面にフォワード
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/V102_01registerInput.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータの取得
	    request.setCharacterEncoding("UTF-8");
	    String userId = request.getParameter("userId");
	    String pass = request.getParameter("pass");
	    String mail = request.getParameter("mail");
	    String name = request.getParameter("name");
	    int age = Integer.parseInt(request.getParameter("age"));
	    	    
	    // Accountインスタンス（ユーザー情報）の生成
	    Account account = new Account(userId,pass,mail,name,age);
	    //フォワード先のパス
	    String path = "WEB-INF/jsp/V102_01registerInput.jsp";
 
	    // 重複ユーザーIDのチェック
	    FindAccountLogic  findAccountLogic = new FindAccountLogic();
	    boolean result = findAccountLogic.execute(account);

	    // resultがtrue（重複ユーザがいない場合）ユーザー登録処理の呼び出し
	    if (result == true) {		//該当するaccountはいない
	    	RegisterLogic registerLogic = new RegisterLogic();
	    	registerLogic.execute(account);
		
	    	// ユーザー情報をリクエストスコープに保存
	    	request.setAttribute("account", account);
	    	//フォワード先のパスを「登録完了」画面にする
	    	path = "WEB-INF/jsp/V102_02registerResult.jsp";
	    }
	
		// 登録結果画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
		}
	
}



