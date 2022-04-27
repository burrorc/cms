package com.yourautospa.cms.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yourautospa.cms.entity.Vehicle;

@Repository
public class VehicleDAOImpl implements VehicleDAO {

	private EntityManager entityManager;
	
	@Autowired
	public VehicleDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	//@Transactional
	public List<Vehicle> findAll() {

		Query theQuery =
				entityManager.createQuery("from Vehicle");
		
		List<Vehicle> vehicles = theQuery.getResultList();
		
		return vehicles;
	}

	@Override
	public Vehicle findById(String theId) {
		
		Vehicle theVehicle =
				entityManager.find(Vehicle.class, theId);
		
		return theVehicle;
	}

	@Override
	public void save(Vehicle theVehicle) {
		Vehicle dbVehicle = entityManager.merge(theVehicle);

		//might need this
		theVehicle.setPlate(dbVehicle.getPlate());
		
	}

	@Override
	public void deleteById(String theId) {
		
		Query theQuery =
				entityManager.createQuery("delete from Vehicle where id=:vehicleId");
		
		theQuery.setParameter("vehicleId", theId);
		
		theQuery.executeUpdate();
	}

}
