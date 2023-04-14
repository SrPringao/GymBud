<?php
    $sucursal = $_GET["sucursal"];
require_once("conexion.php");
$query = "SELECT COUNT(Presence) FROM user WHERE Presence = ?";
$stmt = $link->prepare($query);
$stmt->bind_param("s",$sucursal);
$stmt->execute();
$result = $stmt->get_result();
if (mysqli_num_rows($result) > 0) {  
  echo  $result->fetch_row()[0];
}
$stmt->close();
?>