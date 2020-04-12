package com.company;

import java.util.Objects;

public class State {
    private int[][] state;
    public State(){

    }
    public State(State state){
        this.state = state.getMatrix();
    }
    public State(int[][] state){
        this.state = state;
    }

    public int[][] getMatrix(){
        return this.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.state);
    }

    @Override
    public boolean equals(Object o){
        State state = (State) o;

        int[][] matrix = state.getMatrix();
        for(int i = 0; i <= 4; i++){
            for(int j = 0; j <= 3; j++){
                if(this.state[i][j] != matrix[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString(){

        return this == null ? "null vagyok": state[0][0] + " " + state[0][1] + " "  + state[0][2] + " " + state[0][3] + "\n" +
                state[1][0] + " " + state[1][1] + " " + state[1][2] + " " + state[1][3] + "\n" +
                state[2][0] + " " + state[2][1] + " " + state[2][2] + " " + state[2][3] + "\n" +
                state[3][0] + " " + state[3][1] + " " + state[3][2] + " " + state[3][3] + "\n" +
                state[4][0] + " " + state[4][1] + " " + state[4][2] + " " + state[4][3] + "\n"
                ;
    }

}

