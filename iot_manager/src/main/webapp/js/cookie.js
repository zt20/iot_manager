
//设置Cookie
function setCookie(name, value) {
  var exp = new Date();
  exp.setTime(exp.getTime() + 365 * 24 * 60 * 60 * 1000);
  document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}

//设置Cookie
function setCookie(name, value, time) {
    var exp = new Date();
    exp.setTime(exp.getTime() + time * 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}


//得到Cookie
function getCookie(name) {
  var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");

  if (arr = document.cookie.match(reg)) {
    return (arr[2]);
  } else {
    return null;
  }
}

//删除Cookie
function delCookie(name) {
  var exp = new Date();
  exp.setTime(exp.getTime() - 1);
  var cval = getCookie(name);
  if (cval != null) {
    document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
  }
}



