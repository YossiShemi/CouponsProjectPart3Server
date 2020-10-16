package com.jb.CouponsProjectPart3Server.exc;

public class InvalidAction extends Exception {

	public InvalidAction(String s) {
		super("Invalid action-  "+s);
	}
}
