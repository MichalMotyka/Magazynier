import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tester {

	public static void main(String[] args) throws SQLException {
		Login log = new Login();
		if(log.isZalog("Admin","Admin")) {
			System.out.println("Zalogowano");
		}else {
			System.out.println("Nie zalogowano");
		}
	}

}


