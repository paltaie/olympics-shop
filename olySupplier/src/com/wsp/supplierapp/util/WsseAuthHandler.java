package com.wsp.supplierapp.util;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class WsseAuthHandler implements SOAPHandler<SOAPMessageContext> {

	private static final String WSS_PREFIX = "wsse";
	private static final String WSS_NAMESPACE = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
	private String username;
	private String password;
	
	public WsseAuthHandler(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	@Override
	public boolean handleMessage(SOAPMessageContext smc) {
		boolean outbound = ((Boolean) smc.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY)).booleanValue();
		if (outbound) {
			try {
				SOAPEnvelope envelope = smc.getMessage().getSOAPPart()
						.getEnvelope();
				SOAPFactory soapFactory = SOAPFactory.newInstance();
				
				// WSSecurity <Security> header
				SOAPElement wsSecHeaderElm = soapFactory.createElement(
						"Security",
						WSS_PREFIX,
						WSS_NAMESPACE);
				SOAPElement userNameTokenElm = soapFactory.createElement("UsernameToken",
						WSS_PREFIX,
						WSS_NAMESPACE);
				SOAPElement userNameElm = soapFactory.createElement("Username",
						WSS_PREFIX,
						WSS_NAMESPACE);
				userNameElm.addTextNode(username);
				SOAPElement passwdElm = soapFactory.createElement("Password",
						WSS_PREFIX,
						WSS_NAMESPACE);
				passwdElm.addTextNode(password);
	
				userNameTokenElm.addChildElement(userNameElm);
				userNameTokenElm.addChildElement(passwdElm);
				
				// add child elements to the root element
				wsSecHeaderElm.addChildElement(userNameTokenElm);
	
				// create SOAPHeader instance for SOAP envelope
				SOAPHeader sh = envelope.addHeader();
	
				// add SOAP element for header to SOAP header object
				sh.addChildElement(wsSecHeaderElm);
			} catch (SOAPException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext smc) {
		return true;
	}

	@Override
	public void close(MessageContext smc) {
	}

	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

}
