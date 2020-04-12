package com.company;

import java.util.Set;

public class Node {
    private State state;
    private Node parent;
    private Operator operator;
    public Set<Operator> alreadyUsedOperators;

    public Node(){
        this.state = null;
        this.parent = null;
        this.operator = null;
        this.alreadyUsedOperators = null;
    }

    public Node(Node node){
        this.state = node.state;
        this.parent = node.parent;
        this.operator = node.operator;
        this.alreadyUsedOperators = node.alreadyUsedOperators;
    }

    public Node(State state, Node parent, Operator operator, Set<Operator> alreadyUsedOperators){
        this.state = state;
        this.parent = parent;
        this.operator = operator;
        this.alreadyUsedOperators = alreadyUsedOperators;
    }

    public State getState(){
        return this.state;
    }

    public Node getParent(){
        return this.parent;
    }

    public Set<Operator> getAlreadyUsedOperators(){
        return this.alreadyUsedOperators;
    }

    public void setParent(Node parent){
        this.parent = parent;
    }

    public void setAlreadyUsedOperators(Set<Operator> usedOperators){
        this.alreadyUsedOperators =  usedOperators;
    }

    public void setActualOperator(Operator operator){
        this.alreadyUsedOperators.add(operator);
    }

    @Override
    public String toString(){
        return "State: \n" + this.state.toString() +
                  //"\n\n\n\t szülő: \n" + this.parent.toString()
                 "\nElőállító operátor: \n" + this.operator + "\nHasznált operátorok: \n" + (this.alreadyUsedOperators == null? false : this.alreadyUsedOperators.size())
                ;
    }
}

