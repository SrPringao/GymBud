<?php
require_once "conexion.php";
$sucursal = $_GET["sucursal"];
// Preparar la consulta
$query = "SELECT ServiceId FROM subservices WHERE SubId = $sucursal AND Active = 1";
$result = mysqli_query($link,$query);
$datos = array();
if ($result) {
    while($row = $result->fetch_array()){
        $ServiceId = $row['ServiceId'];
        $datos[] = array ('ServiceId' => $ServiceId);
    }
}
$recordsFiltered = count($datos);
$recordsTotal = $recordsFiltered;

echo json_encode($datos);
$result->close();
?>