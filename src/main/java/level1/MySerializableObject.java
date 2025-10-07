package level1;

import java.io.Serializable;

public class MySerializableObject implements Serializable {

    private String myString;
    private char myChar;
    private int myInt;

    public MySerializableObject(String myString, char myChar, int myInt) {
        this.myString = myString;
        this.myChar = myChar;
        this.myInt = myInt;
    }

    public String getMyString() {
        return myString;
    }

    public char getMyChar() {
        return myChar;
    }

    public int getMyInt() {
        return myInt;
    }

    @Override
    public String toString() {
        return "My String: " + myString + ", my char: " + myChar + ", my int: " + myInt;
    }
}
