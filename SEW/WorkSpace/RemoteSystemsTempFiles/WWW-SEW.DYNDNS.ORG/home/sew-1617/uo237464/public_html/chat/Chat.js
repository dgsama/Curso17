'use strict';

const chatSettings = {
    TIME_REQUEST: 2000, // tiempo en milisegundos entre consultas al servidor
    MAX_MESSAGES: 30, // número de mensajes que se muestran en el cliente
    EDITOR_WIDTH: 50, // anchura del editor de mensajes
    EDITOR_HEIGHT: 6, // altura del editor de mensajes
}

const chat = (function(config) {
    // Si el texto especificado incluye URLs http (o https)
    // lo transforma en un hipervínculo.
    function URLtoHTMLLink(text) {
        // URLs que comienzan por http:// o https://
        const pattern1 = /(\b(https?):\/\/[0-9a-zA-Z]([-.\w]*[0-9a-zA-Z])*(:(0-9)*)*(\/?)([a-zA-Z0-9\-\.\?\,\'\/\\\+&%\$#_=]*)?)/gim;
        let replacedText = text.replace(pattern1,
            '<a href="$1" target="_blank">$1</a>');

        // URLs que comienzan con www. (sin // antes)
        const pattern2 = /(^|[^\/])(www\.[\S]+(\b|$))/gim;
        replacedText = replacedText.replace(pattern2,
            '$1<a href="http://$2" target="_blank">$2</a>');

        return replacedText;
    }

    const chat = {
        // id
        lastId: 0,
        // Petición asíncrona de los últimos mensajes
        requestMessages: function() {
            const mensaje = new window.XMLHttpRequest();
            // se prepara para recibir el mensaje del chat posterior
            mensaje.open('GET', 'message.php?id=' + chat.lastId, true);
            // mostrar la petición
            mensaje.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    chat.showMessages(this.responseXML);
                }
            };
            // enviar petición
            mensaje.send(null);
        },
        // Envío asíncrono de un mensaje del usuario
        sendMessage: function() {
            const mensaje = new window.XMLHttpRequest();
            // Prepara la peticion
            mensaje.open('POST', 'insert.php', true);
            // Limpiat cuando se reciba el mensaje en el servidor
            mensaje.onreadystatechange = function() {
                if (this.readyState == 4) {
                    chat.view.cleanEditor();
                }
            };
            // enviar la peticion
            mensaje.setRequestHeader('Content-Type',
                'application/x-www-form-urlencoded');
            mensaje.send('message=' + chat.view.getMessage());
        },
        // Muestra los mensajes del documento XML y establece la siguiente
        // petición
        // de mensajes al servidor a los config.TIME_REQUEST milisegundos
        showMessages: function(messagesXML) {
            chat.view.show(messagesXML);
            window.setTimeout('chat.requestMessages()', config.TIME_REQUEST);

        },
        // Inicializa el chat: crea la interfaz, añade ésta al contenedor
        // identificado por idContainer y realiza la primera petición de
        // mensajes al servidor
        init: function(nickname) {
            chat.nicknames = new Array(nickname);
            chat.view.create(); // crea la interfaz del chat
            chat.requestMessages(); // petición inicial de los últimos mensajes
            // añade la interfaz al documento
            document.querySelector('section').appendChild(chat.view.root());
            // limpia el editor
            chat.view.cleanEditor();
            // añadir mensajes en blanco para ocupar el espacio disponible
            // lo que permitirá mostrár los mensajes en la parte inferior
            // del contenedor
            const containerMessages = document.querySelector('div.messages')
            while (containerMessages.scrollTop == 0) {
                chat.view.append(' ', ' ');
                chat.view.history--;
            }
        },
        view: {
            // Número de mensajes que contiene la vista
            history: 0,
            // Crea la interfaz del chat: un bloque genérico (div) que contendrá
            // un div con la vista de mensajes y un formulario para el envío de
            // mensajes del usuario. El formulario, constará de un fieldset con
            // leyenda y un área de texto.
            create: function() {
                const xmlns = 'http://www.w3.org/1999/xhtml'; // espacio de
                // nombres XHTML
                this.container = document.createElementNS(xmlns, 'div'); // contenedor
                // para
                // la
                // interfaz
                this.container.setAttribute('class', 'chat');
                const containerMessages = document.createElementNS(xmlns, 'div'); // vista
                // de
                // mensajes
                const containerUsers = document.createElementNS(xmlns, 'div');
                containerUsers.setAttribute('class', 'users');
                const objH2 = document.createElementNS(xmlns, 'h2');
                const objDiv = document.createElementNS(xmlns, 'div');
                containerMessages.setAttribute('class', 'messages');
                const editor = document.createElementNS(xmlns, 'textarea'); // área
                // de
                // texto
                // del
                // formulario
                const formObj = document.createElementNS(xmlns, 'form'); // formulario
                // para
                // el
                // envío
                // de
                // mensajes
                const fieldsetObj = document.createElementNS(xmlns, 'fieldset');
                const legendObj = document.createElementNS(xmlns, 'legend');
                formObj.setAttribute('method', 'post');
                formObj.setAttribute('action', 'javascript:chat.sendMessage()');
                legendObj.appendChild(document.createTextNode('Editar mensaje'));
                fieldsetObj.appendChild(legendObj);
                editor.setAttribute('rows', config.EDITOR_HEIGHT);
                editor.setAttribute('cols', config.EDITOR_WIDTH);
                editor.setAttribute('onkeypress', 'chat.submitMessage(event)');
                fieldsetObj.appendChild(editor);
                formObj.appendChild(fieldsetObj);
                objDiv.appendChild(containerMessages);
                objDiv.appendChild(formObj);
                objH2.appendChild(document.createTextNode('Usuarios'));
                containerUsers.appendChild(objH2);
                containerUsers.appendChild(document.createElementNS(xmlns, 'ul'));
                this.container.appendChild(objDiv);
                this.container.appendChild(containerUsers);
            },
            // Retorna el contenedor de la interfaz (nodo raíz)
            root: function() {
                return this.container;
            },
            // Retorna el contenido del área de texto (mensaje a enviar)
            getMessage: function() {
                return document.querySelector('#chat_container textarea').value;
            },
            // Limpia el contenido del área de texto y pone el foco en éste
            cleanEditor: function() {
                const editor = document.querySelector('section textarea');
                editor.value = '';
                editor.focus();
            },
            // Procesa un documento (objeto) XML con los mensajes y muestra
            // éstos en la interfaz (en la vista de mensajes), indicando la
            // hora en que se recibió y el usuario que lo envío.
            show: function(messagesXML) {
                const status = Number.parseInt(messagesXML.getElementsByTagName('status')[0].firstChild.nodeValue);
                if (status <= 1 && messagesXML !== null) {
                    const mensajes = messagesXML.getElementsByTagName('messages')[0]
                        .getElementsByTagName('message');

                    for (let i = 0; i < mensajes.length; i++) {
                        const m = mensajes[i];
                        let id = Number.parseInt(m.getAttribute('id'));
                        if (id > chat.lastId) {
                            chat.lastId = id;
                            this.append(`[${m.getAttribute('time')}]${m.getAttribute('user')}=>`,
                                m.firstChild.nodeValue);
                        }
                    }
                }
            },
            // Añade un mensaje a la vista de mensajes de la interfaz.
            append: function(prompt, text) {
                const xmlns = 'http://www.w3.org/1999/xhtml'; // espacio de
                // nombres XHTML
                const containerMessages = document.querySelector('div.messages');
                const msgObj = document.createElementNS(xmlns, 'div');
                const userTimeObj = document.createElementNS(xmlns, 'p');
                const messageReceivedObj = document.createElementNS(xmlns, 'pre');
                userTimeObj.setAttribute('class', 'user');
                messageReceivedObj.setAttribute('class', 'msg');
                userTimeObj.appendChild(document.createTextNode(prompt));
                messageReceivedObj.innerHTML = URLtoHTMLLink(text);
                msgObj.appendChild(userTimeObj);
                msgObj.appendChild(messageReceivedObj);
                containerMessages.appendChild(msgObj);
                // mover la barra de scroll
                containerMessages.scrollTop = containerMessages.scrollHeight -
                    containerMessages.clientHeight;
            },
            // Recibe un evento de teclado. Si la tecla es <RETURN>
            // envía los datos del formlario
            submitEnter: function(event) {
                let isEnter = false; // cierto si la tecla pulsada es
                // <RETURN>

                if (event.key)
                    isEnter = event.key == 'Enter';

                // <Carriage Return> sin <Shift>
                if (isEnter && !event.shiftKey) // sólo <RETURN>
                    document.querySelector('section form').submit();
            }
        }
    };

    return {
        'start': chat.init,
        'sendMessage': chat.sendMessage,
        'showMessages': chat.showMessages,
        'requestMessages': chat.requestMessages,
        'submitMessage': chat.view.submitEnter
    };

})(chatSettings);

Object.defineProperties(chat, {
    start: {
        configurable: false,
        enumerable: false,
        writable: false
    },
    sendMessage: {
        configurable: false,
        enumerable: false,
        writable: false
    },
    showMessages: {
        configurable: false,
        enumerable: false,
        writable: false
    },
    requestMessages: {
        configurable: false,
        enumerable: false,
        writable: false
    },
    submitMessage: {
        configurable: false,
        enumerable: false,
        writable: false
    },
});