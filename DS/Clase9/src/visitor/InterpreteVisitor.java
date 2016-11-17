package visitor;

import java.io.Console;

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

public class InterpreteVisitor implements Visitor {

	@Override
	public Object visit(Asignacion asign, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ConstanteInt cosInt, Object param) {
		System.out.print(cosInt.valor);
		return null;
	}

	@Override
	public Object visit(Division div, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Print p, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Producto prod, Object param) {
		// TODO Auto-generated method stub
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
		System.out.print("Introduce el valor de --> ");
		read.var.accept(this, null);
		System.out.println();
		return null;
	}

	@Override
	public Object visit(Suma sum, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Variable var, Object param) {
		System.out.print(var.name);
		return null;
	}

}