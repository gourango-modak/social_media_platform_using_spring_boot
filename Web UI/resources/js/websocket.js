

function getAllActiveUserSuccessful(response) {
    $("#active_user_lists").html("");
    response["result"].forEach(function(res){
      let htmlBuilder = '<div class="active_user d-flex border-bottom align-items-center my-2 p-2">\
                        <i class="fas fa-circle"></i>\
                        <p class="ml-2 mb-0">'+res["name"]+'</p>\
                      </div>';
      $("#active_user_lists").append(htmlBuilder);

    });
    
}

function getAllActiveUserFailed(response) {
    // alert("Error");
    $(".alert").show();
}


function getAllActiveUsers() {
    sendRequest("http://localhost:3333/active_users", "GET", null, getAllActiveUserSuccessful, getAllActiveUserFailed, false);
}


var stompClient = null;

  function connect() {
      var socket = new SockJS('http://localhost:3333/stomp-endpoint');
      stompClient = Stomp.over(socket);
      stompClient.connect({}, function (frame) {
          stompClient.subscribe('/topic/login-notify', function (greeting) {
              let message = JSON.parse(greeting.body);
              if(message["name"] === "refresh_active_user") {
                    getAllActiveUsers();
                    getAllActiveUsers();
              }
          });
      });
  }

  function disconnect() {
      if (stompClient !== null) {
          stompClient.disconnect();
      }
      console.log("Disconnected");
  }

  function sendLoginUserNotification() {
      stompClient.send("/app/login-user-notify", {});
  }

  connect();