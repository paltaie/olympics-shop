package com.wsp.supplierapp.gui;

import com.wsp.olympics.ws.OrderProduct;
import com.wsp.olympics.ws.types.PaidOrder;
import com.wsp.supplierapp.service.SupplierModel;
import com.wsp.supplierapp.view.View;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.text.NumberFormat;
import java.util.Vector;

public class ResultPanel extends JPanel implements View {

	private SupplierModel model;
	private JTable table;
	private OrderTableModel otm = new OrderTableModel();

	ResultPanel(SupplierModel model) {
		this.model = model;
		setup();
		build();
	}

	private void setup() {
		model.attach(this);
		table = new JTable(otm);
		table.setAutoCreateRowSorter(true);
	}

	private void build() {
		add(new JScrollPane(table));
	}

	@Override
	public void update() {
		if (model.getPaidOrderList() != null) {
			otm.fireTableDataChanged();
		}
	}

	private class OrderTableModel extends AbstractTableModel {

		private Vector<String> headings = new Vector<String>();

		public OrderTableModel() {
			headings.add("Order Number");
			headings.add("Surname");
			headings.add("Total Items");
			headings.add("Order Status");
			headings.add("Grand Total");
		}

		@Override
		public String getColumnName(int column) {
			return headings.get(column);
		}

		@Override
		public int getRowCount() {
			if (model.getPaidOrderList() != null) {
				return model.getPaidOrderList().size();
			}
			return 0;
		}

		@Override
		public int getColumnCount() {
			return headings.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			PaidOrder currentOrder = model.getPaidOrderList().get(rowIndex);
			int totalItems = calculateTotalItems(currentOrder);
			String grandTotal = calculateGrandTotal(currentOrder);

			switch (columnIndex) {
				case 0: return currentOrder.getOrder().getOrderNumber();
				case 1: return currentOrder.getOrder().getCustomer().getSurname();
				case 2: return totalItems;
				case 3: return currentOrder.getOrder().getStatus();
				case 4: return grandTotal;
				default: return "???";
			}
		}

		private String calculateGrandTotal(PaidOrder paidOrder) {
			double total = 0;
			for (OrderProduct op : paidOrder.getLineItem()) {
				total += (op.getProduct().getPrice().doubleValue() * op.getQty().intValue());
			}
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			return formatter.format(total);
		}

		private int calculateTotalItems(PaidOrder paidOrder) {
			int total = 0;
			for (OrderProduct op : paidOrder.getLineItem()) {
				total += op.getQty().intValue();
			}
			return total;
		}
	}
}
