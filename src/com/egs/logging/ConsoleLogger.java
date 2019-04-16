package com.egs.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsoleLogger implements Logger {

    private String dateFormat;
    private String className;

    public ConsoleLogger(String className) {
        this.className = className;
        SimpleDateFormat dateFormatPattern = new SimpleDateFormat();
        dateFormat = dateFormatPattern.format(new Date());
    }

    @Override
    public void warn(String message) {
        System.err.println("WARN | " + dateFormat + " | " + message + "\n");
    }

    @Override
    public void error(String message, Throwable t) {
        System.err.println("ERROR | " + dateFormat + " | " + message + " | " + t + "\n");

    }

    @Override
    public void info(String message) {
        System.err.println("INFO | " + dateFormat + " | " + message + "\n");
    }

    @Override
    public void debug(String message) {
        System.err.println(dateFormat + " " + className + "\nDEBUG: "  + message + "\n");
    }
}
