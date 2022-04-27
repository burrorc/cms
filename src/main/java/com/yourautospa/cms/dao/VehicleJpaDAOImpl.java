package com.yourautospa.cms.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yourautospa.cms.entity.Vehicle;

@Repository
public class VehicleJpaDAOImpl implements VehicleJpaDAO {

	private EntityManager entityManager;
	
	@Autowired
	public VehicleJpaDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	//@Transactional
	public List<Vehicle> findAll() {

		Session currentSession = entityManager.unwrap(Session.class);

		Query<Vehicle> theQuery =
				currentSession.createQuery("from Vehicle", Vehicle.class);
		
		List<Vehicle> vehicles = theQuery.getResultList();
		
		return vehicles;
	}

	@Override
	public Vehicle findById(String theId) {
		Session currentSession = entityManager.unwrap(Session.class);

		Vehicle theVehicle =
				currentSession.get(Vehicle.class, theId);
		
		return theVehicle;
	}

	@Override
	public void save(Vehicle theVehicle) {
		Session currentSession = entityManager.unwrap(Session.class);

		currentSession.saveOrUpdate(theVehicle);
		
	}

	@Override
	public void deleteById(String theId) {
		Session currentSession = entityManager.unwrap(Session.class);

		Query theQuery =
				currentSession.createQuery("delete from Vehicle where id=:vehicleId");
		
		theQuery.setParameter("vehicleId", theId);
		
		theQuery.executeUpdate();
	}

}
