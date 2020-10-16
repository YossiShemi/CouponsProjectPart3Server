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
import com.jb.CouponsProjectPart3Server.service.CompanyService;
import com.jb.CouponsProjectPart3Server.service.CustomerService;
import com.jb.CouponsProjectPart3Server.service.RegisterService;
import com.jb.CouponsProjectPart3Server.utils.Env;

@CrossOrigin(origins = Env.URL, allowedHeaders = "*")
@RestController
@RequestMapping("register")
public class RegisterController {

	@Autowired
	private RegisterService register;

	@PostMapping("company")
	public ResponseEntity<?> addCompany(@RequestBody Company company) {
		try {
			register.addCompany(company);
			return new ResponseEntity<>("\"Company was added\"", HttpStatus.CREATED);
		} catch (ItemAlreadyExist e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("customer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		try {
			register.addCustomer(customer);
			return new ResponseEntity<>("\"Customer was added\"", HttpStatus.CREATED);
		} catch (ItemAlreadyExist e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
