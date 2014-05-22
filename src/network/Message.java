package network;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;



public class Message {
	private int timeToLive;
	private ShortestPath pathHome;
	private Hashtable visitedNodes;
	public Message(){
		visitedNodes = new Hashtable(4099);
	}
	public int getTimeToLive(){
		return timeToLive;
	}
	public ShortestPath getPathHome(){
		return pathHome;
	}
	public Hashtable getVisitedNodes(){
		return visitedNodes;
	}
	public void setTimeToLive(int x){
		timeToLive = x;
	}
	public void setPathHome(ShortestPath sp){
		pathHome = sp;
	}
	public void setVisitedNodes(Hashtable table){
		visitedNodes = table;
	}
}
