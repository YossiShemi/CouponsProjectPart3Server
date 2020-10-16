package com.jb.CouponsProjectPart3Server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jb.CouponsProjectPart3Server.beans.Company;
import com.jb.CouponsProjectPart3Server.beans.Coupon;
import com.jb.CouponsProjectPart3Server.beans.Customer;
import com.jb.CouponsProjectPart3Server.exc.IDDoesntExistException;
import com.jb.CouponsProjectPart3Server.exc.InvalidAction;
import com.jb.CouponsProjectPart3Server.exc.ItemAlreadyExist;

@Service
public class AdminService extends ClientService {

	@Override
	public boolean login(String email, String password) {
		if (email.equals("admin@admin.com") && password.equals("admin"))
			return true;
		return false;
	}

	// Companies actions

	public void addCompany(Company company) throws ItemAlreadyExist {
		if (companyRepository.findByEmail(company.getEmail()) != null)
			throw new ItemAlreadyExist("The email " + company.getEmail() + " already exist");
		if (companyRepository.findByName(company.getName()) != null)
			throw new ItemAlreadyExist("The name " + company.getName() + " already exist");
		else
			companyRepository.save(company);
	};

	public void updateCompany(Company company) throws IDDoesntExistException, InvalidAction {
		if (companyRepository.findById(company.getId()) == null)
			throw new IDDoesntExistException();
		Company company2 = companyRepository.getOne(company.getId());
		if (!company.getName().equals(company2.getName())) {
			throw new InvalidAction("Cannot update company name");
		}
		if (!company.getEmail().equals(company2.getEmail()) && 
				(companyRepository.findByEmail(company.getEmail())!=null)) {
			throw new InvalidAction("This mail already exists");
		}
		companyRepository.saveAndFlush(company);
	}

	public void deleteCompany(int id) throws IDDoesntExistException {
		if (companyRepository.findById(id) == null)
			throw new IDDoesntExistException();
		// Delete all purchases related to this company
		Company company = companyRepository.findById(id);
		List<Customer> customers = this.getAllCustomers();
		for (Customer customer : customers) {
			customer.deletCompanyCoupons(company);
		}
		companyRepository.delete(company);
	}

	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	public Company getOneCompany(int companyID) throws IDDoesntExistException {
		if (companyRepository.findById(companyID) == null)
			throw new IDDoesntExistException();
		return companyRepository.findById(companyID);
	}

	// Customers actions

	public void addCustomer(Customer customer) throws ItemAlreadyExist {
		if (customerRepository.findByEmail(customer.getEmail()) != null)
			throw new ItemAlreadyExist("The email " + customer.getEmail() + " already exist");
		customerRepository.save(customer);
	}

	public void updateCustomer(Customer customer) throws IDDoesntExistException, InvalidAction {
		if (customerRepository.findById(customer.getId()) == null)
			throw new IDDoesntExistException();
		Customer customer2 = customerRepository.getOne(customer.getId());
		if (!customer.getEmail().equals(customer2.getEmail()) && 
				(customerRepository.findByEmail(customer.getEmail())!=null)) {
			throw new InvalidAction("This mail already exists");
		}
	
		customerRepository.saveAndFlush(customer);
	}

	public void deleteCustomer(Customer customer) throws IDDoesntExistException {
		if (customerRepository.findById(customer.getId()) == null)
			throw new IDDoesntExistException();
		customerRepository.delete(customer);
	}

	public void deleteCustomer(int id) throws IDDoesntExistException {
		if (customerRepository.findById(id) == null)
			throw new IDDoesntExistException();
		// Delete all purchases related to this company
		Customer customer= customerRepository.findById(id);
		customerRepository.delete(customer);
	}

	
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer getOneCustomer(int customerID) throws IDDoesntExistException {
		if (customerRepository.findById(customerID) == null)
			throw new IDDoesntExistException();
		return customerRepository.findById(customerID);
	}

	// Coupons actions

	public List<Coupon> getAllCoupons() {
		return couponRepository.findAll();
	}


}
