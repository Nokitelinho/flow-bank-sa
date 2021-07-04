package com.flowbank.user.core.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Size(min = 2, message = "username must have a minimum of characters")
    private String username;
    @Size(min = 7, message = "password must have a minimum of characters")
    private String password;
    @NotNull(message = "specify at least 1 user role")
    private List<Role> role;
}
