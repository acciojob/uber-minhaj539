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
	/*	Customer customer=customerRepository2.findById(customerId).get();
		List<Driver> DriverList=driverRepository2.findAll();
		try {
			for (Driver driver : DriverList) {
				Cab cab = driver.getCab();
				if (cab.isAvailable()) {
					List<TripBooking> tripBookingList = driver.getTripBookingList();
					List<TripBooking> custTripBookingList = customer.getTripBookingList();
					TripBooking tripBooking = new TripBooking();
					tripBooking.setFromLocation(fromLocation);
					tripBooking.setToLocation(toLocation);
					tripBooking.setTripStatus(TripStatus.CONFIRMED);
					int bill = cab.getPerKmRate();
					bill = bill * distanceInKm;
					tripBooking.setBill(bill);
					tripBooking.setDriver(driver);
					tripBooking.setCustomer(customer);
					tripBookingList.add(tripBooking);
					custTripBookingList.add(tripBooking);
					customer.setTripBookingList(custTripBookingList);
					customerRepository2.save(customer);

					driver.setCab(cab);
					driver.setTripBookingList(tripBookingList);
					driverService.updateStatus(driver.getId());
					return tripBooking;
				}
			}
		}
		catch (Exception e) {
			throw new Exception("No cab available!");
		}
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query

		return null;*/
		Customer customer=customerRepository2.findById(customerId).get();

		TripBooking tripBook = new TripBooking();

		List<Driver> driverList=driverRepository2.findAll();
		boolean flag=false;


			for (Driver driver : driverList) {
				if (driver.getCab().getAvailable()) {
                    flag=true;
					tripBook.setFromLocation(fromLocation);
					tripBook.setToLocation(toLocation);
					tripBook.setDistanceInKm(distanceInKm);
					tripBook.setCustomer(customer);
					tripBook.setDriver(driver);
					tripBook.setStatus(TripStatus.CONFIRMED);
					int bill = driver.getCab().getPerKmRate();
					bill = bill * distanceInKm;
					tripBook.setBill(bill);

					List<TripBooking> driverTrips = driver.getTripBookingList();
					driverTrips.add(tripBook);
					driver.setTripBookingList(driverTrips);
					List<TripBooking> customerTrips = customer.getTripBookingList();
					customerTrips.add(tripBook);
					customer.setTripBookingList(customerTrips);
					driver.getCab().setAvailable(false);
					//driverService.updateStatus(driver.getDriverId());
					tripBookingRepository2.save(tripBook);
				}
			}

		if(flag==false) throw new Exception("No cab available!");


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
}
