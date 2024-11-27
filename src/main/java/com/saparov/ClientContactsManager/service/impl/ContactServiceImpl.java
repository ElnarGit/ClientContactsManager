package com.saparov.ClientContactsManager.service.impl;

import com.saparov.ClientContactsManager.entity.Contact;
import com.saparov.ClientContactsManager.exception.ContactNotFoundException;
import com.saparov.ClientContactsManager.repository.ContactRepository;
import com.saparov.ClientContactsManager.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;


    @Override
    public List<Contact> findByClientId(Long clientId) {
        return contactRepository.findByClientId(clientId);
    }

    @Override
    public List<Contact> findByClientIdAndType(Long clientId, String type) {
        return contactRepository.findByClientIdAndType(clientId, type);
    }

    @Override
    public Contact getById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(id));
    }

    @Override
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    @Override
    @Transactional
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    @Transactional
    public Contact update(Contact contact) {
        Contact existingContact = getById(contact.getId());

        existingContact.setType(contact.getType());
        existingContact.setValue(contact.getValue());
        existingContact.setClient(contact.getClient());

        return contactRepository.save(existingContact);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if(!contactRepository.existsById(id)) {
            throw new ContactNotFoundException(id);
        }

        contactRepository.deleteById(id);

    }
}
