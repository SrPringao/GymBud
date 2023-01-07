<?php
if ($_SERVER['REQUEST_METHOD']=='GET') 
{
 require_once("conexion.php");
 $user =$_GET["usr"];
 $mail =$_GET["mail"];
 $pass =$_GET["pass"];

 $query ="INSERT INTO user VALUES('4','$user','$mail','$pass','0')";
if(!mysqli_query($link,$query));
  die(printf("<H3>Data record 1 cannot be inserted: [%d] %s</H3>", mysqli_connect_errno(), mysqli_connect_error())); 
 $mysqli ->close();
}