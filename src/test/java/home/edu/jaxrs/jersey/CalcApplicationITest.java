package home.edu.jaxrs.jersey;


import com.jayway.restassured.RestAssured;
import home.edu.jaxrs.jersey.calculator.Operation;
import jersey.repackaged.com.google.common.collect.Lists;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(
        locations = {"classpath:application.properties", "classpath:integration_test.properties"})
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_CLASS)
public class CalcApplicationITest {

    @Value("${security.user.name}")
    private String authUserName;

    @Value("${security.user.password}")
    private String authPassword;

    @Value("${server.port}")
    private Integer wsPort;

    @PostConstruct
    public void setUp() {
        RestAssured.port = wsPort;
        // with adding spring-boot-starter-security mvn dependency Basic Auth gets enabled by default
        RestAssured.authentication = RestAssured.basic(authUserName, authPassword);
    }

    @Test
    public void testCalculate() {
        List<Integer> operands = Lists.newArrayList(1, 2, 5, 7);

        Integer response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .queryParam("operation", Operation.PLUS)
                .queryParam("operands", operands)
                .get("/calc")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Integer.class);
        Assert.assertEquals(Integer.valueOf(15), response);
    }

    @Test
    public void testCheck() {
        Integer response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/calc/get")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Integer.class);
        Assert.assertSame(1, response);
    }

}