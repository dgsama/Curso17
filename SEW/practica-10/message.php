<?php
// crear o continuar la sesión
session_name('namespace_chat');
session_start();

// comprobación de usuario
if (! isset($_SESSION['nick']))
	exit();

include_once 'config.inc.php';

// cabeceras HTTP para el documento que se enviará al cliente
header('Content-Type: text/xml');
header('Cache-Control: no-cache');

// identificador del último mensaje enviado al cliente
$id = isset($_GET['id']) ? $_GET['id'] : 0;

try { // conexión a la BD de mensajes 
	$file_db = new PDO("$driver:$database");
}
catch (PDOException $e) {
	die("Línea {$e->getLine()}: {$e->getMessage()}");
}

// Modo de error y excepciones
$file_db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

// preparación de la consulta a la BD de los mensajes recientes recibidos
// (en los últimos RECENT segundos) de todos los clientes desde la última
// vez que el cliente realizó la misma consulta.
$select = $file_db->prepare('SELECT id, user, msg, time FROM messages WHERE ' .
		                 	  'id > :id AND time >= :time ORDER BY id ASC');
$select->bindParam(':id', $id, PDO::PARAM_INT);
$select->bindValue(':time', time()-RECENT, PDO::PARAM_INT);

// ejecución de la consulta SQL 
$select->execute();

// filas obtenidas como resultado de la consulta a la BD 
$result = $select->fetchAll(PDO::FETCH_OBJ);

// preparación de la consulta a la BD de los usuarios conectados
$selectUsers = $file_db->prepare('SELECT nick FROM Users WHERE ' .
		                         'connect = :connect ORDER BY nick');
$selectUsers->bindValue(':connect', 1, PDO::PARAM_INT);

// ejecución de la consulta SQL
$selectUsers->execute();

// filas obtenidas como resultado de la consulta a la BD 
$resultUsers = $selectUsers->fetchAll();

$users = array();
foreach ($resultUsers as $index => $avalue):
	$users[$index] = $avalue['nick'];
endforeach;

$jsonUsers = json_encode($users);
$showUsers = ! isset($_SESSION['users']) || strcmp($_SESSION['users'], $jsonUsers) != 0;

if ($showUsers): // actualizar la variable de sesión
	$_SESSION['users'] = $jsonUsers;
endif;

// cierra la conexión con la base de datos
unset($file_db);

$time = time(); // hora UNIX del servidor 
$status = MESSAGES_USERS;
if (! $showUsers && empty($result)):
	$status = UNCHANGED;
elseif (empty($result)):
	$status = JUST_USERS;
elseif (! $showUsers):
	$status = JUST_MESSAGES;
endif;

// generación del documento XML con los mensajes
print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
?>
<response xmlns="http://156.35.163.103/sew/messagesSchema"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://156.35.163.103/sew/messagesSchema messages.xsd">
    <status><?php echo $status; ?></status>
    <time><?php echo $time; ?></time>
<?php
if ($status == JUST_USERS || $status == MESSAGES_USERS):
?>
    <users>
<?php
	foreach ($users as $nick):
?>
        <nick><?php echo $nick;?></nick>
<?php
	endforeach;
?>
    </users>
<?php
else:
?>
	<users/>
<?php 
endif;

if ($status == JUST_MESSAGES || $status == MESSAGES_USERS):
?>
    <messages>
<?php
	foreach ($result as $fila): // tratar siguiente mensaje 
?>
		<message id="<?php echo $fila->id; ?>" user="<?php echo $fila->user; ?>"
		time="<?php echo date("H:i:s",$fila->time); ?>"><?php
			echo $fila->msg; ?></message>
<?php
	endforeach;
?>
	</messages>
<?php
else:
?>
    <messages/>
<?php 
endif;
?>
</response>
