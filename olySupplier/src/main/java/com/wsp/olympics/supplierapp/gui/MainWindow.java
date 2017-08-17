package com.wsp.olympics.supplierapp.gui;

import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;

@org.springframework.stereotype.Component
public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane = new JTabbedPane();

	private PaidOrdersPanel paidOrdersPanel;
	private UpdateOrderPanel updateOrderPanel;

    @Autowired
    public MainWindow(PaidOrdersPanel paidOrdersPanel, UpdateOrderPanel updateOrderPanel) throws HeadlessException {
        this.paidOrdersPanel = paidOrdersPanel;
        this.updateOrderPanel = updateOrderPanel;
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
		tabbedPane.addTab("Paid Orders", paidOrdersPanel);
		tabbedPane.addTab("Update Order", updateOrderPanel);
		add(tabbedPane);
	}
}
