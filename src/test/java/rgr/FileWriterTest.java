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
FileWriter writer = new FileWriter("义耱.txt");
writer.write("义耱");
writer.close();

String actual = null;
FileReader reader = new FileReader("义耱.txt");
char [] a = new char[4];
reader.read(a);

actual = String.valueOf(a);

String expected = "义耱";

assertEquals(actual, expected);

} catch(IOException ex) {
ex.printStackTrace();
}



}

}