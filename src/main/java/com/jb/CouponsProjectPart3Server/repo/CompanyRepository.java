package com.jb.CouponsProjectPart3Server.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jb.CouponsProjectPart3Server.beans.Company;

//Equals to DAO+DBDAO- 50,40,10;
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	Company findById(int id);

	Company findByName(String name); // Check for EX

	Company findByEmail(String email); // Check for EX

	Company findByEmailAndPassword(String email, String password); // Login

}