class HFSSystemUtil {
	constructor()
	{
		this._url = window.location.href;
		this._urlAuthServer = $("meta[name='URL-AUTH-SERVER']").attr("content");
		this._authToken = $("meta[name='X-AUTH-TOKEN']").attr("content");
		
		this._anchorHomePage = $('#anchorHomePage');
		this._alertInfo = $('#alert-info');
		this._textAlertInfo = $('#text-alert-info');
		this._alertError = $('#alert-error');
		this._textAlertError = $('#text-alert-error');
		
		this._messageSelectTable = $('#message-select-table').text();
		
		
	}
	
	getSystemPage() {
		var home = this._anchorHomePage[0].href;
		return home.substring(0, home.lastIndexOf("/"));
	}
	
	errorShow(message) {
		this._alertError.show();
		this._textAlertError.html(message);		
	}
	
	errorHide() {
		this._textAlertError.html("");
		this._alertError.hide();
	}

	infoShow(message) {
		this._alertInfo.show();
		this._textAlertInfo.html(message);		
	}
	
	infoHide() {
		this._textAlertInfo.html("");
		this._alertInfo.hide();
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


$(function() {
	$("#locales").change(function () {
        var selectedOption = $("#locales").val();
        if (selectedOption != ''){
        	var sUrl = window.location.href;
        	
            if (sUrl.lastIndexOf("?") > -1){
            	sUrl = sUrl.substr(0, sUrl.indexOf("?lang="));
            }
        	
            window.location.replace(sUrl + '?lang=' + selectedOption);
        }
    });	
});
