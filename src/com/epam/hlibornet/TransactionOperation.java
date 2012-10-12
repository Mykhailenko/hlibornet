package com.epam.hlibornet;

public interface TransactionOperation <E extends Exception>{
	Object execute() throws E;
	int transactionLevel();
}
