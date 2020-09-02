package com.jaden.queries;

public class SqlQueries {
	
	public static String sqlGetUser = "select * from logins where username=? and password=?";
	public static String sqlGetType = "select user_type from logins where username=? and password=?";
}
