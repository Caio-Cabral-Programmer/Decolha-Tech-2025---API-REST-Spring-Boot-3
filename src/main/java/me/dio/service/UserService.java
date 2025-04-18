package me.dio.service;

import me.dio.model.User;

public interface UserService extends CrudService<Long, User> {

}

/*
- Define o contrato específico para operações relacionadas a usuários
- Herda os métodos básicos de CRUD da interface `CrudService`
- Pode adicionar métodos específicos para a entidade User (embora neste caso não tenha)

### Como Funciona:

- Estende `CrudService` especificando que trabalhará com `User` e `Long` (ID)
- Serve como uma camada de abstração entre o controller e a implementação concreta
 */