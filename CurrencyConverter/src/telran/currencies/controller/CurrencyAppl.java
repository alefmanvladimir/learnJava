package telran.currencies.controller;

import telran.currencies.controller.actions.CurrencyActions;
import telran.currencies.service.*;
import telran.view.*;
import terlan.view.Menu;

public class CurrencyAppl {

	public static void main(String[] args) throws Exception {
		
		InputOutput io = new ConsoleInputOutput();
		if (args.length == 0) {
			io.writeObjectLine("Path to the file with rates should be defined");
			return;
		}
		Menu menu = new Menu("Currency Application",
		CurrencyActions.getCurrencyItems(CurrencyConverterApiImpl.getCurrencyConverter()));
		menu.perform(io);
		

	}

}
