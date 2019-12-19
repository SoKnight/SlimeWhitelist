package ru.soknight.swl.handlers;

import ru.soknight.swl.database.DatabaseManager;
import ru.soknight.swl.files.Config;

public class PurgeTask implements Runnable {

	@Override
	public void run() {
		DatabaseManager.purge(Config.maxofflinetime);
	}
	
}
