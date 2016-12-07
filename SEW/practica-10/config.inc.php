<?php
define('UNCHANGED', 3);			// Código de estado para indicar que no hay cambios que mostrar
define('JUST_USERS', 2);		// Código de estado para indicar que sólo hay cambio en los usuarios
define('JUST_MESSAGES', 1);		// Código de estado para indicar que sólo hay nuevos mensajes que mostrar
define('MESSAGES_USERS', 0);	// Código de estado para indicar que hay mensajes a mostrar y nuevos usuarios 
define('RECENT', 600);			// Intervalo de tiempo (en segundos) para los mensajes más recientes

// constantes para el acceso a la BD
$driver = 'sqlite';
$database = 'database/chat.sqlite';
$guest = 'guest';
?>