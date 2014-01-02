$(document).ready(function() {
    
        $("#registrationForm input").keypress(function(event) {
            if (event.which === 13) {
                createAcountAction();
            }
        });
        
        $("#authorizationForm input").keypress(function(event) {
            if (event.which === 13) {
                submitAuthForm();
            }
        });

	$(".cancelModal").click(function() {
		$.modal.close();
	});

	$("form").jqTransform();
        
        $("#register").click(function() {
            createAcountAction();
        });
});

function createAccount() {
	$("form").jqTransform();
	$("#registrationDiv").modal({
		opacity: "60",
		overlayCss: {backgroundColor:"#000000"}
	});
}

function authorization() {
    $("form").jqTransform();
    $("#authorizationDiv").modal({
            opacity: "60",
            overlayCss: {backgroundColor:"#000000"}
    });
}

function createAcountAction() {
    var userLogin = $("#newUserLogin").val();
    var userPassword = $("#newUserPassword").val();
    var userPasswordRepeat = $("#newUserPasswordRepeat").val();
    
    $.ajax({
        type: "POST",
        url: "/createuser/",
        data: {
            userLogin: userLogin,
            userPassword: userPassword,
            userPasswordRepeat: userPasswordRepeat
        },
        success: function(response) {
            
            $("#loginErrors").empty();
            $("#passwordErrors").empty();
            $("#passwordRepeatErrors").empty();
            
            if (!response.success) {
                for (var i=0; i<response.loginErrors.length; i++) {
                    $("#loginErrors").append("<li>"+response.loginErrors[i]+"</li>");
                }
                
                for (var i=0; i<response.passwordErrors.length; i++) {
                    $("#passwordErrors").append("<li>"+response.passwordErrors[i]+"</li>");
                }
                
                for (var i=0; i<response.passwordRepeatErrors.length; i++) {
                    $("#passwordRepeatErrors").append("<li>"+response.passwordRepeatErrors[i]+"</li>");
                }
            } else {
                $("#registrationForm").html($("#successfullRegistration"));
                $("#successfullRegistration").css("display", "block");
            }
        }
    });
    
    $("#successfullRegistration .cancelModal").click(function() {
        location.reload();
    });
    
}

function submitAuthForm() {
    $("#authForm").submit();
}
