class HFSSystemUtil {
	constructor()
	{
		this._url = window.location.href;
		this._urlAuthServer = $("meta[name='URL-AUTH-SERVER']").attr("content");
		this._authToken = $("meta[name='X-AUTH-TOKEN']").attr("content");
		
		this._anchorHomePage = $('#anchorHomePage');
		this._alertInfoMessage = $('#alert-info-message');
		this._alertErrorMessage = $('#alert-error-message');
	}
	
	setCookie(cname,cvalue) {
		var d = new Date();
		// d.setTime(d.getTime() + (exdays*24*60*60*1000));
		d.setTime(d.getTime() + (30*60*1000));
		var expires = "expires=" + d.toGMTString();
		document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
	}

	getCookie(cname) {
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

	removeCookie(cname,cvalue) {
		var d = new Date();
		d.setTime(d.getTime());
		var expires = "expires=" + d.toGMTString();
		document.cookie = cname + "=;" + expires + ";path=/";
	}

	persistItem(key, value){
		if (typeof(Storage) !== "undefined") {
			window.sessionStorage.setItem(key, value);
		} else {
			setCookie(key, value, 1);
		}
	}

	getPersistedItem(key){
		if (typeof(Storage) !== "undefined") {
			return window.sessionStorage.getItem(key);
		} else {
			return getCookie(key);
		}
	}

	removePersistedItem(key){
		if (typeof(Storage) !== "undefined") {
			window.sessionStorage.removeItem(key);
		} else {
			removeCookie(key, value);
		}
	}
	
}
