package com.tyss.capgemini.loanproject.dao;

public interface LoginDaoDeclaration {
	public boolean custLogin(String custUsername, String password);
	public boolean empLogin(String empUsername, String empPass);
	public boolean register(String occupation, String dob, String gender, String username, String email, String password, String firstname, String lastname, long phone, double accountBal);
	public boolean emailExists(String email);
	public boolean usernameExists(String username);
}
