import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;


public class DataForInOutStream implements Serializable {

    @Save
    public String text;
    @Save
    public int constant = 2;
    private int number;

    public DataForInOutStream() {
    }

    public DataForInOutStream(String text, int number) {
        this.text = text;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static void writeInOutFile(String text, String fileName) throws IOException {
        FileOutputStream f = null;
        byte[] b = text.getBytes();
        f = new FileOutputStream(fileName);
        try {
            f.write(b);
        } finally {
            f.close();
        }
    }

    public static DataForInOutStream readInInFile(String fileName) {
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
        String[] arrayParam = t.split(System.lineSeparator());
        DataForInOutStream dfios = new DataForInOutStream();
        dfios.setText(DataForInOutStream.parsParam(arrayParam[0]));
        return dfios;
    }

    private static String parsParam(String text) {
        String[] array = text.split(" ");
        String result = "";
        for (int i = 3; i < array.length; i++) {
            result += array[i];
            if (i == array.length - 1) {
                break;
            }
            result += " ";
        }
        return result;
    }

    @Override
    public String toString() {
        return "DataForInOutStream{" +
                "text='" + text + '\'' +
                ", constant=" + constant +
                ", number=" + number +
                '}';
    }
}
