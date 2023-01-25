package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.CabRepository;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;
	@Autowired
	private CabRepository cabRepository;

	@Autowired
	private DriverServiceImpl driverService;

	@Override
	public void register(Customer customer) {

		customerRepository2.save(customer);
		//Save the customer in database
	}

	@Override
	public void deleteCustomer(Integer customerId) {
        Customer customer=customerRepository2.findById(customerId).get();

		customerRepository2.delete(customer);
		// Delete customer without using deleteById function

	}

	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{



		TripBooking tripBook = new TripBooking();

		List<Driver> driverList=driverRepository2.findAll();

		Driver currDriver=null;


			for (Driver driver : driverList) {
				if (driver.getCab().getAvailable() == true) {
					if (currDriver == null || currDriver.getDriverId() > driver.getDriverId()) {
						currDriver = driver;
					}
				}
			}
			if(currDriver==null) throw new Exception("No cab available!");

		    Customer customer=customerRepository2.findById(customerId).get();

					tripBook.setCustomer(customer);
					tripBook.setDriver(currDriver);
		  			currDriver.getCab().setAvailable(false);
					tripBook.setFromLocation(fromLocation);
					tripBook.setToLocation(toLocation);
					tripBook.setDistanceInKm(distanceInKm);
					tripBook.setStatus(TripStatus.CONFIRMED);


					int bill = currDriver.getCab().getPerKmRate();
					bill = bill * distanceInKm;
					tripBook.setBill(bill);

		    		customer.getTripBookingList().add(tripBook);
					customerRepository2.save(customer);

					currDriver.getTripBookingList().add(tripBook);
					driverRepository2.save(currDriver);







		return tripBook;


	}

	@Override
	public void cancelTrip(Integer tripId){

		TripBooking tripBooking=tripBookingRepository2.findById(tripId).get();
		tripBooking.setStatus(TripStatus.CANCELED);
		Driver driver=tripBooking.getDriver();
		Cab cab=driver.getCab();
		cab.setAvailable(true);
		tripBooking.setBill(0);
		tripBookingRepository2.save(tripBooking);

		//Cancel the trip having given trip Id and update TripBooking attributes accordingly

	}

	@Override
	public void completeTrip(Integer tripId){

		TripBooking tripBooking=tripBookingRepository2.findById(tripId).get();
		tripBooking.setStatus(TripStatus.COMPLETED);
		Driver driver=tripBooking.getDriver();
		Cab cab=driver.getCab();
		cab.setAvailable(true);
		tripBookingRepository2.save(tripBooking);
		//Complete the trip having given trip Id and update TripBooking attributes accordingly

	}
	public List<Customer> customerList(){
		return customerRepository2.findAll();
	}
}
