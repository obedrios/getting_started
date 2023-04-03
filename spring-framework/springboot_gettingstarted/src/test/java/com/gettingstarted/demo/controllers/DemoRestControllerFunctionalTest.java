package com.gettingstarted.demo.controllers;

import com.gettingstarted.demo.json.Greeting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoRestControllerFunctionalTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void getWithName(){
        Greeting response = template.getForObject("/rest?name=Amy", Greeting.class);
        assertEquals("Demo, Amy!", response.getMessage());
    }

    @Test
    public void getWithoutName(){
        ResponseEntity<Greeting> entity = template.getForEntity("/rest", Greeting.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, entity.getHeaders().getContentType());
        Greeting response = entity.getBody();
        if( response != null){
            assertEquals("Demo, World!", response.getMessage());
        }
    }
    //
}
