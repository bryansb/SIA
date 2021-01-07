package ec.edu.ups.test.entities.offer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.edu.ups.entities.offer.Schedule;

class ScheduleTest {
	
	private Schedule schedule;

	@BeforeEach
	void setUp() throws Exception {
		schedule = new Schedule("Lunes", "1/1/2020", "1/6/2020", null);
	}

	@Test
	void test() {
		schedule.setDay("Lunes");
		schedule.setStartTime("1/1/2020");
		schedule.setEndTime("1/6/2020");
		assertEquals("Lunes", schedule.getDay());
		assertEquals("1/1/2020", schedule.getStartTime());
		assertEquals("1/6/2020", schedule.getEndTime());
	}

}
