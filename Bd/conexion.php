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