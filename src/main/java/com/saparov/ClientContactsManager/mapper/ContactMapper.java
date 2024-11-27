package com.saparov.ClientContactsManager.mapper;

import com.saparov.ClientContactsManager.dto.ContactDto;
import com.saparov.ClientContactsManager.entity.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    public ContactDto toDto(Contact contact){
        if(contact == null){
            return null;
        }

        return ContactDto.builder()
                .id(contact.getId())
                .type(contact.getType())
                .value(contact.getValue())
                .clientId(contact.getClient() != null ? contact.getClient().getId() : null)
                .build();
    }


    public Contact toEntity(ContactDto contactDto){
        if(contactDto == null){
            return null;
        }

        return Contact.builder()
                .id(contactDto.getId())
                .type(contactDto.getType())
                .value(contactDto.getValue())
                .build();
    }
}
