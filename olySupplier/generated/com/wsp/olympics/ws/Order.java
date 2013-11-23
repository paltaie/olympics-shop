
package com.wsp.olympics.ws;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import com.wsp.olympics.ws.types.PaidOrder;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Order", targetNamespace = "http://ws.olympics.wsp.com/")
@XmlSeeAlso({
    com.wsp.olympics.ws.ObjectFactory.class,
    com.wsp.olympics.ws.types.ObjectFactory.class
})
public interface Order {


    /**
     * 
     * @return
     *     returns java.util.List<com.wsp.olympics.ws.types.PaidOrder>
     */
    @WebMethod
    @WebResult(name = "cart", targetNamespace = "")
    @RequestWrapper(localName = "getPaidOrders", targetNamespace = "http://ws.olympics.wsp.com/", className = "com.wsp.olympics.ws.GetPaidOrders")
    @ResponseWrapper(localName = "getPaidOrdersResponse", targetNamespace = "http://ws.olympics.wsp.com/", className = "com.wsp.olympics.ws.GetPaidOrdersResponse")
    public List<PaidOrder> getPaidOrders();

    /**
     * 
     * @param status
     * @param orderId
     */
    @WebMethod
    @RequestWrapper(localName = "updateOrderStatus", targetNamespace = "http://ws.olympics.wsp.com/", className = "com.wsp.olympics.ws.UpdateOrderStatus")
    @ResponseWrapper(localName = "updateOrderStatusResponse", targetNamespace = "http://ws.olympics.wsp.com/", className = "com.wsp.olympics.ws.UpdateOrderStatusResponse")
    public void updateOrderStatus(
        @WebParam(name = "orderId", targetNamespace = "")
        String orderId,
        @WebParam(name = "status", targetNamespace = "")
        String status);

}