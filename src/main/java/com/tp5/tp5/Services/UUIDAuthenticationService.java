package com.tp5.tp5.Services;


import com.tp5.tp5.Services.UserAuthenticationService;
import com.tp5.tp5.Services.UserCrudService;
import com.tp5.tp5.Models.User;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
final class UUIDAuthenticationService implements UserAuthenticationService {
    @NonNull
    UserCrudService users;

    @Override
    public Optional<String> login(final String username, final String password) {
        final String uuid = UUID.randomUUID().toString();
        final User user = User
                .builder()
                .id(uuid)
                .username(username) /**Temp Info : Llama al constructor de usuarios y crea al usuario con los datos.*/
                .password(password)
                .build();

        users.saveUser(user);
        return Optional.of(uuid);
    }

    @Override
    public Optional<User> findByToken(final String token) {
        return users.find(token);
    }

    @Override
    public void logout(final User user) {

        /**Temp Info : Supongo que le retirara el Token y nada mas, cosa de no poder acceder a los metodos no publicos.*/
    }
}
