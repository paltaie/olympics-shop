package com.wsp.supplierapp.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.wsp.supplierapp.service.SupplierModel;
import com.wsp.supplierapp.view.View;

public class PaidOrdersPanel extends JPanel implements View {
	private SupplierModel model;
	
	public PaidOrdersPanel(SupplierModel model) {
		this.model = model;
		setup();
		build();
	}

	private void setup() {
		model.attach(this);
		setLayout(new BorderLayout());
	}

	private void build() {
		add(new SearchPanel(model), BorderLayout.NORTH);
		add(new ResultPanel(model), BorderLayout.CENTER);
	}

	@Override
	public void update() {
	}
}
