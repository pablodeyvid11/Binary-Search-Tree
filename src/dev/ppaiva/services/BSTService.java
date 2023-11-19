package dev.ppaiva.services;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import javax.management.AttributeNotFoundException;

import dev.ppaiva.beans.BST;
import dev.ppaiva.beans.Command;

public class BSTService {

	List<BST<?>> trees;
	Set<Command> commands;

	public BSTService() {
		this.trees = new ArrayList<>();
		this.commands = new LinkedHashSet<>();
		loadCommands();
	}

	public String executeCommand(String commandString) throws Exception {
		try {
			String[] members = commandString.split(" ");

			if (members[0].toLowerCase().equals("clear"))
				return "clear";

			if (members[0].toLowerCase().equals("exit"))
				return "exit";

			if (members[0].toLowerCase().equals("help")) {
				Command command = findCommandByFunctionName("help");

				String returnFunction = command.getFunction().apply(commandString);

				return returnFunction;
			}
			if (!members[0].toLowerCase().equals("bst"))
				return "\t-bash: " + members[0] + ": command not found";

			String function = commandString.split(" ")[1];

			Command command = findCommandByFunctionName(function);

			String returnFunction = command.getFunction().apply(commandString);

			return returnFunction;
		} catch (AttributeNotFoundException e) {
			return e.getMessage();
		} catch (ArrayIndexOutOfBoundsException e) {
			return "\t-bash: type the full command";
		} catch (Exception e) {
			return "\t" + e.getMessage();
		}
	}

	private Command findCommandByFunctionName(String function) throws AttributeNotFoundException {
		Command command = commands.stream().filter(c -> c.getCommand().equals(function)).findFirst()
				.orElseThrow(() -> new AttributeNotFoundException("\t-bash: " + function + " - command not found"));
		return command;
	}

	private void loadCommands() {

		// HELP

		String helpCommand = "help";
		String helpStructure = "help [command] or just help";
		String helpDescription = "Show all available commands";
		Function<String, String> helpFunction = (commandString) -> {
			StringBuilder sb = new StringBuilder();

			String[] members = commandString.split(" ");
			if (members.length == 1) {
				sb.append(
						"  Binnary Search Tree - Java Implementation, version 5.2.15(1)-release (x86_64-pc-linux-gnu)\n");
				sb.append("  These shell commands are defined internally.  Type `help' to see this list.\n");
				sb.append("\n");
				sb.append("  A star (*) next to a name means that the command is disabled.\n");
				sb.append("\n");

				for (Command command : commands) {
					sb.append("  - ");
					sb.append(command.getCommandStructure());
					sb.append("\n");
					sb.append("     → ");
					sb.append(command.getDescription());
					sb.append("\n");
				}
			} else {
				String especify = members[1];
				try {
					Command command = findCommandByFunctionName(especify);
					sb.append(" ");
					sb.append(command.getCommandStructure());
					sb.append("\n");
					sb.append("     → ");
					sb.append(command.getDescription());
					sb.append("\n");
				} catch (AttributeNotFoundException e) {
					e.printStackTrace();
					return e.getMessage();
				}

			}

			return sb.toString();
		};

		buildCommand(helpCommand, helpStructure, helpDescription, helpFunction);

		// Create

		String createCommand = "create";
		String createStructure = "create [--file file path] or create [list of numbers] ";
		String createDescription = "Create a Binary Search Tree";
		Function<String, String> createFunction = (commandString) -> {

			BST<Integer> bst = new BST<>();
			bst.setType("Integer");
			String[] members = commandString.split(" ");

			for (int i = 2; i < members.length; i++) {
				bst.insert(Integer.parseInt(members[i]));
			}

			trees.add(bst);

			StringBuilder sb = new StringBuilder();

			sb.append("BST Created - id: ");
			sb.append(trees.size() - 1);

			return "\t" + sb.toString();
		};

		buildCommand(createCommand, createStructure, createDescription, createFunction);

		// Insert
		String insertCommand = "insert";
		String insertStructure = "insert [BST ID] [VALUE]";
		String insertDescription = "Insert a value on BST";
		Function<String, String> insertFunction = (commandString) -> {
			String[] members = commandString.split(" ");

			Integer id = Integer.parseInt(members[2]);
			String value = members[3];
			trees.get(id).insert(Integer.parseInt(value));
			return "\t" + value + " inserted";
		};
		
		buildCommand(insertCommand, insertStructure, insertDescription, insertFunction);
		
		// List

		String listCommand = "list";
		String listStructure = "list";
		String listDescription = "List all Binary Search Tree";
		Function<String, String> listFunction = (commandString) -> {
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < trees.size(); i++) {
				sb.append("\t- [ ");
				sb.append(i);
				sb.append(" ]: ");
				sb.append("Binary Search Tree <");
				sb.append(trees.get(i).getType());
				sb.append(">");
				sb.append("\n");
			}

			return sb.toString();
		};
		buildCommand(listCommand, listStructure, listDescription, listFunction);

		// PrintTree

		String printCommand = "print";
		String printStructure = "print [--method method] [BST ID] or print [BST ID]";
		String printDescription = "Print the Binary Search Tree selected using the default print method or: in-order, pre-order, post-order.";
		Function<String, String> printFunction = (commandString) -> {
			String[] members = commandString.split(" ");

			if (members[2].matches("\\d+")) {
				Integer id = Integer.parseInt(members[2]);
				return trees.get(id).printTree();
			}

			Integer id = Integer.parseInt(members[3]);

			switch (members[2]) {
			case "in-order": {
				return trees.get(id).printTreeInOrder();
			}
			case "pre-order": {
				return trees.get(id).printTreePreOrder();
			}
			case "post-order": {
				return trees.get(id).printTreePostOrder();
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + members[2]);
			}

		};

		buildCommand(printCommand, printStructure, printDescription, printFunction);

		// indexOf

		String indexOfCommand = "indexof";
		String indexOfStructure = "indexof [BST ID] [value]";
		String indexOfDescription = "Return de in-order value index or -1 if dont exists";
		Function<String, String> indexOfFuncion = (commandString) -> {
			String[] members = commandString.split(" ");

			Integer id = Integer.parseInt(members[2]);
			String value = members[3];

			return trees.get(id).indexOf(value) + "";
		};

		buildCommand(indexOfCommand, indexOfStructure, indexOfDescription, indexOfFuncion);

	}

	private void buildCommand(String command, String structure, String description, Function<String, String> function) {
		Command com = new Command();
		com.setCommand(command);
		com.setCommandStructure(structure);
		com.setDescription(description);
		com.setFunction(function);

		commands.add(com);
	}
}