$(document).ready(function(){
    let dangerAlert = $('#dangerAlert');
    let successAlert = $('#successAlert');
    let spinner = $('#spinner');
    let loginText = $('#login_text');
    spinner.hide();
    dangerAlert.hide();
    successAlert.hide();
    $('#submit').click(function (event){
        event.preventDefault();
        let formValid = validateForm();

        if(!formValid){
            dangerAlert.html("All fields are required.");
            dangerAlert.show();
        } else {
            dangerAlert.hide();
            spinner.show();
            loginText.hide();
            $.ajax({
                url:"http://localhost:8080/login",
                type:"POST",
                data: {
                username:$("#username").val(),
                password:$("#password").val()
                },
                success : function(){
                    spinner.hide();
                    loginText.show();
                    window.location.replace("/listUsers");
                },
                error : function() {
                    dangerAlert.html("Username or password incorrect.");
                    dangerAlert.show();
                    spinner.hide();
                    loginText.show();
                }
            });
        }
    })

    function validateForm() {
        let isValid = true;
        $('.form-control').each(function() {
            if ( $(this).val() === '' ){
                isValid = false;
            }
        });
        return isValid;
    }
})