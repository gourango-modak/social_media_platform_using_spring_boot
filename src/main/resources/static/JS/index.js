$(document).ready(function () {
  const WEBDOMAIN = "127.0.0.1";
  let statusIDOfClickEvent = 0;

  // if (getCookie("userid")) {
  if (getCookie("userId") != "") {
    $("#addPostForm").toggle("hide");
    $("#logIn").toggle("hide");
    $(".status-header").toggle("hide");
    $(".main-header #registrationLink").toggle("hide");
    getAllPosts(getCookie("userId"));
  } else {
    $(".main-header").toggle("hide");
    $(".status-header #logOut").toggle("hide");
    getAllPosts("");
  }

  $.ajax({
    url: "http://127.0.0.1:3033/locations",
    contentType: "application/json",
    type: "GET",
    success: function (res) {
      res.forEach(function (loc) {
        $("#locations").append(
          "<option value=" + loc["name"] + ">" + loc["name"] + "</option>"
        );
      });
    },
  });

  function getAllPosts(userID) {
    $("#status_items").html("");
    $.ajax({
      url: "http://127.0.0.1:3033/posts/" + userID,
      contentType: "application/json",
      dataType: "json",
      type: "GET",
      success: function (result) {
        if (result == "") return;
        result.forEach(function (res) {
          const statusItemHTML =
            '<div class="status_item">\
          <div class="edit_status" id="statusID_' +
            res["id"] +
            '">\
            <i class="fas fa-edit"></i>\
          </div>\
          <div class="description" id="status_name">\
            <i class="fas fa-user-friends"></i>\
            <p>' +
            res["user"]["fullname"] +
            '</p>\
          </div>\
          <div class="description" id="status_description">\
            <i class="far fa-comment-alt"></i>\
            <p>' +
            res["statusDes"] +
            '</p>\
          </div>\
          <div class="description" id="status_location">\
            <i class="fas fa-thumbtack"></i>\
            <p>' +
            res["location"] +
            "</p>\
          </div>\
        </div>";
          $("#status_items").append(statusItemHTML);
          $("#statusID_" + res["id"]).on("click", function () {
            statusIDOfClickEvent = res["id"];
            $.ajax({
              url:
                "http://127.0.0.1:3033/posts/" +
                getCookie("userId") +
                "/" +
                res["id"],
              contentType: "application/json",
              dataType: "json",
              type: "GET",
              success: function (result) {
                $("#status_description").val(result["statusDes"]);
                if (result["privacy"] == "1")
                  $("#public").prop("checked", true);
                else {
                  $("#public").prop("checked", false);
                  $("#private").prop("checked", true);
                }
                let location = result["location"];
                $("select option[value=" + location + "]").attr(
                  "selected",
                  "selected"
                );
                $("#post_btn").text("Update");
              },
            });
          });
        });
      },
    });
  }

  $("#logOut").on("click", function () {
    delete_cookie("userId", WEBDOMAIN);
  });

  $("#post_form").submit(function (event) {
    /* stop form from submitting normally */
    event.preventDefault();

    let statusDes = $("#status_description").val();
    let privacy = $("input[name=privacy]:checked").val();
    let privadyID = 0;
    if (privacy == "public") privadyID = 1;
    let location = $("#locations option:selected").val();
    let userId = getCookie("userId");

    const data = {
      statusDes: statusDes,
      privacy: privadyID,
      location: location,
    };

    let btnStatus = $("#post_btn").text();
    if (btnStatus == "Update") {
      $("#public").prop("checked", true);
      $("#public").attr("checked", true);
      $("#status_description").val("");
      $("#private").attr("checked", false);
      $("select option[value='Sylhet']").attr("selected", "selected");

      $.ajax({
        url:
          "http://127.0.0.1:3033/add_post/" +
          userId +
          "/" +
          statusIDOfClickEvent,
        contentType: "application/json",
        dataType: "json",
        type: "POST",
        data: JSON.stringify(data),
        success: function (res) {
          getAllPosts();
        },
      });
      $("#post_btn").text("Post");
    } else {
      $.ajax({
        url: "http://127.0.0.1:3033/add_post/" + userId,
        contentType: "application/json",
        dataType: "json",
        type: "POST",
        data: JSON.stringify(data),
        success: function (res) {
          const statusItemHTML =
            '<div class="status_item">\
            <div class="edit_status" id="' +
            res["user"]["userid"] +
            '">\
              <i class="fas fa-edit"></i>\
            </div>\
            <div class="description" id="status_name">\
              <i class="fas fa-user-friends"></i>\
              <p>' +
            res["user"]["fullname"] +
            '</p>\
            </div>\
            <div class="description" id="status_description">\
              <i class="far fa-comment-alt"></i>\
              <p>' +
            res["statusDes"] +
            '</p>\
            </div>\
            <div class="description" id="status_location">\
              <i class="fas fa-thumbtack"></i>\
              <p>' +
            res["location"] +
            "</p>\
            </div>\
          </div>";
          $("#status_items").append(statusItemHTML);
        },
      });

      $("#public").prop("checked", true);
      $("#public").attr("checked", true);
      $("#status_description").val("");
      $("#private").attr("checked", false);
      $("select option[value='Sylhet']").attr("selected", "selected");
      $("#post_btn").text("Post");
    }
  });
});
