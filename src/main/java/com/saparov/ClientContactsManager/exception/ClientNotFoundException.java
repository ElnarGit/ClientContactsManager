package com.saparov.ClientContactsManager.exception;

public class ClientNotFoundException extends NotFoundException{

    public ClientNotFoundException(Long id) {
        super("Клиент с ID " + id + " не найден", "CLIENT_NOT_FOUND");
    }
}
