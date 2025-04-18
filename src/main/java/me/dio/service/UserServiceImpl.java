package me.dio.service;

import me.dio.model.User;
import me.dio.repository.UserRepository;
import me.dio.exception.BusinessException;
import me.dio.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service // @Service: Diz ao Spring que esta classe é um serviço que contém lógica de negócios
public class UserServiceImpl implements UserService {

    /**
     * ID de usuário utilizado na Santander Dev Week 2023.
     * Por isso, vamos criar algumas regras para mantê-lo integro.
     */
    private static final Long UNCHANGEABLE_USER_ID = 1L; // Usuário que serve de modelo para testar a API. Por isso está sendo feito uma proteção para esse usuário. // Acredito que seja necessário criar este usuário diretamente no DB para funcionar.

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    } // Objeto de fato que irá realizar as operações de CRUD no banco de dados.

    @Transactional(readOnly = true) // readOnly = true: Diz que este method só vai ler dados, não vai modificar nada.
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(NotFoundException::new); // Throw(NotFoundException::new) → Se o usuário não for encontrado, lança uma exceção NotFoundException. O GlobalExceptionHandler captura a exceção: Como temos um @ExceptionHandler(NotFoundException.class) no nosso GlobalExceptionHandler, esse method captura o alarme.
    }

    @Transactional // @Transactional: Garante que todas as operações dentro do method sejam tratadas como uma única transação: Primeiro → Se tudo der certo, todas as mudanças são salvas | Segundo → Se algo der errado, todas as mudanças são desfeitas (como se nunca tivessem acontecido).
    public User create(User userToCreate) {
        ofNullable(userToCreate).orElseThrow(() -> new BusinessException("User to create must not be null.")); // ofNullable → verifica se o objeto userToCreate existe (se tem algo). Se não existir (ou seja, estiver nulo), lança uma exceção BusinessException com a mensagem "User to create must not be null.".
        ofNullable(userToCreate.getAccount()).orElseThrow(() -> new BusinessException("User account must not be null."));
        ofNullable(userToCreate.getCard()).orElseThrow(() -> new BusinessException("User card must not be null."));

        this.validateChangeableId(userToCreate.getId(), "created");
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new BusinessException("This account number already exists.");
        }
        if (userRepository.existsByCardNumber(userToCreate.getCard().getNumber())) {
            throw new BusinessException("This card number already exists.");
        }
        return this.userRepository.save(userToCreate);
    }

    @Transactional
    public User update(Long id, User userToUpdate) {
        this.validateChangeableId(id, "updated");
        User dbUser = this.findById(id); // todo → quem é this?
        if (!dbUser.getId().equals(userToUpdate.getId())) {
            throw new BusinessException("Update IDs must be the same.");
        }

        dbUser.setName(userToUpdate.getName());
        dbUser.setAccount(userToUpdate.getAccount());
        dbUser.setCard(userToUpdate.getCard());
        dbUser.setFeatures(userToUpdate.getFeatures());
        dbUser.setNews(userToUpdate.getNews());

        return this.userRepository.save(dbUser);
    }

    @Transactional
    public void delete(Long id) {
        this.validateChangeableId(id, "deleted");
        User dbUser = this.findById(id);
        this.userRepository.delete(dbUser);
    }

    private void validateChangeableId(Long id, String operation) {
        if (UNCHANGEABLE_USER_ID.equals(id)) {
            throw new BusinessException("User with ID %d can not be %s.".formatted(UNCHANGEABLE_USER_ID, operation));
        }
    }
}

