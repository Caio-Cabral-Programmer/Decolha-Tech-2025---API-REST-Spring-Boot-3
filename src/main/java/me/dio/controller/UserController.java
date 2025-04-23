package me.dio.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.dto.UserDto;
import me.dio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin // Esta anotação permite que o frontend possa acessar o backend sem problemas de segurança. Permite que sites diferentes possam usar nossa API. É como dizer "qualquer um pode pedir um lanche aqui!"
@RestController // Esta anotação indica que este é um controlador RESTful Spring. Significa que ele é um controlador que pode ser acessado através de uma URL que termina com /users. Diz ao Spring que esta classe vai receber pedidos pela internet e devolver respostas.
@RequestMapping("/users") // Esta anotação indica que este é um endpoint RESTful Spring que aceita requisições HTTP GET e POST. Ele faz com que o frontend possa acessar este endpoint através de uma URL que termina com /users. Define o endereço da nossa API. Se nosso site for www.meusite.com, os pedidos serão feitos para www.meusite.com/users.
@Tag(name = "Users Controller", description = "RESTful API for managing users.") // Esta anotação define o nome do controller e a descrição do controller. Adiciona informação para a documentação da API (usando Swagger).
public record UserController(UserService userService) {

    @GetMapping // Este method é um endpoint GET que aceita requisições HTTP GET. Ele faz com que o frontend possa acessar este endpoint através de uma URL que termina com /users. Define o endereço da nossa API. Se nosso site for www.meusite.com, os pedidos serão feitos para www.meusite.com/users.
    @Operation(summary = "Get all users", description = "Retrieve a list of all registered users") // Esta anotação define a descrição do method. Adiciona informação para a documentação da API (usando Swagger).
    @ApiResponses(value = { // Este trecho define as respostas que o endpoint pode retornar. Adiciona informação para a documentação da API (usando Swagger).
            @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    public ResponseEntity<List<UserDto>> findAll() {
        var users = userService.findAll();
        var usersDto = users.stream().map(UserDto::new).collect(Collectors.toList()); // .stream() → abre um fluxo para manipulação | .map(UserDto::new) → Transforma cada objeto User em um objeto UserDto. | collect(Collectors.toList()): coleta os objetos UserDto criados e os transforma em uma lista. | Method Reference (UserDto::new): É um atalho para chamar o construtor de UserDto que aceita um objeto User como parâmetro.
        return ResponseEntity.ok(usersDto); // ResponseEntity.ok(usersDto); // Retorna status 200 (OK) com a lista de usuários em formato JSON (pois s lista passará pelo Jackson para converter cada objeto User em um objeto JSON).
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by ID", description = "Retrieve a specific user based on its ID")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserDto> findById(@PathVariable Long id) { // @PathVariable Long id: Pega o número da URL e coloca na variável id. Busca o usuário com aquele ID e retorna o usuário em formato de DTO (UserDto).
        var user = userService.findById(id);
        return ResponseEntity.ok(new UserDto(user));
    }

    @PostMapping // @PostMapping: Responde a pedidos HTTP POST para /users.
    @Operation(summary = "Create a new user", description = "Create a new user and return the created user's data. OBS.: Para realizar o create (POST), é necessário apagar do JSON todos os IDs, pois o DB irá gerar automaticamente.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid user data provided")
    })
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) { // @RequestBody UserDto userDto: Pega os dados JSON enviados e transforma em um objeto UserDto.
        var user = userService.create(userDto.toModel()); // Transforma o UserDto em um objeto User e chama o method create do serviço para criar o usuário no banco de dados. O method .toModel faz o seguinte: - Pega os dados do UserDto e transforma em um objeto User. - Chama o method create do serviço para criar o usuário no banco de dados. Salva o novo objeto User no banco de dados. - Retorna o novo objeto User em formato de DTO (UserDto).
        URI location = ServletUriComponentsBuilder.fromCurrentRequest() // Cria uma URI com base na requisição atual. Isso é usado para gerar a URL de resposta.
                .path("/{id}") // Adiciona o ID do usuário na URL. Isso é usado para gerar a URL de resposta.
                .buildAndExpand(user.getId()) // Este trecho faz um expand do URI com base no ID do usuário. Isso é usado para gerar a URL de resposta.
                .toUri(); // .toUri() faz uma nova URI com base na requisição atual e adiciona o ID do usuário na URL. Isso é usado para gerar a URL de resposta.
        return ResponseEntity.created(location).body(new UserDto(user)); // Retorna status 201 (Created) com a localização do novo recurso. Retorna o novo objeto User em formato de DTO (UserDto) e a URL de resposta.
    }

    @PutMapping("/{id}") // @PutMapping("/{id}"): Responde a pedidos HTTP PUT para /users/1, /users/2, etc.
    @Operation(summary = "Update a user", description = "Update the data of an existing user based on its ID. OBS.: Para fazer o update, é necessário enviar, no JSON que vai para a API, os IDs do usário (user), conta (account) e cartão (card).")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "User updated successfully"), // todo → raio-x deste bloco.
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "422", description = "Invalid user data provided")
    })
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto userDto) {
        var user = userService.update(id, userDto.toModel());
        return ResponseEntity.ok(new UserDto(user));
    }

    @DeleteMapping("/{id}") // @DeleteMapping("/{id}"): Responde a pedidos HTTP DELETE para /users/1, /users/2, etc.
    @Operation(summary = "Delete a user", description = "Delete an existing user based on its ID")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build(); // Retorna status 204 (No Content) sem corpo (significa “deu certo, mas não tenho nada para te mostrar”). Isso é usado para indicar que o recurso foi deletado com sucesso. Não retorna o novo objeto User em formato de DTO (UserDto) e a URL de resposta.
    }
}
