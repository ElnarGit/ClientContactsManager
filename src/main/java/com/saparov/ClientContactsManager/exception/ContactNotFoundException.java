package com.saparov.ClientContactsManager.exception;

public class ContactNotFoundException extends NotFoundException{

    public ContactNotFoundException(Long id) {
        super("Контакт с ID " + id + " не найден", "CONTACT_NOT_FOUND");
    }
}
