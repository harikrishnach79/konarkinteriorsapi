package in.konarkinteriors.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.konarkinteriors.app.bean.ServiceRequest;

public interface ServiceRequestsRepository extends JpaRepository<ServiceRequest, Integer> {

}
