package bill;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class OrganizeBillGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton add;
	private JButton total;
	private JButton view;
	private JButton pay;
	private JButton exit;
	private JPanel north;
	private JPanel center;
	private JPanel east;
	private BillOrganizer bills;
	private JButton reload;

	public OrganizeBillGui() {
		setTitle("Organize Bills");
		setSize(1200, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createComponents();
		addComponents();

		addListners();
		bills = new BillOrganizer();

	}

	private void addComponents() {
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.add(north, BorderLayout.NORTH);
		center.add(add);
		center.add(total);
		center.add(view);
		center.add(pay);
		center.add(exit);
		center.add(reload);

		container.add(center, BorderLayout.CENTER);
		container.add(east, BorderLayout.EAST);
	}

	private void createComponents() {
		this.north = new JPanel();
		JLabel heading = new JLabel("My Bill Organizer");
		heading.setFont(new Font("Serif", Font.BOLD, 23));
		heading.setForeground(Color.DARK_GRAY);
		north.add(heading);

		// center
		this.center = new JPanel();
		center.setLayout(new GridLayout(3, 2));
		this.add = new JButton("Add a Bill");
		this.total = new JButton("Calculate Total Bill");
		this.view = new JButton("View Bills");
		view.setToolTipText("click button to see a list of ways to view the bills");
		this.pay = new JButton("Pay Bill");
		pay.setToolTipText("click this button to choose which catergory you want to pay a bill from");
		this.exit = new JButton("Save and Exit");
		exit.setToolTipText("click button to save all bills to a file and exit bill orginizer");
		this.reload = new JButton("Reload old Orginizer");

		// east
		east = new JPanel();

	}

	private void addListners() {
		add.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// no need to add here and recreate the panel each click
				AddBillPanel billPanel = new AddBillPanel(bills);

				east.add(billPanel);
				east.revalidate();

			}

		});
		view.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ViewPanel viewPanel = new ViewPanel(bills);

				east.add(viewPanel);
				east.revalidate();

			}

		});

		this.total.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				TotalPanel totalPanel = new TotalPanel(bills);
				east.add(totalPanel);
				east.revalidate();
			}

		});
		this.pay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PayPanel payPanel = new PayPanel(bills);
				east.add(payPanel);
				east.revalidate();
			}

		});
		this.exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					bills.closeOrginizer();
					dispose();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "file io exception");
				}

			}

		});
		this.reload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					bills = new BillOrganizer("BillOrginizer.ser");
					JOptionPane.showMessageDialog(null, "loaded file");
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
	}

	public static void main(String args[]) {
		OrganizeBillGui frame = new OrganizeBillGui();
		frame.setVisible(true);
	}
	
}
