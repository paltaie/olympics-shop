package com.wsp.olympics.action.async;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Controller
public class CountryListController {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @GetMapping(value = "async/countryList", produces = APPLICATION_JSON_VALUE)
    public @ResponseBody List<Country> getCountryList() throws IOException {
        return Arrays.asList(OBJECT_MAPPER.readValue(ResourceUtils.getURL("classpath:countryList.json"), Country[].class));
    }

    static class Country {
        private String label;
        private String value;

        public String getLabel() {
            return label;
        }

        public String getValue() {
            return value;
        }
    }
}
