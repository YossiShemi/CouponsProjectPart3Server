package com.jb.CouponsProjectPart3Server.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jb.CouponsProjectPart3Server.beans.Customer;

//Equals to DAO+DBDAO- 50,40,10;
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findById(int id);

	Customer findByEmail(String email); // Check for EX

	Customer findByEmailAndPassword(String email, String password); // Login

}