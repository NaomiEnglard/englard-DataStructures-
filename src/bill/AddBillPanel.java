package bill;


import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import pharmacy.DuplicateDataException;

import com.toedter.calendar.JCalendar;

public class AddBillPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel name;
	private JLabel amount;
	private JLabel date;
	private JLabel type;
	private JTextField insertName;
	private JTextField insertAmount;
	private JCalendar insertDate;
	private JComboBox<BillType> insertType;
	private JButton addBill;
	private BillOrganizer bill;
	private JLabel message;

	public AddBillPanel(BillOrganizer bills) {
		this.setLayout(new GridBagLayout());
		this.setBorder(LineBorder.createGrayLineBorder());
		createComponents();
		addComponents();
		bill = bills;
		this.addBill.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String name = insertName.getText();
				Double amount = Double.parseDouble(insertAmount.getText());
				Calendar selectedDate = insertDate.getCalendar();
				BillType type = (BillType) insertType.getSelectedItem();
				try {
					bill.insert(new Bill(name, amount, selectedDate, type));
				} catch (DuplicateDataException e1) {
					JOptionPane.showMessageDialog(null, "Duplicate Data not added");
				}
				//inform user that bill was aded
				JOptionPane.showMessageDialog(null, "A Bill was added");
				setVisible(false);
			}

		});

	}

	private void createComponents() {
		name = new JLabel("Name");
		insertName = new JTextField(20);
		insertName.setFocusable(true);
		amount = new JLabel("Bill Amount");
		insertAmount = new JTextField(10);
		date = new JLabel("Date");
		insertDate = new JCalendar();
		type = new JLabel("Bill Type");
		insertType = new JComboBox<BillType>(BillType.values());
			addBill = new JButton("Add Bill");
			message = new JLabel();
	}

	private void addComponents() {
		this.add(name);
		this.add(insertName);
		this.add(amount);
		this.add(insertAmount);
		this.add(date);
		this.add(insertDate);
		this.add(type);
		this.add(insertType);
		this.add(addBill);
		this.add(message);

	}

}
