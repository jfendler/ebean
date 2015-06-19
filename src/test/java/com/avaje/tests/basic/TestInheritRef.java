package com.avaje.tests.basic;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.avaje.ebean.BaseTestCase;
import com.avaje.ebean.Ebean;
import com.avaje.tests.model.basic.Car;
import com.avaje.tests.model.basic.Truck;
import com.avaje.tests.model.basic.Vehicle;

public class TestInheritRef extends BaseTestCase {

	@Test
	public void testAssocOne() {
		
		Ebean.createUpdate(Vehicle.class, "delete from vehicle");

		Car c = new Car();
		c.setLicenseNumber("C6788");
		c.setDriver("CarDriver");
		Ebean.save(c);
		
		Truck t = new Truck();
		t.setLicenseNumber("T1098");
		t.setCapacity(20D);
		Ebean.save(t);

		List<Integer> ids = new ArrayList<Integer>();
		ids.add(c.getId());
		ids.add(t.getId());

		List<Vehicle> list = Ebean.find(Vehicle.class)
			.setAutofetch(false)
			.where().idIn(ids)
			.findList();
		
		Assert.assertTrue(list.size() > 0);
		for (Vehicle vehicle : list) {
			if (vehicle instanceof Truck){
				Truck truck = (Truck)vehicle;
				Assert.assertTrue(truck.getLicenseNumber().equals("T1098"));
				Assert.assertTrue(truck.getCapacity() == 20D);
			}
		}
		
	}
}
