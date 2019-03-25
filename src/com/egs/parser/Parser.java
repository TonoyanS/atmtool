package com.egs.parser;

import java.text.ParseException;
import java.util.List;

// Good
// Would be nice to have @FunctionalInterface annotation
// What Functional interface is?
public interface Parser<T> {

    List<T> parse (final String path) throws ParseException;

}
