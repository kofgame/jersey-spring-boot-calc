package home.edu.jaxrs.jersey.calculator;

import jersey.repackaged.com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

public class DummyCalcServiceTest {

    DummyCalcService calcService = new DummyCalcService();

    @Test
    public void testPlus() {
        Assert.assertEquals(15,
                calcService.calculate(Operation.PLUS, Lists.newArrayList(1, 2, 3, 4, 5)));
    }
    @Test
    public void testMinus() {
        Assert.assertEquals(1,
                calcService.calculate(Operation.MINUS, Lists.newArrayList(7, 3, 2, 1)));
    }
    @Test
    public void testMultiply() {
        Assert.assertEquals(120,
                calcService.calculate(Operation.MULTIPLY, Lists.newArrayList(1, 2, 3, 4, 5)));
    }
    @Test
    public void testDivide() {
        Assert.assertEquals(1,
                calcService.calculate(Operation.DIVIDE, Lists.newArrayList(64, 8, 4, 2)));
    }
}
