import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

public class SerialAndDeserial {
    public static void writeInOutFile(String text, String fileName) throws IOException {
        FileOutputStream f = null;
        byte[] b = text.getBytes();
        f = new FileOutputStream(fileName);
        try {
            f.write(b);
        } finally {
            f.close();
        }
        System.out.println("The parameters are saved to a file.");
        System.out.println();
    }

    public static String readInInFile(String fileName) {
        String t = "";
        try (FileInputStream f = new FileInputStream(fileName)) {
            byte[] b = new byte[f.available()];
            f.read(b);
            for (byte a : b) {
                t += ((char) a);
            }
        } catch (IOException e) {
            System.out.println("FILE READ ERROR");
        }
        return t;
    }

    public static <T> T parsingText(String text, Class<T> cl) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        T ob = (T) cl.newInstance();
        String[] arrayParam = text.split(System.lineSeparator());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrayParam.length; i++) {
            String[] arrayParsParam = arrayParam[i].split(" ");
            int indexPoint = arrayParsParam[2].indexOf(".");
            String paramName = arrayParsParam[2].substring(indexPoint + 1, arrayParsParam[2].length());
            Field field = cl.getDeclaredField(paramName);
            if (field.isAnnotationPresent(Save.class)) {
                if (field.getType() == int.class && arrayParsParam[1].equals("int")) {
                    field.set(ob, Integer.parseInt(arrayParsParam[3]));
                } else if (field.getType() == double.class && arrayParsParam[1].equals("double")) {
                    field.set(ob, Double.parseDouble(arrayParsParam[3]));
                } else if (field.getType() == long.class && arrayParsParam[1].equals("long")) {
                    field.set(ob, Long.parseLong(arrayParsParam[3]));
                } else if (field.getType() == String.class && arrayParsParam[1].equals("java.lang.String")) {
                    if (arrayParsParam.length > 3) {
                        for (int j = 3; j < arrayParsParam.length; j++) {
                            sb.append(arrayParsParam[j]).append(" ");
                        }
                        String t = sb.toString().substring(0,sb.toString().length()-1);
                        field.set(ob, t);
                    } else {
                        field.set(ob, arrayParsParam[3]);
                    }
                }
            }
        }
        return ob;
    }
}
