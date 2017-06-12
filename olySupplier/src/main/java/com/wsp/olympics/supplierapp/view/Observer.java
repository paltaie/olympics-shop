package com.wsp.olympics.supplierapp.view;

import java.util.ArrayList;
import java.util.List;

public class Observer {
	private List<View> views = new ArrayList<>();
	
	public void attach(View view) {
		views.add(view);
	}
	
	public void update() {
		views.forEach(View::update);
	}
}
