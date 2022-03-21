package com.example.api.curso.controller;

import com.example.api.curso.domain.User;
import com.example.api.curso.domain.UserRequest;
import com.example.api.curso.util.Constant;
import com.example.api.curso.util.CryptPassword;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final CryptPassword api = new CryptPassword();

    @GetMapping("")
    public ResponseEntity<List<User>> getRoles(
            @RequestHeader(value = "Authorization", required = false) String authorization,
            @RequestParam(value = "name", required = false) String name) {
        String token = authorization.split("\"")[1];
        if (api.md5(Constant.TOKEN) != null && api.md5(Constant.TOKEN).equals(token)) {

            if (name == null) {
                return ResponseEntity.ok(Constant.users);
            } else {
                return ResponseEntity.ok(Constant.users.stream()
                        .filter(user -> user.getNombres().toLowerCase().contains(name.toLowerCase()))
                        .collect(java.util.stream.Collectors.toList()));
            }
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(
            @RequestHeader(value = "Authorization", required = false) String authorization,
            @PathVariable("id") String identificacion) {
        String token = authorization.split("\"")[1];
        if (api.md5(Constant.TOKEN) != null && api.md5(Constant.TOKEN).equals(token)) {
            return ResponseEntity.ok(Constant.users.stream()
                    .filter(user -> user.getIdentificacion().equals(identificacion))
                    .findFirst()
                    .orElse(null));
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("")
    public ResponseEntity createUser(
            @RequestHeader(value = "Authorization", required = false) String authorization,
            @RequestBody UserRequest user) {
        String token = authorization.split("\"")[1];
        if (api.md5(Constant.TOKEN) != null && api.md5(Constant.TOKEN).equals(token)) {
            User newUser = new User().fromUserRequest(user);
            Constant.users.add(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(
            @RequestHeader(value = "Authorization", required = false) String authorization,
            @PathVariable("id") String identificacion,
            @RequestBody UserRequest user) {
        String token = authorization.split("\"")[1];
        if (api.md5(Constant.TOKEN) != null && api.md5(Constant.TOKEN).equals(token)) {
            Constant.users.removeIf(u -> u.getIdentificacion().equals(identificacion));
            User newUser = new User().fromUserRequest(user);
            Constant.users.add(newUser);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(
            @RequestHeader(value = "Authorization", required = false) String authorization,
            @PathVariable("id") String identificacion) {
        String token = authorization.split("\"")[1];
        if (api.md5(Constant.TOKEN) != null && api.md5(Constant.TOKEN).equals(token)) {
            Constant.users.removeIf(u -> u.getIdentificacion().equals(identificacion));
            boolean deleted = Constant.users.stream().anyMatch(u -> u.getIdentificacion().equals(identificacion));
            return new ResponseEntity<>(!deleted, HttpStatus.OK);
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @GetMapping("/exist-by-identificacion/{id}")
    public ResponseEntity<Boolean> existUserByIdentificacion(
            @RequestHeader(value = "Authorization", required = false) String authorization,
            @PathVariable("id") String identificacion) {
        String token = authorization.split("\"")[1];
        if (api.md5(Constant.TOKEN) != null && api.md5(Constant.TOKEN).equals(token)) {
            boolean exist = Constant.users.stream().anyMatch(u -> u.getIdentificacion().equals(identificacion));
            return new ResponseEntity<>(exist, HttpStatus.OK);
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @GetMapping("/exist-by-email/{id}")
    public ResponseEntity<Boolean> existUserByEmail(
            @RequestHeader(value = "Authorization", required = false) String authorization,
            @PathVariable("id") String email) {
        String token = authorization.split("\"")[1];
        if (api.md5(Constant.TOKEN) != null && api.md5(Constant.TOKEN).equals(token)) {
            boolean exist = Constant.users.stream().anyMatch(u -> u.getEmail().equals(email));
            return new ResponseEntity<>(exist, HttpStatus.OK);
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @GetMapping("/exist-by-username/{id}")
    public ResponseEntity<Boolean> existUserByUsername(
            @RequestHeader(value = "Authorization", required = false) String authorization,
            @PathVariable("id") String username) {
        String token = authorization.split("\"")[1];
        if (api.md5(Constant.TOKEN) != null && api.md5(Constant.TOKEN).equals(token)) {
            boolean exist = Constant.users.stream().anyMatch(u -> u.getUsername().equals(username));
            return new ResponseEntity<>(exist, HttpStatus.OK);
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}
