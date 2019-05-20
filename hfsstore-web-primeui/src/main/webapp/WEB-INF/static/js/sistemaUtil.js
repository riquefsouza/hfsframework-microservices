var sURL_BACKEND = "http://localhost:8090"; 

function setCookie(cname,cvalue,exdays) {
	var d = new Date();
	//d.setTime(d.getTime() + (exdays*24*60*60*1000));
	d.setTime(d.getTime() + (exdays*60*60*1000));
	var expires = "expires=" + d.toGMTString();
	document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
  var name = cname + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var ca = decodedCookie.split(';');
  for(var i = 0; i < ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

function removeCookie(cname,cvalue) {
	var d = new Date();
	d.setTime(d.getTime());
	var expires = "expires=" + d.toGMTString();
	document.cookie = cname + "=;" + expires + ";path=/";
}


function persistItem(key, value){
	if (typeof(Storage) !== "undefined") {
		window.sessionStorage.setItem(key, value);
	} else {
		setCookie(key, value, 1);
	}
}


function getPersistedItem(key){
	if (typeof(Storage) !== "undefined") {
		return window.sessionStorage.getItem(key);
	} else {
		return getCookie(key);
	}
}

function removePersistedItem(key){
	if (typeof(Storage) !== "undefined") {
		window.sessionStorage.removeItem(key);
	} else {
		removeCookie(key, value);
	}
}