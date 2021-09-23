package by.parakhnevich.multithreadingmatrix.bean;

import java.util.Arrays;

public class Matrix {
    private int[][] array;
    public Matrix(int n, int m){
        this.array = new int[n][m];
    }

    public Matrix(int n, int m, int number){
        this.array = new int[n][m];
        for (int index1 = 0; index1 < n; index1++){
            for (int index2 = 0; index2 < m; index2++){
                this.array[index1][index2] = number;
            }
        }
    }
    //this constructor copy matrix from another matrix
    public Matrix(Integer[][] array) {
        this.array = new int[array.length][array[0].length];
        for(int i = 0 ; i < array.length; ++i) {
            System.arraycopy(array[i], 0, this.array[i], 0, array[i].length);
        }
    }
    //this constructor copy Object Matrix from another Object Matrix
    public Matrix(Matrix matrix) {
        this.copyArray(matrix);
    }

    public void put(int n, int m, int element) {
        array[n][m] = element;
    }

    public int toPureString(int n, int m) {
        return array[n][m];
    }
    //return matrix in string without unnecessary info
    public String toPureString(){
        StringBuilder result = new StringBuilder();
        for (int index1 = 0; index1 < this.getRows(); index1++) {
            for (int index2 = 0; index2 < this.getColumns(); index2++) {
                result.append(array[index1][index2]).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    public int getRows() {
        return this.array.length;
    }

    public int getColumns() {
        return this.array[0].length;
    }

    private void copyArray(int[][] array){
        this.array = new int[array.length][array[0].length];
        for (int index1 = 0; index1 < array.length; index1++){
            for (int index2 = 0; index2 < array[0].length; index2++){
                this.array[index1] = Arrays.copyOf(array[index1],array[index1].length);
            }
        }
    }

    private void copyArray(Matrix matrix){
        this.array = new int[matrix.getRows()][matrix.getColumns()];
        for (int index1 = 0; index1 < matrix.getRows(); index1++){
            for (int index2 = 0; index2 < matrix.getColumns(); index2++) {
                this.array[index1] = Arrays.copyOf(matrix.array[index1],matrix.array[index1].length);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        int length = 0;
        for (int index = length;index < array[0].length;index++){
            if (!Arrays.equals(array[index],matrix.array[index]))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(array);
    }

    @Override
    public String toString() {

        return "Matrix{" +
                "array=\n" + this.toPureString() +
                '}';
    }
}
