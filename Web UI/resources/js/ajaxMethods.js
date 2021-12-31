
let TOKEN = $.cookie("access-token");

function sendRequest(url, method, data, success, fail, isheaderInclude) {
    
    TOKEN = $.cookie("access-token");
    
    let headerInfo = "";
    if(isheaderInclude == true) {
        headerInfo = {
            "Authorization": "Bearer " + TOKEN
        };
    }
    $.ajax({
        url: url,
        contentType: "application/json",
        dataType: "json",
        type: method,
        headers: headerInfo,
        data: JSON.stringify(data),
        success: function (result) {
            success(result);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            fail(XMLHttpRequest.responseJSON);
        }
    });
}
