<?php
if ($_SERVER['REQUEST_METHOD']=='GET') 
{
    require_once("conexion.php");
    $mail = $_GET["mail"];


    $query = "SELECT * FROM user WHERE mail='$mail'";
    $result = $link ->query($query);

    if ($link -> affected_rows > 0) {
        while ($row = $result ->fetch_assoc()) 
        {
            $array = $row;
        }
       echo json_encode($array);
    }else {
        
		echo "Not found any rows";
    }
    $result ->close();
    $link ->close();
}
