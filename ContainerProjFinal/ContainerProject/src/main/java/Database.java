import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Database {
	
	private ArrayList<Journey> journey = new ArrayList<Journey>();
	private ArrayList<Container> containerWarehouse = new ArrayList<Container>();
	private ArrayList<client> clients = new ArrayList<client>();
	
//can only add clients that don't already exist
	void add (client c) {
		if (!exists(c)) {
		clients.add(c);
		}
	}
	
	boolean exists (client c) {
		for (int i=0; i < clients.size(); i++) {
			if ((clients.get(i)).getId()==c.getId()) {return true;}	
		} 
		return false;
	}
	
	//search for clients
	ArrayList<client> search (String c){
		ArrayList<client> results = new ArrayList<client>();
		for (client cl: clients) {

			if ((cl.getAddress().contentEquals(c)||cl.getCompany().contentEquals(c)||cl.getEmail().contentEquals(c)||cl.getName().contentEquals(c))) {
				results.add(cl);
			}
		}
		return results;	
	}
	public ArrayList<Journey> getJourney() {
		return journey;
	}

	public void setJourney(ArrayList<Journey> journey) {
		this.journey = journey;
	}

	public ArrayList<Container> getContainerWarehouse() {
		return containerWarehouse;
	}

	public void setContainerWarehouse(ArrayList<Container> containerWarehouse) {
		this.containerWarehouse = containerWarehouse;
	}
	public ArrayList<client> getClients() {
		return clients;
	}

	public void setClients(ArrayList<client> clients) {
		this.clients = clients;
	}
	
	//create client
	public client createClient( String company, String address, String email, String name, String password) {
		client c = new client(company, address, email, name, password);
		clients.add(c);
		return c;
	}

	//find a journey, used for making chart in GUI
	public Set<Journey> findUsingLoop (String search){
	
			Set<Journey> matches = new HashSet<Journey>();
			for (Journey j : journey) {
				if ((j.getOrigin().equalsIgnoreCase(search))||
						(j.getDestination().equalsIgnoreCase(search))
						|| (j.getId().equalsIgnoreCase(search))
						|| (j.getCurrentLocation().equalsIgnoreCase(search))) {
					matches.add(j);
					}
				}
			return matches;
			
		}
//find a journey for making a new journey
	public ArrayList<Journey> findJourney (String origin, String destination, ArrayList<Journey> journey){
		
		ArrayList<Journey> results = new ArrayList<Journey>();
		for (Journey j : journey) {
			if ((j.getOrigin().equalsIgnoreCase(origin))&&
					(j.getDestination().equalsIgnoreCase(destination))&&
					(j.getCurrentLocation().equalsIgnoreCase(origin))) {
				results.add(j);
				}
			}
		return results;
		
	}
	
//assign a container, make a new one if there's no availible containers in the warehouse
//if there are availible containers in the warehouse use those instead
	public Container assignContainer(String content, String company, String id) {
		if (containerWarehouse.size() == 0) {
			Container container = new Container( content, company, id);
			return container;
		}
		else {
			Container container = containerWarehouse.get(0);
			containerWarehouse.remove(0);
			container.setCompany(company);
			container.setContent(content);
			return container;
		}
		
	}
	
	//if journey doesn't exist make a new one, otherwise update the journey to the new origin and container
	public void createJourney( String origin, String destination, String content, String company) {
		if (findJourney( origin, destination, journey).size() == 0) {
			 Journey j = new Journey(origin, destination, content, company);
			 journey.add(j);
			 Container container = assignContainer(content, company, j.getId());
			 j.getContainerList().add(container);
			 j.updateCurrentLocation(origin);
			
		}
		else {
			Container container = assignContainer(content, company, findJourney( origin, destination, journey).get(0).getId());
			findJourney( origin, destination, journey).get(0).getContainerList().add(container);
			findJourney( origin, destination, journey).get(0).updateCurrentLocation(origin);
		
		}
	}
	//search for a container
	public ArrayList<Container> findContainer(String keyword) {
		ArrayList<Container> containers = new ArrayList<Container>();
		for (Journey j : journey) {
			for (Container c : j.getContainerList()) {
				if ((c.getContainerId().equalsIgnoreCase(keyword)) 
						|| (c.getCompany().equalsIgnoreCase(keyword)) 
						|| (c.getContent().equalsIgnoreCase(keyword))
						|| (c.getCurrentLocation().equalsIgnoreCase(keyword))) {
					containers.add(c);
				}
			}
		}
		return containers;
	}
	
	public ArrayList<Container> getActiveContainers() {
		ArrayList<Container> Containers = new ArrayList<Container>();
		for (Journey j : journey) {
			for (Container c : j.getContainerList()) {
				Containers.add(c);
			}
		}
		return Containers;
	}
	//adds warehouse containers to all containers 
	
	public ArrayList<Container> getAllContainers() {
		ArrayList<Container> Containers = getActiveContainers();
		Containers.addAll(containerWarehouse);
		return Containers;
	}
	
	


}
		

	  

