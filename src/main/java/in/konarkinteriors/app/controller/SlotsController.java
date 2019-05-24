package in.konarkinteriors.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.konarkinteriors.app.bean.Slot;
import in.konarkinteriors.app.repository.SlotsRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SlotsController {

	@Autowired
	private SlotsRepository slotsRepository;

	@RequestMapping(value = "/getslots/{value}", method = RequestMethod.GET)
	public Slot getAvailableSlots(@PathVariable String value) {
		Slot slot = slotsRepository.getById(value);
		if (slot != null) {
			return slot;
		}
		return new Slot(value, true, true, true, true);
	}
}
