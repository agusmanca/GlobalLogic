package com.globallogic.challenge.globallogicchallenge.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Table(name = "PHONES")
@Data
public class Phone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NUMBER")
    private Long number;

    @Column(name = "CITY_CODE")
    private Integer citycode;

    @Column(name = "CONTRY_CODE")
    private String contrycode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_USER")
    private Usuario user;
}
