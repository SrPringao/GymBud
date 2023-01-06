<?php
if ($_SERVER['REQUEST_METHOD']=='GET') 
{
    require_once("conexion.php");
    $id = $_GET["usr"];
    $pass =$_GET["pass"];

    $query = "SELECT * FROM user WHERE  User= '$id' AND Password = '$pass'";
    $result = $mysqli ->query($query);

    if ($mysqli -> affected_rows > 0) {
        while ($row = $result ->fetch_assoc()) 
        {
            $array = $row;
        }
        echo json_encode($array);
    }else {
        echo "Not found any rows";
    }
    $result ->close();
    $mysqli ->close();
}
