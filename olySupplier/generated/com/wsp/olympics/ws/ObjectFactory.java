
package com.wsp.olympics.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.wsp.olympics.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetPaidOrders_QNAME = new QName("http://ws.olympics.wsp.com/", "getPaidOrders");
    private final static QName _UpdateOrderStatus_QNAME = new QName("http://ws.olympics.wsp.com/", "updateOrderStatus");
    private final static QName _UpdateOrderStatusResponse_QNAME = new QName("http://ws.olympics.wsp.com/", "updateOrderStatusResponse");
    private final static QName _GetPaidOrdersResponse_QNAME = new QName("http://ws.olympics.wsp.com/", "getPaidOrdersResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.wsp.olympics.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPaidOrdersResponse }
     * 
     */
    public GetPaidOrdersResponse createGetPaidOrdersResponse() {
        return new GetPaidOrdersResponse();
    }

    /**
     * Create an instance of {@link GetPaidOrders }
     * 
     */
    public GetPaidOrders createGetPaidOrders() {
        return new GetPaidOrders();
    }

    /**
     * Create an instance of {@link UpdateOrderStatusResponse }
     * 
     */
    public UpdateOrderStatusResponse createUpdateOrderStatusResponse() {
        return new UpdateOrderStatusResponse();
    }

    /**
     * Create an instance of {@link UpdateOrderStatus }
     * 
     */
    public UpdateOrderStatus createUpdateOrderStatus() {
        return new UpdateOrderStatus();
    }

    /**
     * Create an instance of {@link Product }
     * 
     */
    public Product createProduct() {
        return new Product();
    }

    /**
     * Create an instance of {@link Category }
     * 
     */
    public Category createCategory() {
        return new Category();
    }

    /**
     * Create an instance of {@link OrderProduct }
     * 
     */
    public OrderProduct createOrderProduct() {
        return new OrderProduct();
    }

    /**
     * Create an instance of {@link Order_Type }
     * 
     */
    public Order_Type createOrder_Type() {
        return new Order_Type();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPaidOrders }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.olympics.wsp.com/", name = "getPaidOrders")
    public JAXBElement<GetPaidOrders> createGetPaidOrders(GetPaidOrders value) {
        return new JAXBElement<GetPaidOrders>(_GetPaidOrders_QNAME, GetPaidOrders.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateOrderStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.olympics.wsp.com/", name = "updateOrderStatus")
    public JAXBElement<UpdateOrderStatus> createUpdateOrderStatus(UpdateOrderStatus value) {
        return new JAXBElement<UpdateOrderStatus>(_UpdateOrderStatus_QNAME, UpdateOrderStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateOrderStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.olympics.wsp.com/", name = "updateOrderStatusResponse")
    public JAXBElement<UpdateOrderStatusResponse> createUpdateOrderStatusResponse(UpdateOrderStatusResponse value) {
        return new JAXBElement<UpdateOrderStatusResponse>(_UpdateOrderStatusResponse_QNAME, UpdateOrderStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPaidOrdersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.olympics.wsp.com/", name = "getPaidOrdersResponse")
    public JAXBElement<GetPaidOrdersResponse> createGetPaidOrdersResponse(GetPaidOrdersResponse value) {
        return new JAXBElement<GetPaidOrdersResponse>(_GetPaidOrdersResponse_QNAME, GetPaidOrdersResponse.class, null, value);
    }

}
