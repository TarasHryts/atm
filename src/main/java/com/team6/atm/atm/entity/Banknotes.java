package com.team6.atm.atm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "banknotes")
public class Banknotes implements Comparable<Banknotes> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Long billId;
    @Column(name = "value")
    private Long value;
    @Column(name = "amount")
    private Long amount;

    public Banknotes(Long value, Long amount) {
        this.value = value;
        this.amount = amount;
    }

    @Override
    public int compareTo(Banknotes o) {
        return o.getValue().compareTo(this.getValue());
    }
}
