import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TowarSQL {
	private String Magazyn;
	private String SQL;
	private int Stan ;
	SQLStatment SQLS = new SQLStatment();
	public void ADDMAGAZYN(String magazyn, String Lokalizacja, Boolean czyAktywny, String string) {
		if(czyAktywny) {
			this.Stan = 1;
		}else {
			this.Stan = 0;
		}
		SQL = "INSERT INTO MAGAZYNY(Nazwa,Lokalizacja,czyAkty,DataAktywacji,Edycja) VALUES ('"+magazyn+"','"+Lokalizacja+"',"+this.Stan+",'"+string+"',0)";
		
		SQLS.setSQL(SQL);
		
	} 
	public ResultSet GETMAGAZYN() {
		SQL = "SELECT Nazwa, Lokalizacja FROM Magazyny";
		return SQLS.getSQL(SQL);
		
	}
	public String[] GETVALUES_MAG(String Nazwa) {
		SQL = "SELECT Nazwa,Lokalizacja,czyAkty,DataAktywacji,DataDezaktywacji FROM Magazyny WHERE Nazwa = '"+Nazwa+"'";
		ResultSet rs = SQLS.getSQL(SQL);
		String[] Values = new String[5];
		try {
			if(rs.next()) {
				Values[0] = rs.getString("Nazwa"); 
				Values[1] = rs.getString("Lokalizacja"); 
				Values[2] = rs.getString("czyAkty"); 
				Values[3] = rs.getString("DataAktywacji"); 
				Values[4] = rs.getString("DataDezaktywacji"); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Values;
		
	}
	public void UPDATEMAGAZYN(String magazyn, String Lokalizacja, Boolean czyAktywny, String DataA,String DataD,String WHERENazwa) {
		if(czyAktywny) {
			this.Stan = 1;
		}else {
			this.Stan = 0;
		}
		SQL = "UPDATE MAGAZYNY SET Nazwa ='"+magazyn+"', Lokalizacja = '"+Lokalizacja+"',czyAkty ="+this.Stan+",DataAktywacji ='"+DataA+"',DataDezaktywacji = '"+DataD+"',Edycja = 0 WHERE Nazwa ='"+WHERENazwa+"'";
		
		SQLS.setSQL(SQL);
	}
}
