package in.konarkinteriors.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.konarkinteriors.app.bean.Slot;
import in.konarkinteriors.app.repository.SlotsRepository;

@Service
public class SlotService {

	@Autowired
	private SlotsRepository slotRepository;

	public boolean checkAvailability(String date, String slotOption) {
		Slot slot = slotRepository.getById(date);
		if (slot == null) {
			slot = new Slot(date, true, true, true, true);
			slotRepository.save(slot);
			return true;
		} else {
			if (slotOption == "slot1") {
				return slot.isSlot1();
			}
			if (slotOption == "slot2") {
				return slot.isSlot2();
			}
			if (slotOption == "slot3") {
				return slot.isSlot3();
			}
			return slot.isSlot4();
		}
	}

	public boolean updateSlots(String date, String bookedSlot) {
		Slot slot = slotRepository.getById(date);
		if (bookedSlot.equalsIgnoreCase("slot1")) {
			slot.setSlot1(false);
		}
		if (bookedSlot.equalsIgnoreCase("slot2")) {
			slot.setSlot2(false);
		}
		if (bookedSlot.equalsIgnoreCase("slot3")) {
			slot.setSlot3(false);
		}
		if (bookedSlot.equalsIgnoreCase("slot4")) {
			slot.setSlot4(false);
		}
		Slot savedSlot = slotRepository.save(slot);
		if (savedSlot != null) {
			return true;
		}
		return false;
	}
}
