package in.konarkinteriors.app.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ki_tbl_slots")
public class Slot {

	@Id
	private String date;
	private boolean slot1;
	private boolean slot2;
	private boolean slot3;
	private boolean slot4;

	public Slot() {
		super();
	}

	public Slot(String date, boolean slot1, boolean slot2, boolean slot3, boolean slot4) {
		super();
		this.date = date;
		this.slot1 = slot1;
		this.slot2 = slot2;
		this.slot3 = slot3;
		this.slot4 = slot4;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isSlot1() {
		return slot1;
	}

	public void setSlot1(boolean slot1) {
		this.slot1 = slot1;
	}

	public boolean isSlot2() {
		return slot2;
	}

	public void setSlot2(boolean slot2) {
		this.slot2 = slot2;
	}

	public boolean isSlot3() {
		return slot3;
	}

	public void setSlot3(boolean slot3) {
		this.slot3 = slot3;
	}

	public boolean isSlot4() {
		return slot4;
	}

	public void setSlot4(boolean slot4) {
		this.slot4 = slot4;
	}

}
