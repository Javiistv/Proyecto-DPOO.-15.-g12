package init;

import javax.swing.JDialog;

import logic.Terminal;
import view.Login;
import view.Principal;

public class Main {

	public static void main(String[] args) {
		try {
			Terminal t = new Terminal();
			Login dialog = new Login(null, t);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
