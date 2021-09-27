import java.sql.SQLException;

public class StatusOperacji {
	private String SQL;
	private static boolean czyMagazyn_ADD = false ;
	private static boolean czyEdit = false;
	private static boolean czyMagazyn_EDIT = false;
	private static boolean czyMagazyn_VIEW = false;
	
	private SQLStatment TSQL = new SQLStatment();
	
	
	
	
	
	
	
	public boolean isCzyMagazyn_ADD() {
		return czyMagazyn_ADD;
	}

	public static void setCzyMagazyn_ADD(boolean czyMagazyn_ADd) {
		czyMagazyn_ADD = czyMagazyn_ADd;
	} 
	
	
	public void BlokadaObiektu(String typ, String obiekt,String Login) {
		this.SQL = "UPDATE "+typ+" SET Edycja = 1, User_Edit ='"+Login+"' WHERE Nazwa ='"+obiekt+"'";
		TSQL.setSQL(this.SQL);
	}
	public Boolean isBlokadaObiektu(String typ, String obiekt) throws SQLException {
		this.SQL = "Select User_Edit FROM "+typ+" WHERE Nazwa = '"+obiekt+"' AND Edycja = 1";
		if(!TSQL.getSQL(this.SQL).next()) {
			czyEdit = false;
		}else {
			czyEdit = true;
		}
		return czyEdit;
	}
	
	public void OdblokowanieObiektu(String typ, String obiekt) {
		this.SQL = "UPDATE "+typ+" SET Edycja = 0, User_Edit = NULL WHERE Nazwa ='"+obiekt+"'";
		TSQL.setSQL(this.SQL);
	}

	public static boolean isCzyMagazyn_VIEW() {
		return czyMagazyn_VIEW;
	}

	public static void setCzyMagazyn_VIEW(boolean czyMagazyn_VIEW) {
		StatusOperacji.czyMagazyn_VIEW = czyMagazyn_VIEW;
	}

	public static boolean isCzyMagazyn_EDIT() {
		return czyMagazyn_EDIT;
	}

	public static void setCzyMagazyn_EDIT(boolean czyMagazyn_EDIT) {
		StatusOperacji.czyMagazyn_EDIT = czyMagazyn_EDIT;
	}

}
