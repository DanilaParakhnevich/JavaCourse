package by.parakhnevich.arraysanddecomposition.utilmatrix;

import by.parakhnevich.arraysanddecomposition.bean.Matrix;

public class MatrixComparator {
    public void checkingBounds(int n, int m, Matrix<Number> matrix) throws ArrayIndexOutOfBoundsException{
        if (n < 0 || n >= matrix.getRows() || m < 0 ||
        m >= matrix.getColumns()) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void compareTwoMatrix(Matrix<Number> matrix1, Matrix<Number> matrix2) throws ArrayIndexOutOfBoundsException{
        if (matrix1.getColumns() != matrix2.getColumns()
        || matrix1.getRows() != matrix2.getRows()) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
