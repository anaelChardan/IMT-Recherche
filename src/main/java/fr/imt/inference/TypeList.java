package fr.imt.inference;

import fr.imt.inference.type.Type;

import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;


// needs a iface ?
public class TypeList {

    private LinkedList<Type> types;

    public TypeList() {
        this.types = new LinkedList<>();
    }

    public TypeList(Type... types) {
        this.types = new LinkedList<>();
        Collections.addAll(this.types, types);
    }

    public TypeList(LinkedList<Type> types) {
        this.types = types;
    }


    public boolean isEmpty() {
        return types.isEmpty();
    }

    public Type head() {
        return types.poll();
    }

    public TypeList applySubstitution(SubstitutionCollection substitutions) {
        LinkedList<Type> typesSubstitued =
                new LinkedList<>(this.types.stream()
                    .map(type -> type.applySubstitution(substitutions))
                    .collect(Collectors.toList()));

        return new TypeList(typesSubstitued);
    }

    public TypeList add(Type... types) {
        LinkedList<Type> list = new LinkedList<>();
        Collections.addAll(list, types);
        return new TypeList(list);
    }
}