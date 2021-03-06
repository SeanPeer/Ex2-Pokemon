package Tests;

import api.directed_weighted_graph;
import api.dw_graph_algorithms;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import api.DWGraph_Algo;
import api.DWGraph_DS;
import api.Node;

class DWGraph_AlgoTest {

	private DWGraph_DS connectGraph() {
		DWGraph_DS graph = new DWGraph_DS();
		graph.addNode(new Node(1));
		graph.addNode(new Node(2));
		graph.addNode(new Node(3));
		graph.connect(1, 3, 10);
		graph.connect(1, 2, 1);

		graph.connect(2, 3, 1);
		graph.connect(2, 1, 1);
		graph.connect(3, 1, 10);
		graph.connect(3, 2, 10);
		return graph;
	}

	private DWGraph_DS notConnectGraph() {
		DWGraph_DS graph = new DWGraph_DS();
		graph.addNode(new Node(1));
		graph.addNode(new Node(2));
		graph.addNode(new Node(3));
		graph.connect(1, 3, 1);
		graph.connect(1, 2, 1);


		return graph;
	}
	@Test
	void testInit() {
		fail("Not yet implemented");
	}

	@Test
	void testGetGraph() {
		fail("Not yet implemented");
	}

	@Test
	void testCopy() {
		fail("Not yet implemented");
	}

	@Test
	void testDjikstra() {
		fail("Not yet implemented");
	}

	@Test
	void testIsConnected() {
		directed_weighted_graph con_graph = new DWGraph_DS();

		con_graph.addNode(new Node(0));
		con_graph.addNode(new Node(1));
		con_graph.addNode(new Node(2));
		con_graph.addNode(new Node(3));

		con_graph.connect(0, 1, 1);
		con_graph.connect(1, 2, 2);
		con_graph.connect(2, 3, 3);

		dw_graph_algorithms algo = new DWGraph_Algo();
		algo.init(con_graph);

		assertFalse(algo.isConnected());

		algo.save("test1");

		algo.load("test1");

	}

	@Test
	void testShortestPathDist() {
		fail("Not yet implemented");
	}

	@Test
	void testShortestPath() {
		fail("Not yet implemented");
	}

	@Test
	void testSaveload() {
		
		DWGraph_DS conGraph = connectGraph();//
		DWGraph_DS NConGraph = notConnectGraph();
		DWGraph_Algo algo = new DWGraph_Algo(conGraph);
//		graph.addNode(1);
//
//		graph.addNode(3);
//		graph.addNode(4);
//
//		graph.connect(1, 3, 5);
//		graph.connect(3, 4, 5);
//		graph.connect(4, 1, 3);
//
//		graph.addNode(2);
//		graph.connect(1, 2, 70);
//		graph.connect(2, 3, 10);
//        weighted_graph_algorithms ag0 = new WGraph_Algo();
//        ag0.init(graph);
//        String str = "g0.obj";
//        ag0.save(str);
//        WGraph_DS g1 = new WGraph_DS();
//		g1.addNode(1);
//
//		g1.addNode(3);
//		g1.addNode(4);
//
//		g1.connect(1, 3, 5);
//		g1.connect(3, 4, 5);
//		g1.connect(4, 1, 3);
//
//		g1.addNode(2);
//		g1.connect(1, 2, 70);
//		g1.connect(2, 3, 10);
//        ag0.load(str);
//              assertEquals(graph, g1);
              
	}

//	private void DWGraph_AlgoTest() {
//		// TODO Auto-generated method stub
//		
//	}

}
