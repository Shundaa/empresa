package com.qa.app.dao.response;

import com.qa.app.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDao {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;

    private List<ClassRegistrationDao> registrations = new ArrayList<>();
    private Long bug;
    private Long coin;
    private Long refill;
    private Long victory;
    private Long xp;
    private Long level;
}
