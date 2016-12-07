<?php
// crear o continuar la sesión
session_name('namespace_chat');
session_start();

// comprobación de usuario
if (! isset($_SESSION['nick']))
	exit();

include_once 'config.inc.php';

// comprobación de recepción de menesaje no vacío
if (empty($_POST['message']))
	exit();

try { // conexión a la BD de mensajes 
	$file_db = new PDO("$driver:$database");
}
catch (PDOException $e) {
	die("Línea {$e->getLine()}: {$e->getMessage()}");
}

// Modo de error y excepciones
$file_db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

// preparar SQL para insertar el mensaje recibido
$insert = $file_db->prepare('INSERT INTO messages (user, msg, time) ' .
			    'VALUES (:user, :msg, :time)');
$insert->bindParam(':user', $_SESSION['nick'], PDO::PARAM_STR);
$insert->bindParam(':msg', htmlspecialchars($_POST['message']), PDO::PARAM_STR);
$insert->bindValue(':time', time(), PDO::PARAM_INT);

// inserción
$insert->execute();

// cierra la conexión con la base de datos
unset($file_db);
?>
