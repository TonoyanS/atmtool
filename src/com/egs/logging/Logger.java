package com.egs.logging;

public interface Logger {

        void warn(String message);

        void error(String message, Throwable t);

        void info(String message);

        void debug(String message);
}
