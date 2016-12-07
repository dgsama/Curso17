<?php
// crear o continuar la sesión
session_name('namespace_chat');
session_start();

// comprobación de usuario
if (! isset($_SESSION['nick']))
	exit();

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

$update = $file_db->prepare("UPDATE Users SET connect = :connect " .
							"WHERE iduser = :iduser");
$update->bindParam(':iduser', $_SESSION['iduser'], PDO::PARAM_STR);
$update->bindValue(':connect', 0, PDO::PARAM_INT);
$update->execute();

$_SESSION['nick'] = '';
$_SESSION = array();
session_regenerate_id(true);

// cierra la conexión con la base de datos
unset($file_db);
header('location: ' . dirname($_SERVER['SCRIPT_NAME']) . '/chat.php');
?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es">
<head>
<meta charset="UTF-8"/>
<title>Práctica de AJAX</title>
</head>
<body>
    <p>Cierra el navegador.</p>
	<footer>
	</footer>
</body>
</html>