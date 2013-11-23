function validateAddToCart() {
	var invalid = false;
	var errorList = new Array();
	var qty = $("#qty").val();
	if (qty != null && qty.length != 0) {
		if (!isNumber(qty)) {
			invalid = true;
			errorList.push("Quantity is not a number");
		} else if (qty < 0) {
			invalid = true;
			errorList.push("Can't add less than one item to cart (if you wish to update your cart, please go to the Cart page)");
		}
	} else {
		invalid = true;
		errorList.push("No quantity was entered for this item");
	}
	return evaluateValidation(invalid, errorList);
}

function validateUpdateCart() {
	var invalid = false;
	var errorList = new Array();
	var qtys = $("input[id^='qty']");
	for ( var i = 0; i < qtys.length; i++) {
		var thisQty = $(qtys[i]).val();
		if (thisQty != null && thisQty.length != 0) {
			if (!isNumber(thisQty)) {
				invalid = true;
				errorList.push("\"" + $(thisQty).val() + "\" is not a valid number. Quantity must be equal to or greater than zero");
			} else if (thisQty < 0) {
				invalid = true;
				errorList.push("Quantities must be equal to or greater than zero");
			}
		} else {
			invalid = true;
			errorList.push("You didn't enter one item's quantity. Please review the shopping cart");
		}
	}
	return evaluateValidation(invalid, errorList);
}

function validateTrack() {
	var invalid = false;
	var errorList = new Array();
	var orderId = $("#order_id").val();
	if (orderId == null || orderId.length == 0) {
		invalid = true;
		errorList.push("No order ID was provided");
	}
	return evaluateValidation(invalid, errorList);
}


/**
 * Validates the user's input in the checkout section
 * @returns true if checkout form is OK, false otherwise
 */
function validateCheckout() {
	var invalid = false;
	var errorList = new Array();
	var requiredFields = new Array("givenName", "surname", "email", "houseNo", "street", "suburb", "state", "postcode", "country", "ccNumber");
	var descriptions = new Array("First name", "Surname", "Email", "House number", "Street", "Suburb", "State", "Postcode", "Country", "Credit card number");
	for (var i = 0; i < requiredFields.length; i++) {
		var fieldValue = $("#" + requiredFields[i]).val();
		if (fieldValue == null || fieldValue == "") {
			invalid = true;
			errorList.push(descriptions[i] + " is required");
		}
	}
	return evaluateValidation(invalid, errorList);
}

/**
 * Returns true if the number provided is actually a number (and not a garbage string for example)
 * 
 * @param o The value to test
 * @returns {Boolean} true if this is a number, false otherwise
 */
function isNumber (o) {
  return ! isNaN (o-0) && o != null;
}

/**
 * Generic function for evaluating whether there have been any problems
 * validating form input. Shows an alert to the user if there are problems.
 * For usage scenarios, see validateXxx() methods in this file. 
 * 
 * @param invalid whether the form input is invalid
 * @param errorList the list of errors to display (if any)
 * @returns {Boolean} true if form input is valid, false otherwise
 */
function evaluateValidation(invalid, errorList) {
	if (invalid) {
		var alertText = "The following errors occurred:\n";
		for (var i = 0; i < errorList.length; i++) {
			alertText += "- " + errorList[i] + "\n";
		}
		alert(alertText);
		return false;
	}
	return true;
}