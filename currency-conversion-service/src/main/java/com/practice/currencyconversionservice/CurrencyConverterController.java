package com.practice.currencyconversionservice;

import com.practice.currencyconversionservice.bean.CurrencyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConverterController {

    @Autowired
    CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    @GetMapping("/currency-convert/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConverter convertCurrency(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ){
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from",from); uriVariables.put("to",to);

        ResponseEntity<CurrencyConverter> responseEntity = new RestTemplate().
                getForEntity("http://localhost:8001/currency-exchange/from/{from}/to/{to}",CurrencyConverter.class,uriVariables);

        CurrencyConverter response =  responseEntity.getBody();

        return new CurrencyConverter(response.getId(),from,to,
                response.getConversionMultiple(),quantity,
                quantity.multiply(response.getConversionMultiple()),response.getPort());
    }

    @GetMapping("/currency-convert-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConverter convertCurrencyFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ){

        CurrencyConverter response = currencyExchangeServiceProxy.retrieveExchangeValue(from, to);

        return new CurrencyConverter(response.getId(),from,to,
                response.getConversionMultiple(),quantity,
                quantity.multiply(response.getConversionMultiple()),response.getPort());

    }

}
