package com.egs.parser;

import java.text.ParseException;
import java.util.List;

public interface Parser<T> {

    List<T> parse (final String path) throws ParseException;

}
