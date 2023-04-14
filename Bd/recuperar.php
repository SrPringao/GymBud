<?php
if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    $UID = $_GET["UID"];
} ?>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Recuperar</title>

	<style>
		@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap');
		*
		{
			margin: 0;
			padding: 0;
			box-sizing: border-box;
		font-family: 'Poppins', sans-serif;
		}
		body 
		{
			display: flex;
			justify-content: center;
			align-items: center;
			min-height: 100vh;
			flex-direction: column;
			background: #23242a;
		}
		.box 
		{
			position: relative;
			width: 380px;
			height: 420px;
			background: #1c1c1c;
			border-radius: 8px;
			overflow: hidden;
		}
		.box::before 
		{
			content: '';
			z-index: 1;
			position: absolute;
			top: -50%;
			left: -50%;
			width: 380px;
			height: 420px;
			transform-origin: bottom right;
			background: linear-gradient(0deg,transparent,#efb810,#efb810);
			animation: animate 6s linear infinite;
		}
		.box::after 
		{
			content: '';
			z-index: 1;
			position: absolute;
			top: -50%;
			left: -50%;
			width: 380px;
			height: 420px;
			transform-origin: bottom right;
			background: linear-gradient(0deg,transparent,#efb810,#efb810);
			animation: animate 6s linear infinite;
			animation-delay: -3s;
		}
		@keyframes animate 
		{
			0%
			{
				transform: rotate(0deg);
			}
			100%
			{
				transform: rotate(360deg);
			}
		}
		form 
		{
			position: absolute;
			inset: 2px;
			background: #28292d;
			padding: 50px 40px;
			border-radius: 8px;
			z-index: 2;
			display: flex;
			flex-direction: column;
		}
		h2 
		{
			color: #ffffff;
			font-weight: 500;
			text-align: center;
			letter-spacing: 0.1em;
		}
		.inputBox 
		{
			position: relative;
			width: 300px;
			margin-top: 35px;
		}
		.inputBox input 
		{
			position: relative;
			width: 100%;
			padding: 20px 10px 10px;
			background: transparent;
			outline: none;
			box-shadow: none;
			border: none;
			color: #23242a;
			font-size: 1em;
			letter-spacing: 0.05em;
			transition: 0.5s;
			z-index: 10;
		}
		.inputBox span 
		{
			position: absolute;
			left: 0;
			padding: 20px 0px 10px;
			pointer-events: none;
			font-size: 1em;
			color: #8f8f8f;
			letter-spacing: 0.05em;
			transition: 0.5s;
		}
		.inputBox input:valid ~ span,
		.inputBox input:focus ~ span 
		{
			color: #ffffff;
			transform: translateX(0px) translateY(-34px);
			font-size: 0.75em;
		}
		.inputBox i 
		{
			position: absolute;
			left: 0;
			bottom: 0;
			width: 100%;
			height: 2px;
			background: #efb810;
			border-radius: 4px;
			overflow: hidden;
			transition: 0.5s;
			pointer-events: none;
			z-index: 9;
		}
		.inputBox input:valid ~ i,
		.inputBox input:focus ~ i 
		{
			height: 44px;
		}
		.links 
		{
			display: flex;
			justify-content: space-between;
		}
		.links a 
		{
			margin: 10px 0;
			font-size: 0.75em;
			color: #8f8f8f;
			text-decoration: beige;
		}
		.links a:hover, 
		.links a:nth-child(2)
		{
			color: #efb810;
		}
		input[type="submit"]
		{
			border: none;
			outline: none;
			padding: 11px 25px;
			background: #efb810;
			cursor: pointer;
			border-radius: 4px;
			font-weight: 600;
			width: 100px;
			margin-top: 10px;
		}
		input[type="submit"]:active 
		{
			opacity: 0.8;
		}

	</style>
  </head>

  <!DOCTYPE html>
  <html lang="en">
  <head>
	  <meta charset="UTF-8">
	  <title>Cambio de contraseña GymBud</title>
	  <link rel="stylesheet" href="style.css">
  </head>
  <body>

	
  
		  <div class="box">
			  <form autocomplete="off" name="formularioDatos" method="post" action="query.php" onsubmit="return checkPasswordsMatch()">
				  <h2>Ingresa tu nueva contraseña</h2>
				  <div class="inputBox">
					
					  <input type="text" required="required" value="" name="con" id="password1"  oninput="validatePassword(), countCharacters()"  >
					  <span id="character-count">Contraseña nueva</span> 
					  
					  <i></i>
					</div>

				  <div class="inputBox">
					  <input type="text" required="required" value="" name="ccon" id="password2" oninput="validatePassword(), countCharacters()">
					  <span id="character-count2">Confirmar contraseña</span>
					  <i></i>
				  </div>
			  
				  <center>
					  <input disabled  type="submit" value="Login"  id="submitBtn" style="margin-top: 30px;">
				  </center>
  
				  <input type="hidden" name="Ocultin" value=<?php echo $UID = $_GET["UID"]; ?> />
			  </form>
		  </div>

		  <div id="error-msg" style="color: red;"></div>
	  </form>

	  <script>
		var passwordsMatch = false;
		var passwordEspecific = false;
	  
		function validatePassword() {
  var password1 = document.getElementById("password1").value;
  var password2 = document.getElementById("password2").value;

 
  // Comprueba si la contraseña tiene entre 8 y 12 caracteres
   if (!/^.{8,18}$/.test(password1)) {
    document.getElementById("error-msg").innerHTML = "La contraseña debe tener entre 8 y 18 caracteres.";
    document.getElementById("submitBtn").disabled = true;
    passwordsMatch = false;
  } 
  // Comprueba si la contraseña tiene al menos una letra minúscula
  else if (!/^(?=.*[a-z])/.test(password1)) {
    document.getElementById("error-msg").innerHTML= "La contraseña debe tener al menos una letra minúscula.";
document.getElementById("submitBtn").disabled = true;
passwordsMatch = false;
}
else if ((!password1.match(/[A-Z]/) )) {
	document.getElementById("error-msg").innerHTML= "La contraseña debe de contar con al menos una letra mayuscula.";
document.getElementById("submitBtn").disabled = true;
passwordsMatch = false;
	
}else if (!password1.match(/[0-9]/)) {
	
	document.getElementById("error-msg").innerHTML= "La contraseña debe de contar con al menos un numero.";
document.getElementById("submitBtn").disabled = true;
passwordsMatch = false;	
}else if (!password1.match(/[!@#$%^&*.,]/)) {
	document.getElementById("error-msg").innerHTML= "La contraseña debe de contar con al menos un caracter especial.";
document.getElementById("submitBtn").disabled = true;
passwordsMatch = false;
	
} else if (password1 != password2) {
    document.getElementById("error-msg").innerHTML = "Las contraseñas no coinciden.";
    document.getElementById("submitBtn").disabled = true;
    passwordsMatch = false;
  } 
else {
document.getElementById("submitBtn").disabled = false;
document.getElementById("error-msg").innerHTML = "";


passwordsMatch = true;
}
}


function countCharacters() {
  var input = document.getElementById("password1");
  var count = document.getElementById("character-count");

  var input2 = document.getElementById("password2");
  var count2 = document.getElementById("character-count2");
  
  
  if (input.value.length == 0) {
	count.innerHTML = "Contraseña nueva"
  }else{
	  count.innerHTML = input.value.length;
  }

  if (input2.value.length == 0) {
	count2.innerHTML = "Confirmar contraseña"
  }else{
	  count2.innerHTML = input2.value.length;
  }



}
	  </script>
  </body>
  </html>
</html>