//this is the class that holds all the container's infornmation

public class Container {
	private String containerId;
	private String content;
	private String company;
	private String id; //journeyId
	private String currentLocation;
    
    private String containerID;
    private String journeyID;
    private String uniqueID = containerID + "," + journeyID;   
    
    public Container(Container cont) {
   	 this.containerId = cont.getContainerId();
   	 this.content = cont.getContent();
   	 this.company = cont.getCompany();
   	 this.id = cont.getId(); //journeyId
   	 this.currentLocation = cont.getCurrentLocation();
  
   }
	
	private static int cCounter = 0;
	
	public Container( String content, String company, String id) {
		this.id = id;
		this.content = content;
		this.company = company;
		this.containerId = "C"+ cCounter;
		
		cCounter++;
	}


	public static int getcCounter() {
		return cCounter;
	}
	public static void setcCounter(int cCounter) {
		Container.cCounter = cCounter;
	}
    
	public void setContainerID(String containerID) {
        this.containerID = containerID;
    }

	public void setJourneyID(String journeyID) {
		this.journeyID = journeyID;
	}

    public String getUniqueID() {
		return uniqueID;
	}
	public String getContainerId() {
		return containerId;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}



}
