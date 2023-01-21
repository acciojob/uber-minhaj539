package com.driver.controllers;

import com.driver.model.Customer;
import com.driver.model.TripBooking;
import com.driver.services.CustomerService;
import com.driver.services.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerServiceImpl customerService;
	@PostMapping("/register")
	public ResponseEntity<Void> registerCustomer(@RequestBody Customer customer){
		customerService.register(customer);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public void deleteCustomer(@RequestParam("id") Integer customerId){

		customerService.deleteCustomer(customerId);
	}

	@PostMapping("/bookTrip")
	public ResponseEntity<Integer> bookTrip(@RequestParam("id") Integer customerId, @RequestParam("from") String fromLocation, @RequestParam("to") String toLocation, @RequestParam("distance") Integer distanceInKm) throws Exception {
		TripBooking bookedTrip;
		try {
			 bookedTrip = customerService.bookTrip(customerId, fromLocation, toLocation, distanceInKm);
		}
		catch (Exception e){
			return new ResponseEntity<>(-1, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(bookedTrip.getTripBookingId(), HttpStatus.CREATED);
	}

	@DeleteMapping("/complete")
	public void completeTrip(@RequestParam("id") Integer tripId){
		customerService.completeTrip(tripId);
	}

	@DeleteMapping("/cancelTrip")
	public void cancelTrip(@RequestParam("id") Integer tripId){
		customerService.cancelTrip(tripId);
	}
}
