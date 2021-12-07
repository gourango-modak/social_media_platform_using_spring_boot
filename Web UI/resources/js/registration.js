$(document).ready(function () {
  
  $("#reg_form").submit(function (event) {
    /* stop form from submitting normally */
    event.preventDefault();

    let name = $("#name").val();
    let username = $("#username").val();
    let password = $("#password").val();
    let re_password = $("#retype_password").val();
    let emailAddress = $("#email").val();

    if (re_password == password) {
      $("#passwordHelp").text("");
      const data = {
        name: name,
        email: emailAddress,
        username: username,
        password: password,
      };

      function registrationSuccess() {
        window.location.href = "/templates/login.html";
      }

      function registrationFail(result) {
        let userNameError = result["error"]["username"];
        let emailError = result["error"]["email"];
        if(userNameError)
          $("#usernameHelp").text(userNameError);
        else $("#usernameHelp").text("");
        if(emailError)
          $("#emailHelp").text(emailError);
        else $("#emailHelp").text("");
      }

      sendRequest("http://localhost:3333/add_user",
      "POST", data, registrationSuccess, registrationFail, false);


    } else {
      $("#passwordHelp").text("password doesn't match.");
      $("#password").val("");
      $("#retype_password").val("");
    }
  });
});