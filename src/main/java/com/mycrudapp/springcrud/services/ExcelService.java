package com.mycrudapp.springcrud.services;

import com.mycrudapp.springcrud.Entity.Contact;
import com.mycrudapp.springcrud.FileExporter.ExcelFileExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService
{
    @Autowired
    ExcelFileExporter excelFileExporter;

    @Autowired
    ContactService contactService;

    public ByteArrayOutputStream getDataStream() throws IOException {
        List<Contact> contacts = contactService.getAllContacts();
        String[] headers = new String[]{"Name", "Email", "Mobile", "Landline", "Website", "Address"};
        return excelFileExporter.exportExcelFile(contacts, headers);
    }
}
