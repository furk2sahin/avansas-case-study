package com.avansas.avansascase.dto;

import com.avansas.avansascase.security.UserRole;

import javax.validation.constraints.*;
import java.util.Date;

public class UserDto {

    @Min(value = 1, message = "Id cannot be less then 1.")
    private Long id;

    @NotBlank(message = "Name can't be empty.")
    @Size(min = 3, max = 50, message = "Name length should be between 3 and 50.")
    private String name;

    @NotBlank(message = "Surname can't be empty.")
    @Size(min = 3, max = 50, message = "Surname length should be between 3 and 50.")
    private String surname;

    @NotBlank(message = "Email can't be empty.")
    @Email(message = "Wrong email format")
    private String email;

    @NotBlank(message = "Password can't be empty.")
    @Size(min=6, max = 50, message = "Password length should be between 6 and 50")
    private String password;

    @NotBlank(message = "Phone number can't be empty.")
    @Pattern(regexp = "^(0[25])([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$",
            message = "Wrong phone number format. Examples 0(2 or 5)xx xxx xx xx, 0(2 or 5)xxxxxxxxx")
    private String phoneNumber;

    @PastOrPresent(message = "Birth date should be past or present.")
    private Date birthDate;

    private Date createDate;

    private UserRole role;

    public UserDto() {
        // No args constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}