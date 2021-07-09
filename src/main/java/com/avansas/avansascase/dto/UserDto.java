package com.avansas.avansascase.dto;

import javax.validation.constraints.*;

public class UserDto {

    @Min(1)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50, message = "Name length should be between 3 and 50.")
    private String name;

    @NotBlank
    @Size(min = 3, max = 50, message = "Surname length should be between 3 and 50.")
    private String surname;

    @NotBlank
    @Email(message = "Wrong email format")
    private String email;

    @NotBlank
    @Size(min=6, max = 50, message = "Password length should be between 6 and 50")
    private String password;

    @NotBlank
    @Size(min=6, max = 50, message = "Password length should be between 6 and 50")
    @Pattern(regexp = "^(0[25])([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$",
            message = "Wrong phone number format. Examples 0(2 or 5)xx xxx xx xx, 0(2 or 5)xxxxxxxxx")
    private String phoneNumber;

    @NotBlank
    @Size(min=6, max = 50, message = "Password length should be between 6 and 50")
    @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(-)(((0)[0-9])|((1)[0-2]))(-)\\d{4}$",
            message = "Birth date format should be dd-mm-yyyy")
    private String birthDate;

    public UserDto() {
    }

    public UserDto(Long id,
                   String name,
                   String surname,
                   String email,
                   String password,
                   String phoneNumber,
                   String birthDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}