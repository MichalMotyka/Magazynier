import java.sql.SQLException;

public class Login {
	private static String Login;
	private String haslo;
	private boolean zalog;
	private String SQL;
	
	
	public void setLogin(String Login) {
		this.Login = Login;
	}
	public String getLogin() {
		return this.Login;
		
	}
	
	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}
	public boolean isZalog(String login, String haslo) throws SQLException {
		SQL = "Select UserLogin.ID from UserLogin, UserPassLog where UserLogin = '"+login+"' AND UserPassLog.PASSLOG = HASHBYTES('SHA','"+haslo+"')";
		SQLStatment SQLS = new SQLStatment();
		if(SQLS.getSQL(SQL).next()) {
			zalog = true;
			this.Login = login;
		}else {
			zalog = false;
			this.Login = null;
			this.haslo = null;
		}
		return zalog;
		
	}
}
