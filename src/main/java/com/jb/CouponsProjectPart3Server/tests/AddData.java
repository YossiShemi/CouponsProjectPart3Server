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

		Coupon coupon = new Coupon(company.getId(), Category.Food, "3 in 1",
				"Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 10, 10)), Utils.convertUtilDateToSQL(new Date(2021, 10, 10)),
				10, 50, "https://image.freepik.com/free-photo/flat-lay-arrangement-with-burgers-pizza_23-2148308817.jpg");
		company.addCoupon(coupon);
		Coupon coupon1 = new Coupon(company.getId(), Category.Electricity, "Laptop", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 10, 10)), Utils.convertUtilDateToSQL(new Date(2021, 10, 10)),
				10, 60, "https://media.htzone.co.il/512/70/136322.jpg");
		company.addCoupon(coupon1);
		Coupon coupon2 = new Coupon(company1.getId(), Category.Food, "McDonald's", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 10, 10)), Utils.convertUtilDateToSQL(new Date(2021, 10, 10)),
				10, 125, "https://image.freepik.com/free-photo/big-hamburger-with-double-beef-french-fries_252907-8.jpg");
		company1.addCoupon(coupon2);
		Coupon coupon3 = new Coupon(company1.getId(), Category.Sport, "Running", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 10, 10)), Utils.convertUtilDateToSQL(new Date(2021, 10, 11)),
				10, 99, "https://image.freepik.com/free-photo/strong-man-training-gym_1303-23876.jpg");
		company1.addCoupon(coupon3);
		Coupon coupon4 = new Coupon(company2.getId(), Category.Food, "Schnitzelד", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 11, 11)), Utils.convertUtilDateToSQL(new Date(2021, 11, 11)),
				10, 138, "https://image.freepik.com/free-photo/top-view-chicken-wings-plate-with-sesame-seeds-beer_23-2148679082.jpg");
		company2.addCoupon(coupon4);
		Coupon coupon5 = new Coupon(company2.getId(), Category.Electricity, "Dyson V11", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 10, 10)), Utils.convertUtilDateToSQL(new Date(2021, 10, 10)),
				10, 179, "https://media.htzone.co.il/31434/94557.jpg");
		company2.addCoupon(coupon5);
		Coupon coupon6 = new Coupon(company3.getId(), Category.Vacation, "Mauritius", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 11, 11)), Utils.convertUtilDateToSQL(new Date(2021, 11, 11)),
				10, 567, "https://image.freepik.com/free-photo/young-man-relax-bed-enjoying-mountain-view_1423-236.jpg");
		company3.addCoupon(coupon6);
		Coupon coupon7 = new Coupon(company3.getId(), Category.Vacation, "Thailand", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 12, 12)), Utils.convertUtilDateToSQL(new Date(2021, 12, 12)),
				10, 33, "https://image.freepik.com/free-photo/beautiful-girl-picnic-asummer-field_1157-37959.jpg");
		company3.addCoupon(coupon7);
		
		Coupon coupon8 = new Coupon(company3.getId(), Category.Electricity, "Car Camera", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 12, 12)), Utils.convertUtilDateToSQL(new Date(2021, 12, 12)),
				10, 10, "https://media.htzone.co.il/502/38/134090.jpg");
		company3.addCoupon(coupon8);
		Coupon coupon9 = new Coupon(company3.getId(), Category.Vacation, "Picnic", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 12, 12)), Utils.convertUtilDateToSQL(new Date(2021, 12, 12)),
				10, 20, "https://image.freepik.com/free-photo/happy-black-family-having-fun-doing-picnic-outdoor_166273-247.jpg");
		company3.addCoupon(coupon9);
		Coupon coupon10 = new Coupon(company3.getId(), Category.Vacation, "Zoo", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 12, 12)), Utils.convertUtilDateToSQL(new Date(2021, 12, 12)),
				10, 777, "https://image.freepik.com/free-photo/happy-family-sitting-car_107420-82288.jpg");
		company1.addCoupon(coupon10);
		Coupon coupon11 = new Coupon(company3.getId(), Category.Sport, "Weights", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 12, 12)), Utils.convertUtilDateToSQL(new Date(2021, 12, 12)),
				10, 666, "https://image.freepik.com/free-photo/strong-man-training-gym_1303-23478.jpg");
		company1.addCoupon(coupon11);
		Coupon coupon12 = new Coupon(company3.getId(), Category.Food, "Potato Chips", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 12, 12)), Utils.convertUtilDateToSQL(new Date(2021, 12, 12)),
				10, 44, "https://image.freepik.com/free-photo/crispy-french-fries-with-ketchup-mayonnaise_1150-26588.jpg");
		company2.addCoupon(coupon12);
		Coupon coupon13 = new Coupon(company3.getId(), Category.Food, "PizzaHut", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 12, 12)), Utils.convertUtilDateToSQL(new Date(2021, 12, 12)),
				10, 79, "https://media.htzone.co.il/40582/114322.jpg");
		company2.addCoupon(coupon13);
		Coupon coupon14 = new Coupon(company3.getId(), Category.Food, "KFC", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 12, 12)), Utils.convertUtilDateToSQL(new Date(2021, 12, 12)),
				10, 87, "https://image.freepik.com/free-photo/fried-chicken-with-french-fries-nuggets-meal_1339-82478.jpg");
		company.addCoupon(coupon14);
		Coupon coupon15 = new Coupon(company3.getId(), Category.Sport, "Yoga", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 12, 12)), Utils.convertUtilDateToSQL(new Date(2021, 12, 12)),
				10, 100, "https://image.freepik.com/free-photo/workout-street_144627-45043.jpg");
		company.addCoupon(coupon15);
		Coupon coupon16 = new Coupon(company3.getId(), Category.Sport, "Bicycle", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 12, 12)), Utils.convertUtilDateToSQL(new Date(2021, 12, 12)),
				10, 169, "https://image.freepik.com/free-photo/riding-mountains_207634-12.jpg");
		company.addCoupon(coupon16);
		Coupon coupon17 = new Coupon(company3.getId(), Category.Sport, "Stretches", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorem exercitationem porro cupiditate.",
				Utils.convertUtilDateToSQL(new Date(2020, 05, 05)), Utils.convertUtilDateToSQL(new Date(2021, 05, 05)),
				10, 179, "https://image.freepik.com/free-photo/asian-runner-warm-up-his-body-before-start-running-road_30478-199.jpg");
		company.addCoupon(coupon17);
		
		
		
		
		
		
		

		admin.updateCompany(company);
		admin.updateCompany(company1);
		admin.updateCompany(company2);
		admin.updateCompany(company3);

	}

}
