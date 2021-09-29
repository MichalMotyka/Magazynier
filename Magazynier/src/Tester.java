import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tester {

	public static void main(String[] args) throws SQLException, ParseException {
		Weryfikator wer = new Weryfikator();
		Date data = new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-20");
		Date dataD = new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-19");
		if(wer.CompareTwoDate(dataD, data)) {
			System.out.println("Wiêksza jest data"+ data);
		}else{
			System.out.println("Wiêksza jest data"+ dataD);
		}
	}

}


