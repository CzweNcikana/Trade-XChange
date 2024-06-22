package com.itec.xchangeservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itec.xchangeservice.dto.XChangeRateDTO;
import com.itec.xchangeservice.entity.XChangeEntity;
import com.itec.xchangeservice.repository.XChangeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j

public class XChangeService {

    private final String API_URL = "https://real-time-finance-data.p.rapidapi.com/currency-exchange-rate";
    private final String API_KEY = "c5b0bb4f97mshcf6026f9e07ae43p1b2f35jsn47a327f55e59";
    private final String API_HOST = "real-time-finance-data.p.rapidapi.com";

    private final XChangeRepository xChangeRepository;

    public XChangeEntity processData(String fromSymbol, String toSymbol) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-host", API_HOST);
        headers.set("x-rapidapi-key", API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                API_URL + "?from_symbol=" + fromSymbol +
                        "&language=en&to_symbol=" + toSymbol,
                HttpMethod.GET,
                entity,
                String.class);

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode status = root.get("status");
            JsonNode dataNode = root.get("data");

            String baseCurrency = dataNode.get("from_symbol").asText();
            String targetCurrency = dataNode.get("to_symbol").asText();
            double xchangeRate = dataNode.get("exchange_rate").asDouble();
            double previousRate = dataNode.get("previous_close").asDouble();
            String statusCode = status.asText();

            String lastUpdate = dataNode.get("last_update_utc").asText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            LocalDateTime localDateTime = LocalDateTime.parse(lastUpdate, formatter);
            log.info("Last Updated: {}", localDateTime);
            //LocalDateTime localDateTime = offsetDateTime.toLocalDateTime();


            //LocalDateTime lastUpdate = dataNode.get("last_update_utc").asText();

            XChangeEntity xChangeEntity = XChangeEntity.builder()
                    .baseCurrency(baseCurrency)
                    .targetCurrency(targetCurrency)
                    .exchangeRate(xchangeRate)
                    .previousExchangeRate(previousRate)
                    .status(statusCode)
                    .lastUpdated(localDateTime)
                    .build();

            log.info(xChangeEntity.getBaseCurrency());
            xChangeRepository.save(xChangeEntity);
            log.info("Exchange rate stored with status: {}", statusCode);

            return xChangeEntity;

        } catch (JsonProcessingException e) {
            log.error("Error Parsing JSON response: {}", e.getMessage());
            return null;
        }


    }
}
