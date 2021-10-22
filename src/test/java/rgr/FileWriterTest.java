package rgr;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class FileWriterTest {

@Test
public void test() {
try {
FileWriter writer = new FileWriter("Тест.txt");
writer.write("Тест");
writer.close();

String actual = null;
FileReader reader = new FileReader("Тест.txt");
char [] a = new char[4];
reader.read(a);

actual = String.valueOf(a);

String expected = "Тест";

assertEquals(actual, expected);

} catch(IOException ex) {
ex.printStackTrace();
}



}

}
