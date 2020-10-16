package com.jb.CouponsProjectPart3Server.exc;

public class ItemAlreadyExist extends Exception {

	public ItemAlreadyExist(String message) {
		super("Already exist. "+message);
	}
}
