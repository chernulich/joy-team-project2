package com.coffeeshop.service.item;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.profiles.active=dev")
class ProductItemManagementServiceImplTest {

    @LocalServerPort
    int randomServerPort;

    /*All tests should be start manually, don't try to start all tests in one time */
    //todo part should be replaces with spring test infra and converted to a proper integration test


    @Test
    void findAndMarkAsSoldUseCase1() {
        /*
        Pre: Create 1 product in the database and 100 items
        Execution: Run at least 10 concurrent clients, each “buying” 50 product items. Controller must identify each
        “client” by name.
        Results: Among 10 clients, there must be 2 with successful result and other 8 with OutOfStockException.

         * */


        RestTemplate restTemplate = new RestTemplate();

        Map<HttpStatus, Integer> counterByStatus = new ConcurrentHashMap<>();

        counterByStatus.put(HttpStatus.OK, 0);
        counterByStatus.put(HttpStatus.PRECONDITION_FAILED, 0);
        counterByStatus.put(HttpStatus.INTERNAL_SERVER_ERROR, 0);

        String url = "http://localhost:" + randomServerPort +
                "/test/findAndMark/1/50";

        Stream.iterate(1, n -> n + 1)
                .limit(10)
                .parallel()
                .forEach(x -> {
                    try {
                        ResponseEntity<Object> exchange = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,
                                Object.class);

                        counterByStatus.compute(exchange.getStatusCode(), (status, i) -> i + 1);

                    } catch (RestClientResponseException e) {
                        counterByStatus.compute(HttpStatus.valueOf(e.getRawStatusCode()), (status, i) -> i + 1);
                    }
                });

        Assert.assertEquals(2, counterByStatus.get(HttpStatus.OK).intValue());
        Assert.assertEquals(8, counterByStatus.get(HttpStatus.PRECONDITION_FAILED).intValue());
    }

    @Test
    void findAndMarkAsSoldUseCase2() {
        /*
        Execution: Run at least 10 concurrent clients, each buys 20 items.
        Results: There must be 5 successful.*/

        RestTemplate restTemplate = new RestTemplate();

        Map<HttpStatus, Integer> counterByStatus = new ConcurrentHashMap<>();

        counterByStatus.put(HttpStatus.OK, 0);
        counterByStatus.put(HttpStatus.PRECONDITION_FAILED, 0);
        counterByStatus.put(HttpStatus.INTERNAL_SERVER_ERROR, 0);

        String url = "http://localhost:" + randomServerPort +
                "/test/findAndMark/1/20";

        Stream.iterate(1, n -> n + 1)
                .limit(10)
                .parallel()
                .forEach(x -> {
                    try {
                        ResponseEntity<Object> exchange = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY
                                , Object.class);
                        counterByStatus.compute(exchange.getStatusCode(), (status, i) -> i + 1);
                    } catch (RestClientResponseException e) {
                        counterByStatus.compute(HttpStatus.valueOf(e.getRawStatusCode()), (status, i) -> i + 1);
                    }
                });

        Assert.assertEquals(5, counterByStatus.get(HttpStatus.OK).intValue());
    }


    @Test
    void findAndMarkAsSoldUseCase3() {
        /*
        Pre: Create 2 products, first has 5 items, second – 100 items
        Execution: Run at least 10 clients, buying 1 and 20 each
        Results: There must be 5 successful.*/

        RestTemplate restTemplate = new RestTemplate();

        Map<HttpStatus, Integer> counterByStatus = new ConcurrentHashMap<>();
        counterByStatus.put(HttpStatus.OK, 0);
        counterByStatus.put(HttpStatus.PRECONDITION_FAILED, 0);
        counterByStatus.put(HttpStatus.INTERNAL_SERVER_ERROR, 0);

        String url = "http://localhost:" + randomServerPort +
                "/test/findAndMark/1/20/2/1";

        Stream.iterate(1, n -> n + 1)
                .limit(10)
                .parallel()
                .forEach(x -> {
                    try {
                        ResponseEntity<Object> exchange = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,
                                Object.class);

                        counterByStatus.compute(exchange.getStatusCode(), (status, i) -> i + 1);
                    } catch (RestClientResponseException e) {
                        counterByStatus.compute(HttpStatus.valueOf(e.getRawStatusCode()), (status, i) -> i + 1);
                    }
                });

        Assert.assertEquals(5, counterByStatus.get(HttpStatus.OK).intValue());
    }
}
