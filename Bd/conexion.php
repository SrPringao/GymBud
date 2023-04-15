<?php
  $host_name = 'db5011450842.hosting-data.io';
  $database = 'dbs9660695';
  $user_name = 'dbu2427293';
  $password = 'francogol2004';

  $link = new mysqli($host_name, $user_name, $password, $database);

  if ($link->connect_error) {
    die('Error al conectar con servidor MySQL: '. $link->connect_error);
  } else {
    //echo 'Se ha establecido la conexión al servidor MySQL con éxito';
  }
?>
