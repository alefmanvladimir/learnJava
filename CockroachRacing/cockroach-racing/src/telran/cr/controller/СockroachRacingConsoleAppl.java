package telran.cr.controller;

import telran.cr.dto.Cockroach;
import telran.cr.services.СockroachRacingOperations;
import telran.cr.services.СockroachRacingOperationsImpl;
import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;

public class СockroachRacingConsoleAppl {
	public static void main(String[] args) {
		СockroachRacingOperations cockroachRacingOperations;
		InputOutput io = new ConsoleInputOutput();

		cockroachRacingOperations = new СockroachRacingOperationsImpl(getCockroachNumber(io), getDistance(io));
		Cockroach winner = cockroachRacingOperations.start();
		System.out.println("Winner is - cockroachId: " + winner.id);

	}

	private static int getDistance(InputOutput io) {
		return io.readInt("Enter distance");
	}

	private static int getCockroachNumber(InputOutput io) {
		return io.readInt("Enter number of cockroaches");
	}

}
