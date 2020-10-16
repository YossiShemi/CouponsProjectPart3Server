package com.jb.CouponsProjectPart3Server.exc;

public class LoginFailed extends Exception {

	public LoginFailed() {
		super("The Email or/and Password provided doesnt exist");
	}
}
