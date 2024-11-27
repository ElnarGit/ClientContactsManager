package com.saparov.ClientContactsManager.service.impl;

import com.saparov.ClientContactsManager.entity.Client;
import com.saparov.ClientContactsManager.exception.ClientNotFoundException;
import com.saparov.ClientContactsManager.repository.ClientRepository;
import com.saparov.ClientContactsManager.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public Client getById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client update(Client client) {
        Client existingClient = getById(client.getId());

        existingClient.setName(client.getName());
        existingClient.setContacts(client.getContacts());

        return clientRepository.save(existingClient);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if(!clientRepository.existsById(id)) {
            throw new ClientNotFoundException(id);
        }

        clientRepository.deleteById(id);
    }
}
