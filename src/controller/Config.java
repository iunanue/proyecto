package controller;


public class Config {

	private static Config config;
	private String root = "http://localhost:8080/proyecto/";
	private String username;

	private Config() {
	}

	public static Config getInstance() {
		if (config == null) {
			config = new Config();
		}
		return config;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
