package com.saparov.ClientContactsManager.service;

import com.saparov.ClientContactsManager.entity.Contact;

import java.util.List;

public interface ContactService extends GenericService<Contact, Long> {
    List<Contact> findByClientId(Long clientId);
    List<Contact> findByClientIdAndType(Long clientId, String type);
}
