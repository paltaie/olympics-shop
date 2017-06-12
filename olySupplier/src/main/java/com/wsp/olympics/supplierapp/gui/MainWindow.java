package com.wsp.olympics.supplierapp.gui;

import java.awt.BorderLayout;

import javax.swing.*;

import com.wsp.olympics.supplierapp.service.SupplierModel;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private SupplierModel model;
	private JTabbedPane tabbedPane = new JTabbedPane();
	
	public MainWindow(SupplierModel model) {
		this.model = model;
		setup();
		build();
		pack();
		setVisible(true);
	}
	
	private void setup() {
		setTitle("olySupplier");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
	}
	
	private void build() {
		tabbedPane.addTab("Paid Orders", new PaidOrdersPanel(model));
		tabbedPane.addTab("Update Order", new UpdateOrderPanel(model));
		add(tabbedPane);
	}
}