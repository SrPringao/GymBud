<?php

function conectar(){
	$user = "root";
	$pass = "";
	$server = "localhost";
	$db = "gymbud";
	$con = mysqli_connect($server,$user,$pass) or die ("Error al conectar al servidor de la base de datos".mysql_error());
	mysqli_select_db($con, $db) or die ("Error al conecar con la base de datos");
	
	return $con;	
}

?>