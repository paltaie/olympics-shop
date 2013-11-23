package com.wsp.supplierapp.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.ws.soap.SOAPFaultException;

import com.wsp.supplierapp.service.SupplierModel;
import com.wsp.supplierapp.view.View;

public class UpdateOrderPanel extends JPanel implements View {
	private static final String[] STATUSES = {"ORDERED", "SENT"};
	
	private SupplierModel model;
	private JTextField orderIdField = new JTextField(15);
	private JComboBox statusComboBox = new JComboBox(STATUSES);
	private JButton updateButton = new JButton("Update");
	
	public UpdateOrderPanel(SupplierModel model) {
		this.model = model;
		setup();
		build();
	}

	private void setup() {
		model.attach(this);
		updateButton.setEnabled(false);
		updateButton.addActionListener(new UpdateButtonListener());
	}

	private void build() {
		add(orderIdField);
		add(statusComboBox);
		add(updateButton);
	}

	@Override
	public void update() {
		
	}
	
	private class UpdateButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				model.getOrderService().updateOrderStatus(orderIdField.getText(),
						statusComboBox.getSelectedItem().toString());
				JOptionPane.showMessageDialog(null, "Successfully updated order ID \"" +
						orderIdField.getText() + "\" to \"" +
						statusComboBox.getSelectedItem().toString() + 
						"\"", "Success!", JOptionPane.INFORMATION_MESSAGE);
			} catch (SOAPFaultException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error returned from server", JOptionPane.ERROR_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
