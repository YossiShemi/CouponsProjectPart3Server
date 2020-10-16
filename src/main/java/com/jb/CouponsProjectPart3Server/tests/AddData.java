package com.jb.CouponsProjectPart3Server.tests;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jb.CouponsProjectPart3Server.beans.Category;
import com.jb.CouponsProjectPart3Server.beans.Company;
import com.jb.CouponsProjectPart3Server.beans.Coupon;
import com.jb.CouponsProjectPart3Server.beans.Customer;
import com.jb.CouponsProjectPart3Server.service.AdminService;
import com.jb.CouponsProjectPart3Server.utils.Utils;

@Component
@Order(value = 1)
public class AddData implements CommandLineRunner {

	@Autowired
	private AdminService admin;

	@Override
	public void run(String... args) throws Exception {

		Customer customer = new Customer("Yossi", "Shemi", "yossi@gmail.com", "1111", null);
		Customer customer1 = new Customer("Kobi", "Shshsa", "Kobi@gmail.com", "2222", null);
		Customer customer2 = new Customer("Noam", "Marciano", "noam@gmail.com", "3333", null);
		Customer customer3 = new Customer("Eden", "Gal", "eden@gmail.com", "4444", null);
		Customer customer4 = new Customer("Noam", "bat", "noamBat@gmail.com", "5555", null);
		Customer customer5 = new Customer("Dan", "Sapir", "dan@gmail.com", "6666", null);
		Customer customer6 = new Customer("Gilad", "Badin", "gilad@gmail.com", "7777", null);
		Customer customer7 = new Customer("Dudi", "Abargil", "dudi@gmail.com", "8888", null);
		Customer customer8 = new Customer("Vlad", "Soluzki", "vlad@gmail.com", "9999", null);
		Customer customer9 = new Customer("Meital", "Cohen", "meital@gmail.com", "0000", null);
		admin.addCustomer(customer);
		admin.addCustomer(customer1);
		admin.addCustomer(customer2);
		admin.addCustomer(customer3);
		admin.addCustomer(customer4);
		admin.addCustomer(customer5);
		admin.addCustomer(customer6);
		admin.addCustomer(customer7);
		admin.addCustomer(customer8);
		admin.addCustomer(customer9);

		Company company = new Company("Coca-Cola", "cola@gmail.com", "1111", null);
		Company company1 = new Company("Pepsi", "pepso@gmail.com", "2222", null);
		Company company2 = new Company("Spring", "spring@gmail.com", "3333", null);
		Company company3 = new Company("Prigat", "prigat@gmail.com", "4444", null);
		Company company4 = new Company("Black", "black@gmail.com", "5555", null);
		Company company5 = new Company("Orange", "orange@gmail.com", "6666", null);
		Company company6 = new Company("green", "green@gmail.com", "7777", null);
		admin.addCompany(company);
		admin.addCompany(company1);
		admin.addCompany(company2);
		admin.addCompany(company3);
		admin.addCompany(company4);
		admin.addCompany(company5);
		admin.addCompany(company6);

		Coupon coupon = new Coupon(company.getId(), Category.Food, "couponOne",
				"Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 10, 10)), Utils.convertUtilDateToSQL(new Date(2020, 10, 10)),
				10, 50, "https://media.htzone.co.il/482/89/130729.jpg");
		company.addCoupon(coupon);
		Coupon coupon1 = new Coupon(company.getId(), Category.Electricity, "couponTwo", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 10, 10)), Utils.convertUtilDateToSQL(new Date(2020, 10, 10)),
				10, 60, "https://media.htzone.co.il/489/36/131810.jpg");
		company.addCoupon(coupon1);
		Coupon coupon2 = new Coupon(company1.getId(), Category.Food, "couponThree", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 10, 10)), Utils.convertUtilDateToSQL(new Date(2020, 10, 10)),
				10, 125, "https://media.htzone.co.il/504/15/134488.jpg");
		company1.addCoupon(coupon2);
		Coupon coupon3 = new Coupon(company1.getId(), Category.Sport, "couponFour", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 10, 10)), Utils.convertUtilDateToSQL(new Date(2020, 10, 11)),
				10, 99, "https://www.idosport.co.il/wp-content/uploads/2020/06/trx-%D7%AA%D7%95%D7%90%D7%9D.png");
		company1.addCoupon(coupon3);
		Coupon coupon4 = new Coupon(company2.getId(), Category.Food, "couponFive", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 11, 11)), Utils.convertUtilDateToSQL(new Date(2020, 11, 11)),
				10, 138, "https://media.htzone.co.il/503/93/134448.jpg");
		company2.addCoupon(coupon4);
		Coupon coupon5 = new Coupon(company2.getId(), Category.Electricity, "couponSix", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 10, 10)), Utils.convertUtilDateToSQL(new Date(2020, 10, 10)),
				10, 179, "https://media.htzone.co.il/31434/94557.jpg");
		company2.addCoupon(coupon5);
		Coupon coupon6 = new Coupon(company3.getId(), Category.Vacation, "couponSeven", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 11, 11)), Utils.convertUtilDateToSQL(new Date(2020, 11, 11)),
				10, 567, "https://media.htzone.co.il/37636/108017.jpg");
		company3.addCoupon(coupon6);
		Coupon coupon7 = new Coupon(company3.getId(), Category.Vacation, "couponEight", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 12, 12)), Utils.convertUtilDateToSQL(new Date(2020, 12, 12)),
				10, 33, "https://media.htzone.co.il/497/30/133224.jpg");
		company3.addCoupon(coupon7);
		
		Coupon coupon8 = new Coupon(company3.getId(), Category.Electricity, "couponNine", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 12, 12)), Utils.convertUtilDateToSQL(new Date(2020, 12, 12)),
				10, 10, "https://media.htzone.co.il/502/38/134090.jpg");
		company3.addCoupon(coupon8);
		Coupon coupon9 = new Coupon(company3.getId(), Category.Vacation, "couponTen", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 12, 12)), Utils.convertUtilDateToSQL(new Date(2020, 12, 12)),
				10, 20, "https://media.htzone.co.il/507/80/135168.jpg");
		company3.addCoupon(coupon9);
		Coupon coupon10 = new Coupon(company3.getId(), Category.Vacation, "couponEleven", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 12, 12)), Utils.convertUtilDateToSQL(new Date(2020, 12, 12)),
				10, 777, "https://media.htzone.co.il/503/50/134357.jpg");
		company1.addCoupon(coupon10);
		Coupon coupon11 = new Coupon(company3.getId(), Category.Sport, "couponTwelve", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 12, 12)), Utils.convertUtilDateToSQL(new Date(2020, 12, 12)),
				10, 666, "https://media.htzone.co.il/431/35/120628.jpg");
		company1.addCoupon(coupon11);
		Coupon coupon12 = new Coupon(company3.getId(), Category.Food, "couponThirteen", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 12, 12)), Utils.convertUtilDateToSQL(new Date(2020, 12, 12)),
				10, 44, "https://media.htzone.co.il/34674/100369.jpg");
		company2.addCoupon(coupon12);
		Coupon coupon13 = new Coupon(company3.getId(), Category.Food, "couponFourteen", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 12, 12)), Utils.convertUtilDateToSQL(new Date(2020, 12, 12)),
				10, 79, "https://media.htzone.co.il/40582/114322.jpg");
		company2.addCoupon(coupon13);
		Coupon coupon14 = new Coupon(company3.getId(), Category.Food, "couponfifteen", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 12, 12)), Utils.convertUtilDateToSQL(new Date(2020, 12, 12)),
				10, 87, "https://media.htzone.co.il/494/49/132689.jpg");
		company.addCoupon(coupon14);
		Coupon coupon15 = new Coupon(company3.getId(), Category.Sport, "couponSixteen", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 12, 12)), Utils.convertUtilDateToSQL(new Date(2020, 12, 12)),
				10, 100, "https://media.htzone.co.il/507/97/135227.jpg");
		company.addCoupon(coupon15);
		Coupon coupon16 = new Coupon(company3.getId(), Category.Sport, "couponSeventeen", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 12, 12)), Utils.convertUtilDateToSQL(new Date(2020, 12, 12)),
				10, 169, "https://media.htzone.co.il/25421/75754.jpg");
		company.addCoupon(coupon16);
		Coupon coupon17 = new Coupon(company3.getId(), Category.Sport, "couponEighteen", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 05, 05)), Utils.convertUtilDateToSQL(new Date(2020, 05, 05)),
				10, 179, "https://media.htzone.co.il/435/40/121771.png");
		company.addCoupon(coupon17);
		
		
		
		
		
		
		

		admin.updateCompany(company);
		admin.updateCompany(company1);
		admin.updateCompany(company2);
		admin.updateCompany(company3);

	}

}
