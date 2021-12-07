
let TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYWppYiIsImV4cCI6MTYzODgwOTI1MiwiaWF0IjoxNjM4ODAyMDUyfQ.cxXOM6gs6ZRJiKmocijFF0SqY9aXPFA3yBBmsX8cvVg";

function sendRequest(url, method, data, success, fail, isheaderInclude) {
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