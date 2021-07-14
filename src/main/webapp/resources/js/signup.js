$(document).ready(function() {
    let errors = $('#errors');
    let success = $('#success');
    errors.hide();
    success.hide();
    $("#add_user").click(function() {
        errors.hide();
        errors.html("");
        if(validateForm()){
            $.ajax({
                url: "api/v1/users/addUser",
                type: "POST",
                contentType: "application/json",
                datatype: "JSON",
                data: JSON.stringify({
                    name:$('#name').val(),
                    surname:$('#surname').val(),
                    email:$('#email').val(),
                    password:$('#password').val(),
                    phoneNumber:$('#phoneNumber').val(),
                    birthDate:$('#birthDate').val(),
                    role:'USER'
                }),
                success : function(){
                    success.show();
                    success.html("Successfully registered. You'll be redirect to login page.");
                    setTimeout(function(){window.location.replace("/login"); }, 2000);
                },
                error : function (response){
                    let json = response.responseJSON;
                    let errorInfo = "<b>Unable to register!</b><br/><ul>";
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
                    errorInfo+="</ul>";
                    errors.html(errorInfo);
                    errors.show();
                }
            });
        } else {
            errors.html("All fields are required.");
            errors.show();
        }
    });

    function validateForm() {
        let isValid = true;
        $('.form-control').each(function() {
            if ( $(this).val() === '' ){
                isValid = false;
            }
        });
        return isValid;
    }
});