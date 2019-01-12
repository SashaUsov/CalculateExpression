
import org.junit.Assert;
import org.junit.Test;

public class CalculateTest {



    @Test
    public void sumTest() {

        Double actual = 15.0 ;

        Double expectedSum = Calculate.covertToExpression("10+5").calculate();

                Assert.assertEquals(expectedSum, actual);
    }

    @Test
    public void subtructionTest() {

        Double actual = 5.0 ;

        Double expectedSum = Calculate.covertToExpression("15- 10").calculate();

        Assert.assertEquals(expectedSum, actual);
    }

    @Test
    public void subtructionSumTest() {

        Double actual = 20.0 ;

        Double expectedSum = Calculate.covertToExpression("15+ 5-10-5+15").calculate();

        Assert.assertEquals(expectedSum, actual);
    }

    @Test
    public void multiplTest() {

        Double actual = 5.0 ;

        Double expectedSum = Calculate.covertToExpression("1*5").calculate();

        Assert.assertEquals(expectedSum, actual);
    }

    @Test
    public void divisionTest() {

        Double actual = 5.0 ;

        Double expectedSum = Calculate.covertToExpression("15/3").calculate();

        Assert.assertEquals(expectedSum, actual);
    }

    @Test
    public void priorityOfOperationsTest() {

        Double actual = 3.0 ;

        Double expectedSum = Calculate.covertToExpression("1*6/2+1*5-9+2*2.0").calculate();

        Assert.assertEquals(expectedSum, actual);
    }

    @Test
    public void withOneExpressionInParenthesesTest() {

        Double actual = 3.0 ;

        Double expectedSum = Calculate.covertToExpression("1*2.0+(3-2*1)").calculate();

        Assert.assertEquals(expectedSum, actual);
    }

    @Test
    public void withManyExpressionInParenthesesTest() {

        Double actual = 8.0 ;

        Double expectedSum = Calculate.covertToExpression("1*2.0+(3-2*1)+(2*1)+(6/2)").calculate();

        Assert.assertEquals(expectedSum, actual);
    }
}
