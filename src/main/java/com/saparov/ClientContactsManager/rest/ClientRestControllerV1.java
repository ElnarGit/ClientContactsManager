package com.saparov.ClientContactsManager.rest;

import com.saparov.ClientContactsManager.dto.ClientDto;
import com.saparov.ClientContactsManager.entity.Client;
import com.saparov.ClientContactsManager.mapper.ClientMapper;
import com.saparov.ClientContactsManager.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clients")
public class ClientRestControllerV1 {
    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable("id") Long id) {
        Client client = clientService.getById(id);
        ClientDto clientDto = clientMapper.toDto(client);
        return ResponseEntity.ok(clientDto);
    }


    @GetMapping
    public ResponseEntity<List<ClientDto>> getAllClients(){
        List<ClientDto> clientDtos =  clientService
                .getAll().stream()
                .map(clientMapper::toDto)
                .toList();

        return ResponseEntity.ok(clientDtos);
    }

    @PostMapping
    public ResponseEntity<ClientDto> createClient(@RequestBody @Valid ClientDto clientDto) {
        Client client = clientMapper.toEntity(clientDto);

        Client savedClient =  clientService.save(client);
        return ResponseEntity.status(201).body(clientMapper.toDto(savedClient));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable("id") Long id, @RequestBody @Valid ClientDto clientDto){
        clientDto.setId(id);
        Client client = clientMapper.toEntity(clientDto);

        Client updatedClient = clientService.update(client);
        return ResponseEntity.ok(clientMapper.toDto(updatedClient));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
