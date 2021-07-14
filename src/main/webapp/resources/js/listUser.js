$(document).ready(function() {
    let user = {};
    let logoutText = $("#logout_text");
    let logoutSpinner = $("#logout_spinner");
    let updateText = $("#update_text");
    let updateSpinner = $("#update_spinner");
    let addText = $("#add_text");
    let addSpinner = $("#add_spinner");
    let deleteText = $('#delete_text');
    let deleteSpinner = $("#delete_spinner");
    let userToDelete = 0;
    logoutSpinner.hide();
    deleteSpinner.hide();
    addSpinner.hide();
    updateSpinner.hide();
    $(document).on('click', ".btn-info", function() {
        deleteText.hide();
        deleteSpinner.show();
        $.ajax({
            url:"/api/v1/users/delete/" + userToDelete,
            type: "DELETE",
            success : function(){
                deleteText.show();
                deleteSpinner.hide();
                location.reload();
            },
            error : function(response){
                console.log(response.responseJSON)
            },
        })
    });

    $(".btn-danger").click(function (e){
        userToDelete = e.target.value;
    })

    $(document).on('click', ".btn-warning", function(event){
        $.ajax({
            url:"/api/v1/users/findUserById/" + event.target.value,
            type:"GET",
            success : function (response){
                user = response;
                $("#name").val(response.name);
                $("#surname").val(response.surname);
                $("#email").val(response.email);
                $("#phoneNumber").val(response.phoneNumber);
                $("#birthDate").val(response.birthDate.substring(0,10));
            },
            error : function (response){
                console.log(response);
            }
        })
    });

    let errors = $('#errors');
    errors.hide();
    $(document).on('click', ".btn-outline-warning", function() {
        errors.hide();
        errors.html("");
        updateSpinner.show();
        updateText.hide();
        if(validateForm()){
            console.log("worked");
            $.ajax({
                url: "api/v1/users/update",
                type: "PUT",
                contentType: "application/json",
                datatype: "JSON",
                data: JSON.stringify({
                    id:user.id,
                    name:$('#name').val(),
                    surname:$('#surname').val(),
                    email:$('#email').val(),
                    phoneNumber:$('#phoneNumber').val(),
                    birthDate:$('#birthDate').val(),
                    password:user.password,
                    createDate:user.createDate,
                    role:user.role
                }),
                success : function(){
                    updateSpinner.show();
                    updateText.hide();
                    $('#updateModal').toggle('hide');
                    location.reload();
                },
                error : function (response){
                    let json = response.responseJSON;
                    let errorInfo = "<b>Unable to update!</b><br/><ul>";
                    errorInfo += errorInfoHandler(json);
                    errors.html(errorInfo);
                    errors.show();
                }
            });
        } else {
            errors.html("All fields are required.");
            errors.show();
        }
    });

    let user_error = $('#user_error');
    user_error.hide();

    $("#logout").click(function (){
        logoutSpinner.show();
        logoutText.hide();
        $.ajax({
            url : "logout",
            type : "GET",
            success : function (){
                logoutSpinner.hide();
                logoutText.show();
                window.location.replace("/login");
            }
        });
    });

    $("#add_user").click(function() {
        user_error.hide();
        user_error.html("");
        addSpinner.show();
        addText.hide();
        if (validateAddUserForm()) {
            $.ajax({
                url: "api/v1/users/addUser",
                type: "POST",
                contentType: "application/json",
                datatype: "JSON",
                data: JSON.stringify({
                    name: $('#user_name').val(),
                    surname: $('#user_surname').val(),
                    email: $('#user_email').val(),
                    password: $('#user_password').val(),
                    phoneNumber: $('#user_phoneNumber').val(),
                    birthDate: $('#user_birthDate').val(),
                    role: 'USER'
                }),
                success: function () {
                    addSpinner.hide();
                    addText.show();
                    location.reload();
                },
                error: function (response) {
                    addSpinner.hide();
                    addText.show();
                    let json = response.responseJSON;
                    let errorInfo = "<b>Unable to add!</b><br/><ul>";
                    errorInfo += errorInfoHandler(json);
                    user_error.html(errorInfo);
                    user_error.show();
                }
            });
        } else {
            user_error.html("All fields are required.");
            user_error.show();
        }
    });

    function validateForm() {
        let isValid = true;
        if($('#name').val() === ''){
            isValid = false;
        }
        if($('#email').val() === ''){
            isValid = false;
        }
        if($('#password').val() === ''){
            isValid = false;
        }
        if($('#surname').val() === ''){
            isValid = false;
        }
        if($('#birthDate').val() === ''){
            isValid = false;
        }
        if($('#phoneNumber').val() === ''){
            isValid = false;
        }
        return isValid;
    }

    function validateAddUserForm() {
        let isValid = true;
        if($('#user_name').val() === ''){
            isValid = false;
        }
        if($('#user_email').val() === ''){
            isValid = false;
        }
        if($('#user_password').val() === ''){
            isValid = false;
        }
        if($('#user_surname').val() === ''){
            isValid = false;
        }
        if($('#user_birthDate').val() === ''){
            isValid = false;
        }
        if($('#user_phoneNumber').val() === ''){
            isValid = false;
        }
        return isValid;
    }

    function errorInfoHandler(json){
        let errorInfo = "";
        if(json.name !== undefined ){
            errorInfo += "<li><b> Name </b>: " + json.name + "</li>"
        }
        if(json.surname !== undefined ){
            errorInfo += "<li><b> Surname </b>: " + json.surname + "</li>"
        }
        if(json.email !== undefined){
            errorInfo += "<li><b> Email </b>: " + json.email + "</li>"
        }
        if(json.password !== undefined){
            errorInfo += "<li><b> Password </b>: " + json.password + "</li>"
        }
        if(json.phoneNumber !== undefined){
            errorInfo += "<li><b> Phone Number </b>: " + json.phoneNumber + "</li>"
        }
        if(json.birthDate !== undefined){
            errorInfo += "<li><b> Birth Date </b>: " + json.birthDate + "</li>"
        }
        errorInfo += "</ul>";
        return errorInfo;
    }
});