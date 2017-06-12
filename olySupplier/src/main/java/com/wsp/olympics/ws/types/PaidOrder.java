
package com.wsp.olympics.ws.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.wsp.olympics.ws.OrderProduct;
import com.wsp.olympics.ws.Order_Type;


/**
 * <p>Java class for paidOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paidOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lineItem" type="{http://ws.olympics.wsp.com/}orderProduct" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="order" type="{http://ws.olympics.wsp.com/}order" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paidOrder", propOrder = {
    "lineItem",
    "order"
})
public class PaidOrder {

    @XmlElement(nillable = true)
    protected List<OrderProduct> lineItem;
    protected Order_Type order;

    /**
     * Gets the value of the lineItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lineItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLineItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderProduct }
     * 
     * 
     */
    public List<OrderProduct> getLineItem() {
        if (lineItem == null) {
            lineItem = new ArrayList<OrderProduct>();
        }
        return this.lineItem;
    }

    /**
     * Gets the value of the order property.
     * 
     * @return
     *     possible object is
     *     {@link Order_Type }
     *     
     */
    public Order_Type getOrder() {
        return order;
    }

    /**
     * Sets the value of the order property.
     * 
     * @param value
     *     allowed object is
     *     {@link Order_Type }
     *     
     */
    public void setOrder(Order_Type value) {
        this.order = value;
    }

}
