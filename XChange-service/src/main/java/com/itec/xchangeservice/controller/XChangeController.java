package com.itec.xchangeservice.controller;

import com.itec.xchangeservice.service.XChangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
@Slf4j

public class XChangeController {

    private final XChangeService xChangeService;

    @GetMapping("/exchange-rate")
    public ResponseEntity<String> getExchangeRate(@RequestParam String fromSymbol, @RequestParam String toSymbol) {
        xChangeService.processData(fromSymbol, toSymbol);
        return ResponseEntity.ok().build();
    }

}
