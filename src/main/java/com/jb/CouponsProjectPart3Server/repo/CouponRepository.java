package com.jb.CouponsProjectPart3Server.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jb.CouponsProjectPart3Server.beans.Category;
import com.jb.CouponsProjectPart3Server.beans.Coupon;

//Equals to DAO+DBDAO- 50,40,10;
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	Coupon findById(int id);

	Coupon findByTitle(String title); // Check for EX+ Sync beans with DB

	List<Coupon> findByCompanyID(int companyID);

	List<Coupon> findByCategoryAndCompanyID(Category category, int companyID);

	List<Coupon> findByPriceLessThanAndCompanyID(double price, int companyID);
	
	List<Coupon> findByCategory(Category category);


//	@Modifying
//	@Transactional
//	@Query("DELETE FROM Coupon c WHERE c.endDate < CURRENT_DATE")
//	void deleteExpiredCoupons();

}