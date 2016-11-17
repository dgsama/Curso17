package visitor;

import nodos.Asignacion;
import nodos.ConstanteInt;
import nodos.Division;
import nodos.Print;
import nodos.Producto;
import nodos.Programa;
import nodos.Read;
import nodos.Suma;
import nodos.Variable;

public interface Visitor {
	public Object visit(Asignacion asign, Object param);

	public Object visit(ConstanteInt cosInt, Object param);

	public Object visit(Division div, Object param);

	public Object visit(Print p, Object param);

	public Object visit(Producto prod, Object param);

	public Object visit(Programa prog, Object param);

	public Object visit(Read read, Object param);

	public Object visit(Suma sum, Object param);

	public Object visit(Variable var, Object param);

}
