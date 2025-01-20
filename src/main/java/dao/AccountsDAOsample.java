package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.Login;

public class AccountsDAO {
	//DB接続に使用する情報
		private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/sukkiriShop";
		private final String DB_USER = "sa";
		private final String DB_PASS = "";
		
	//1件検索するメソッド
	public Account findByLogin(Login login) {
		
		Account account = null;
		
		//JDBCドライバを読みこむ
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベースに接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			
			//select文を準備
			String sql = "select user_id,pass,mail,name,age from accounts where "
					+ "user_id = ? and pass = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//?に値を設定し、selectを実行
			pStmt.setString(1, login.getUserId());
			pStmt.setString(2, login.getPass());
			ResultSet rs = pStmt.executeQuery();
			
			//ユーザーが存在したらデータを取得し、Accountインスタンスを生成する
			if(rs.next()) {
				String id = rs.getString("user_id");
				String pass = rs.getString("pass");
				String mail = rs.getString("mail");
				String name = rs.getString("name");
				int age = rs.getInt("age");
								
				account = new Account(id,pass,mail,name,age);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return account;
	}
		//登録前にユーザーIDの重複をチェックする1件検索
		public Account findAccount(Account account){
			
		//検索したアカウント情報を代入する変数を定義
		Account findAccount = null;
		//JDBCドライバを読みこむ
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベースに接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			//select文を準備
			String sql = "select user_id,pass,mail,name,age from accounts where "
					+ "user_id = ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//?に値を設定し、selectを実行
			pStmt.setString(1, account.getUserId());
			ResultSet rs = pStmt.executeQuery();

			//ユーザーが存在したらエラーメッセ―ジを設定し例外をスロー
			if (rs.next()) {
				String id = rs.getString("user_id");
				String pass = rs.getString("pass");
				String mail = rs.getString("mail");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				findAccount = new Account(id,pass,mail,name,age);
				return findAccount;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return findAccount;
	}
	//ユーザー登録処理
	public boolean registerAccount(Account account) {

		//JDBCドライバを読みこむ
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベースに接続
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			//SQL文を準備
			String userId = account.getUserId();
			String pass = account.getPass();
			String mail = account.getMail();
			String name = account.getName();
			int age = account.getAge();
			String sql = "insert into ACCOUNTS values (?,?,?,?,?);";
			PreparedStatement pStmt = con.prepareStatement(sql);

			//INパラメーターをセット
			pStmt.setString(1, userId);
			pStmt.setString(2, pass);
			pStmt.setString(3, mail);
			pStmt.setString(4, name);
			pStmt.setInt(5, age);
			
			//SQL文を実行
			int count = pStmt.executeUpdate();

			//1行登録できたかを判定
			if (count == 1) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
