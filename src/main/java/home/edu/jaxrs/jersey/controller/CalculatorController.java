package home.edu.jaxrs.jersey.controller;

import home.edu.jaxrs.jersey.calculator.DummyCalcService;
import home.edu.jaxrs.jersey.calculator.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import java.util.List;


@Component
@RestController
public class CalculatorController {

    @Autowired
    private DummyCalcService dummyCalculator;

    public CalculatorController(DummyCalcService dummyCalculator) {
        this.dummyCalculator = dummyCalculator;
    }

    @Produces("application/json")
    @RequestMapping(value = "/calc", method = RequestMethod.GET)
    public Integer calculate(@RequestParam ("operation") String operation, @RequestParam ("operands") final List<Integer> operands) {
        Operation operator = Operation.fromValue(operation);
        return dummyCalculator.calculate(operator, operands);
    }

    @GET
    @Produces("application/json")
    @GetMapping(value= "/calc/get")
    public Integer check() {
        return 1;
    }

}