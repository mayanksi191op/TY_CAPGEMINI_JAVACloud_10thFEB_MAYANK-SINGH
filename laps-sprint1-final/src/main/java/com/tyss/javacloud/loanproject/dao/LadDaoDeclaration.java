package com.tyss.javacloud.loanproject.dao;

public interface LadDaoDeclaration {
	public boolean viewLoanPrograms();
	public boolean ladReviewForms(String apid, String status);
	public boolean ladViewForms(String planString);
	public boolean requestedForms();
	public boolean loanTypes();
	public String loanTypes(String k);
	public boolean applicationExist(String id);
}
