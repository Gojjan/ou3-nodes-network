package network;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;



public class Message {
	private int timeToLive;
	private ShortestPath pathHome;
	private Hashtable visitedNodes = new Hashtable(4099);;
	public Message(){
	}
	public int getTimeToLive(){
		return timeToLive;
	}
	public ShortestPath getPathHome(){
		return pathHome;
	}
	public void setTimeToLive(int x){
		timeToLive = x;
	}
	public void setPathHome(ShortestPath sp){
		pathHome = sp;
	}
	public boolean visitedNodeI(Position pos){
		if(visitedNodes.containsKey(pos)){
			return true;
		}
		return false;
	}
	public void visitNodeI(Position pos){
		visitedNodes.put(pos, 1);
	}
}
