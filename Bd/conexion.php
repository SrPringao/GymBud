<?php
$hostname = 'us-east.connect.psdb.cloud';
$dbName = 'gymbud';
$username = 'kfddgh7jntqzfrt34uh7';
$password = 'pscale_pw_XSlDKm1xjE16LrWdgcyVg4dEydOn0ijT8tOIBUPWx7F';
$ssl = 'MYSQL_ATTR_SSL_CA';

$mysqli = mysqli_init();
$mysqli->ssl_set(NULL, NULL, "C:\Users\SGame\GymBud\Bd\cacert.pem", NULL, NULL);
$mysqli->real_connect($hostname, $username, $password, $dbName);

if ($mysqli->connect_error) {
    echo 'not connected to the database';
} else {
    echo "Connected successfully";
}

// <?php
//   $host_name = 'db5010326703.hosting-data.io';
//   $database = 'dbs8751665';
//   $user_name = 'dbu1646673';
//   $password = 'francogol2004';

//   $link = new mysqli($host_name, $user_name, $password, $database);

//   if ($link->connect_error) {
//     die('<p>Error al conectar con servidor MySQL: '. $link->connect_error .'</p>');
//   } else {
//     echo '<p>Se ha establecido la conexión al servidor MySQL con éxito.</p>';
//   }
// 