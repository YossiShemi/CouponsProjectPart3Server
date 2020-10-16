package com.jb.CouponsProjectPart3Server.service;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jb.CouponsProjectPart3Server.beans.Category;
import com.jb.CouponsProjectPart3Server.beans.Company;
import com.jb.CouponsProjectPart3Server.beans.Coupon;
import com.jb.CouponsProjectPart3Server.beans.Customer;
import com.jb.CouponsProjectPart3Server.exc.IDDoesntExistException;
import com.jb.CouponsProjectPart3Server.exc.InvalidAction;
import com.jb.CouponsProjectPart3Server.exc.ItemAlreadyExist;
import com.jb.CouponsProjectPart3Server.exc.LoginFailed;

@Service // Equals to facade
@Scope("prototype")
public class CompanyService extends ClientService {

	private int companyID; 

	@Override
	public boolean login(String email, String password) throws LoginFailed {
		if (companyRepository.findByEmailAndPassword(email, password) == null)
			throw new LoginFailed();
		this.companyID = companyRepository.findByEmailAndPassword(email, password).getId();
		return true;
	}

	public Company getCompanyDetails() {
		return companyRepository.findById(companyID);
	}
	
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
	

	public void addCoupon(Coupon coupon) throws ItemAlreadyExist {
		if (couponRepository.findByTitle(coupon.getTitle()) != null) {
			throw new ItemAlreadyExist("Coupon with title " + coupon.getTitle() + " already exist");
		}
		coupon.setCompanyID(companyID);
		Company company = companyRepository.findById(this.companyID);
		company.addCoupon(coupon);
		companyRepository.saveAndFlush(company);
	}

	public void updateCoupon(Coupon coupon) throws IDDoesntExistException, ItemAlreadyExist {
		if (couponRepository.findById(coupon.getId()) == null)
			throw new IDDoesntExistException();
		Coupon coupon2= couponRepository.findById(coupon.getId());
		if (!coupon.getTitle().equals(coupon2.getTitle()) &&
				(couponRepository.findByTitle(coupon.getTitle()))!=null) {
			throw new ItemAlreadyExist("Coupon with title " + coupon.getTitle() + " already exist");
		}
		couponRepository.saveAndFlush(coupon);
	}

	public void deleteCoupon(int id ) throws IDDoesntExistException {
		if (couponRepository.findById(id) == null)
			throw new IDDoesntExistException();
		Company company = companyRepository.findById(this.companyID);
		List<Customer> customers = customerRepository.findAll();
		for (Customer customer : customers) {
			customer.deleteCoupon(couponRepository.findById(id));
			customerRepository.saveAndFlush(customer);
		}
		company.deleteCoupon(couponRepository.findById(id));
		companyRepository.saveAndFlush(company);
	}

	public Coupon getOneCoupon(int id) {

		return couponRepository.findById(id);
	}
	
	public List<Coupon> getAllCoupons() {

		return couponRepository.findByCompanyID(companyID);
	}

	public List<Coupon> getAllCouponsByCategory(Category category) {
		return couponRepository.findByCategoryAndCompanyID(category, companyID);
	}

	public List<Coupon> getAllCouponsUnderPrice(double price) {
		return couponRepository.findByPriceLessThanAndCompanyID(price, companyID);

	}
	
	

}
