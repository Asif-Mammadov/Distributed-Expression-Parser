package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

import services.Calculator;
import services.CalculatorImpl;

/**
 * Creates and establishes connection with clients. Exports remote object so
 * that the clients are able to use it.
 */
public class Server {

	public static void main(String[] args) {
		try {
			Calculator skeleton = (Calculator) UnicastRemoteObject.exportObject(new CalculatorImpl(), 0);
			System.out.println(" Server is ready ");
			Registry registry = LocateRegistry.getRegistry(1099);
			if (!Arrays.asList(registry.list()).contains(" Calculator "))
				registry.bind(" Calculator ", skeleton);
			else
				registry.rebind(" Calculator ", skeleton);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
