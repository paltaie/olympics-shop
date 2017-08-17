package com.wsp.olympics.supplierapp.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.wsp.olympics.supplierapp.view.View;
import com.wsp.olympics.supplierapp.service.SupplierModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaidOrdersPanel extends JPanel implements View {
	private SupplierModel model;

	@Autowired
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
