package fr.imt.inference;

import fr.imt.inference.AST.Expression;
import fr.imt.inference.AST.Variable;
import fr.imt.inference.type.Type;

import java.util.HashMap;
import java.util.Map;

public class Environment {

    private Map<Expression, Type> env;

    public Environment() {
        this.env = new HashMap<>();
    }


    public void extend(Expression expression, Type type) {
        this.env.put(expression, type);
    }

    public void remove(Expression expression) {
        this.env.remove(expression);
    }

    public Type get(Variable variable) {
        return this.env.get(variable);
    }
}