package com.tyss.capgemini.loanproject.servicestest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;

import com.tyss.javacloud.loanproject.exceptions.InvalidLoanTypeException;
import com.tyss.javacloud.loanproject.repository.Repository;
import com.tyss.javacloud.loanproject.services.AdminServicesImplementation;

public class AdminServicesTest {

	AdminServicesImplementation implementation = new AdminServicesImplementation();

	@Test
	void viewCLientsTest1() {
		Repository.userTable();
		Boolean isBoolean = implementation.viewClients();
		assertEquals(isBoolean, true);
	}

	@Test
	void loanUdpateTest1() {
		Repository.userTable();
		try {
			Boolean isBoolean = implementation.loanUpdate("House Loan", 1, "garib loan");
			assertEquals(isBoolean, true);
		} catch (Exception e) {
			assertThrows(InvalidLoanTypeException.class, () -> {
				implementation.loanUpdate("House Loan", 1, "garib loan");
			});
		}
	}
	
	@Test
	void loanUdpateTest2() {
		Repository.userTable();
		try {
			Boolean isBoolean = implementation.loanUpdate("asdasd", 1, "garib loan");
			assertEquals(isBoolean, false);
		} catch (Exception e) {
			assertThrows(InvalidLoanTypeException.class, () -> {
				implementation.loanUpdate("asdasd", 1, "garib loan");
			});
		}
	}
	
	@Test
	void loanUdpateTest3() {
		Repository.userTable();
		try {
			Boolean isBoolean = implementation.loanUpdate("House Loan", 5, "garib loan");
			assertEquals(isBoolean, true);
		} catch (Exception e) {
			assertThrows(InvalidLoanTypeException.class, () -> {
				implementation.loanUpdate("House Loan", 5, "garib loan");
			});
		}
	}
	
	@Test
	void insertLoanTest1() {
		Repository.userTable();
		Boolean isBoolean =implementation.insertLoan("garib loan", "15 months", "29%");
		assertEquals(isBoolean, true);
	}
	
	@Test
	void insertLoanTest2() {
		Repository.userTable();
		Boolean isBoolean =implementation.insertLoan("onion loan", "15 months", "29%");
		assertEquals(isBoolean, true);
	}
	
	@Test
	void deleteLoanTest1() {
		Repository.userTable();
		try {
			Boolean isBoolean = implementation.deleteLoan("House Loan");
			assertEquals(isBoolean, true);
		} catch (Exception e) {
			assertThrows(InvalidLoanTypeException.class, ()->{
				implementation.deleteLoan("House Loan");
			});
		}
	}
	
	@Test
	void deleteLoanTest2() {
		Repository.userTable();
		try {
			Boolean isBoolean = implementation.deleteLoan("tomato Loan");
			assertEquals(isBoolean, false);
		} catch (Exception e) {
			assertThrows(InvalidLoanTypeException.class, ()->{
				implementation.deleteLoan("tomato Loan");
			});
		}
	}
	
	@Test
	void deleteLoanTest3() {
		Repository.userTable();
		try {
			Boolean isBoolean = implementation.deleteLoan("asdasd");
			assertEquals(isBoolean, true);
		} catch (Exception e) {
			assertThrows(InvalidLoanTypeException.class, ()->{
				implementation.deleteLoan("asdasd");
			});
		}
	}
	
	@Test
	void viewLoanProgramsTest1() {
		Repository.userTable();
		Boolean isBoolean = implementation.viewLoanPrograms();
		assertEquals(isBoolean, true);
	}
	
	@Test
	void approvedFormsTest1() {
		Repository.userTable();
		Boolean isBoolean = implementation.approvedForms();
		assertEquals(isBoolean, true);
	}
	
	@Test
	void rejectedFormsTest1() {
		Repository.userTable();
		Boolean isBoolean = implementation.rejectedForms();
		assertEquals(isBoolean, true);
	}
	
	@Test
	void requestedFormsTest1() {
		Repository.userTable();
		Boolean isBoolean = implementation.requestedForms();
		assertEquals(isBoolean, true);
	}
}
