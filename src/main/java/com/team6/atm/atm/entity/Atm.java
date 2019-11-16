package com.team6.atm.atm.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "atm")
public class Atm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "atm_id")
    private Long atmId;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "atm_banknotes",
            joinColumns = @JoinColumn(name = "atm_id", referencedColumnName = "atm_id"),
            inverseJoinColumns = @JoinColumn(name = "bill_id", referencedColumnName = "bill_id"))
    private List<Banknotes> banknotesList;
}
