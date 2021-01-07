package ec.edu.ups.test.entities.offer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.edu.ups.entities.offer.Group;

class GroupTest {
	
	private Group group;

	@BeforeEach
	void setUp() throws Exception {
		group = new Group("academicPeriodTest", "physicalSpaceTest", 1, null, null, null, null);
	}

	@Test
	void test() {
		group.setAcademicPeriod("academicPeriodTest");
		group.setPhysicalSpace("physicalSpaceTest");
		group.setQuota(1);
		assertEquals("academicPeriodTest", group.getAcademicPeriod());
		assertEquals("physicalSpaceTest", group.getPhysicalSpace());
		assertEquals(1, group.getQuota());

	}

}
