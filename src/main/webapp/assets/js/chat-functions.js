$(document).ready(function() {
    $("#write input").keypress(function(event) {
        if (event.which === 13) {
            sendMessage();
            $("#write input").val("");
        }
    });
    
    function scrollUsers() {
        $("#users").slimScroll({
            height: "570px",
            alwaysVisible: true,
            position: "right",
            color: "#B8B8B8",
            width: "250px"
        });
    }
    
    scrollMessages();
    scrollUsers();
    

    $("#write input").focus();

});

function sendMessage() {
    var message = $("#write input").val();
    $.ajax({
        url: "/sendmessage",
        type: "POST",
        data: {
            message: message
        },
        success: function() {
            if (message == "/exit") {
                location.href="/";
            }
        }
    });
}

function scrollMessages() {
    var scrollToValue = $("#messages").prop("scrollHeight") + "px";
    $("#messages").slimScroll({
        height: "570px",
        width: "750px",
        alwaysVisible: true,
        position: "left",
        scrollTo: scrollToValue,
        color: "#B8B8B8"
    });
}

function getMessages() {
    $.ajax({
        url: "/getmessages",
        type: "POST",
        success: function(response) {
            for (msgId in response) {
                message = response[msgId];
                
                var stringToAppend = "";
        
                stringToAppend += "<div class=\"message\">";
                stringToAppend += "<span class=\"time\">"+message.time+"</span>";
                stringToAppend += "<span class=\"nick\">"+message.author+":</span>";
                stringToAppend += "<span class=\"msg\">"+message.message+"</span>";
                stringToAppend += "</div>";
                
                $("#messages").append(stringToAppend);
                scrollMessages();
            }
            activateUser();
        }
    });
}

function activateUser() {
    $.ajax({
        url: "/activateuser",
        type: "POST",
        success: function(response) {
            $("#users ul").empty();
            for (var i=0; i<response.length; i++) {
                $("#users ul").append("<li>" + response[i] + "</li>");
            }
            setTimeout(getMessages, 1000);
        }
    });
}

activateUser();