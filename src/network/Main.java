package network;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
public class Main {
	public static void main(String args[]) {
		System.out.println("Hello world!");
		Hashtable wat = new Hashtable();
		System.out.println(wat.size());
		wat.put(5, 4);
		System.out.println(wat.size());
		wat.put(6, 4);
		System.out.println(wat.size());
		System.out.println(wat.get(5));
		Network network = new Network(50, 50, 0.01, 0.5, 50, 45);
		for(int i = 0; i < 10000; i++){
			network.timeTick();
		}
		System.out.println("Hello world!");
	}
}
