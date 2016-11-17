package visitor;

import nodos.Asignacion;
import nodos.ConstanteInt;
import nodos.Division;
import nodos.Print;
import nodos.Producto;
import nodos.Programa;
import nodos.Read;
import nodos.Sentencia;
import nodos.Suma;
import nodos.Variable;

public class PrintVisitor implements Visitor {

	@Override
	public Object visit(Asignacion asigna, Object param) {
		asigna.variable.accept(this, null);
		System.out.print(" = ");
		asigna.expr.accept(this, null);
		System.out.println(";");
		return null;
	}

	@Override
	public Object visit(ConstanteInt cosInt, Object param) {
		System.out.print(cosInt.valor);
		return null;
	}

	@Override
	public Object visit(Division div, Object param) {
		div.left.accept(this, null);
		System.out.print(" / ");
		div.right.accept(this, null);
		return null;
	}

	@Override
	public Object visit(Print p, Object param) {
		System.out.print("print ");
		p.expr.accept(this, null);
		return null;
	}

	@Override
	public Object visit(Producto prod, Object param) {
		prod.left.accept(this, null);
		System.out.print(" * ");
		prod.right.accept(this, null);
		return null;
	}

	@Override
	public Object visit(Programa prog, Object param) {
		for (Sentencia sentencia : prog.sentencias) {
			sentencia.accept(this, null);
		}
		return null;
	}

	@Override
	public Object visit(Read read, Object param) {
		System.out.print("read ");
		read.var.accept(this, null);
		System.out.println(";");
		return null;
	}

	@Override
	public Object visit(Suma sum, Object param) {
		sum.left.accept(this, null);
		System.out.print(" + ");
		sum.right.accept(this, null);
		System.out.println(";");
		return null;
	}

	@Override
	public Object visit(Variable var, Object param) {
		System.out.print(var.name);
		return null;
	}
}
