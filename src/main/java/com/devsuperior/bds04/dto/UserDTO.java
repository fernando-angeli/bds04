package com.devsuperior.bds04.dto;

import com.devsuperior.bds04.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @Email(message = "E-mail válido obrigatório.")
    private String email;

    Set<RoleDTO> roles = new HashSet<>();

    public UserDTO(User entity){
        id = entity.getId();
        email = entity.getEmail();
        entity.getRoles().forEach(role -> roles.add(new RoleDTO(role)));
    }

}
