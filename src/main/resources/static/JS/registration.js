$(document).ready(function () {
  $("#reg_form").submit(function (event) {
    /* stop form from submitting normally */
    event.preventDefault();

    let fullname = $("#name").val();
    let email = $("#username").val();
    let password = $("#password").val();
    let re_password = $("#retype_password").val();

    if (re_password == password) {
      const data = {
        fullname: fullname,
        username: username,
        password: password,
      };

      $.ajax({
        url: "http://localhost:8080/add_user",
        contentType: "application/json",
        dataType: "json",
        type: "POST",
        data: JSON.stringify(data),
        success: function (result) {
          console.log(result);
          // window.location.replace(
          //   "http://127.0.0.1:5500/src/main/resources/static/HTML/login.html"
          // );
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
          alert("Server Down");
        }
      });
    } else {
      alert("Password Wrong");
      $("#name").val("");
      $("#username").val("");
      $("#password").val("");
      $("#retype_password").val("");
    }
  });
});
