package me.dio.model;

import jakarta.persistence.Entity;

@Entity(name = "tb_feature")
public class Feature extends BaseItem {
    
}
// Aplicação do conceito de DRY (Sigla de Don't Repeat Yourself) para evitar duplicação de código.
// DRY é uma prática de programação que implica em evitar a repetição de código. Isso é feito através de técnicas como herança, polimorfismo e encapsulamento. Aqui, a classe Feature herda de BaseItem, que já está definida no código. Portanto, não é necessário repetir o código da classe BaseItem.