package com.jaden.data;

public class DatePair {

	  private final String start;
	  private final String end;

	  public DatePair(String start, String end) {
	    assert start != null;
	    assert end != null;

	    this.start = start;
	    this.end = end;
	  }

	  public String getStart() { return start; }
	  public String getEnd() { return end; }
	  
}