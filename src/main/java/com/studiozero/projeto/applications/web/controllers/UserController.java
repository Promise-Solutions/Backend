//package com.studiozero.projeto.applications.web.controllers;
//
//import com.studiozero.projeto.domain.services.UserService;
//import com.studiozero.projeto.applications.web.dtos.request.UserCreateRequestDTO;
//import com.studiozero.projeto.applications.web.dtos.request.UserDeleteRequestDTO;
//import com.studiozero.projeto.applications.web.dtos.request.UserReadRequestDTO;
//import com.studiozero.projeto.applications.web.dtos.request.UserUpdateRequestDTO;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import io.swagger.v3.oas.annotations.Operation;
//import jakarta.validation.Valid;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/users")
//@Tag(name = "Users", description = "Endpoints for User Management")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/create")
//    @Operation(
//            summary = "Create user",
//            description = "This method is responsible for create the user to the repository"
//    )
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<User> create(
//            @Valid
//            @RequestBody
//            UserCreateRequestDTO request
//    ) {
//        return ResponseEntity.ok(userService.save(request));
//    }
//
//    @GetMapping()
//    @Operation(
//            summary = "Search all users",
//            description = "This method is responsible for searching the all users in the repository"
//    )
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<List<User>> searchAll() {
//        return ResponseEntity.ok(userService.searchAll());
//    }
//
//    @PostMapping("/search")
//    @Operation(
//            summary = "Search user",
//            description = "This method is responsible for searching the user in the repository"
//    )
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<Optional<User>> search(
//            @Valid
//            @RequestBody
//            UserReadRequestDTO request
//    ) {
//        return ResponseEntity.ok(userService.read(request));
//    }
//
//    @PutMapping("/update")
//    @Operation(
//            summary = "Update user",
//            description = "This method is responsible for updating the user in the repository"
//    )
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<User> update(
//            @Valid
//            @RequestBody
//            UserUpdateRequestDTO request
//    ) {
//        return ResponseEntity.ok(userService.update(request));
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(
//            summary = "Delete user",
//            description = "This method is responsible for deleting the user from the repository"
//    )
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<Optional<User>> delete(
//            @Valid
//            @RequestBody
//            UserDeleteRequestDTO request
//    ) {
//        return ResponseEntity.ok(userService.delete(request));
//    }
//}