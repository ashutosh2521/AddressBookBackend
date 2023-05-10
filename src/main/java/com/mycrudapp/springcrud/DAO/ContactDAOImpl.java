package com.mycrudapp.springcrud.DAO;

import com.mycrudapp.springcrud.ContactNotFoundException;
import com.mycrudapp.springcrud.Entity.Contact;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactDAOImpl implements ContactDAO
{
    EntityManager entityManager;

    @Autowired
    public ContactDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Contact> getAllContacts() {
        TypedQuery<Contact> theQuery = entityManager.createQuery("From Contact", Contact.class);
        List<Contact> contacts = theQuery.getResultList();
        return contacts;
    }

    @Override
    public Contact getContactById(int id) {
        return entityManager.find(Contact.class, id);
    }

    @Override
    public Contact updateContact(Contact contact) {
        Contact updatedContact = entityManager.merge(contact);
        return updatedContact;
    }

    @Override
    public void deleteContactById(int id)
    {
        Contact contact = entityManager.find(Contact.class, id);
        if(contact == null)
        {
            throw new ContactNotFoundException("Contact is not found, Can't Delete");
        }
        entityManager.remove(contact);
    }
}
