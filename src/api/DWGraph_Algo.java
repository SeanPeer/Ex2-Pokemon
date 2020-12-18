package api;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.google.gson.*;

/**
 * This interface represents a Directed (positive) Weighted Graph Theory Algorithms including:
 * 0. clone(); (copy)
 * 1. init(graph);
 * 2. isConnected(); // strongly (all ordered pais connected)
 * 3. double shortestPathDist(int src, int dest);
 * 4. List<node_data> shortestPath(int src, int dest);
 * 5. Save(file); // JSON file
 * 6. Load(file); // JSON file
 *
 * @author boaz.benmoshe
 *
 */
public class DWGraph_Algo implements dw_graph_algorithms {

	directed_weighted_graph graph;

	private HashMap<Integer, Double> dist;
	private HashMap<Integer, Integer> prev;

	private HashMap<Integer, Double> dist_transpose;
	private HashMap<Integer, Integer> prev_transpose;

	/**
	 * Init the graph on which this set of algorithms operates on.
	 *
	 * @param g
	 */
	public void init(directed_weighted_graph g) {
		this.graph = g;

		this.dist = new HashMap<>();
		this.prev = new HashMap<>();

		this.dist_transpose = new HashMap<>();
		this.prev_transpose = new HashMap<>();
	}

	public DWGraph_Algo() {

		this.graph = null;
		this.dist = null;
		this.prev = null;
	}


	public DWGraph_Algo(DWGraph_DS g) {
		this.graph = g;

		this.dist = new HashMap<>();
		this.prev = new HashMap<>();
	}

	/**
	 * Return the underlying graph of which this class works.
	 *
	 * @return
	 */
	public directed_weighted_graph getGraph() {
		return graph;
	}

	/**
	 * Compute a deep copy of this weighted graph.
	 *
	 * @return
	 */
	public directed_weighted_graph copy() {

		directed_weighted_graph deep_copy = new DWGraph_DS();

		for (node_data vertex : this.graph.getV()) {

			deep_copy.addNode(vertex);

		}

		for (node_data vertex : this.graph.getV()) { //for each vertex in graph

			for (edge_data edge : this.graph.getE(vertex.getKey())) { //for each neighbor of vertex

				deep_copy.connect(edge.getSrc(), edge.getDest(), edge.getWeight());

			}
		}

		return deep_copy;
	}


	public void djikstra(directed_weighted_graph graph, int source) {
		//google "djikstra psuedo code" (wikipedia)
		//https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm

		//create priority Queue
		PriorityQueue<node_data> q = new PriorityQueue<>();

		for (node_data vertex : graph.getV()) { //for each node in graph

			if (vertex.getKey() == source) {

				this.dist_transpose.put(source, 0.0); //dist[source] -> 0
				vertex.setWeight(0.0); //for comperable in priority queue

			} else {

				this.dist_transpose.put(vertex.getKey(), Double.MAX_VALUE); //dist[node] -> inf
				this.prev_transpose.put(vertex.getKey(), null); //prev[node] -> null
				vertex.setWeight(Double.MAX_VALUE); //for comperable in priority queue
			}

			q.add(vertex);
		}

		//add source to Q

		while (!q.isEmpty()) { //Q is not empty

			node_data u = q.poll(); // Q.poll(); minheap.poll()

			for (edge_data edge : graph.getE(u.getKey())) { //for each neighbor of node


				double alt = this.dist_transpose.get(u.getKey()) + edge.getWeight();

				if (alt < this.dist_transpose.get(edge.getDest())) {

					this.dist_transpose.put(edge.getDest(), alt);
					this.prev_transpose.put(edge.getDest(), u.getKey());

					graph.getNode(edge.getDest()).setWeight(alt);

					q.remove(graph.getNode(edge.getDest())); //remove neighbor from Q
					q.add(graph.getNode(edge.getDest())); //insert into correct place (decrease priority)

					//https://stackoverflow.com/questions/1871253/updating-java-priorityqueue-when-its-elements-change-priority

				}

			}

		}

	}

	public void djikstra(int source) {
		//google "djikstra psuedo code" (wikipedia)
		//https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm

		//create priority Queue
		PriorityQueue<node_data> q = new PriorityQueue<>();

		for (node_data vertex : this.graph.getV()) { //for each node in graph

			if (vertex.getKey() == source) {

				this.dist.put(source, 0.0); //dist[source] -> 0
				vertex.setWeight(0.0); //for comperable in priority queue

			} else {

				this.dist.put(vertex.getKey(), Double.MAX_VALUE); //dist[node] -> inf
				this.prev.put(vertex.getKey(), null); //prev[node] -> null
				vertex.setWeight(Double.MAX_VALUE); //for comperable in priority queue
			}

			q.add(vertex);
		}

		//add source to Q

		while (!q.isEmpty()) { //Q is not empty

			node_data u = q.poll(); // Q.poll(); minheap.poll()

			for (edge_data edge : this.graph.getE(u.getKey())) { //for each neighbor of node


				double alt = this.dist.get(u.getKey()) + edge.getWeight();

				if (alt < this.dist.get(edge.getDest())) {

					this.dist.put(edge.getDest(), alt);
					this.prev.put(edge.getDest(), u.getKey());

					this.graph.getNode(edge.getDest()).setWeight(alt);

					q.remove(this.graph.getNode(edge.getDest())); //remove neighbor from Q
					q.add(this.graph.getNode(edge.getDest())); //insert into correct place (decrease priority)

					//https://stackoverflow.com/questions/1871253/updating-java-priorityqueue-when-its-elements-change-priority

				}

			}

		}

	}



