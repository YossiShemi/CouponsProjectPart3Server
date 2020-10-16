package com.jb.CouponsProjectPart3Server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jb.CouponsProjectPart3Server.beans.Category;
import com.jb.CouponsProjectPart3Server.beans.Company;
import com.jb.CouponsProjectPart3Server.beans.Coupon;
import com.jb.CouponsProjectPart3Server.exc.IDDoesntExistException;
import com.jb.CouponsProjectPart3Server.exc.ItemAlreadyExist;
import com.jb.CouponsProjectPart3Server.exc.LoginFailed;
import com.jb.CouponsProjectPart3Server.repo.CouponRepository;

@Service // Equals to facade
public class CouponsService  {
	
	@Autowired
	private CouponRepository couponRepository;

	

	public List<Coupon> getAllCoupons(){
		return couponRepository.findAll();
	}
	
	public Coupon getOneCoupon(int id) {
		return couponRepository.findById(id);
	}
	
	public List<Coupon> getAllCouponsByCategory(Category category) {
		return this.couponRepository.findByCategory(category);
	}

	

}
