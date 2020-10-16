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
import com.jb.CouponsProjectPart3Server.utils.Env;

@CrossOrigin(origins = Env.URL, allowedHeaders = "*")
@RestController
@RequestMapping("company")
public class CompanyController extends ClientController {


	@PostMapping("login")
	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
		HttpHeaders resHeaders = new HttpHeaders();
		try {
			String token = loginManager.login(email, password, ClientType.Company);
			resHeaders.set("Token", token);
			resHeaders.add("Access-Control-Expose-Headers", "Token"); // set this to expose custom header
			return ResponseEntity.ok().headers(resHeaders).body("\"Logged in as company \"");
		} catch (LoginFailed e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("getCompanyDetails")
	public ResponseEntity<?> getCompanyDetails(@RequestHeader(name = "Token", required = false) String token) {
		return new ResponseEntity<Company>(((CompanyService) tokenManager.getMyService(token)).getCompanyDetails(),
				HttpStatus.OK);
	}

	@PutMapping("updateCompany")
	public ResponseEntity<?> updateCompany(@RequestBody Company company,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			((CompanyService) tokenManager.getMyService(token)).updateCompany(company);
			return new ResponseEntity<>("\"Company was updated\"", HttpStatus.NO_CONTENT);
		} catch (IDDoesntExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (InvalidAction e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("addCoupon")
	public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			((CompanyService) tokenManager.getMyService(token)).addCoupon(coupon);
			return new ResponseEntity<>("\"Coupon was added \"", HttpStatus.CREATED);
		} catch (ItemAlreadyExist e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("updateCoupon")
	public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			((CompanyService) tokenManager.getMyService(token)).updateCoupon(coupon);
			return new ResponseEntity<>("Coupon was updated", HttpStatus.NO_CONTENT);
		} catch (IDDoesntExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (ItemAlreadyExist e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("deleteCoupon/{id}")
	public ResponseEntity<?> deleteCoupon(@PathVariable int id,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			((CompanyService) tokenManager.getMyService(token)).deleteCoupon(id);
			return new ResponseEntity<>("\"Coupon was deleted \"", HttpStatus.NO_CONTENT);
		} catch (IDDoesntExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("getOneCoupon/{id}")
	public ResponseEntity<?> getOneCoupon(@PathVariable int id,
			@RequestHeader(name = "Token", required = false) String token) {
		return new ResponseEntity<Coupon>(((CompanyService) tokenManager.getMyService(token)).getOneCoupon(id),
				HttpStatus.OK);
	}

	@GetMapping("getAllCoupons")
	public ResponseEntity<?> getAllCoupons(@RequestHeader(name = "Token", required = false) String token) {
		return new ResponseEntity<List<Coupon>>(((CompanyService) tokenManager.getMyService(token)).getAllCoupons(),
				HttpStatus.OK);
	}

	@GetMapping("getAllCouponsByCategory/{category}")
	public ResponseEntity<?> getAllCouponsByCategory(@PathVariable Category category,
			@RequestHeader(name = "Token", required = false) String token) {
		return new ResponseEntity<List<Coupon>>(
				((CompanyService) tokenManager.getMyService(token)).getAllCouponsByCategory(category), HttpStatus.OK);
	}

	@GetMapping("getAllCouponsUnderPrice/{price}")
	public ResponseEntity<?> getAllCouponsUnderPrice(@PathVariable double price,
			@RequestHeader(name = "Token", required = false) String token) {
		return new ResponseEntity<List<Coupon>>(
				((CompanyService) tokenManager.getMyService(token)).getAllCouponsUnderPrice(price), HttpStatus.OK);
	}

}
