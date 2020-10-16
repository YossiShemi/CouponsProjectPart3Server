package com.jb.CouponsProjectPart3Server.exc;

public class TokenDoesntExist extends Exception {

	public TokenDoesntExist() {
		super("Sorry, the token doesnt exist- Not allowed");
	}
}
