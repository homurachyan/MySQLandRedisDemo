package com.seele.MySQL;

public class OldjdbcTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        OldJdbc oldjdbc = new OldJdbcImpl();
        oldjdbc.insertPerson("xingoo1");
        oldjdbc.insertPerson("xingoo2");
        oldjdbc.findAllPerson();
	}

}
