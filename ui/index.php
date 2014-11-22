<!doctype html>
<html>
	<head>
		<title>Hello Search</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="icon" href="favicon.ico">
		<link href="css/bootstrap.css" rel="stylesheet">
		<link href="css/main.css" rel="stylesheet">
	</head>
	<body>
		<nav>
			<ul>
				<li class="active"><a href="#">Search</a></li>
				<li><a href="#">History</a></li>
			</ul>
		</nav>
		<div class="container search">
			<div class="logo">
				<h1>Hello</h1>
				<div class="thing"></div>
			</div>
			<form class="search" method="POST" action="results.php">
				<input name="query" class="form-control" autocomplete="off">
			</form>
		</div><!-- /.container -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="js/bootstrap.js"></script>
	</body>
</html>