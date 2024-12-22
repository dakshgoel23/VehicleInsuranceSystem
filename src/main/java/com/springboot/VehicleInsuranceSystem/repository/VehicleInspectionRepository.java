package com.springboot.VehicleInsuranceSystem.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.VehicleInsuranceSystem.enums.InspectionStatus;
import com.springboot.VehicleInsuranceSystem.model.VehicleInspection;

public interface VehicleInspectionRepository extends JpaRepository<VehicleInspection, Integer>{



	@Query("select vi from VehicleInspection vi join vi.vehicle v where vi.status=?1")
	List<VehicleInspection> showInspectionbooked(InspectionStatus status);

	@Query("select i from VehicleInspection i where i.vehicle.id in ?1")
	List<VehicleInspection> findAllInspections(List<Integer> list);

}
