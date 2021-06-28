package task_95.core;

public class Station implements Comparable<Station>{
    private String name;
    private String lineNumber;

    public Station(String name, Line line) {
        this.name = name;
        this.lineNumber = line.getNumber();
    }

    public String getName() {
        return this.name;
    }

    public String getLineNumber() {
        return this.lineNumber;
    }

    @Override
    public int compareTo(Station o) {
        if (this.getLineNumber().equals(o.getLineNumber())){
            return this.getName().compareTo(o.getName());
        }
        else
            return this.getLineNumber().compareTo(o.getLineNumber());
    }
}
