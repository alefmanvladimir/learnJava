package telran.cr.services;

import java.util.ArrayList;

import telran.cr.dto.Cockroach;

public class СockroachRacingOperationsImpl implements СockroachRacingOperations {
	public int cockroaches_number;
	public int destination;
	ArrayList<Cockroach> cockroaches = new ArrayList<>();

	public СockroachRacingOperationsImpl(int cockroaches_number, int _destination) {
		this.cockroaches_number = cockroaches_number;
		this.destination = _destination;
	}

	@Override
	public Cockroach start() {
		creatingCockroaches(cockroaches);
		startingCockroaches(cockroaches);
		joiningCockroaches(cockroaches);
		return cockroaches.get(0).getWinner();
	}

	private void creatingCockroaches(ArrayList<Cockroach> cockroaches) {
		for (int i = 0; i < cockroaches_number; i++) {
			cockroaches.add(new Cockroach(i, destination));
		}
	}

	private void startingCockroaches(ArrayList<Cockroach> cockroaches) {
		for (Cockroach c : cockroaches) {
			c.start();
		}
	}

	private void joiningCockroaches(ArrayList<Cockroach> cockroaches) {
		for (Cockroach c : cockroaches) {
			try {
				c.join();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
