package com.saparov.ClientContactsManager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "contact_type")
    private String type;

    @Column(name = "contact_value", length = 255, nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
