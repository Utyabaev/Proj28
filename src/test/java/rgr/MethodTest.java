package rgr;

import org.junit.Test;
import team28.Calc;

import static org.junit.Assert.assertTrue;

public class MethodTest {

@Test
public void test() {
double actual = Calc.calc_bcards(1,1,1,1,1,1,1);
double expected = 76.5;
    assertTrue(actual == expected);

}

}
