package com.mycrudapp.springcrud.Controller;

import com.mycrudapp.springcrud.ContactErrorResponse;
import com.mycrudapp.springcrud.ContactNotFoundException;
import com.mycrudapp.springcrud.DAO.ContactDAO;
import com.mycrudapp.springcrud.Entity.Contact;
import com.mycrudapp.springcrud.services.ContactService;
import com.mycrudapp.springcrud.services.ExcelService;
import jakarta.annotation.PostConstruct;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactRestController {
    private ContactService contactService;

    private ExcelService excelService;

    @Autowired
    public ContactRestController(ContactService contactService, ExcelService excelService) {
        this.contactService = contactService;
        this.excelService = excelService;
    }

    @GetMapping("/contacts")
    public List<Contact> getContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/contact/{contactId}")
    public Contact getContact(@PathVariable int contactId) {
        Contact contact =  contactService.getContactById(contactId);
        if(contact == null)
        {
            throw new ContactNotFoundException("Contact is not found");
        }
        return contact;
    }

    @PostMapping("/contact")
    public Contact addContact(@RequestBody Contact contact)
    {
        contact.setId(0);
        return contactService.updateContact(contact);
    }

    @PutMapping("/contact")
    public  Contact updateContact(@RequestBody Contact contact)
    {
        return contactService.updateContact(contact);
    }

    @DeleteMapping("/contact/{contactId}")
    public boolean deleteContact(@PathVariable int contactId)
    {
        contactService.deleteContactById(contactId);
        return true;
    }

    @GetMapping("/topcontact")
    public Contact getTopContact()
    {
        return contactService.getTopContact();
    }

    @GetMapping(value="/downloadExcel")
    public ResponseEntity<ByteArrayResource> downloadTemplate() throws Exception {
        try {
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "force-download"));
            String filename = "contacts.xlsx";
           // header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+filename);
            ByteArrayOutputStream stream = excelService.getDataStream();
//            return new ResponseEntity<>(new ByteArrayResource(stream.toByteArray()),
//                    header, HttpStatus.CREATED);
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+filename);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new ByteArrayResource(stream.toByteArray()));


        } catch (Exception e) {
            //log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
