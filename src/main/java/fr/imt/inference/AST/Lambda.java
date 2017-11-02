package fr.imt.inference.AST;

import fr.imt.inference.ConstraintRepository;
import fr.imt.inference.Environment;
import fr.imt.inference.FreshVariableProvider;
import fr.imt.inference.logger.Logger;
import fr.imt.inference.type.ArrowType;
import fr.imt.inference.type.Type;
import fr.imt.inference.type.TypeVariable;

public class Lambda implements Expression {

    public final Variable identifier;
    public final Expression body;
    private Logger logger = new Logger();

    public Lambda(Variable identifier, Expression body) {
        this.identifier = identifier;
        this.body = body;
    }

    @Override
    public Type infer(Environment env, ConstraintRepository constraintRepository) {
        logger.debug("Current exp " + this.toString());

        TypeVariable resultType = FreshVariableProvider.getInstance().provideFresh();

        env.extend(this.identifier, resultType);

        Type bodyType = this.body.infer(env, constraintRepository);

        logger.debug("Type for body `" + this.body + "` is " + bodyType);

        env.remove(this.identifier);

        return new ArrowType(resultType, bodyType);
    }


    @Override
    public String toString() {
        return "(\\" + this.identifier + " -> " + this.body + ")";
    }

}
