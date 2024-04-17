package com.practice.currencyconversionservice;

import com.practice.currencyconversionservice.bean.CurrencyConverter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConverter retrieveExchangeValue(@PathVariable String from, @PathVariable String to);

}
