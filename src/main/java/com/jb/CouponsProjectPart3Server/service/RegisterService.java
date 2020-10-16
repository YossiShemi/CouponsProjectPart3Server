package com.jb.CouponsProjectPart3Server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jb.CouponsProjectPart3Server.beans.Company;
import com.jb.CouponsProjectPart3Server.beans.Coupon;
import com.jb.CouponsProjectPart3Server.beans.Customer;
import com.jb.CouponsProjectPart3Server.exc.IDDoesntExistException;
import com.jb.CouponsProjectPart3Server.exc.InvalidAction;
import com.jb.CouponsProjectPart3Server.exc.ItemAlreadyExist;
import com.jb.CouponsProjectPart3Server.repo.CompanyRepository;
import com.jb.CouponsProjectPart3Server.repo.CustomerRepository;

@Service
public class RegisterService   {
	
	@Autowired
	protected CompanyRepository companyRepository;
	@Autowired
	protected CustomerRepository customerRepository;   
	
	public void addCompany(Company company) throws ItemAlreadyExist {
		if (companyRepository.findByEmail(company.getEmail()) != null)
			throw new ItemAlreadyExist("The email " + company.getEmail() + " already exist");
		if (companyRepository.findByName(company.getName()) != null)
			throw new ItemAlreadyExist("The name " + company.getName() + " already exist");
		else
			companyRepository.save(company);
	};


	public void addCustomer(Customer customer) throws ItemAlreadyExist {
		if (customerRepository.findByEmail(customer.getEmail()) != null)
			throw new ItemAlreadyExist("The email " + customer.getEmail() + " already exist");
		customerRepository.save(customer);
	}

	
}
