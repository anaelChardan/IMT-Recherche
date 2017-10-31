package fr.imt.inference;

import fr.imt.inference.logger.Logger;
import fr.imt.inference.type.Type;

import java.util.LinkedList;
import java.util.List;

public class ConstraintRepository {

    private Logger logger = new Logger();

    private LinkedList<Tuple<Type, Type>> constraints = new LinkedList<>();

    private static ConstraintRepository ourInstance = new ConstraintRepository();

    public static ConstraintRepository getInstance() {
        return ourInstance;
    }

    private ConstraintRepository() {
    }


    public List<Tuple<Type, Type>> getConstraints(){
        return this.constraints;
    }

    public void uni(Type t1, Type t2) {
        logger.debug("Adding constraint : (" + t1 + ", " + t2 + ")");
        constraints.add(new Tuple<>(t1, t2));
    }

    public boolean isEmpty() {
        return constraints.isEmpty();
    }

    public Tuple<Type, Type> head() {
        return constraints.getFirst();
    }

    public ConstraintRepository tail() {
        return null; // todo -> turn not to be a singleton anymore
    }

    public ConstraintRepository applySubstitution(SubstitutionCollection headSubtitution) {
        return null; // todo -> turn not to be a singleton anymore
    }
}
