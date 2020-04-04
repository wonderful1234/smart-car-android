package com.example.car;
import java.net.DatagramSocket;

public class Config {
	private static String serverIp;
	private static int  serverPort;
	private static int localPort;
	private static DatagramSocket ds;
	public static DatagramSocket getDs() {
		return ds;
	}
	public static void setDs(DatagramSocket ds) {
		Config.ds = ds;
	}
	public static String getServerIp() {
		return serverIp;
	}
	public static void setServerIp(String serverIp) {
		Config.serverIp = serverIp;
	}
	public static int getServerPort() {
		return serverPort;
	}
	public static void setServerPort(int serverPort) {
		Config.serverPort = serverPort;
	}
	public static int getLocalPort() {
		return localPort;
	}
	public static void setLocalPort(int localPort) {
		Config.localPort = localPort;
	}
	

}
