package com.nationale.account.nnTask.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nationale.account.nnTask.common.domain.exception.RateNotFoundException;
import com.nationale.account.nnTask.common.domain.vo.RestClientService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class CurrencyRestClientService implements RestClientService {

    public final String BASE_CURRENCY_API_ENDPOINT = "http://api.nbp.pl/api/exchangerates/rates/a/";
    public final String SUFIX_ENDPOINT = "/?format=json";
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public BigDecimal getDataFromApi(String endpoint) {
        String url = BASE_CURRENCY_API_ENDPOINT + endpoint + SUFIX_ENDPOINT;
        JsonNode root;

        try {
            var response = restTemplate.getForObject(url, String.class);

            if (response == null || response.isBlank()) {
                throw new RateNotFoundException("Rate not found for currency: " + endpoint);
            }
            root = objectMapper.readTree(response);

        } catch (HttpClientErrorException.NotFound e) {
            throw new RateNotFoundException("Currency endpoint not found: " + endpoint);
        } catch (RestClientException e) {
            throw new RuntimeException("Error communicating with external API: " + e.getMessage(), e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing JSON response from API", e);
        }

        return root.path("rates").get(0).path("mid").decimalValue();
    }
}
