public class TestClass {
    @Save
    public long testLong;
    private String testText;
    @Save
    public double testDouble;

    public TestClass() {
    }

    public long getTestLong() {
        return testLong;
    }

    public void setTestLong(long testLong) {
        this.testLong = testLong;
    }

    public String getTestText() {
        return testText;
    }

    public void setTestText(String testText) {
        this.testText = testText;
    }

    public double getTestDouble() {
        return testDouble;
    }

    public void setTestDouble(double testDouble) {
        this.testDouble = testDouble;
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "testLong=" + testLong +
                ", testText='" + testText + '\'' +
                ", testDouble=" + testDouble +
                '}';
    }
}
