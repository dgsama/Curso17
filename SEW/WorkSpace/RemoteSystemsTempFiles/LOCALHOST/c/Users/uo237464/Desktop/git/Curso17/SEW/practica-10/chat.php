<?php
// poner los parámetros de la cookie de sesión
session_set_cookie_params(
		0,									// caduca al cerrar el navegador
		dirname($_SERVER['SCRIPT_NAME']),	// restringida a la ruta de la práctica
		$_SERVER['SERVER_NAME'],			// dominio al que se aplica
		false,								// no segura
		true);								// httponly

// crear o continuar la sesión
session_name('namespace_chat');
session_start();

// garantiza que la primera vez se cree una nueva sesión
if (! isset($_SESSION['nick']) || $_SESSION['SID'] != session_id()) {
	$_SESSION = array();
	session_regenerate_id();
	$_SESSION['SID'] = session_id();
	$_SESSION['nick'] = '';
}

include_once 'config.inc.php';

$error = null;
try { // conexión a la BD de mensajes
	$file_db = new PDO("$driver:$database");
}
catch (PDOException $e) {
	die("Línea {$e->getLine()}: {$e->getMessage()}");
}

// modo de error y excepciones
$file_db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

if (isset($_POST['signin'])) {
	if (!isset($_SESSION['email'])) {
		$_SESSION['email'] = isset($_POST['email']) ? htmlspecialchars($_POST['email']) : null;
	}
	
	if (!isset($_SESSION['passwd'])) {
		$_SESSION['passw'] = isset($_POST['passw']) ? md5($_POST['passw']) : null;
	}
}

if (isset($_POST['nick'])) {
	$_SESSION['nick'] = isset($_POST['nick']) ? htmlspecialchars($_POST['nick']) : null;
}

if (isset($_SESSION['email']) && isset($_SESSION['passw'])) {			
	// comprobación de usuario, contraseña y nick opcional
	$select = $file_db->prepare("SELECT iduser, email, password, nick  FROM " .
			                    "Users WHERE email = :email");
	$select->bindParam(':email', $_SESSION['email'], PDO::PARAM_STR);
	$select->execute();
	$result = $select->fetchAll();	

	if (count($result) == 0) { // dar de alta si el nick está disponible
		if (! empty($_SESSION['nick'])) {
			if (isset($_POST['nick'])) { // el nick debe de ser único
				$select = $file_db->prepare("SELECT iduser FROM " .
						                    "Users WHERE nick = :nick");
				$select->bindParam(':nick', $_SESSION['nick'], PDO::PARAM_STR);
				$select->execute();
				$result = $select->fetchAll();
				
				if (count($result) != 0) { // nick duplicado
					$error = "El nick " . $_SESSION['nick'] . " ya se está utilizando.";
					$_SESSION['nick'] = '';
				}
				else {
					$insert = $file_db->prepare('INSERT INTO Users (email, password, nick, connect) ' .
							'VALUES (:email, :passw, :nick, :connect)');
					$insert->bindParam(':email', $_SESSION['email'], PDO::PARAM_STR);
					$insert->bindParam(':passw', $_SESSION['passw'], PDO::PARAM_STR);
					$insert->bindParam(':nick', $_SESSION['nick'], PDO::PARAM_STR);
					$insert->bindValue(':connect', 1, PDO::PARAM_INT);
					$insert->execute();
					
					$_SESSION['iduser'] = $file_db->lastInsertId();
				}
			}				
		}
	}
	else { // comprobar contraseña y si es necesario solicitar el nick
		if ($result[0]['password'] == $_SESSION['passw']) { // obtener nick
			$_SESSION['nick'] = $result[0]['nick'];
			$_SESSION['iduser'] = $result[0]['iduser'];
		}
		else {
			$_SESSION['nick'] = null;
			$error = "Usuario o contraseña incorrecta.";
		}
	}
}

// cierra la conexión con la base de datos
unset($file_db);
?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es">
<head>
<meta charset="UTF-8"/>
<title>Práctica de AJAX</title>
<link rel="stylesheet" type="text/css" href="chat.css" media="screen"/>
<script src="Chat.js">
</script>
</head>
<?php
if (! isset($_SESSION['email']) || ! isset($_SESSION['passw'])) {
?>
<body>
    <header>
        <h1>Práctica de AJAX</h1>   
    </header>
<?php
    if (isset($error)) {
        echo "    <p>$error</p>";
    }
?>
    <form method="post" action="<?php echo $_SERVER['SCRIPT_NAME'];?>" class="signin">
        <fieldset>
            <legend>Signin/Register</legend>
            <label>E-mail:</label><br/>
            <input type="email" name="email" required="required" size="25"/><br/>
            <label>Password:</label></br>
            <input type="password" name="passw" required="required" size="25"/><br/>
            <input type="hidden" name="signin"/>
            <input type="submit" value=" Enviar "/>
            <input type="reset" value="Restablecer"/>
        </fieldset>
    </form>
<?php
}
elseif (isset($_SESSION['nick']) && ! empty($_SESSION['nick'])) {
?>
<body onload="<?php echo "chat.start('" . $_SESSION['nick'] . "')"; ?>">
    <header>
        <h1>Práctica de AJAX</h1>   
    </header>
    <section id="chat_container">
        <h2>
            Nickname <?php echo $_SESSION['nick'];?>
        </h2>
    </section>
    <form method="post" action="exit.php">
        <fieldset>            
            <input type="hidden" value="<?php echo $_SESSION['nick']?>"
             name="logout"/>
            <input type="submit" value=" Salir "/>
        </fieldset>
    </form>
    
<?php
}
elseif (isset($_SESSION['nick']) && empty($_SESSION['nick'])) {
?>
<body>
    <header>
        <h1>Práctica de AJAX</h1>   
    </header>
<?php
    if (isset($error)) {
        echo "    <p>$error</p>";
    }
?>
	<section id="chat_container">
        <h2>
            Nickname
            <form method="post" id="nick" action="<?php echo $_SERVER['SCRIPT_NAME'];?>"
                <fieldset>
		            <input type="text" placeholder="¿tu nick?" required="required"
		             name="nick" onchange="document.querySelector('#nick').submit()"/>
                    <input type="hidden" name="setNick"/>
                </fieldset>
            </form>
       </h2>
    </section>
<?php
}
?>
	<footer>
	</footer>
</body>
</html>