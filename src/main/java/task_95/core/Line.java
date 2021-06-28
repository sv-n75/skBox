package task_95.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Line implements Comparable<Line>{
    private String number;
    private String name;


    public Line(String number, String name) {
        this.number = number;
        this.name = name;

    }



    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Line{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Line line) {

        return this.getNumber().compareTo(line.getNumber());
    }
}
