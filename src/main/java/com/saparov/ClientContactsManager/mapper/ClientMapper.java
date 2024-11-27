package com.saparov.ClientContactsManager.mapper;

import com.saparov.ClientContactsManager.dto.ClientDto;
import com.saparov.ClientContactsManager.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientDto toDto(Client client){
        if(client == null){
            return null;
        }

        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .build();
    }

    public Client toEntity(ClientDto clientDto){
        if(clientDto == null){
            return null;
        }

        return Client.builder()
                .id(clientDto.getId())
                .name(clientDto.getName())
                .build();
    }
}
