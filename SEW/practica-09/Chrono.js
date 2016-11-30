const Chrono = (function() {
	
	// clase para manejar el tiempo transcurrido
	class Time { . . . }
	
	class Events {
		constructor() {
		}
		// Pone las propiedades que se indiquen ('start', 'stop' o 'reset')
		// a true y el resto a false
		setEvents(...ev) {
		}
	}
	
	class State {
		// De forma opcional se pueden proporcionar los valores
		// de los botones actuables del cronómetro. Por defecto,
		// éstos toman los valores del array actions
		constructor(...buttonValues) {
			if (buttonValues.length != 3)
				buttonValues = actions;
			. . .
		}
		
		// Arranca el cronómetro y actualiza el estado de las acciones que
		// se pueden realizar sobre éste. Cambia el estado cambia la vista
		start() {
			if (this.events.start) { // se puede arrancar
			}
		}
		
		// Para el cronómetro y actualiza el estado de las acciones que se
		// pueden realizar sobre éste. Cambia el estado cambia la vista
		stop() {
			if (this.events.stop) { // se puede parar
			}
		}	
		
		// Reinicia el cronómetro y actualiza el estado de las acciones que
		// se pueden realizar sobre éste. Cambia el estado cambia la vista.
		reset() {
			if (this.events.reset) { // se puede reiniciar
			}
		}
		
		// Incrementa el tiempo del cronómetro. Cambia el estado
		// cambia la vista
		incr_time() {
		}
		
		// Retorna el nodo raíz de la interfaz de la vista
		// del cronómetro
		node() {
		}
		
		// Retorna la referencia del cronómetro identificado según
		// se especifica 
		static instance(idChrono) {
			return State.instances[idChrono];
		}
	}
	
	// Campos de clase (estáticos)
	Object.defineProperties(State, {
		'instances': {
			value: {},
			configurable: false,
			enumerable: false,
			writable: false
		},
		'counter': {
			value: 0,
			configurable: false,
			enumerable: false,
			writable: true
		}
	});

	// Clase para crear la interfaz de un cronómero
	class View {
		
		// Requiere la referencia del objeto cronómetro y los valores
		// de los botones actuables
		constructor(objChrono, buttonValues) {
			const xmlns = 'http://www.w3.org/1999/xhtml'; // espacio de nombres xhtml
			this.objChrono = objChrono;					  // referencia del cronómetro
			// objeto raíz del subárbol de la vista del cronómetro
			this.objDiv = document.createElementNS(xmlns, 'div');
			this.objDiv.setAttribute('id', this.objChrono.strObject); // atributo id
			this.objDiv.setAttribute('class', 'chrono');			  // atributo class
			// área de texto para la hora de sólo lectura
			this.objText = document.createElementNS(xmlns, 'input');
			this.objText.setAttribute('size', 11);				 // atributo size
			this.objText.setAttribute('type', 'text');			 // atributo type
			this.objText.setAttribute('readondly', 'readondly'); // readonly
			this.objDiv.appendChild(this.objText);				 // añadir al div
			// salto de línea
			this.objDiv.appendChild(document.createElementNS(xmlns, 'br'));
			this.objButton = new Object();
			for (let index in actions) {
				const action = actions[index];
				// crear los botones y añadir al div
			}
		}
		
		// Actualiza la vista del cronómetro: tiempo y botones.
		// Los botones se deshabilitan si la operación asociada no se puede realizar.
		// Necesita el tiempo transcurrido en formato de cadena: hh:mm:ss.t e indicar
		// cuando el cambio de estado no se produce por intervención del usuario 
		show(time_str, userAction = true) {
		}
		
		// Retorna el nodo raíz de la interfaz del cronómetro
		root() {
		}
	}
	
	/* Funciones y variables auxiliares */
	
	// retorna la cadena de dos dígitos terminada con el caracter
	// ch especficado correspondiente al entero n dado.
	function int2str (n, ch) { 
		let s = n < 10 ? "0" : "";
		return s + `${n}${ch}`;				
	}
	
	// retorna un identificador único para cada cronómeto
	function idChrono () {
		return "chrono_" + State.counter++;
	}
	
	const actions = ["start", "stop", "reset"]; // acciones y etiquetas por defecto de los botones
	
	return State; // se exporta el estado del cronómetro	
})();

/*
 * Para crear un cronómetro:
 * 
 * 		var a = new Chrono();
 *		var b = new Chrono("arrancar", "parar", "reiniciar"); 
 */