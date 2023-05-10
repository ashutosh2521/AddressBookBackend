package com.mycrudapp.springcrud.services;

import com.mycrudapp.springcrud.Entity.Contact;

import java.util.List;

public interface ContactService
{
    List<Contact> getAllContacts();
    Contact getContactById(int id);
    Contact updateContact(Contact contact);
    void deleteContactById(int id);
    Contact getTopContact();
}
