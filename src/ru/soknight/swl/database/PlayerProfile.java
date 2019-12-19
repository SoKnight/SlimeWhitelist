package ru.soknight.swl.database;

public class PlayerProfile {

	private String name;
	private long time;
	
	public PlayerProfile(String name, long time) {
		this.name = name;
		this.time = time;
	}
	
	public PlayerProfile(String name) {
		this.name = name;
		this.time = System.currentTimeMillis() / 60000;
	}

	public String getName() {
		return name;
	}

	public long getTime() {
		return time;
	}
	
}
