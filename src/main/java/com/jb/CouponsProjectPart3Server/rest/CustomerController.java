package com.jb.CouponsProjectPart3Server.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jb.CouponsProjectPart3Server.beans.Category;
import com.jb.CouponsProjectPart3Server.beans.Company;
import com.jb.CouponsProjectPart3Server.beans.Coupon;
import com.jb.CouponsProjectPart3Server.beans.Customer;
import com.jb.CouponsProjectPart3Server.exc.IDDoesntExistException;
import com.jb.CouponsProjectPart3Server.exc.InvalidAction;
import com.jb.CouponsProjectPart3Server.exc.ItemAlreadyExist;
import com.jb.CouponsProjectPart3Server.exc.LoginFailed;
import com.jb.CouponsProjectPart3Server.exc.TokenDoesntExist;
import com.jb.CouponsProjectPart3Server.service.AdminService;
import com.jb.CouponsProjectPart3Server.service.ClientType;
import com.jb.CouponsProjectPart3Server.service.CompanyService;
import com.jb.CouponsProjectPart3Server.service.CustomerService;
import com.jb.CouponsProjectPart3Server.utils.Env;

@CrossOrigin(origins = Env.URL, allowedHeaders = "*")
@RestController
@RequestMapping("customer")
public class CustomerController extends ClientController {

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
		HttpHeaders resHeaders = new HttpHeaders();
		try {
			String token = loginManager.login(email, password, ClientType.Customer);
			resHeaders.set("Token", token);
			resHeaders.add("Access-Control-Expose-Headers", "Token"); // set this to expose custom header
			return ResponseEntity.ok().headers(resHeaders).body("\"Logged in as customer \"");
		} catch (LoginFailed e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("getCustomerDetails")
	public ResponseEntity<?> getCustomerDetails(@RequestHeader(name = "Token", required = false) String token) {
		return new ResponseEntity<Customer>(((CustomerService) tokenManager.getMyService(token)).getCustomerDetails(),
				HttpStatus.OK);
	}

	@PutMapping("updateCustomer")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			((CustomerService) tokenManager.getMyService(token)).updateCustomer(customer);
			return new ResponseEntity<>("\"Customer was updated\"", HttpStatus.NO_CONTENT);
		} catch (IDDoesntExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (InvalidAction e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("purchaseCoupon")
	public ResponseEntity<?> purchaseCoupon(@RequestBody Coupon coupon,
			@RequestHeader(name = "Token", required = false) String token)
			throws IDDoesntExistException, InvalidAction {
		try {
			((CustomerService) tokenManager.getMyService(token)).purchaseCoupon(coupon);
			return new ResponseEntity<>("\"Coupon was purchased successfuly \"", HttpStatus.NO_CONTENT);
		} catch (IDDoesntExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (InvalidAction e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("deleteCouponPurchase/{id}")
	public ResponseEntity<?> deleteCoupon(@PathVariable int id,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			((CustomerService) tokenManager.getMyService(token)).deleteCouponPurchase(id);
			return new ResponseEntity<>("\"Coupon purchase was deleted \"", HttpStatus.NO_CONTENT);
		} catch (IDDoesntExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("getAllCoupons")
	public ResponseEntity<?> getAllCoupons(@RequestHeader(name = "Token", required = false) String token) {
		return new ResponseEntity<List<Coupon>>(((CustomerService) tokenManager.getMyService(token)).getAllCoupons(),
				HttpStatus.OK);
	}

	@GetMapping("getAllCouponsByCategory/{category}")
	public ResponseEntity<?> getAllCouponsByCategory(@PathVariable Category category,
			@RequestHeader(name = "Token", required = false) String token) {
		return new ResponseEntity<List<Coupon>>(
				((CustomerService) tokenManager.getMyService(token)).getAllCouponsByCategory(category), HttpStatus.OK);

	}

	@GetMapping("getAllCouponsUnderPrice/{price}")
	public ResponseEntity<?> getAllCouponsUnderPrice(@PathVariable double price,
			@RequestHeader(name = "Token", required = false) String token) {
		return new ResponseEntity<List<Coupon>>(
				((CompanyService) tokenManager.getMyService(token)).getAllCouponsUnderPrice(price), HttpStatus.OK);

	}

}
