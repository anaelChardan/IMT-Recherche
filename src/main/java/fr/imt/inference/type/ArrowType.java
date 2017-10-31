package fr.imt.inference.type;

import fr.imt.inference.SubstitutionCollection;

import java.util.HashSet;
import java.util.Set;

public class ArrowType implements Type {
    public final Type left;
    public final Type right;

    public ArrowType(Type left, Type right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return this.left + " -> " + this.right;
    }

    @Override
    public boolean isTypeVariable() {
        return false;
    }

    @Override
    public Set<TypeVariable> getFreeTypeVariables() {
        Set<TypeVariable> leftFTV = left.getFreeTypeVariables();
        Set<TypeVariable> rightFTV = right.getFreeTypeVariables();

        Set<TypeVariable> result = new HashSet<>(leftFTV);
        result.addAll(rightFTV);

        return result;
    }

    @Override
    public boolean isArrow() {
        return true;
    }

    @Override
    public Type applySubstitution(SubstitutionCollection substitutions) {
        Type newLeft = left.applySubstitution(substitutions);
        Type newRight = right.applySubstitution(substitutions);
        return new ArrowType(newLeft, newRight);
    }
}
