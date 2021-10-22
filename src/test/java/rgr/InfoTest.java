package rgr;

import static org.junit.Assert.*;
import org.junit.Test;
import team28.Info;
public class InfoTest {

@Test
public void Info() {
Info i = new Info();
String sum_string = i.madeby + i.u2 + i.u3 + i.u4;
sum_string = sum_string.replaceAll(" ", "");
int actual = sum_string.length();
int expected = 84;
System.out.println("Значение: " + actual + " " + "Ожидалось: " + expected);
System.out.println(sum_string);
assertEquals(actual, expected);
}
}
