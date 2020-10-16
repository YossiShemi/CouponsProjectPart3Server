package com.jb.CouponsProjectPart3Server.dailyTasks;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jb.CouponsProjectPart3Server.beans.Company;
import com.jb.CouponsProjectPart3Server.beans.Customer;
import com.jb.CouponsProjectPart3Server.exc.IDDoesntExistException;
import com.jb.CouponsProjectPart3Server.exc.InvalidAction;
import com.jb.CouponsProjectPart3Server.service.AdminService;

@Component
public class CouponsExpirationDaily {

	private static final int sleep = 24 * 60 * 60 * 1000; // 24 hours
	@Autowired
	AdminService adminService;

	
	@Scheduled(fixedRate = sleep)
	public void deleteExpiere() throws InvalidAction {
		System.out.println();
		System.err.println("Start cleaner " + new Date().toLocaleString());

		List<Customer> customers = adminService.getAllCustomers();
		List<Company> companies = adminService.getAllCompanies();

		for (Customer customer : customers) {
			customer.deleteExpieredCoupons();
			try {
				adminService.updateCustomer(customer);
			} catch (IDDoesntExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (Company company : companies) {
			company.deleteExpieredCoupons();

			try {
				adminService.updateCompany(company);
			} catch (IDDoesntExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidAction e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.err.println("Coupons leaner is going to sleep " + (sleep / 1000 / 60 / 60) + " hours");

	}

}
