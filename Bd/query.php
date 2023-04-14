<?php
if ($_SERVER['REQUEST_METHOD']=='POST') 
{
    require_once("conexion.php");

    $contrasena = $_POST["con"];
  
   $UID = $_POST["Ocultin"];
  $encriptado = hash('sha256', $contrasena);
  
   $query = "UPDATE user SET Password = '$encriptado' WHERE UID = '$UID'";


   if ($link->query($query) === TRUE) {
      header('Location: http://francoaldrete.com/succesfull.html');
    exit();
    echo "Record updated successfully";
  } else {
    echo "Error updating record: " . $conn->error;
  }
  $link->close();

}
?>


