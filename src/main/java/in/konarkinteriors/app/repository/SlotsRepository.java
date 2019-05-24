package in.konarkinteriors.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import in.konarkinteriors.app.bean.Slot;

public interface SlotsRepository extends JpaRepository<Slot, String> {

	@Query("select p from Slot p where p.date=:id")
	public Slot getById(@RequestParam("value") String id);
}
