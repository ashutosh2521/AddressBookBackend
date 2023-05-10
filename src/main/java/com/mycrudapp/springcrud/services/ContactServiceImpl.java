package com.mycrudapp.springcrud.services;

import com.mycrudapp.springcrud.ContactNotFoundException;
import com.mycrudapp.springcrud.DAO.ContactDAO;
import com.mycrudapp.springcrud.Entity.Contact;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService
{
    ContactDAO contactDAO;

    @Autowired
    public ContactServiceImpl(ContactDAO contactDAO)
    {
        this.contactDAO = contactDAO;
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactDAO.getAllContacts();
    }

    @Override
    public Contact getContactById(int id) {
        return contactDAO.getContactById(id);
    }

    @Override
    @Transactional
    public Contact updateContact(Contact contact) {
        return  contactDAO.updateContact(contact);
    }

    @Override
    @Transactional
    public void deleteContactById(int id) {
         contactDAO.deleteContactById(id);
    }

    @Override
    public Contact getTopContact() {
        List<Contact> contacts = this.getAllContacts();
        if(contacts == null)
        {
            throw new ContactNotFoundException("Not top contact");
        }
        return contacts.get(0);
    }
}
