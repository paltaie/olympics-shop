package com.wsp.olympics.supplierapp.gui;

import com.wsp.olympics.supplierapp.service.SupplierModel;
import com.wsp.olympics.supplierapp.view.View;
import org.springframework.web.client.HttpClientErrorException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UpdateOrderPanel extends JPanel implements View {
	private static final String[] STATUSES = {"SENT", "ORDERED"};
	
	private SupplierModel model;
	private JLabel orderIdLabel = new JLabel("Order Number:");
	private JTextField orderNumberField = new JTextField(15);
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
		orderNumberField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!orderNumberField.getText().isEmpty()) {
					updateButton.setEnabled(true);
				} else {
					updateButton.setEnabled(false);
				}
				super.keyTyped(e);
			}
		});
		updateButton.addActionListener(new UpdateButtonListener());
	}

	private void build() {
		add(orderIdLabel);
		add(orderNumberField);
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
				model.getOrderService().updateOrderStatus(orderNumberField.getText(),
						statusComboBox.getSelectedItem().toString());
				JOptionPane.showMessageDialog(null, "Successfully updated order ID \"" +
						orderNumberField.getText() + "\" to \"" +
						statusComboBox.getSelectedItem().toString() + 
						"\"", "Success!", JOptionPane.INFORMATION_MESSAGE);
			} catch (HttpClientErrorException ex) {
				JOptionPane.showMessageDialog(null, ex.getResponseBodyAsString(),
						"Error from service",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
