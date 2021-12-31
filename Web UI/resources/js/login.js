$(document).ready(function () {

  if($.cookie("access-token")) {
    window.location.href = "/templates/index.html";
  }

  function addLoginUserSuccessful(response) {
    console.log(response);
  }

  function addLoginUserFailed(response) {
    console.log(response);
  }

  $("#login_form").submit(function (event) {
    event.preventDefault();

    let email = $("#email").val();
    let password = $("#password").val();
    const data = {
      email: email,
      password: password,
    };

    function loginSuccessful(response) {
      $.cookie("access-token",response["result"]["access-token"]);
      $.cookie("refresh-token",response["result"]["refresh-token"]);
      sendRequest("http://localhost:3333/add_acitve_user", "GET", null, addLoginUserSuccessful, addLoginUserFailed, true);
      sendLoginUserNotification();
      window.location.href = "/templates/index.html";
    }

    function loginFailed(response) {
      
      if(!(response))
        $(".alert").show();
      else $(".alert").hide();
    }

    sendRequest("http://localhost:3333/login_user", "POST", data, loginSuccessful, loginFailed, false);
  });
});