	private directed_weighted_graph transpose_graph(directed_weighted_graph graph) {


		directed_weighted_graph transpose = new DWGraph_DS();

		for (node_data vertex : this.graph.getV()) {

			transpose.addNode(vertex);

		}

		for (node_data vertex : this.graph.getV()) { //for each vertex in graph

			for (edge_data edge : this.graph.getE(vertex.getKey())) { //for each neighbor of vertex

				transpose.connect(edge.getDest(), edge.getSrc(), edge.getWeight());

			}
		}

		return transpose;

	}


	/**
	 * Returns true if and only if (iff) there is a valid path from each node to each
	 * other node. NOTE: assume directional graph (all n*(n-1) ordered pairs).
	 *
	 * @return
	 */
	public boolean isConnected() {

		if (this.graph.nodeSize() == 1 || this.graph.nodeSize() == 0) return true;

		int k_ = 0;

		for (node_data node : this.graph.getV()) {

			djikstra(node.getKey());
			k_ = node.getKey();
			break;
		}

		if (!this.dist.containsValue(Double.MAX_VALUE)) {

			directed_weighted_graph g_t = transpose_graph(this.graph);

			djikstra(g_t, k_);

			return !this.dist_transpose.containsValue(Double.MAX_VALUE);
		}

		return false;
	}

	/**
	 * returns the length of the shortest path between src to dest
	 * Note: if no such path --> returns -1
	 *
	 * @param src  - start node
	 * @param dest - end (target) node
	 * @return
	 */
	public double shortestPathDist(int src, int dest) {
		djikstra(src);
		return this.dist.get(dest);
	}

	/**
	 * returns the the shortest path between src to dest - as an ordered List of nodes:
	 * src--> n1-->n2-->...dest
	 * see: https://en.wikipedia.org/wiki/Shortest_path_problem
	 * Note if no such path --> returns null;
	 *
	 * @param src  - start node
	 * @param dest - end (target) node
	 * @return
	 */
	public List<node_data> shortestPath(int src, int dest) {

		List<node_data> res = new ArrayList<>();

		List<node_data> back_res = new ArrayList<>();

		djikstra(src);

		int current = dest;

		res.add(this.graph.getNode(current));

		while (true) {

			if (current == src) {
				break;
			}

			res.add(this.graph.getNode(this.prev.get(current)));
			current = this.prev.get(current);

		}

		for (int i = res.size() - 1; i >= 0; i--) {
			back_res.add(res.get(i));
		}

		return back_res;

	}

	/**
	 * Saves this weighted (directed) graph to the given
	 * file name - in JSON format
	 *
	 * @param file - the file name (may include a relative path).
	 * @return true - iff the file was successfully saved
	 */
	public boolean save(String file) {
//		try {
//            FileOutputStream fos = new FileOutputStream(file);
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(this.graph);
////            oos.writeObject(bookedTickets);
////            oos.writeObject(baggage);
//            oos.close();
//           
//        } catch (IOException ex) {
//            return false;
//        }
//        return true;
//	}
		try {
			FileOutputStream fos = new FileOutputStream(file);
			Gson gson = new Gson();
			String jsonS = gson.toJson(this.graph);
			System.out.println("json: " + jsonS);

			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(jsonS);
//        oos.writeObject(bookedTickets);
//        oos.writeObject(baggage);
			oos.close();

		} catch (IOException ex) {
			return false;
		}
		return true;
	}

	/**
	 * This method load a graph to this graph algorithm.
	 * if the file was successfully loaded - the underlying graph
	 * of this class will be changed (to the loaded one), in case the
	 * graph was not loaded the original graph should remain "as is".
	 *
	 * @param file - file name of JSON file
	 * @return true - iff the graph was successfully loaded.
	 */
	public boolean load(String file) {
//		 try {
//
//		 		JsonParser jsonParser = new JsonParser();
//
//		 		FileReader reader = new FileReader(file);
//		 		Object ojb =
//
//	        } catch (IOException e) {
//	            return false;
//	        } catch (ClassNotFoundException e) {
//	            return false;
//	        }
//	        return true;
//	    }

		try {
			Gson gson = new Gson();


			String d = new String(Files.readAllBytes(Paths.get(file)));

			System.out.println(d);

			// "{" json starts with brackets
			DWGraph_DS js = gson.fromJson(d.substring(d.indexOf("{")), DWGraph_DS.class);

			System.out.println(js);


			return true;

		}
		catch (IOException e) {
			e.printStackTrace();
		}


		return false;
	}
}
