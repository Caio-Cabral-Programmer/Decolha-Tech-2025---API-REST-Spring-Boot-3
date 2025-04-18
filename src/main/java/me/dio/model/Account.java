package me.dio.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "tb_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // A anotação @Column(unique = true) especifica que este campo deve ser único na tabela. Isso significa que não pode haver duas entradas na tabela com o mesmo valor para este campo. nullable = false → especifica que este campo não pode ser nulo.
    private String number;

    private String agency;

    @Column(precision = 13, scale = 2) // A anotação @Column(precision = 13, scale = 2) especifica que este campo deve ter um tamanho fixo de 13 caracteres e 2 casas decimais. Isso significa que o campo "balance" deve ter um tamanho total de 15 caracteres (13 para o número e 2 para a casa decimal
    private BigDecimal balance;

    @Column(name = "additional_limit", precision = 13, scale = 2) // Foi trocado o nome limit para additional_limit, pois em alguns bancos de dados a palavra limit é uma palavra reservada.
    private BigDecimal limit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

}
