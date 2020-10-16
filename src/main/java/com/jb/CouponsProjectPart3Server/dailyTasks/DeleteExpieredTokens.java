package com.jb.CouponsProjectPart3Server.dailyTasks;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jb.CouponsProjectPart3Server.secure.TokenManager;

@Component
public class DeleteExpieredTokens {

	private static final int sleep = 24 * 60 * 60 * 1000; // 24 hours

	@Autowired
	TokenManager tokenManager;

	@Scheduled(fixedRate = sleep)
	public void deleteExpiere() {
		System.out.println();
		System.err.println("Start token cleaner " + new Date().toLocaleString());
		tokenManager.deleteExpieredTokens();
		System.err.println("Token cleaner is going to sleep " + (sleep / 1000 / 60 / 60) + " hours");

	}

}
