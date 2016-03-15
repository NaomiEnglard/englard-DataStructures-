package bill;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import bill.LinkedList.LinkedListInternalIterator;

public class ViewPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton doneView;
	private JComboBox<BillCriteria> viewOptions;
	private JLabel billList;
	private BillOrganizer bill;
	private JScrollPane scrollList;

	public ViewPanel(BillOrganizer bill) {
		this.bill= bill;
		this.setPreferredSize(new Dimension(400,225));
		createComponents();
		addComponents();
		addLisetners();		
		
	}

	private void addLisetners() {
		//on change of choice refresh
		this.viewOptions.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				BillCriteria choice =(BillCriteria) viewOptions.getSelectedItem();
				String text = null;
				if (choice.equals(BillCriteria.BILLDUEDATE)) {
					
					text =(bill.toString(bill.iteratorByDate()));
				} else if (choice.equals(BillCriteria.BILLAMOUNT)) {
					text=(bill.toString(bill.iteratorByAmount()));
				} else if (choice.equals(BillCriteria.BILLTYPE)) {
					text =(bill.toString(bill
							.iteratorByBillType()));
				} 
				//text.replaceAll("\\n","<br>");
				text = "<html>" + text +"</html>";
				billList.setText(text);
				revalidate();
				
			}
			
		});
		//on click of done close panel
		this.doneView.addActionListener(new ActionListener()		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		
		});
		
	}

	private void createComponents() {
		doneView = new JButton("Done Viewing");
		viewOptions = new JComboBox<BillCriteria>(BillCriteria.values());
		String text =bill.toString(bill.iteratorByDate());
				text = "<html>" + text +"</html>";
		billList = new JLabel(text);
		 scrollList = new JScrollPane(billList);
		scrollList.setPreferredSize(new Dimension(150,getPreferredSize().height  ));
		

	}
	
	private void addComponents(){
		add(this.viewOptions);
		add(this.scrollList);
		add(this.doneView);
	}

}
