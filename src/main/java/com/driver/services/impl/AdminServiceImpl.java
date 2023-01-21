package com.driver.services.impl;

import java.util.List;

import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Admin;
import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.AdminRepository;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository1;

	@Autowired
	DriverRepository driverRepository1;

	@Autowired
	CustomerRepository customerRepository1;

	@Override
	public void adminRegister(Admin admin) {
		Admin admin1=new Admin();
		admin1.setUsername(admin.getUsername());
		admin1.setPassword(admin.getPassword());
		adminRepository1.save(admin1);
		//Save the admin in the database
	}

	@Override
	public Admin updatePassword(Integer adminId, String password) {

		Admin admin=adminRepository1.findById(adminId).get();
		//String oldPassword=admin.getPassword();
		admin.setPassword(password);
		adminRepository1.save(admin);
		return admin;
		//Update the password of admin with given id

	}

	@Override
	public void deleteAdmin(int adminId){
		adminRepository1.deleteById(adminId);
		// Delete admin without using deleteById function

	}

	@Override
	public List<Driver> getListOfDrivers() {
		//Find the list of all drivers

		List<Driver> listOfDrivers=driverRepository1.findAll();

       return listOfDrivers;
	}

	@Override
	public List<Customer> getListOfCustomers() {
		List<Customer> listOfCustomers=customerRepository1.findAll();
		return listOfCustomers;

		//Find the list of all customers


	}

}
