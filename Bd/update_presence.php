<?php
  $host_name = 'db5011450842.hosting-data.io';
  $database = 'dbs9660695';
  $user_name = 'dbu2427293';
  $password = 'francogol2004';

  $link = new mysqli($host_name, $user_name, $password, $database);

  if ($link->connect_error) {
    die('Error al conectar con servidor MySQL: '. $link->connect_error);
  } else {
    // Obtener el UID de la tarjeta desde el parámetro GET
    $uid = $_GET['uid'];

    // Obtener la variable sucursal desde el parámetro GET
    $sucursal = $_GET['sucursal'];

    // Ejecutar la consulta para actualizar la tabla
    $sql = "SELECT Presence FROM user WHERE UID = '$uid'";
    $result = $link->query($sql);

    if ($result === FALSE) {
      echo "Error al obtener el registro: " . $link->error;
    } else {
      $row = $result->fetch_assoc();
      $presence = $row['Presence'];
      
      // Si presence es diferente de 0, entonces establecerlo en 0
      if ($presence != 0) {
        $sql = "UPDATE user SET Presence = '0' WHERE UID = '$uid'";
        $result = $link->query($sql);
        
        if ($result === TRUE) {
          echo "Registro actualizado correctamente";
        } else {
          echo "Error al actualizar el registro: " . $link->error;
        }
      }
      
      // Si presence es igual a 0, establecerlo a la sucursal correspondiente
      else {
        $sql = "UPDATE user SET Presence = '$sucursal' WHERE UID = '$uid'";
        $result = $link->query($sql);
        
        if ($result === TRUE) {
          echo "Registro actualizado correctamente";
        } else {
          echo "Error al actualizar el registro: " . $link->error;
        }
      }
    }
  }
?>
