package server.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import server.dto.VehicleModel;

@Service
public class VehicleService {

	@PersistenceContext
	@Autowired
	private EntityManager entityManager;

	public Long create(VehicleModel vehicle) {
		entityManager.getTransaction().begin();
		entityManager.persist(vehicle);
		entityManager.getTransaction().commit();
		return vehicle.getId();
	}

	public VehicleModel find(Long primary) {
		entityManager.getTransaction().begin();
		VehicleModel vehicle = entityManager.find(VehicleModel.class, primary, LockModeType.PESSIMISTIC_READ);
		if (vehicle != null) {
			entityManager.detach(vehicle);
		}
		entityManager.getTransaction().rollback();

		return vehicle;
	}
	
	public List<VehicleModel> getVehicles() {
		return entityManager
				.createNativeQuery("select * from vehicle_management.Vehicle ", VehicleModel.class).getResultList();
	}
	
	public void update(Long primary, VehicleModel vehicle) {
		entityManager.getTransaction().begin();
		VehicleModel oldVehicleInfo = entityManager.find(VehicleModel.class, primary, LockModeType.PESSIMISTIC_WRITE);
		if (vehicle != null) {
			entityManager.detach(oldVehicleInfo);
			oldVehicleInfo.updateValues(vehicle);
			entityManager.merge(oldVehicleInfo);
		}
		entityManager.getTransaction().commit();
	}

	public boolean delete(Long primary) {
		entityManager.getTransaction().begin();
		VehicleModel vehicle = entityManager.find(VehicleModel.class, primary, LockModeType.PESSIMISTIC_WRITE);
		if (vehicle != null) {
			entityManager.remove(vehicle);
		}
		entityManager.getTransaction().commit();
		return vehicle == null ? false : true;
	}

}
