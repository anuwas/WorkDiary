package com.org.pack.wd.exceptions;

public class NoSuchRecordExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String message;
	
	public NoSuchRecordExistsException() {}

    public NoSuchRecordExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
