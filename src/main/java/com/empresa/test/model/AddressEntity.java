package com.empresa.test.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Table(name = "address")
public class AddressEntity {

	@Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "zipCode")
    private String zipCode;

    @Column(name = "number")
    private String number;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<CustomerEntity> customer;
}
