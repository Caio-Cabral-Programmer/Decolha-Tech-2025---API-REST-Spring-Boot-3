package me.dio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.model.User;

@Repository // Essa anotação indica que esta classe é um repositório Spring Data JPA (ou seja, ela é um "CRUDEIRO") que pode ser usado para acessar e manipular os dados da entidade User. O tipo de dado que será usado como chave primária é Long. No banco de dados, essa anotação especifica que o campo "id" será a chave primária da tabela "tb_user" e que o campo "name" será uma coluna da tabela "tb_user".
public interface UserRepository extends JpaRepository<User, Long> { // Este trecho significa que a interface UserRepository herda de JpaRepository, que é um interface Spring Data JPA que fornece uma série de métodos para acessar e manipular os dados de uma entidade. A entidade que será acessada será User, e o tipo de dado que será usado como chave primária (ID) é Long.
    
    boolean existsByAccountNumber(String number); // Este method verifica se existe uma entidade de User com o número de conta especificado. Ou seja, colocamos o nome da Classe Account e o nome do atributo Number no nome deste method para que o JPA entenda que é para fazer um join da tabela Account com a tabela User pelo campo accountNumber da tabela Account. O method retorna um boolean, que indica se a entidade foi encontrada ou não.

    boolean existsByCardNumber(String number); // Este method verifica se existe uma entidade de User com o número de cartão especificado. Ou seja, colocamos o nome da Classe Card e o nome do atributo Number no nome deste method para que o JPA entenda que é para fazer um join da tabela Card com a tabela User pelo campo cardNumber da tabela Card. O method retorna um boolean, que indica se a entidade foi encontrada ou não.
}
// Não preciso escrever methods CRUD para este method, pois ele é implementado na interface JpaRepository. No banco de dados, essa anotação especifica que o campo "id" será a chave primária da tabela "tb_user" e que o campo "name" será uma coluna da tabela "tb_user".