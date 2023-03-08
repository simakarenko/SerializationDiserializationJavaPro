import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String fileName = "/Users/svetlana/Documents/JavaPro Progects/SerializationDiserializationJavaPro/Result.txt";
        DataForInOutStream dfios = new DataForInOutStream();
        dfios.setText("Hello world");
        dfios.setNumber(5);
        System.out.println(dfios);
        System.out.println();

        StringBuilder sb = new StringBuilder();
        Class<?> cl = dfios.getClass();
        Field[] fields = cl.getDeclaredFields();
        for (Field fild : fields) {
            if (fild.isAnnotationPresent(Save.class)) {
                try {
                    sb.append(fild.toString() + " " + fild.get(dfios)).append(System.lineSeparator());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        try {
            DataForInOutStream.writeInOutFile(sb.toString(), fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DataForInOutStream dfiosTwo = DataForInOutStream.readInInFile(fileName);
        System.out.println("Serialization: " + sb.toString());
        System.out.print("Deserialization: " + dfiosTwo.toString());


    }
}