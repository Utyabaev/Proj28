package rgr;

import static org.junit.Assert.*;

import org.junit.Test;
import team28.Calc;

public class MethodTest {

@Test
public void test() {
Calc c = new Calc();
double actual = c.calc_bcards(
/*Тираж*/ 0
,
/*Бумага*/ 0
,
/*Формат*/ 0
,
/*Ламинация*/ 0
,
/*Углы*/ 0
,
/*Тип печати*/ 0
,
/*Д.Карта*/ 0
);
/*Значение в калькуляторе*/
double expected = 0;
System.out.print("Значение: " + actual + " " + "Ожидалось: " + expected);
assertTrue(actual == expected);
}

}
