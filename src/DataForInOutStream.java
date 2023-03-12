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

    @Override
    public String toString() {
        return "DataForInOutStream{" +
                "text='" + text + '\'' +
                ", constant=" + constant +
                ", number=" + number +
                '}';
    }
}
