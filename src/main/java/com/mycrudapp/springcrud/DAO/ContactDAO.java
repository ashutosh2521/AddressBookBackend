package com.mycrudapp.springcrud.DAO;

import com.mycrudapp.springcrud.Entity.Contact;

import java.util.List;

public interface ContactDAO
{
    List<Contact> getAllContacts();
    Contact getContactById(int id);
    Contact updateContact(Contact contact);
    void deleteContactById(int id);
}
