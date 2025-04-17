package me.dio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.domain.model.User;

@Repository // Essa anotação indica que esta classe é um repositório Spring Data JPA (ou seja, ela é um "CRUDEIRO") que pode ser usado para acessar e manipular os dados da entidade User. O tipo de dado que será usado como chave primária é Long. No banco de dados, essa anotação especifica que o campo "id" será a chave primária da tabela "tb_user" e que o campo "name" será uma coluna da tabela "tb_user".
public interface UserRepository extends JpaRepository<User, Long> { // Este trecho significa que a interface UserRepository herda de JpaRepository, que é um interface Spring Data JPA que fornece uma série de métodos para acessar e manipular os dados de uma entidade. A entidade que será acessada será User, e o tipo de dado que será usado como chave primária (ID) é Long.
    
    boolean existsByAccountNumber(String number); // Este method verifica se existe uma entidade de User com o número de conta especificado.

    boolean existsByCardNumber(String number); // Este method verifica se existe uma entidade de User com o número de cartão especificado.
}
// Não preciso escrever methods CRUD para este method, pois ele é implementado na interface JpaRepository. No banco de dados, essa anotação especifica que o campo "id" será a chave primária da tabela "tb_user" e que o campo "name" será uma coluna da tabela "tb_user".