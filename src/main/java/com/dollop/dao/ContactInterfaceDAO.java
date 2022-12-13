package com.dollop.dao;

import java.util.List;

import com.dollop.model.Contact;

public interface ContactInterfaceDAO {
	public boolean createContact(Contact contact);
	public List<Contact> viewAllContact();
	public Contact viewContactById(Integer id);
	public boolean updateContact(Contact contact);
	public boolean deleteContact(Integer id);

}
