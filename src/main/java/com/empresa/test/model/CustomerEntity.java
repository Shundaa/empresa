package com.empresa.test.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.empresa.test.dto.AddressDto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Entity
@Data
@Accessors(chain = true)
@Table(name = "CUSTOMER")
public class CustomerEntity implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
	@Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "documentId")
    private String documentId;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private String age;

    @Column(name = "registrationDate")
    private String registrationDate;

    @Column(name = "lastUpdateInfo")
    private String lastUpdateInfo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private List<AddressEntity> address;

}
