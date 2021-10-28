import java.sql.SQLException;
import java.util.Date;

public class Weryfikator {
	private String SQL;
	private SQLStatment SQLT= new SQLStatment();
	public boolean CheckDate() {
		
		return true ;
	}
	public boolean checkValuesIsNULL(String a) {
		return a.isEmpty();
	}
	public boolean CompareTwoDate(Date Data, Date Sprawdzana) {
		boolean result;
		if(Data.compareTo(Sprawdzana)<=0) {
			result = true;
		}else {
			result = false;
		}
		
		return result;
	}
	public boolean IsExist(String Sprawdzana,String Obiekt,String TYP) throws SQLException {
		SQL = "Select "+Obiekt+" FROM "+TYP+" WHERE "+Obiekt+" = '"+Sprawdzana+"'";
		return SQLT.getSQL(SQL).next();
	}

}
