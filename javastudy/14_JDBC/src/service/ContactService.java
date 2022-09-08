package service;

import domain.ContactDTO;

public interface ContactService {
	public void addContact(ContactDTO contact);
	public void modifyContact(ContactDTO contact);
	public void deleteContact(int contact_no);
	public void findContact(int contact_no);
	public void findALLContacts();
}

