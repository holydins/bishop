package com.company;

import java.util.Objects;


public class Operator {

    private int horizontal;
    private int vertical;
    private int step;
    private int[] figure;

    public Operator(Operator operator){
        this.horizontal = operator.horizontal;
        this.vertical = operator.vertical;
        this.step = operator.step;
        this.figure = operator.figure;
    }

    public Operator(int horizontal, int vertical, int step){
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.step = step;
    }

    public Operator(Operator operator, int[] figure){
        this.horizontal = operator.horizontal;
        this.vertical = operator.vertical;
        this.step = operator.step;
        this.figure = figure;
    }

    public Operator(int horizontal, int vertical, int step, int[] figure){
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.step = step;
        this.figure = figure;
    }


    public boolean isPreCondition(State state, int[] figure){
        int[][] stateArray = state.getMatrix();
        if(stateArray[figure[0]][figure[1]] != 4 &&
                stateArray[figure[0]][figure[1]] != 5 ){
            return false;
        }

        if(isBlack(stateArray[figure[0]][figure[1]])){
            return isApplicable(stateArray, figure, true);
        }else if(isWhite(stateArray[figure[0]][figure[1]])){
            return isApplicable(stateArray, figure, false);
        }
        return false;
    }

    public boolean isBlack(int value){
        return value == 4 ? true : false;
    }

    public boolean isWhite(int value){
        return value == 5 ? true : false;
    }

    private boolean isApplicable(int[][] stateArray ,int[] figure, boolean black) {
        if(black){
            return isCorrectValue(stateArray, figure, true);
        }else{
            return isCorrectValue(stateArray, figure, false);
        }
    }

    private boolean isCorrectValue(int[][] stateArray, int[] figure, boolean black) {
        int newXCoordinate = figure[0] + this.horizontal * this.step;
        int newYCoordinate = figure[1] + this.vertical * this.step;

        if(isInside(newXCoordinate, newYCoordinate)){
            if(black){
                return stateArray[newXCoordinate][newYCoordinate] == 1 ? true : false;
            }else{
                return stateArray[newXCoordinate][newYCoordinate] == 2 ? true : false;
            }
        }else{
            return false;
        }
    }


    public boolean isInside( int newX, int newY){
        if(0 <= newX && newX <= 4 &&
                0 <= newY && newY <= 3){
            return true;
        }else{
            return false;
        }
    }

    public State use(State state) {
        int[][] stateArray = new int[5][4];

        for(int i = 0; i <= 4; i++){
            for(int j = 0; j <= 3; j++){
                stateArray[i][j] = state.getMatrix()[i][j];
            }
        }


        int indexOfRow = this.figure[0] + this.horizontal * this.step;
        int indexOfColumn = this.figure[1] + this.vertical * this.step;

        stateArray[indexOfRow][indexOfColumn] = stateArray[figure[0]][figure[1]];
        stateArray[figure[0]][figure[1]] = 0;
        return new State(makeNewMatrix(stateArray));
    }

    public int[][] makeNewMatrix(int[][] matrix){

        for(int i = 0; i <= 4; i++){
            for(int j = 0; j <= 3; j++){
                if(matrix[i][j] != 4 && matrix[i][j] != 5){
                    matrix[i][j] = 0;
                }
            }
        }
        for(int i = 0; i <= 4; i++){
            for(int j = 0; j <= 3; j++){

                if(isFigure(matrix[i][j])){
                    boolean black = isBlack(matrix[i][j]);
                    boolean white = isWhite(matrix[i][j]);

                    for(int s = 1; s <= 3; s++){
                        //RIGHT DOWN
                        if(moveAbleToRightDown(i, j, s)){
                            int currentValue = matrix[i + s][j + s];
                            if(black){
                                if(currentValue == 0 || currentValue == 2){
                                    matrix[i + s][j + s] += 1;
                                }else if(currentValue == 5){
                                    System.err.println("Error makeNewMatrix");
                                }
                            }
                            if(white){
                                if(currentValue == 0 || currentValue == 1){
                                    matrix[i + s][j + s] += 2;
                                }else if(currentValue == 4){
                                    System.err.println("Error makeNewMatrix");
                                }
                            }

                        }

                        //LEFT DOWN
                        if(moveAbleToLeftDown(i, j, s)){
                            int currentValue = matrix[i + s][j - s];

                            if(black){
                                if(currentValue == 0 || currentValue == 2){
                                    matrix[i + s][j - s] += 1;
                                }else if(currentValue == 5){
                                    System.err.println("Error makeNewMatrix");
                                }
                            }
                            if(white){
                                if(currentValue == 0 || currentValue == 1){
                                    matrix[i + s][j - s] += 2;
                                }else if(currentValue == 4){
                                    System.err.println("Error makeNewMatrix");
                                }
                            }
                        }
                        //LEFT UP
                        if(moveAbleToLeftUp(i, j, s)){
                            int currentValue = matrix[i - s][j - s];
                            if(i == 1 && j == 1){
                            }
                            if(black){
                                if(currentValue == 0 || currentValue == 2){
                                    matrix[i - s][j - s] += 1;
                                }else if(currentValue == 5){
                                    System.err.println("Error makeNewMatrix");
                                }
                            }
                            if(white){
                                if(currentValue == 0 || currentValue == 1){
                                    matrix[i - s][j - s] += 2;
                                }else if(currentValue == 4){
                                    System.err.println("Error makeNewMatrix");
                                }
                            }

                        }
                        //RIGHT UP
                        if(moveAbleToRightUp(i, j, s)){
                            int currentValue = matrix[i - s][j + s];

                            if(black){
                                if(currentValue == 0 || currentValue == 2){
                                    matrix[i - s][j + s] += 1;
                                }else if(currentValue == 5){
                                    System.err.println("Error makeNewMatrix");
                                }
                            }
                            if(white){
                                if(currentValue == 0 || currentValue == 1){
                                    matrix[i - s][j + s] += 2;
                                }else if(currentValue == 4){
                                    System.err.println("Error makeNewMatrix");
                                }
                            }
                        }
                    }
                }
            }
        }

        return matrix;
    }

    private boolean moveAbleToRightDown(int i, int j, int step){
        return i + step <= 4 && j + step <= 3;
    }

    private boolean moveAbleToLeftDown(int i, int j, int step){
        return i + step <= 4 && j - step >= 0;
    }

    private boolean moveAbleToLeftUp(int i, int j, int step){
        return i - step >= 0 && j - step >= 0;
    }

    private boolean moveAbleToRightUp(int i, int j, int step){
        return i - step >= 0 && j + step <= 3;
    }

    private boolean isFigure(int value) {
        return value == 4 || value == 5 ? true: false;
    }

    @Override
    public String toString(){
        if(this.figure != null){
            return vertical + ", " + horizontal + ", " + step + " x: " + figure[0]  + " y: " + figure[1];
        }else{
            return vertical + ", " + horizontal + ", " + step;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.horizontal, this.vertical, this.step, this.figure);
    }

    @Override
    public boolean equals(Object o){
        Operator test = (Operator) o;
        if(test.horizontal == this.horizontal &&
                test.vertical == this.vertical &&
                test.step == this.step &&
                test.figure[0] == this.figure[0] &&
                test.figure[1] == this.figure[1]
        ){
            return true;
        }else{
            return false;
        }
    }
}
