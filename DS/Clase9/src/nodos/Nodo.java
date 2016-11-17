package nodos;

import visitor.Visitor;

public interface Nodo {
	public Object accept(Visitor v, Object param);
}
