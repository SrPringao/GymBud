<?php

//Import PHPMailer classes into the global namespace
//These must be at the top of your script, not inside a function
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\SMTP;
use PHPMailer\PHPMailer\Exception;

require 'PHPMailer/Exception.php';
require 'PHPMailer/PHPMailer.php';
require 'PHPMailer/SMTP.php';
if ($_SERVER['REQUEST_METHOD']=='GET') 
{
    $correo = $_GET["mail"];
    require_once("conexion.php");
    $query = "SELECT UID FROM user WHERE  Mail= '$correo'";
    $result = $link ->query($query);
    while ($row = $result ->fetch_assoc()) 
    {
        $array = $row;
    }
    $jsondecoded = json_decode(json_encode($array),false);
    $uid = $jsondecoded->UID;
//Create an instance; passing `true` enables exceptions
$mail = new PHPMailer(true);

try {
    $url="https://francoaldrete.com/GymBud/recuperar.php?UID=".$uid;
    $codigo = rand(1000,9999);
    //Server settings
    $mail->SMTPDebug = 0;                      //Enable verbose debug output
    $mail->isSMTP();                                            //Send using SMTP
    $mail->Host       = 'smtp.ionos.mx';                     //Set the SMTP server to send through
    $mail->SMTPAuth   = true;                                   //Enable SMTP authentication
    $mail->Username   = 'Gymbud@francoaldrete.com';                     //SMTP username
    $mail->Password   = 'GymBud2023@';                               //SMTP password
    $mail->SMTPSecure = PHPMailer::ENCRYPTION_STARTTLS;            //Enable implicit TLS encryption
    $mail->Port       = 587;
     //TCP port to connect to; use 587 if you have set `SMTPSecure = PHPMailer::ENCRYPTION_STARTTLS`

    //Recipients
    $mail->setFrom('Gymbud@francoaldrete.com', 'Gymbud');
    $mail->addAddress($correo);     //Add a recipient

    //Content
    $mail->isHTML(true);                                  //Set email format to HTML
    $mail->Subject = 'Cambio contrasena Gymbud';//En el de aqui abajo es donde le metes todo el diseño, es el cuerpo del correo y se codifica como si fuera un html pa q le metas diseño de los god
        $mail->Body = 
"<body style='background-color: transparent'>
<table style='max-width: 600px; padding: 10px; margin:0 auto; border-collapse: collapse;'>
    <tr>
        <td style='padding: 0'>
            <img style='padding: 0; display: block' src='https://francoaldrete.com/GymBud/recursos/Gymbudmail.png' width='100%'>
        </td>
    </tr>
    <tr>
        <td style='background-color: #ecf0f1'>
            <div style='color: #34495e; margin: 4% 10% 2%; text-align: justify;font-family: sans-serif'>
                <h2 style='color: #e67e22; margin: 0 0 7px'>Hola GymBro!</h2>
                <p style='margin: 2px; font-size: 15px; margin-bottom: 50px;'>
                Se solicito un cambio de contraseña en su cuenta de Gymbud, si usted no lo solicito haga caso omiso a este correo</p>
                <div style='width: 100%; text-align: center'>
                    <form method=POST action=$url>
                        <a style='text-decoration: none; border-radius: 5px; padding: 11px 23px; color: white; background-color: #3498db; margin: 70;' href=$url >Ir a la página</a>	
                    </form>
                </div>
                <p style='color: #b3b3b3; font-size: 12px; text-align: center;margin: 30px 0 0'>Gymbud App</p>
            </div>
        </td>
    </tr>
</table>
</body>";

    $mail->send();
    echo 'Se envio el mensaje';
} catch (Exception $e) {
    echo "Message could not be sent. Mailer Error: {$mail->ErrorInfo}";
}
}