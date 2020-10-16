package com.jb.CouponsProjectPart3Server.secure;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.jb.CouponsProjectPart3Server.exc.TokenDoesntExist;
import com.jb.CouponsProjectPart3Server.service.ClientService;

@Component
public class TokenManager {

	private static final long timeToClean = 30 * 60 * 100; // 30 min

	private Map<String, CustomSession> tokens = new HashMap<String, CustomSession>();

	public Map<String, CustomSession> getTokens() {
		return tokens;
	}
	
	public String addToken(ClientService clientService) {
		String token = UUID.randomUUID().toString();
		CustomSession customSession = new CustomSession(clientService, new Date());
		tokens.put(token, customSession);
		return token;
	}

	public void deleteToken(String token) {
		tokens.remove(token);

	}

	public boolean isTokenExist(String token) throws TokenDoesntExist {

		if (tokens.get(token) != null) {
			return true;
		}

		throw new TokenDoesntExist();
	}

	public ClientService getMyService(String token) {
		return tokens.get(token).getClientService();
	}

	public void deleteExpieredTokens() {
		for (Map.Entry<String, CustomSession> entry : tokens.entrySet()) {
			if (System.currentTimeMillis() - entry.getValue().getDate().getTime() > timeToClean)
				tokens.remove(entry.getKey());
		}
	}

}
