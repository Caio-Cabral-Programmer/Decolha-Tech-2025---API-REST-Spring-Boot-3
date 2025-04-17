package me.dio.domain.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass // Esta anotação indica que esta classe é uma superclasse que pode ser usada como uma superclasse para outras classes. Portanto, ela é marcada com a anotação @MappedSuperclass. Ela é necessária para que as classes que herdam dela possam ser persistidas no banco de dados. No banco de dados, essa anotação especifica que o campo "id" será a chave primária da tabela "tb_base_item" e que o campo "icon" e "description" serão colunas da tabela "tb_base_item". No entanto, a classe BaseItem não tem um campo de chave primária, mas ela pode ser usada como uma superclasse para outras classes que herdam dela. Portanto, ela é marcada com a anotação @MappedSuperclass.
public abstract class BaseItem { // Esta classe serve de base para outras classes que herdam de ela. Ela não tem um campo de chave primária, mas ela pode ser usada como uma superclasse para outras classes que herdam dela. Portanto, ela é marcada com a anotação @MappedSuperclass.
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String icon;
    
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
