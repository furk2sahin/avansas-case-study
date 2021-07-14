$(document).ready(function(){
    let dangerAlert = $('#dangerAlert');
    let successAlert = $('#successAlert');
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
            $.ajax({
                url:"http://localhost:8080/login",
                type:"POST",
                data: {
                username:$("#username").val(),
                password:$("#password").val()
                },
                success : function(data, textStatus, xhr){
                    if(xhr.responseText === ""){
                        successAlert.show();
                        setTimeout(function(){window.location.replace("/listUsers"); }, 2000);
                    } else {
                        dangerAlert.html("Username or password incorrect.");
                        dangerAlert.show();
                    }
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