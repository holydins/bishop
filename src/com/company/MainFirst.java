package com.company;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.swing.*;
import javax.tools.StandardLocation;
import java.util.*;

public class MainFirst {

    public static void maintwo() throws InterruptedException {
        /*int[][] start = {
                {4, 4, 4, 4},
                {3, 1, 1, 3},
                {3, 3, 3, 3},
                {3, 2, 2, 3},
                {5, 5, 5, 5},
        };
*/

        /*List<Operator> operators = Arrays.asList(
                //RIGHT UP
                new Operator(1, -1, 1),
                new Operator(1, -1, 2),
                new Operator(1, -1, 3),

                //RIGHT DOWN
                new Operator(1, 1, 1),
                new Operator(1, 1, 2),
                new Operator(1, 1, 3),

                //LEFT DOWN
                new Operator(-1, 1, 1),
                new Operator(-1, 1, 2),
                new Operator(-1, 1, 3),

                //LEFT UP
                new Operator(-1, -1, 1),
                new Operator(-1, -1, 2),
                new Operator(-1, -1, 3)
        );


        State startState = new State(start);
        boolean isThereSolution = false;
        Node actualNode = new Node(new State(startState), null, null, new HashSet<>());

        List<State> states = new ArrayList<>();
        List<Node> nodes = new ArrayList<>();
        Node reference = actualNode;
        int ik = 0;
        while(true){
            if(actualNode.getParent() != null){
                System.out.println(actualNode.getParent().toString());
                System.out.println("############################################");
            }
            //System.out.println(actualNode.toString());
            if(ik++ == 3){break;}
            if(actualNode == null){
                break;
            }
            if(isGoalState(actualNode.getState())){
                isThereSolution = true;
                break;
            }

            if(states.contains(actualNode.getState())){
                actualNode = actualNode.getParent();
            }
            states.add(actualNode.getState());
            nodes.add(actualNode);

            List<Operator> applicableOperators = new LinkedList<>();

            for(Operator operator: operators){
                for(int i = 0; i <= 4; i++){
                    for(int j = 0; j <= 3; j++){
                        if(operator.isPreCondition(actualNode.getState(), new int[]{i, j}) &&
                                !actualNode.getAlreadyUsedOperators().contains(operator)){
                            applicableOperators.add(new Operator(operator, new int[]{i, j}));
                        }
                    }
                }
            }

            if(applicableOperators.size() > 0){
                Operator actualOperator = applicableOperators.get(0 );
                State state = new State(actualOperator.use(actualNode.getState()));
                Node parent = new Node(actualNode);
                Operator maker = actualOperator;
                Set<Operator> newSet = new HashSet<>();
                Node newNode = new Node(state, parent, maker, newSet);
                //newNode.setState(new State(actualOperator.use(actualNode.getState())));
                //newNode.setParent(actualNode);


                Set<Operator> set = actualNode.getAlreadyUsedOperators();
                if(set == null) {
                    set = new HashSet<>();
                }
                set.add(actualOperator);
                actualNode.setAlreadyUsedOperators(set);
                //előállító
                //newNode.setOperator(actualOperator);
                //newNode.setAlreadyUsedOperators(new HashSet<>());
                actualNode = newNode;
                if(actualNode.getParent() != null){
                    //System.out.println("itt");
                }else{
                    System.out.println("valamiért null ");
                }

            }else if(actualNode.getParent() != null){
                actualNode = actualNode.getParent();
            }else{
                System.out.println("else a forban");
                actualNode = null;
            }

        }

        /*System.out.println("NODE KIÍRÁS");
        for(Node node: nodes){
            System.out.println(node);
        }*/

        /*System.out.println("STATE KIÍRÁS");
        for(State state: states){
            System.out.println(state);
        }

        if (isThereSolution) {
            System.out.println("Van megoldás");
            System.out.println("states size: " + states.size());
            List<Node> solution = new ArrayList<Node>();
            while (actualNode.getParent() != null) {
                solution.add(actualNode);
                actualNode = actualNode.getParent();
            }
            for (int i = 0; i <= solution.size(); i++) {
                System.out.println(solution.get(i).getState());
            }
        } else {
            System.out.println("Sajnos nincs megoldás");
        }
        System.out.println("mérete:" + states.size());
    }


    /**
     * Megvizsgáljuk, hogy a legfelső sorban, csak a fehér bábúk vannak, illetve, hogy a legalsó sorban csak a fekete bá-
     * búk helyezkednek e el. Ha teljesül, akkor igazat ad vissza.
     *
     * @param state what represent our figures position.
     * @return true if its a goal state.
     */
    /*private static boolean isGoalState(State state) {
        int indexOfBlack = 4;
        int indexOfWhite = 0;
        int[][] stateTest = state.getMatrix();
        for (int i = 0; i <= 3; i++) {
            if (stateTest[indexOfWhite][i] != 5 || stateTest[indexOfBlack][i] != 4) {
                return false;
            }
        }
        return true;
    }
}
    /*
    while(true){
            /*System.out.println("states size: " + states.size());
            System.out.println("előállító operátor: " + actualNode.getOperator());
            System.out.println("parent isnull? : " + actualNode.getParent());
            System.out.println("Már használt operátorok mérete: " + actualNode.getAlreadyUsedOperators().size());
            System.out.println(actualNode.getState());
            if(actualNode.getParent() != null){
                System.out.println("SZÜLŐ HASZNÁLT OPERÁTOR MÉRET");
                System.out.println(actualNode.getParent().getAlreadyUsedOperators().size());
            }
            if(actualNode == null){
        break;
    }

            if(isGoalState(actualNode.getState())){
        isThereSolution = true;
        break;
    }

            if(states.contains(actualNode.getState())){
        actualNode = actualNode.getParent();
    }
            states.add(actualNode.getState());
            nodes.add(actualNode);

    List<Operator> applicableOperators = new LinkedList<>();

            for(Operator operator: operators){
        for(int i = 0; i <= 4; i++){
            for(int j = 0; j <= 3; j++){
                if(operator.isPreCondition(actualNode.getState(), new int[]{i, j}) &&
                        !actualNode.getAlreadyUsedOperators().contains(operator)){
                    applicableOperators.add(new Operator(operator, new int[]{i, j}));

                }
            }
        }
    }

            if(applicableOperators.size() > 0){
        Operator actualOperator = applicableOperators.get(applicableOperators.size() - 1);
        Node newNode = new Node();
        newNode.setState(new State(actualOperator.use(actualNode.getState())));
        newNode.setParent(new Node(actualNode));

        List<Operator> usedOperators = new ArrayList<>();

        usedOperators.add(actualOperator);
        actualNode.setActualOperator(actualOperator);

        //előállító
        newNode.setOperator(actualOperator);
        newNode.setAlreadyUsedOperators(new ArrayList<>());
                /*for(Operator op: applicableOperators){
                    System.out.println("op: " + op);
                }
        actualNode = newNode;
    }else if(actualNode.getParent() != null){
        actualNode = actualNode.getParent();
    }else{
        System.out.println("else a forban");
        actualNode = null;
    }*/

}
}