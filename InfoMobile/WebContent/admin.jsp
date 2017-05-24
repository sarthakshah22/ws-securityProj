<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style>
table, td,th {
    border: 1px solid black;
}
input{
	background-color: #ccc;
}
</style>

</head>
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script>
function addUser(){
    var userId = $("#userId").val();
    var password = $("#password").val();
    var email = $("#email").val();
 jQuery.ajax({
		url : 'rest/UserService/addUsers',
		type : 'POST',
		headers : {
			'Authorization' : 'Basic SW5mb01vYmlsZUF1dGhDb2RlOjAwMA==',
			'X_CSRF_TOKEN' : '<%=request.getParameter("token")%>'
		},
		data : { userId: userId, password : password, email : email},
		success: function(data){
		 if(data == 'success')
				{ 
					getUser();
				} 
		 else if(data == 'InvalidToken'){
				window.location.href = "InvalidToken.jsp"
				}
			},
		error: function(data){
			alert("error");
			}
	});
	
}

function getUser(){
	$("#tableBody").empty();
    jQuery.ajax({
		url : "rest/UserService/users",
		type : 'GET',
		headers : {
			'Authorization' : 'Basic SW5mb01vYmlsZUF1dGhDb2RlOjAwMA==',
			'X_CSRF_TOKEN' : '<%=request.getParameter("token")%>'
		},
		success: function(data){
			jQuery("#data").css("display","block");
			if(data.response != 'InvalidToken'){
				var table = document.getElementById("tableBody");
				for(var i=0;i<data.response.length;i++){
					var row = table.insertRow(i);
					var obj= data.response[i];
					for ( var key in obj) {
						name = key;
						if(obj[key] != null){
						value = obj[key].toString();
						var cell = row.insertCell(key);
						if(name=='ID'){
							cell.innerHTML = value;
							cell.id = "row"+i+name;
							} else{
								cell.innerHTML =" <input type='text' id='row"+i+name+"' value='"+value+"' />";
							}
						}
					}
				 	var cellupdate = row.insertCell(3);
				 	cellupdate.innerHTML = "<button class='update' id='row"+i+"update' onClick='updateUser("+i+")'>Update</button>";
				 	var cellupdate = row.insertCell(4);
				 	cellupdate.innerHTML = "<button id='row"+i+"delete' onClick='deleteUser("+i+")'>Delete</button>";
					}

				}else{
					window.location.href = "InvalidToken.jsp"
					}
			
			},
		error: function(data){
			alert("fail");
			}
	});
}
function updateUser(id){
	var passwordVal = document.getElementById("row"+id+"Password").value;
	var emailVal = document.getElementById("row"+id+"Email").value;
	var IdVal = document.getElementById("row"+id+"ID").innerText;
	 jQuery.ajax({
			url : 'rest/UserService/addUsers',
			type : 'POST',
			headers : {
				'Authorization' : 'Basic SW5mb01vYmlsZUF1dGhDb2RlOjAwMA==',
				'X_CSRF_TOKEN' : '<%= request.getParameter("token")%>'
			},
			data : { userId: IdVal, password : passwordVal, email : emailVal},
			success: function(data){
			 if(data == 'success')
					{
				 		getUser();
					} 
			 else if(data == 'InvalidToken'){
					window.location.href = "InvalidToken.jsp"
					}
				},
			error: function(data){
				alert("error");
				}
		});
	
}

function deleteUser(id){
	var IdVal = document.getElementById("row"+id+"ID").innerText;
	 jQuery.ajax({
			url : 'rest/UserService/deleteUsers',
			type : 'POST',
			headers : {
				'Authorization' : 'Basic SW5mb01vYmlsZUF1dGhDb2RlOjAwMA==',
				'X_CSRF_TOKEN' : '<%= request.getParameter("token")%>'
				},
			data : {
				userId : IdVal
			},
			success : function(data) {
				if (data == 'success') {
					getUser();
				} else if (data == 'InvalidToken') {
					window.location.href = "InvalidToken.jsp"
				}
			},
			error : function(data) {
				alert("error");
			}
		});

	}
</script>

<body>
ADMIN PORTAL
 </br></br>
 <button id="getUsers" onclick="getUser()">Get ALL User</button>
 </br>
  </br></br></br>
  <div id="addData">
  	<table  id="dataTable">
  		 <thead>
  		 	<th>Password</th>
  		 	<th>USer ID</th>
  		 	<th>Email</th>
  		 </thead>
  		 <tbody id="">
  		 	<tr>
  		 		<td><input type="text" name="userId" id="userId"/></td>
  		 		<td><input type="text" name="password" id="password"/></td>
  		 		<td><input type="text" name="email" id="email"/></td>
  		 		<td><button id="addUser" onclick="addUser()">Add users</button></td>
  		 	</tr>
  		 </tbody>
  	</table>
  </div>
  </br></br></br>
  <div id="data" style="display:none">
  	<table  id="dataTable">
  		 <thead>
  		 	<th>Password</th>
  		 	<th>USer ID</th>
  		 	<th>Email</th>
  		 	<th>Update password/email</th>
  		 	<th>Delete entry</th>
  		 </thead>
  		 <tbody id="tableBody">
  		 
  		 </tbody>
  	</table>
  </div>
 
</body>
</html>