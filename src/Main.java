import java.io.IOException;
import java.lang.reflect.Field;


public class Main {
    public static void main(String[] args) {

        String fileName = "/Users/svetlana/Documents/JavaPro Progects/SerializationDiserializationJavaPro/Result.txt";
        //   DataForInOutStream dfios = new DataForInOutStream();
        // dfios.setText("Hello world");
        //dfios.setNumber(5);
        //System.out.println(dfios);

        TestClass tc = new TestClass();
        tc.setTestDouble(2.6);
        tc.setTestLong(6543);
        tc.setTestText("I love Java");
        System.out.println(tc);
        System.out.println();

        StringBuilder sb = new StringBuilder();
        //  Class<?> cl = dfios.getClass();
        //   Field[] fields = cl.getDeclaredFields();
        //  for (Field fild : fields) {
        //     if (fild.isAnnotationPresent(Save.class)) {
        //         try {
        //             sb.append(fild.toString() + " " + fild.get(dfios)).append(System.lineSeparator());
        //        } catch (IllegalAccessException e) {
        //            throw new RuntimeException(e);
        //        }
        //     }
        //   }
        Class<?> clTest = tc.getClass();
        Field[] fieldsTest = clTest.getDeclaredFields();
        for (Field fildTest : fieldsTest) {
            if (fildTest.isAnnotationPresent(Save.class)) {
                try {
                    sb.append(fildTest.toString() + " " + fildTest.get(tc)).append(System.lineSeparator());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        try {
            SerialAndDeserial.writeInOutFile(sb.toString(), fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //  DataForInOutStream dfiosResult = new DataForInOutStream();
        TestClass tcResult = new TestClass();
        String t = SerialAndDeserial.readInInFile(fileName);
        try {
            //  dfiosResult = SerialAndDeserial.parsingText(t, DataForInOutStream.class);
            tcResult = SerialAndDeserial.parsingText(t, TestClass.class);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Serialization: " + sb.toString());
        // System.out.print("Deserialization: " + dfiosResult.toString());
        System.out.println("Deserialization: " + tcResult.toString());

    }
}