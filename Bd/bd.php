<?php

class Basededatos
{
private $con;
public function __construct()
{

$this ->con = new PDO('mysql:host=localhost;dbname=gymbud','root','');
}

public function consulta($id)
{
$sql = $this-> con->prepare("SELECT * FROM PersonalInfo where PersonalInfo.UserId = ".$id);
$sql -> execute();
$res = $sql->fetchAll();
$arreglo = array();
    foreach ($res as $dato)
    {
    $obj = new Persona();
    $obj -> nombre = $dato['nombre'];

    array_push($arreglo, $obj);
    } 

return $arreglo;
}

public function ingreso($usr, $pass)
{
    $sql = $this->con->prepare("SELECT * FROM PersonalInfo WHERE BINARY PersonalInfo.UserId = '" .$usr. "' and BINARY Password = '" . $pass ."'");
    $sql->execute();
    $res = $sql->fetchAll();

    if(count($res)>0){
        foreach($res AS $dato)
        return $dato['id'];
    }
    return -1;
}

}
