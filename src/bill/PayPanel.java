package bill;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import School.NotFoundException;

public class PayPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<BillCriteria> criteria;
	private JLabel amount;
	private JButton pay;
	private JButton close;
	private BillOrganizer bill;
	private JButton payById;
	private JLabel enterID;
	private JTextField insertId;

	public PayPanel(BillOrganizer bill) {
		this.bill = bill;
		setLayout(new GridLayout(7, 1));
		createComponents();
		addComponents();
		actionLiners();
	}

	private void actionLiners() {
		this.pay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Bill payed = bill.payNextBill((BillCriteria) criteria
							.getSelectedItem());
					amount.setText("Your payed bill was "
							+ payed.getAmountDue());
				} catch (ListEmptyException | NotFoundException e1) {
					JOptionPane.showMessageDialog(null, "No bills to pay");
				}

			}

		});
		this.payById.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Bill payed = bill.payBill(Integer.parseInt(insertId
							.getText()));
					amount.setText("Your payed bill was "
							+ payed.getAmountDue());
				} catch (ListEmptyException | NotFoundException e1) {
					JOptionPane.showMessageDialog(null, "No bills to pay");
				}

			}

		});
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}

		});
	}

	private void addComponents() {
		add(new JLabel("Choose A category"));
		add(criteria);
		add(pay);

		add(amount);

		add(enterID);
		add(insertId);
		add(payById);
		add(close);
	}

	private void createComponents() {
		criteria = new JComboBox<BillCriteria>(BillCriteria.values());
		amount = new JLabel("");
		pay = new JButton("Pay By Category");
		close = new JButton("Close Pay");
		payById = new JButton("Pay By Id");
		insertId = new JTextField(5);
		enterID = new JLabel("Enter Id: ");

	}

}
