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
import com.jb.CouponsProjectPart3Server.utils.Env;

@CrossOrigin(origins = Env.URL, allowedHeaders = "*")
@RestController
@RequestMapping("admin")
public class AdminController extends ClientController {

	@Autowired
	private AdminService admin;

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
		HttpHeaders resHeaders = new HttpHeaders();
		try {
			String token = loginManager.login(email, password, ClientType.Administrator);
			resHeaders.add("Token", token);
			resHeaders.add("Access-Control-Expose-Headers", "Token"); // set this to expose custom header
			return ResponseEntity.ok().headers(resHeaders).body("\"Logged in as admin\"");
		} catch (LoginFailed e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// Companies:

	@PostMapping("addCompany")
	public ResponseEntity<?> addCompany(@RequestBody Company company,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			((AdminService) tokenManager.getMyService(token)).addCompany(company);
			return new ResponseEntity<>("\"Company was added \"", HttpStatus.CREATED);
		} catch (ItemAlreadyExist e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("updateCompany")
	public ResponseEntity<?> updateCompany(@RequestBody Company company,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			((AdminService) tokenManager.getMyService(token)).updateCompany(company);
			return new ResponseEntity<>("\"Company was updated\"", HttpStatus.NO_CONTENT);
		} catch (IDDoesntExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (InvalidAction e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("deleteCompany/{id}")
	public ResponseEntity<?> deleteCompany(@PathVariable int id,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			((AdminService) tokenManager.getMyService(token)).deleteCompany(id);
			return new ResponseEntity<>("\"Company was deleted \"", HttpStatus.NO_CONTENT);
		} catch (IDDoesntExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("getAllCompanies")
	public ResponseEntity<?> getAllCompanies(@RequestHeader(name = "Token", required = false) String token) {
		return new ResponseEntity<List<Company>>(((AdminService) tokenManager.getMyService(token)).getAllCompanies(),
				HttpStatus.OK);
	}

	@GetMapping("getOneCompany/{id}")
	public ResponseEntity<?> getOneCompany(@PathVariable int id,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			tokenManager.isTokenExist(token);
			return new ResponseEntity<Company>(((AdminService) tokenManager.getMyService(token)).getOneCompany(id),
					HttpStatus.OK);
		} catch (TokenDoesntExist e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (IDDoesntExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// Customers:

	@PostMapping("addCustomer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			((AdminService) tokenManager.getMyService(token)).addCustomer(customer);
			return new ResponseEntity<>("\"Customer was added \"", HttpStatus.CREATED);
		} catch (ItemAlreadyExist e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("updateCustomer")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			((AdminService) tokenManager.getMyService(token)).updateCustomer(customer);
			return new ResponseEntity<>("\"Customer was updated\"", HttpStatus.NO_CONTENT);
		} catch (IDDoesntExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (InvalidAction e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("deleteCustomer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int id,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			((AdminService) tokenManager.getMyService(token)).deleteCustomer(id);
			return new ResponseEntity<>("\"Company was deleted \"", HttpStatus.NO_CONTENT);
		} catch (IDDoesntExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("getAllCustomers")
	public ResponseEntity<?> getAllCustomers(@RequestHeader(name = "Token", required = false) String token) {
		return new ResponseEntity<List<Customer>>(((AdminService) tokenManager.getMyService(token)).getAllCustomers(),
				HttpStatus.OK);
	}

	@GetMapping("getOneCustomer/{id}")
	public ResponseEntity<?> getOneCustomer(@PathVariable int id,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			return new ResponseEntity<Customer>(((AdminService) tokenManager.getMyService(token)).getOneCustomer(id),
					HttpStatus.OK);
		} catch (IDDoesntExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	// Coupons:

	@GetMapping("getAllCoupons")
	public ResponseEntity<?> getAllCoupons(@RequestHeader(name = "Token", required = false) String token) {
		return new ResponseEntity<List<Coupon>>(((AdminService) tokenManager.getMyService(token)).getAllCoupons(),
				HttpStatus.OK);
	}

}
