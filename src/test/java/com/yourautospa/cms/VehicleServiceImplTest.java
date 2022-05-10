package com.yourautospa.cms;


import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yourautospa.cms.dao.VehicleRepository;
import com.yourautospa.cms.entity.Vehicle;
import com.yourautospa.cms.service.VehicleService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VehicleServiceImplTest {

	@Autowired
	private VehicleRepository repository;
	
	@Test
	public void testFindAll() {
		Vehicle Vehicle1 = new Vehicle("XYZ");
		Vehicle Vehicle2 = new Vehicle("ABC");
		repository.save(Vehicle1);
		repository.save(Vehicle2);
		
		List<Vehicle> VehicleList = repository.findAll();
		Assertions.assertThat(VehicleList).isNotNull();
		
	}

	@Test
	public void testFindById() {
		Vehicle Vehicle1 = new Vehicle("XYZ");
		repository.save(Vehicle1);
		Optional<Vehicle> foundVehicle = repository.findById(Vehicle1.getPlate());
		
		Assertions.assertThat(foundVehicle.get().getPlate()).isEqualTo(Vehicle1.getPlate());
	}
	
	@Test
	public void testSave() {
		Vehicle theVehicle = new Vehicle("XYZ");
		repository.save(theVehicle);

		Assertions.assertThat(theVehicle.getPlate()).isNotNull();
	}
	
	@Test
	public void testDeleteById() {
		Vehicle Vehicle1 = new Vehicle("XYZ");
		repository.save(Vehicle1);
		repository.deleteById(Vehicle1.getPlate());
		Optional<Vehicle> foundVehicle = repository.findById(Vehicle1.getPlate());
		
		Assertions.assertThat(foundVehicle.isEmpty());
	}
	
}
