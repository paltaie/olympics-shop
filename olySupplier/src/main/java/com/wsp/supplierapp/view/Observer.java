package com.wsp.supplierapp.view;

import java.util.ArrayList;
import java.util.List;

public class Observer {
	private List<View> views = new ArrayList<View>();
	
	public void attach(View view) {
		views.add(view);
	}
	
	public void update() {
		for (View view : views) {
			view.update();
		}
	}
}
