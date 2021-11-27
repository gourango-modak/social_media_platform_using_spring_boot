$(document).ready(function () {
  $("#login_form").submit(function (event) {
    /* stop form from submitting normally */
    event.preventDefault();

    let email = $("#email").val();
    let password = $("#password").val();
    const data = {
      email: email,
      password: password,
    };

    $.ajax({
      url: "http://127.0.0.1:3033/auth",
      contentType: "application/json",
      dataType: "json",
      type: "POST",
      data: JSON.stringify(data),
      success: function (result) {
        window.location.replace(
          "http://127.0.0.1:5500/src/main/resources/static/HTML/index.html"
        );
        setCookie("userId", result["userid"], 1);
      },
      error: function () {
        alert("Enter Correct Mail & Password!!");
        $("#email").val("");
        $("#password").val("");
      },
    });
  });
});
