package com.saparov.ClientContactsManager.repository;

import com.saparov.ClientContactsManager.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
