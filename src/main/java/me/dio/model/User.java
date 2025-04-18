package me.dio.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

// todo → adicionar o lombok no projeto.

@Entity(name = "tb_user") // Essa anotação define que esta classe será uma entidade no banco de dados e deve ser persistida no banco de dados. O nome "tb_user" será o nome da tabela no banco de dados.
public class User {
    
    @Id // A anotação @Id especifica que este campo será o campo de chave primária da entidade.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // A anotação @GeneratedValue especifica que o valor do campo "id" será gerado automaticamente. O valor será gerado através da estratégia "IDENTITY", que significa que o valor do campo "id" será gerado pelo banco de dados.
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL) // A anotação @OneToOne especifica que este campo é uma relação one-to-one com outra entidade. O campo "account" será uma entidade do tipo "Account" e a relação será bidirecional. O campo "cascade = CascadeType.ALL" especifica que todas as operações de persistência (create, update, delete) em "account" serem cascadas para a entidade "User", ou seja, quando uma entidade de "Account" for persistida, todas as entidades de "User" que tenham essa entidade de "Account" como relacionamento também serão persistidas. E se User for deletada, todas as entidades de "Account" que tenham essa entidade de "User" como relacionamento também serão deletadas. No banco de dados, essa anotação especifica que o campo "id" será a chave primária da tabela "tb_user" e que o campo "account" será uma chave estrangeira que aponta para a tabela "tb_account".
    private Account account;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Card card;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // A anotação @OneToMany especifica que este campo é uma relação one-to-many com outra entidade. O campo "features" será uma lista de entidades do tipo "Feature" e a relação será bidirecional. O campo "cascade = CascadeType.ALL" especifica que todas as operações de persistência (create, update, delete) em "features" serem cascadas para a entidade "User", ou seja, quando uma entidade de "Features" for persistida, todas as entidades de "User" que tenham essa entidade de "Features" como relacionamento também serão persistidas. E se User for deletada, todas as entidades de "Features" que tenham essa entidade de "User" como relacionamento também serão deletadas. O campo "fetch = FetchType.EAGER" especifica que o campo "features" será carregado imediatamente ao carregar a entidade "User", ou seja, quando a entidade "User" for carregada, todas as entidades de "Features" que tenham essa entidade de "User" como relacionamento também serão carregadas. No banco de dados, essa anotação especifica que o campo "id" será a chave primária da tabela "tb_user" e que o campo "features" será uma chave estrangeira que aponta para a tabela "tb_feature".
    private List<Feature> features;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<News> news;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

}
