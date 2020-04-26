package shared.transferObjects;

import java.io.Serializable;

public class Report implements Serializable {
    private Object arg;
    private String type;

    public Report(Object arg, String type) {
        this.type=type;

        this.arg = arg;
    }

    public String getType() {
        return type;
    }

    public Object getArg() {
        return arg;
}}
