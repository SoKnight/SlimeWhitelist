package ru.soknight.swl.utils;

import ru.soknight.swl.files.Messages;

public class HelpCommand {

	private String format, command, description;
	
	public HelpCommand(String command) {
		this.format = Messages.format;
		this.command = command;
		this.description = Messages.getMessage("help-descriptions." + command);
	}
	
	public HelpCommand(String command, String... args) {
		this.format = Messages.format;
		this.command = command;
		this.description = Messages.getMessage("help-descriptions." + command);
		for(String a : args)
			this.command += " " + Messages.getMessage("help-nodes." + a);
	}
	
	@Override
	public String toString() {
		return format.replace("%cmd%", command).replace("%desc%", description);
	}
	
}
