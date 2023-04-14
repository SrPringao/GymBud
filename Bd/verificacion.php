<?php
require_once "conexion.php";

$usr = $_GET['usr'];


// Preparar la consulta
$query = "SELECT User FROM user WHERE User == ?";
$stmt = $link->prepare($query);
$stmt->bind_param("s", $usr);

// Ejecutar la consulta
$stmt->execute();

// Obtener el resultado
$result = $stmt->get_result();

// Comprobar si el usuario existe
if ($result->num_rows === 0) {
    echo "El usuario no existe";
} else {
	$usuario_bd = $result->fetch_assoc();
    echo "El usuario ya existe";
}

// Cerrar la conexión
$stmt->close();

?>