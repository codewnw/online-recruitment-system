<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	
	<div class="container">
<div class="row">
		<div class="col">Header</div>
	</div>
		<div class="row">
			<div class="col"></div>

			<div class="col-10">
				<form>
					<div class="form-group">
						<label for="exampleInputEmail1">Email address</label> <input
							type="text" name="username" class="form-control"
							id="exampleInputEmail1" aria-describedby="emailHelp"
							placeholder="Enter email"> <small id="emailHelp"
							class="form-text text-muted">We'll never share your email
							with anyone else.</small>
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Password</label> <input
							type="password" name="password" class="form-control"
							id="exampleInputPassword1" placeholder="Password">
					</div>
					<input type="submit" class="btn btn-primary" value="Login" />
				</form>
			</div>


			<div class="col"></div>
		</div>

<div class="row">
		<div class="col">Footer</div>
	</div>
	</div>
	
</body>
</html>