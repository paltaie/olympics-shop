package com.wsp.olympics.action.async;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Controller
public class CountryListController {

    @GetMapping(value = "async/countryList", produces = APPLICATION_JSON_VALUE)
    public String getCountryList() {
        return "async/countryList";
    }
}
