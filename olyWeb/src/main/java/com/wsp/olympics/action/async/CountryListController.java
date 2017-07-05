package com.wsp.olympics.action.async;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Controller
public class CountryListController {

    @RequestMapping(value = "async/countryList", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public String getCountryList() {
        return "async/countryList";
    }
}
