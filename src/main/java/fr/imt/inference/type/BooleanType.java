package fr.imt.inference.type;

import fr.imt.inference.SubstitutionCollection;

import java.util.HashSet;
import java.util.Set;

public class BooleanType extends TypeLiteral {
    @Override
    public String toString() {
        return "Bool";
    }

    @Override
    public boolean isTypeVariable() {
        return false;
    }

    @Override
    public Set<TypeVariable> getFreeTypeVariables() {
        return new HashSet<>();
    }

    @Override
    public boolean isArrow() {
        return false;
    }

    @Override
    public Type applySubstitution(SubstitutionCollection substitutions) {
        return this;
    }
}
