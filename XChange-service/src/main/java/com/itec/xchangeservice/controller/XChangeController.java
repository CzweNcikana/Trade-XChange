package com.itec.xchangeservice.controller;

import com.itec.xchangeservice.dto.XChangeRateDTO;
import com.itec.xchangeservice.entity.XChangeEntity;
import com.itec.xchangeservice.service.XChangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
