package com.jb.CouponsProjectPart3Server.exc;

public class IDDoesntExistException extends Exception {

	public IDDoesntExistException() {
		super("The ID provided doesn't exist");
	}
}
