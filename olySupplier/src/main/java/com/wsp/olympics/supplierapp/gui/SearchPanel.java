package com.wsp.olympics.supplierapp.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.wsp.olympics.supplierapp.service.SupplierModel;
import com.wsp.olympics.supplierapp.view.View;
import com.wsp.olympics.ws.types.PaidOrder;
import org.springframework.web.client.HttpClientErrorException;

public class SearchPanel extends JPanel implements View {
	private SupplierModel model;
	private JButton searchButton = new JButton("Get Paid Orders");
	
	public SearchPanel(SupplierModel model) {
		this.model = model;
		setup();
		build();
	}

	private void setup() {
		model.attach(this);
		searchButton.addActionListener(new ClickListener());
	}

	private void build() {
		add(searchButton);
	}

	@Override
	public void update() {
	}

	private class ClickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				List<PaidOrder> paidOrderList = model.getOrderService().getPaidOrders();
				model.setPaidOrderList(paidOrderList);
				model.update();
				if (paidOrderList.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No paid orders found", "Information", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (HttpClientErrorException ex) {
				JOptionPane.showMessageDialog(null, ex.getResponseBodyAsString(), "Error returned by web service", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(),
						"General error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
