package com.wsp.olympics.ejb;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.wsp.olympics.model.ShoppingCart;

@Remote
public interface ShoppingCartBeanRemote {
	/**
	 * Gets a shopping cart based on the given order ID.
	 * 
	 * @param orderId the ID to search for
	 * @return the shopping cart for that ID, or null if the cart cannot be found.
	 */
	public ShoppingCart getCartByOrderId(String orderId);

	/**
	 * Retrieves a list of shopping carts by their order status
	 * 
	 * @param statuses a list of statuses to search for
	 * @return a list of shopping carts with those particular statuses (or an empty cart if none could be found)
	 */
	public List<ShoppingCart> getCartsByStatus(String[] statuses);

	/**
	 * Submits a shopping cart for insertion into the data source
	 * 
	 * @param cart the cart to insert to the DB
	 * @return an updated ShoppingCart with IDs and statuses that have been returned from the data source
	 * @throws SQLException 
	 */
	public ShoppingCart submitShoppingCart(ShoppingCart cart);
}
