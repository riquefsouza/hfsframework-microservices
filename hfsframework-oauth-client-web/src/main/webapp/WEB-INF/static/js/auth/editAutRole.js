class EditAutRole extends HFSSystemUtil {
	constructor()
	{
		super();
		
		this._urlApiServer = this.getPersistedItem("urlApiServer");
		this._saveMethod = this.getPersistedItem("saveMethod");	

		this._autRoleId = $('#autRole_id');
		this._autRoleName = $('#autRole_name');
		
		if (this._saveMethod==="PUT"){
			$.get({
				url: this._urlApiServer,
				dataType: "json",
			    contentType: "application/json; charset=utf-8",								
		        context: this,
		        beforeSend: function (xhr) {
		            xhr.setRequestHeader("Authorization", "Bearer " + this._authToken);
		        },
		        success: function(data, status) {
					this.setFields(data);
		        }
			}).fail(function(xhr, textStatus, msg){
				this.dangerShow("An error occured on Edit: " + xhr.status + " " + xhr.statusText);
		    });
		}

	}
	
	setFields(obj){
		this._autRoleId.val(obj.id);
		this._autRoleName.val(obj.name);
	}

	/*
	getFields(){
		var sId = this._autRoleId.val();
		var nId = sId.length == 0 ? null : parseInt(sId);
		
		var obj = {
			id: nId,
			name: this._autRoleName.val()
		}
		
		return JSON.stringify(obj);
	}
	 */
	
	btnCancelClick(event) {
		event.preventDefault();
		
		if (this._saveMethod==="PUT")
			window.location.href=this._url.replace("/edit", "");
		else
			window.location.href=this._url.replace("/add", "");
	}
	
	
	btnSaveClick(event) {
		event.preventDefault();
		/*
		$.ajax({
			method: this._saveMethod,
			url: this._urlApiServer,
			data: this.getFields(),
			dataType: "json",
		    contentType: "application/json; charset=utf-8",								
	        context: this,
	        beforeSend: function (xhr) {
	            xhr.setRequestHeader("Authorization", "Bearer " + this._authToken);
	        }
		})
		.done(function(data, status) {
			this.removePersistedItem("saveMethod");
			this.removePersistedItem("urlApiServer");
			if (this._saveMethod==="PUT")
    			window.location.href=this._url.replace("/edit", "");
    		else
    			window.location.href=this._url.replace("/add", "");
		})
		.fail(function(xhr, textStatus, msg){
			this.dangerShow("An error occured on save: " + xhr.status + " " + xhr.statusText);
	    });
		 */
	}
	
}

$(function() {
	const editAutRole = new EditAutRole();
	
	//$('#btnSave').click(editAutRole.btnSaveClick.bind(editAutRole));
	$('#btnCancel').click(editAutRole.btnCancelClick.bind(editAutRole));
	
});
