package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import api.*;

class NodeTest {

	@Test
	void testNodeInt() {
		Node n=new Node ( 0);
		Node n2=new Node ( 0);
		System.out.println(n.toString());
		assertEquals("Node [key=0]",n.toString() );
		assertEquals(n2,n );
	}

	@Test
	void testNodeNode_data() {
		fail("Not yet implemented");
	}

	@Test
	void testGetKey() {
		fail("Not yet implemented");
	}

	@Test
	void testGetLocation() {
		fail("Not yet implemented");
	}

	@Test
	void testSetLocation() {
		fail("Not yet implemented");
	}

	@Test
	void testGetWeight() {
		fail("Not yet implemented");
	}

	@Test
	void testSetWeight() {
		fail("Not yet implemented");
	}

	@Test
	void testGetInfo() {
		fail("Not yet implemented");
	}

	@Test
	void testSetInfo() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTag() {
		fail("Not yet implemented");
	}

	@Test
	void testSetTag() {
		fail("Not yet implemented");
	}

	@Test
	void testCompareTo() {
		fail("Not yet implemented");
	}

}
