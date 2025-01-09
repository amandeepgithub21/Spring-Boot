package com.dac.producthive.exception;

public class ResourceNotFoundException  extends Exception{

	private static final long serialVersionUID = 1L;

	//Generate constructor from Super claas
	public ResourceNotFoundException(String message) {
		super(message);
		
	}

}
