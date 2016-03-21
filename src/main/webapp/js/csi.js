/**
 * Created by lc on 16/3/21.
 */

String.prototype.format = function() {
    var args = arguments;
    return this.replace(/\{(\d+)\}/g,
        function(m,i){
            return args[i];
        });
}

function init() {
    if (getCookie("ssid") == null) {
        location.href = "login.html";
        return;
    }

    $.ajax({
        type: 'POST',
        url: "all",
        beforeSend: function(XMLHttpRequest){
        },
        success: function(data, textStatus){
            //console.log(data);
            var resp = eval("(" + data + ")");
            if (resp["code"] == 200) {
                var tbody = document.getElementById("vcard-tbody");
                var obj = resp["obj"];
                for (var idx in obj) {
                    var item = obj[idx];
                    tbody.innerHTML += "<tr><td>" + item['name'] + "</td><td>" + item['mobile'] + "</td>"
                        + "<td>" + item['city'] + "</td><td>"+ item['company'] + "</td>"
                        + "<td>"+ item['pos'] +"</td><td>" + item['graduation'] + "</td></tr>";
                }
            }
        },
        complete: function(XMLHttpRequest, textStatus){
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.error();
        }
    });
}

function add() {
    var name = $('#name').val();
    var namelen = name.replace(/(^\s*)|(\s*$)/g, "").length;
    if (namelen == 0) {
        alert("请输入姓名");
        return;
    }
    if (namelen > 4) {
        alert("姓名不能超过4个字")
    }
    var mobile = $('#mobile').val();
    if (mobile.replace(/(^\s*)|(\s*$)/g, "").length == 0) {
        alert("请输入手机号");
        return;
    }
    var city = $('#city').val();
    var citylen = city.replace(/(^\s*)|(\s*$)/g, "").length;
    if (citylen == 0) {
        alert("请输入城市");
        return;
    }
    if (citylen > 4) {
        alert("城市不能超过4个字");
        return;
    }
    var company = $('#company').val();
    if (company.replace(/(^\s*)|(\s*$)/g, "").length == 0) {
        alert("请输入公司");
        return;
    }
    var position = $('#position').val();
    if (position.replace(/(^\s*)|(\s*$)/g, "").length == 0) {
        alert("请输入职位");
        return;
    }
    var graduation = $('#graduation').val();
    if (graduation.replace(/(^\s*)|(\s*$)/g, "").length == 0) {
        alert("请输入毕业时间");
        return;
    }
    var postData = "name={0}&mobile={1}&city={2}&company={3}&position={4}&graduation={5}".format(name, mobile, city, company, position, graduation);
    console.log(postData);
    $.ajax({
        type: 'POST',
        url: "add",
        data: postData,
        beforeSend: function(XMLHttpRequest){
        },
        success: function(data, textStatus){
            //console.log(data);
            var resp = eval("(" + data + ")");
            console.log(resp);
            if (resp["code"] == 200) {
                var obj = resp["obj"];
                var tbody = document.getElementById("vcard-tbody");
                tbody.innerHTML += "<tr id='"+name+"'><td>" +name + "</td><td>" + mobile + "</td>"
                    + "<td>" + city + "</td><td>"+ company + "</td>"
                    + "<td>"+ position +"</td><td>" + graduation + "</td></tr>";
            } else {
                alert("添加失败: " + resp["msg"]);
            }
        },
        complete: function(XMLHttpRequest, textStatus){
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.error();
        }
    });
}

function removecsi() {
    var name = $('#name').val();
    if (name.replace(/(^\s*)|(\s*$)/g, "").length == 0) {
        alert("请输入要删除的姓名");
        return;
    }
    $.ajax({
        type: 'POST',
        url: "remove",
        data: "name=" + name,
        beforeSend: function(XMLHttpRequest){
        },
        success: function(data, textStatus){
            //console.log(data);
            var resp = eval("(" + data + ")");
            console.log(resp);
            if (resp["code"] == 200) {
                $("#" + name).remove();
            } else {
                alert("删除失败: " + resp["msg"]);
            }
        },
        complete: function(XMLHttpRequest, textStatus){
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.error();
        }
    });
}

function login() {
    var username = $('#username').val();
    if (username.replace(/(^\s*)|(\s*$)/g, "").length == 0) {
        alert("请输入用户名");
        return;
    }
    var pass = $('#password').val();
    if (pass.replace(/(^\s*)|(\s*$)/g, "").length == 0) {
        alert("请输入密码");
        return;
    }
    var postData = "username=" + md5(username)  +"&pass=" + md5(pass);
    console.log(postData);
    $.ajax({
        type: 'POST',
        url: "dologin",
        data: postData,
        beforeSend: function(XMLHttpRequest){
        },
        success: function(data, textStatus){
            //console.log(data);
            var resp = eval("(" + data + ")");
            console.log(resp);
            if (resp["code"] == 200) {
                setCookie("ssid", resp['obj']);
                location.href = "csi.html";
            } else {
                console.log("login failed");
                $('#username').val("");
                $('#password').val("");
                alert("用户名密码错误!");
            }
        },
        complete: function(XMLHttpRequest, textStatus){
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.error();
        }
    });
}

function initLogin() {
    var logincookie = getCookie("ssid");
    if (logincookie != null) {
        $.ajax({
            type: 'POST',
            url: "cookielogin",
            data: "cookie=" + logincookie,
            beforeSend: function(XMLHttpRequest){
            },
            success: function(data, textStatus){
                //console.log(data);
                var resp = eval("(" + data + ")");
                console.log(resp);
                if (resp["code"] == 200) {
                    location.href = "csi.html";
                } else {
                    delCookie("ssid");
                }
            },
            complete: function(XMLHttpRequest, textStatus){
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                console.error();
            }
        });
    }
}

function setCookie(name,value) {
    var Days = 7;
    var exp  = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

function getCookie(name) {
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
    if(arr != null) return unescape(arr[2]); return null;
}

function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null) document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}
