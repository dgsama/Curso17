function muestra(strID) {
	var str = new String(document.getElementById(strID).getElementsByTagName('code')[0].innerHTML);
	var nw = window.open(); // nueva ventana

	// Sustituir entidades, quitar resaltados y
	// volcar el código en la nueva ventana
	str = str.replace(/<strong class="marcado">/g, "");
	str = str.replace(/<\/strong>/g, "");
	str = str.replace(/&lt;/g, "<");
	str = str.replace(/&gt;/g, ">");
	str = str.replace(/&amp;/g, "&");

	nw.document.open();
	nw.document.write(str);
	nw.document.close();
}

function setCookie (name, value, days) {
	var expire = new Date();
	var cookie = name + "=" + escape(value);

	expire.setDate(expire.getDate() + days);
	if (days != null)
		cookie += "; expires=" + expire.toUTCString();
	
	document.cookie = cookie;
}

function getCookie (name) {
	if (document.cookie.length > 0) {
		start = document.cookie.indexOf(name + "=");
		if (start != -1) {
			start += name.length + 1;
			end = document.cookie.indexOf(";", start);
			if (end != -1)
				return unescape(document.cookie.substring(start, end));
			else
				return unescape(document.cookie.substring(start));
		}
	}
	
	return "";
}

function initSize() {
	// restablece el tamaño de la fuente de los ejemplos
	if (size != 1.0) {
		size = 1.0;
		fSize(0);
	}
}

function fSize(incr) {
	var newSize = size + incr;
	if (newSize < 0.4 || newSize > 3.0) // retorna sin cambios
		return;
	
	var css = document.styleSheets[0];
	size = newSize;

	if (flagAddRule) // borrar la regla previa
		css.deleteRule(css.cssRules.length-1);
	
	css.insertRule('code {font-size: ' + size + 'em;}', css.cssRules.length);
	flagAddRule = true;
	setCookie('exampleFontSize', size, 30);
}

function sizeFonts(strID) {
	// muestra el ejemplo con el tamaño de fuente elegido (cookie)
	var sizeText = getCookie('exampleFontSize');
	if (sizeText) {
		size = parseFloat(sizeText);
		if (size != 1.0)
			fSize(0);
	}

	// añade al documento los botones de interacción para:
	//	  1. Poder modificar el tamaño de la fuente del ejemplo
	//	  2. Presentar el ejemplo
	var objP = document.createElementNS('http://www.w3.org/1999/xhtml', 'p');
	objP.setAttribute('class', 'fuentes');
	var vInput = [{label: "A+", func: "fSize(+0.2)"},
	              {label: "A-", func: "fSize(-0.2)"},
	              {label: "reset", func: "initSize()"},
	              {label: "Muestra", func: "muestra('" + strID + "')"}
	             ];
	
	var objInput = new Array();
	for (var i = 0; i < vInput.length; i++) {
		objInput[i]= document.createElementNS('http://www.w3.org/1999/xhtml', 'input');
		objInput[i].setAttribute('type', 'button');
		objInput[i].setAttribute('value', vInput[i].label);
		objInput[i].setAttribute('onclick', vInput[i].func);
	}

	objInput[vInput.length-1].setAttribute('class', 'der');
	objP.appendChild(objInput[vInput.length-1]);  // botón de muestra
	
	var objSmall = document.createElementNS('http://www.w3.org/1999/xhtml', 'small');
	
	objSmall.appendChild(document.createTextNode('Tamaño fuente'));
	objP.appendChild(objSmall);
	objP.appendChild(document.createElementNS('http://www.w3.org/1999/xhtml', 'br'));
	
	for (var i=0; i < vInput.length-1; i++)  // botones de modificación de la fuente
		objP.appendChild(objInput[i]);
	
	document.body.insertBefore(objP, document.getElementById('doc_html'));
}

var size = 1.0, flagAddRule = false;
sizeFonts('doc_html');
