package com.egs.parser;

import java.text.ParseException;
import java.util.List;

@FunctionalInterface
public interface Parser<T> {

    List<T> parse (final String fileName) throws ParseException;

}
