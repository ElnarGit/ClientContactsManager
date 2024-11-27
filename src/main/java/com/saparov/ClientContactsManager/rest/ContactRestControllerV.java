package com.saparov.ClientContactsManager.rest;

import com.saparov.ClientContactsManager.dto.ContactDto;
import com.saparov.ClientContactsManager.entity.Contact;
import com.saparov.ClientContactsManager.mapper.ContactMapper;
import com.saparov.ClientContactsManager.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contacts")
public class ContactRestControllerV {
    private final ContactService contactService;
    private final ContactMapper contactMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> getContactById(@PathVariable("id") Long id) {
        Contact contact = contactService.getById(id);
        ContactDto contactDto = contactMapper.toDto(contact);
        return ResponseEntity.ok(contactDto);
    }


    @GetMapping
    public ResponseEntity<List<ContactDto>> getAllContacts(){
        List<ContactDto> contactDtos =  contactService
                .getAll().stream()
                .map(contactMapper::toDto)
                .toList();

        return ResponseEntity.ok(contactDtos);
    }

    @PostMapping
    public ResponseEntity<ContactDto> createContact(@RequestBody @Valid ContactDto contactDto) {
        Contact contact = contactMapper.toEntity(contactDto);

        Contact savedContact =  contactService.save(contact);
        return ResponseEntity.status(201).body(contactMapper.toDto(savedContact));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ContactDto> updateContact(@PathVariable("id") Long id, @RequestBody @Valid ContactDto contactDto){
        contactDto.setId(id);
        Contact contact = contactMapper.toEntity(contactDto);

        Contact updatedContact = contactService.update(contact);
        return ResponseEntity.ok(contactMapper.toDto(updatedContact));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable("id") Long id) {
        contactService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<ContactDto>> getContactsByClientId(@PathVariable("clientId") Long clientId) {
        List<Contact> contacts = contactService.findByClientId(clientId);
        List<ContactDto> contactDtos = contacts.stream()
                .map(contactMapper::toDto)
                .toList();
        return ResponseEntity.ok(contactDtos);
    }

    @GetMapping("/client/{clientId}/type/{type}")
    public ResponseEntity<List<ContactDto>> getContactsByClientIdAndType(
            @PathVariable("clientId") Long clientId, @PathVariable("type") String type) {
        List<Contact> contacts = contactService.findByClientIdAndType(clientId, type);
        List<ContactDto> contactDtos = contacts.stream()
                .map(contactMapper::toDto)
                .toList();
        return ResponseEntity.ok(contactDtos);
    }


}
