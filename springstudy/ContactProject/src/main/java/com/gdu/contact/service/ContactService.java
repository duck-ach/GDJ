package com.gdu.contact.service;

import java.util.List;

import com.gdu.contact.domain.ContactDTO;

public interface ContactService {
	public List<ContactDTO> findAllContact();
	public int addContact(ContactDTO contact);
	public int modifyContact(ContactDTO contact);
	public int removeContact(ContactDTO contact);
}
