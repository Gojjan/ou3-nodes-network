package network;

public class Main {
	public static void main(String args[]) {
		System.out.println("Hello world!");
		Network network = new Network(50, 50, 0.01, 0.5, 50, 45);
		for(int i = 0; i < 10000; i++){
			network.timeTick();
		}
		System.out.println("Hello world!");
	}
}
