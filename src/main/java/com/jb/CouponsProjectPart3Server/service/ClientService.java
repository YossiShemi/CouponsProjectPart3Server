package com.jb.CouponsProjectPart3Server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jb.CouponsProjectPart3Server.exc.LoginFailed;
import com.jb.CouponsProjectPart3Server.repo.CompanyRepository;
import com.jb.CouponsProjectPart3Server.repo.CouponRepository;
import com.jb.CouponsProjectPart3Server.repo.CustomerRepository;

@Service
public abstract class ClientService {

	@Autowired
	protected CompanyRepository companyRepository;
	@Autowired
	protected CustomerRepository customerRepository;
	@Autowired
	protected CouponRepository couponRepository;

	abstract boolean login(String email, String password) throws LoginFailed;
}
