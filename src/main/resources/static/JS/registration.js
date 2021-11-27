$(document).ready(function () {
  $("#reg_form").submit(function (event) {
    /* stop form from submitting normally */
    event.preventDefault();

    let fullname = $("#fullname").val();
    let email = $("#email").val();
    let password = $("#password").val();
    let re_password = $("#retype_password").val();

    if (re_password == password) {
      const data = {
        fullname: fullname,
        email: email,
        password: password,
      };

      $.ajax({
        url: "http://127.0.0.1:3033/register",
        contentType: "application/json",
        dataType: "json",
        type: "POST",
        data: JSON.stringify(data),
        success: function (result) {
          window.location.replace(
            "http://127.0.0.1:5500/src/main/resources/static/HTML/login.html"
          );
        },
      });
    } else {
      alert("Password Wrong");
      $("#fullname").val("");
      $("#email").val("");
      $("#password").val("");
      $("#retype_password").val("");
    }
  });
});
