$(document).ready(function () {
    
  let editStatusID = 0;
  if($.cookie("access-token")) {
    $("#status_form").show();
    $("#logout").show();
    $("#login").hide();
    $("#active_user_section").hide();
    $("#main-section").addClass("mx-auto");
    $("#main-section").addClass("col-6");
    sendRequest("http://localhost:3333/locations", "GET", null, locationDataSuccessful, locationDataFailed, true);
  } else {
    $("#login").show();
    $("#logout").hide();
  }

  function locationDataSuccessful(response) {
    response.forEach(function (loc) {
      $("#locations").append(
        "<option value=" + loc["name"] + ">" + loc["name"] + "</option>"
      );
    });
  }

  function locationDataFailed(response) {
    if($.cookie("access-token")) {
      $(".alert").show();
    }
  }

  function statusHtmlBuild(statusId, name, status_description, location) {
    let statusEditBtn = "";
    if($.cookie("access-token")) {
      statusEditBtn = '<div class="btn btn-sm float-right" id="statusID_' +
                      statusId +
                        '">\
                        <i class="fas fa-edit"></i>\
                      </div>';
    }
    let statusItemHTML =
            '<div class="status_item border-bottom mb-5">\
            '+statusEditBtn+'<div class="status_des">\
          <div id="status_username" class="d-flex">\
            <i class="fas fa-user-friends"></i>\
            <p class="ml-2">' +
            name +
            '</p>\
          </div>\
          <div class="d-flex" id="status_description">\
            <i class="far fa-comment-alt"></i>\
            <p class="ml-2">' +
            status_description +
            '</p>\
          </div>\
          <div class="d-flex" id="status_location">\
            <i class="fas fa-thumbtack"></i>\
            <p class="ml-2">' +
            location +
            "</p>\
          </div>\
        </div>\
        </div>";
    return statusItemHTML;
  }

  function getEditStatusSuccessful(response) {
    $("#status").val(response["result"]["statusDescription"]);
    if (response["result"]["privacy"] == "public")
      $("#public").prop("checked", true);
    else {
      $("#public").prop("checked", false);
      $("#private").prop("checked", true);
    }
    let location = response["result"]["location"]["name"];
    $("select option[value=" + location + "]").attr(
      "selected",
      "selected"
    );
    $("#post_btn").text("Update");
    editStatusID = response["result"]["statusId"];
  }

  function getEditStatusFailed() {
    alert("get status failed");
    // $(".alert").show();
  }

  function addEditStatusBtn(statusId) {
    $("#statusID_"+statusId).on("click", function () {
      const statusID = this.id.split("_")[1];
      sendRequest("http://localhost:3333/"+statusID, "GET", null, getEditStatusSuccessful, getEditStatusFailed, true);
    });
  }

  
  function getAllStatusSuccessful(response) {
    response["result"].forEach(function(res){
        const statusItemHTML = statusHtmlBuild(res["statusId"], res["user"]["name"], res["statusDescription"], res["location"]["name"]);
        $("#status_items").append(statusItemHTML);
        addEditStatusBtn(res["statusId"]);
    });
  }

  function getAllStatusFailed(response) {
    // alert("get status failed");
    $(".alert").show();
  }

  function getAllStatus() {
    $("#status_items").html("");
    sendRequest("http://localhost:3333/posts", "GET", null, getAllStatusSuccessful, getAllStatusFailed, true);
  }

  function logoutSuccessful(response) {
    sendLoginUserNotification();
    // console.log(response);
  }

  function logoutFailed(response) {
    console.log(response);
  }

  $("#logout").on("click", function () {
    sendRequest("http://localhost:3333/logout_user", "GET", null, logoutSuccessful, logoutFailed, true);
    $.removeCookie('access-token');
    $.removeCookie('refresh-token');
    sendLoginUserNotification();
    window.location.href = "/templates/index.html";
  });

  function statusSuccessful(response) {
    const statusItemHTML = statusHtmlBuild(response["result"]["statusId"], 
    response["result"]["user"]["name"], 
    response["result"]["statusDescription"], 
    response["result"]["location"]["name"]);
    $("#status_items").append(statusItemHTML);
    addEditStatusBtn(response["result"]["statusId"]);
  }

  function statusFailed(response) {
    $(".alert").show();
  }

  function updateStatusSuccessful(response) {
    getAllStatus();
  }

  function updateStatusFailed(response) {
    alert("update status failed");
  }

  $("#status_form").submit(function (event) {
    event.preventDefault();

    let statusDescription = $("#status").val();
    let privacy = $("input[name=privacy]:checked").val();
    let location = $("#locations option:selected").val();

    const data = {
      statusDescription: statusDescription,
      privacy: privacy,
      location: location,
    };

    let btnStatus = $("#post_btn").text();
    if (btnStatus == "Post") {
      sendRequest("http://localhost:3333/add_status", "POST", data, statusSuccessful, statusFailed, true);
    } else {
      sendRequest("http://localhost:3333/"+editStatusID, "PUT", data, updateStatusSuccessful, updateStatusFailed, true);
    }

    $("#public").prop("checked", true);
    $("#status").val("");
    $("#private").prop("checked", false);
    $("select option[value='Dhaka']").attr("selected", "selected");
    $("#post_btn").text("Post");
  });


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

  getAllStatus();
  getAllActiveUsers();


});
