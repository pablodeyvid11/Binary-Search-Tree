package dev.ppaiva.beans;

import java.util.Objects;
import java.util.function.Function;

public class Command {
	private String command;
	private String commandStructure;
	private String description;
	private Function<String, String> function;

	public Command() {
	}

	public Command(String command, String commandStructure, String description, Function<String, String> function) {
		this.command = command;
		this.commandStructure = commandStructure;
		this.description = description;
		this.function = function;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getCommandStructure() {
		return commandStructure;
	}

	public void setCommandStructure(String commandStructure) {
		this.commandStructure = commandStructure;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Function<String, String> getFunction() {
		return function;
	}

	public void setFunction(Function<String, String> function) {
		this.function = function;
	}

	@Override
	public int hashCode() {
		return Objects.hash(command);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Command other = (Command) obj;
		return Objects.equals(command, other.command);
	}
}
