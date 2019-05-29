class HFSInfoUser extends HFSSystemUtil {
	constructor()
	{
		super();
		
		this._urlApiServer = this.getSystemPage() + "/private/userinfo";
	
		$.get({
			url: this._urlApiServer,
			dataType: "json",
		    contentType: "application/json; charset=utf-8",								
	        context: this,
	        beforeSend: function (xhr) {
	            xhr.setRequestHeader("Authorization", "Bearer " + this._authToken);
	        }
		})
		.done(function(data) {
			$('.infoUser-username').empty();
			$('.infoUser-roles').empty();
			
			$('.infoUser-username').append(data.username);
			
			data.roles.forEach(function(element, index, array){
				$('.infoUser-roles').append(element.name);
				$('.infoUser-roles').append("<br>");
			});
			
		})
		.fail(function(xhr, textStatus, msg){
			this.dangerShow("An error occured on InfoUser: " + xhr.status + " " + xhr.statusText);
	    });

	}
}

$(function() {
	const hfsInfoUser = new HFSInfoUser();
	
});

