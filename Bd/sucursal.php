<?php
require_once "conexion.php";

// Preparar la consulta
$query = "SELECT * FROM subsidary";
$result = mysqli_query($link,$query);
$datos = array();
if ($result) {
    while($row = $result->fetch_array()){
        $id = $row['Id'];
        $SubName = $row['SubName'];
        $Location = $row['Location'];
        $ImageLink = $row['ImageLink'];
        $CurrentUsers = $row['CurrentUsers'];
        $ContactNumber = $row['ContactNumber'];
        $Schedule = $row['Schedule'];
        $Rating = $row['Rating'];
      	$Latitud = $row['Latitud'];
      	$Longitud = $row['Longitud'];

        $datos[] = array ('id' => $id,'SubName' => $SubName, 'Location' => $Location, 'ImageLink' => $ImageLink, 'CurrentUsers' => $CurrentUsers, 'ContactNumber' => $ContactNumber, 'Schedule' => $Schedule,'Rating' =>$Rating ,'Latitud' =>$Latitud ,'Longitud' =>$Longitud);
    }
}
$recordsFiltered = count($datos);
$recordsTotal = $recordsFiltered;

echo json_encode($datos);
$result->close();
?>