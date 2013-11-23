package com.wsp.supplierapp.gui;

import java.util.Properties;

import javax.swing.JOptionPane;
import javax.xml.ws.WebServiceException;

import com.wsp.supplierapp.service.SupplierModel;

public class Root {
	public static void main(String[] args) {
		try {
			/*FileInputStream fis = null;
			try {
				String propertyFile = System.getProperty("prop.file");
				File file = new File(propertyFile);
				fis = new FileInputStream(file);
				if (!file.exists() || file.isDirectory()) {
					throw new IllegalArgumentException("Property file is required for operation. Set the \"prop.file\" property to point to this file");
				}
				Properties props = new Properties();
				props.load(fis);
				new MainWindow(props);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}*/
			Properties props = new Properties();
			props.put("svc.url", "http://localhost:7001/olyWS/OrderService");
			props.put("ws.username", "supplier");
			props.put("ws.password", "supplierpw");
			SupplierModel model = new SupplierModel(props);
			new MainWindow(model);
		} catch (WebServiceException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Error finding web service", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
