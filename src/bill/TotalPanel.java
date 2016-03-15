package bill;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TotalPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel total;
	private JButton close;

	public TotalPanel(BillOrganizer bill) {
		total = new JLabel(String.valueOf(bill.totalBill()));
		close = new JButton("Close Total Calculator");
		add(total);
		add(close);
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}

		});
	}
}
