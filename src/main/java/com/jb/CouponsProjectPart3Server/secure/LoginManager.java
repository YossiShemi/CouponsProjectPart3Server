package com.jb.CouponsProjectPart3Server.secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jb.CouponsProjectPart3Server.exc.LoginFailed;
import com.jb.CouponsProjectPart3Server.service.AdminService;
import com.jb.CouponsProjectPart3Server.service.ClientService;
import com.jb.CouponsProjectPart3Server.service.ClientType;
import com.jb.CouponsProjectPart3Server.service.CompanyService;
import com.jb.CouponsProjectPart3Server.service.CustomerService;

@Service
public class LoginManager {

	@Autowired
	AdminService admin;
	@Autowired
	CustomerService customerService;
	@Autowired
	CompanyService companyService;
	@Autowired
	TokenManager tokenManager;

	public String login(String email, String password, ClientType clientType) throws LoginFailed {

		switch (clientType) {
		case Administrator: {
			if (admin.login(email, password))
				return tokenManager.addToken(admin);
		}
		case Company: {
			if (companyService.login(email, password))
				return tokenManager.addToken(companyService);
		}
		case Customer: {
			if (customerService.login(email, password))
				return tokenManager.addToken(customerService);
		}
		default:
			throw new LoginFailed();

		}

	}
}
