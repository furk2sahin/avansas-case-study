# Avansas Case Study

Basic user management system with Spring Boot & JSP.

#### Used Technologies

----

- Spring Data JPA
- Spring Web
- PostgreSQL
- Spring Security
- Hibernate Validation
- Swagger UI
- Mapstruct
- Guava
- JSTL
- JQuery
- Bootstrap

----



#### SQL Code to create default admin

``````
INSERT INTO 
users(id, name, surname, email, password, phone_number, birth_date, role, create_date)
values (1, 'Furkan', 'ŞAHİN', 'avansas@case',
        '$2a$10$v2TMvk59ePmDT3f4v48jcuK8CZCc3wUkBeY/YedNrw8Vf.kaOuwty',
        '05123154256', '1995-07-12T13:17:09.913+00:00', 1, current_timestamp);

INSERT INTO roles(authority, user_id) VALUES ('ROLE_ADMIN', 1);
``````

**Default Username : **avansas@case

**Default Password : **merhaba



----



#### Screenshots

----

**Login Page**

![login](output/loginpage.png)



**Register Page**

![Register Page](output/registerpage.png)



**User List Page (User)**

![User List Page User](output/userlist_user.png)



**User List Page (Admin)**

![User List Page Admin](output/userlist_admin.png)



**Add User Modal (Admin)**

![Add User Modal Admin](output/adduser_modal.png)



**User Update Modal (Admin)**

![User Update Modal Admin](output/update_modal.png)



**User Delete Modal (Admin)**

![User Delete Modal Admin](output/delete_modal.png)