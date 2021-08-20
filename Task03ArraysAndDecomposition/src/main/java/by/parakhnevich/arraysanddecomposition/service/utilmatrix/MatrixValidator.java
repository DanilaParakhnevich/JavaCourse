package by.parakhnevich.arraysanddecomposition.service.utilmatrix;

import by.parakhnevich.arraysanddecomposition.bean.Matrix;

public class MatrixValidator {
    public boolean checkingBounds(int n, int m, Matrix<Number> matrix) throws ArrayIndexOutOfBoundsException{
        return n >= 0 && n < matrix.getRows() && m >= 0 &&
                m < matrix.getColumns();
    }

    public boolean compareTwoMatrix(Matrix<Number> matrix1, Matrix<Number> matrix2) throws ArrayIndexOutOfBoundsException{
        return matrix1.getColumns() == matrix2.getColumns()
                && matrix1.getRows() == matrix2.getRows();
    }
}
