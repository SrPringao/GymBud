<?php
$host_name = 'db5011450842.hosting-data.io';
$database = 'dbs9660695';
$user_name = 'dbu2427293';
$password = 'francogol2004';

$link = new mysqli($host_name, $user_name, $password, $database);

if ($link->connect_error) {
    die('Error al conectar con servidor MySQL: ' . $link->connect_error);
} else {
    // Obtener el UID de la tarjeta desde el parámetro GET
    $uid = $_GET['uid'];
    // Obtener la variable sucursal desde el parámetro GET
    $sucursal = $_GET['sucursal'];
    // Obtener la variable cal1 desde el parámetro GET
    $cal1 = $_GET['cal1'];
    // Obtener la variable cal2 desde el parámetro GET
    $cal2 = $_GET['cal2'];
    // Obtener la variable cal3 desde el parámetro GET
    $cal3 = $_GET['cal3'];

    // Verificar si ya existe una calificación para esta sucursal y usuario
    $sql_select = "SELECT * FROM rating WHERE UserId='$uid' AND SubId='$sucursal'";
    $result_select = $link->query($sql_select);

    if ($result_select->num_rows > 0) {
        // Si ya existe una calificación, actualizar los valores existentes
        $row = $result_select->fetch_assoc();
        $id = $row['Id'];
        $avg = ($cal1 + $cal2 + $cal3) / 3;
        $sql_update = "UPDATE rating SET Cal1='$cal1', Cal2='$cal2', Cal3='$cal3', Average='$avg' WHERE Id='$id'";
        $result_update = $link->query($sql_update);

        if ($result_update) {
            // Mostrar mensaje de éxito
            echo "Se ha actualizado la calificación";
        } else {
            // Mostrar mensaje de error
            echo "No se ha podido actualizar la calificación\n";
            // Mostrar mensaje de error de MySQL
            echo $link->error;
        }
    } else {
        // Si no existe una calificación, insertar un nuevo registro
        $avg = ($cal1 + $cal2 + $cal3) / 3;
        $sql_insert = "INSERT INTO rating (UserId, SubId, Cal1, Cal2, Cal3, Average) VALUES ('$uid', '$sucursal', '$cal1', '$cal2', '$cal3', '$avg')";
        $result_insert = $link->query($sql_insert);

        if ($result_insert) {
            // Mostrar mensaje de éxito
            echo "Se ha guardado la calificación";
        } else {
            // Mostrar mensaje de error
            echo "No se ha podido guardar la calificación\n";
            // Mostrar mensaje de error de MySQL
            echo $link->error;
        }
    }

    // Actualizar el promedio de calificación para la sucursal en la tabla subsidary
    $sql_update_sub = "UPDATE subsidary s
JOIN (SELECT SubId, AVG(Average) AS rating_avg
      FROM rating
      GROUP BY SubId) r
ON s.id = r.SubId
SET s.rating = r.rating_avg
WHERE s.id = '$sucursal'";
    $result_update_sub = $link->query($sql_update_sub);
    if ($result_update_sub) {
        // Mostrar mensaje de éxito
        echo "\nSe ha actualizado el promedio de calificación de la sucursal";
    } else {
        // Mostrar mensaje de error
        echo "\nNo se ha podido actualizar el promedio de calificación de la sucursal\n";
        // Mostrar mensaje de error de MySQL
        echo $link->error;
    }

}
?>