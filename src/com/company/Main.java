package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int[][] start = {
                {4, 4, 4, 4},
                {3, 1, 1, 3},
                {3, 3, 3, 3},
                {3, 2, 2, 3},
                {5, 5, 5, 5},
        };


        List<Operator> operators = Arrays.asList(
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

        Node actualNode = new Node(new State(startState), null, null, new HashSet<>());
        List<State> states = new ArrayList<>();


        boolean isThereSolution = false;
        while(true){
            if(actualNode == null){
                break;
            }
            if(isGoalState(actualNode.getState())){
                states.add(actualNode.getState());
                isThereSolution = true;
                break;
            }

            if(states.contains(actualNode.getState())){
                actualNode = actualNode.getParent();
            }else {
                states.add(actualNode.getState());
            }

            List<Operator> applicableOperators = new ArrayList<>();

            for(Operator operator: operators){
                for(int i = 0; i <= 4; i++){
                    for(int j = 0; j <= 3; j++){
                        if(operator.isPreCondition(actualNode.getState(), new int[]{i, j})){
                            boolean isItContain = false;
                            for(Operator usedOperator: actualNode.getAlreadyUsedOperators()){
                                if(usedOperator.equals(new Operator(operator, new int[]{i, j}))){
                                    isItContain = true;
                                }
                            }
                            if(!isItContain){
                                applicableOperators.add(new Operator(operator, new int[]{i, j}));
                            }
                        }
                    }
                }
            }

            if(applicableOperators.size() > 0) {
                Operator actualOperator = new Operator(applicableOperators.get((int) (Math.random() * applicableOperators.size())));

                State state = new State(actualOperator.use(actualNode.getState()));

                Operator maker = new Operator(actualOperator);

                actualNode.setActualOperator(actualOperator);
                Node newNode = new Node(state, actualNode, maker, new HashSet<>());

                actualNode = newNode;
            }else{
                actualNode = actualNode.getParent();
            }

        }

        if (isThereSolution) {
            System.out.println("There is a solution.");
            System.out.println("Different states: " + states.size());
            List<Node> solution = new ArrayList<>();
            while (actualNode != null) {
                solution.add(actualNode);
                actualNode = actualNode.getParent();
            }

            System.out.println("Number of nodes in the solution: " + solution.size());
            for (int i = solution.size() - 1; i > -1; i--) {
                System.out.println(solution.size() - i);
                System.out.println(solution.get(i).getState());

            }
        } else {
            System.out.println("There is no solution.");
        }
    }

    private static boolean isGoalState(State state) {
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
