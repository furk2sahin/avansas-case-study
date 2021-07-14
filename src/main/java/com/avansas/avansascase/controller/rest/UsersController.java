package com.avansas.avansascase.controller.rest;

import com.avansas.avansascase.dto.UserDto;
import com.avansas.avansascase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserDto> addNewUser(@RequestBody @Valid UserDto userDto){
        UserDto user = userService.addNewUser(userDto);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Object> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        if(users.isEmpty()){
            return ResponseEntity.badRequest().body("No user were found.");
        } else {
            return ResponseEntity.ok(users);
        }
    }

    @GetMapping("/findUserById/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Object> findUserById(@PathVariable("id") Long id){
        UserDto user = userService.findUserById(id);
        if(user == null){
            return ResponseEntity.badRequest().body("No user were found with given id: " + id);
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDto){
        return ResponseEntity.ok(userService.updateUser(userDto));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted successfully with given id: " + id);
    }
}