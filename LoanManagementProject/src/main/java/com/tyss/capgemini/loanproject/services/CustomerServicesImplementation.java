package com.tyss.capgemini.loanproject.services;

import com.tyss.capgemini.loanproject.exceptions.DateLimitException;
import com.tyss.capgemini.loanproject.exceptions.InsufficientBalanceException;
import com.tyss.capgemini.loanproject.exceptions.InvalidDateFormatException;
import com.tyss.capgemini.loanproject.exceptions.InvalidPasswordException;
import com.tyss.capgemini.loanproject.exceptions.LoanExcessException;
import com.tyss.capgemini.loanproject.factory.FactoryClass;
import com.tyss.capgemini.loanproject.repository.Repository;
import com.tyss.capgemini.loanproject.validation.ValidationClass;

public class CustomerServicesImplementation implements CustomerServicesDeclaration {

	ValidationClass validationClass = new ValidationClass();

	@Override
	public boolean viewLoanPrograms() {
		if (FactoryClass.getCustomerDao().viewLoanPrograms()) {
			return true;
		} else
			return false;
	}

	@Override
	public boolean changePassword(String custUsername, String newPass) {
		if (validationClass.passValid(newPass)) {
			FactoryClass.getCustomerDao().changePassword(custUsername, newPass);
			return true;
		} else {
			throw new InvalidPasswordException("Password not strong enough.");
		}
	}

	@Override
	public boolean checkBalance(String custUsername) {
		if (FactoryClass.getCustomerDao().checkBalance(custUsername)) {
			return true;
		} else
			return false;
	}

	@Override
	public boolean loanApplicationForm(String applicationId, String accountNo, String applicantFirstName,
			String applicantMiddleName, String applicantLastName, String dateOfBirth, String coapplicantFirstName,
			String coapplicantMiddleName, String coapplicantLastName, String loanChoice, String branchCode,
			String branchName, String openDate, String requestDate, String sub) {
		if (validationClass.dateValid(dateOfBirth)) {
			String[] dateOfBirthArr = dateOfBirth.split("/");
			int dobmonth = Integer.parseInt(dateOfBirthArr[1]);
			int dobyear = Integer.parseInt(dateOfBirthArr[2]);
			if ((dobmonth > 3) && (dobyear >= 2020)) {
				throw new DateLimitException("Not inside date limit.");
			} else if (validationClass.dateValid(openDate)) {
				String[] openDateArr = openDate.split("/");
				int odmonth = Integer.parseInt(openDateArr[1]);
				int odyear = Integer.parseInt(openDateArr[2]);
				if ((odmonth > 3) && (odyear >= 2020)) {
					throw new DateLimitException("Not inside date limit.");
				} else if (validationClass.dateValid(requestDate)) {
					String[] reqDateArr = requestDate.split("/");
					int rdmonth = Integer.parseInt(reqDateArr[1]);
					int rdyear = Integer.parseInt(reqDateArr[2]);
					if (((rdmonth < 3) && (rdyear < 2020)) && (rdyear > 2021)) {
						throw new DateLimitException("Not inside date limit.");
					} else
						FactoryClass.getCustomerDao().loanApplicationForm(applicationId, accountNo, applicantFirstName,
								applicantMiddleName, applicantLastName, dateOfBirth, coapplicantFirstName,
								coapplicantMiddleName, coapplicantLastName, loanChoice, branchCode, branchName,
								openDate, requestDate, sub);
					return true;
				} else
					throw new InvalidDateFormatException("Enter correct date format (DD/MM/YYYY).");
			} else
				throw new InvalidDateFormatException("Enter correct date format (DD/MM/YYYY).");
		} else
			throw new InvalidDateFormatException("Enter correct date format (DD/MM/YYYY).");
	}

	@Override
	public boolean payLoan(String custUsername, Double loanPay) {
		for (int i = 0; i < Repository.customerList.size(); i++) {
			if (custUsername.equals(Repository.customerList.get(i).get("username"))) {
				Double loan = (Double) Repository.customerList.get(i).get("loanAmount");
				if (loanPay > loan) {
					throw new LoanExcessException("Enter amount less than your loan amount.");
				}
				if (loanPay > (Double) Repository.customerList.get(i).get("AccountBal")) {
					throw new InsufficientBalanceException("Insufficient balance in account.");
				} else {
					FactoryClass.getCustomerDao().payLoan(custUsername, loanPay);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean checkLoan(String custUsername) {
		if (FactoryClass.getCustomerDao().checkLoan(custUsername)) {
			return true;
		} else
			return false;
	}
}
