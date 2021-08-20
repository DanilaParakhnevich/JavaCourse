package by.parakhnevich.arraysanddecomposition.utilmatrix;

import by.parakhnevich.arraysanddecomposition.bean.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Its calculator for matrix's operations
 * 1.Sum of matrices
 * 2.Difference of matrices
 * 3.Multiplication of matrix and number
 * 4.Multiplication of matrices
 * 5.Transposition of matrix
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.utilarrays.Sorting
 */
public class MatrixCalculator<T extends Number> {
    private final Logger logger = LogManager.getLogger(MatrixCalculator.class.getName());

    public Matrix<Integer> sum (Matrix<Number> matrix1 , Matrix<Number> matrix2) {
        try {
            new MatrixComparator().compareTwoMatrix(matrix1, matrix2);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            logger.error(e);
        }
        Matrix<Integer> matrix = new Matrix<>(matrix1.getRows(), matrix1.getColumns());
        for (int index1 = 0; index1 < matrix1.getRows(); index1++) {
            for (int index2 = 0; index2 < matrix1.getColumns(); index2++){
                matrix.put(index1, index2, matrix1.getIntegerValue(index1,index2) +
                        matrix2.getIntegerValue(index1, index2));
            }
        }
        return matrix;
    }

    public Matrix<Number> difference(Matrix<Number> matrix1 , Matrix<Number> matrix2) {
        try {
            new MatrixComparator().compareTwoMatrix(matrix1, matrix2);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            logger.error(e);
        }
        Matrix<Number> matrix = new Matrix<>(matrix1.getRows(), matrix1.getColumns());
        for (int index1 = 0; index1 < matrix1.getRows(); index1++) {
            for (int index2 = 0; index2 < matrix1.getColumns(); index2++){
                matrix.put(index1, index2, matrix1.getIntegerValue(index1,index2) -
                        matrix2.getIntegerValue(index1, index2));
            }
        }
        return matrix;
    }

    public Matrix<Number> multiply(Matrix<Number> matrix, int value){
        for (int index1 = 0; index1 < matrix.getRows(); index1++) {
            for (int index2 = 0; index2 < matrix.getColumns(); index2++) {
                matrix.put(index1, index2 , value * matrix.getIntegerValue(index1, index2));
            }
        }
        return matrix;
    }

    public Matrix<Integer> multiply(Matrix<T> matrix1,Matrix<T> matrix2){
        try {
            if (matrix1.getColumns() != matrix2.getRows()) {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            logger.error(e);
        }
        Matrix<Integer> matrix = new Matrix<>(matrix1.getRows(), matrix2.getColumns());
        for (int index1 = 0; index1 < matrix1.getRows(); index1++) {
            for (int index2 = 0; index2 < matrix2.getColumns(); index2++) {
                matrix.put(index1, index2, 0);
                for (int index3 = 0; index3 < matrix1.getColumns(); index3++) {
                     matrix.put(index1, index2,matrix.getIntegerValue(index1,index2) +
                             matrix1.getIntegerValue(index1, index3) * matrix2.getIntegerValue(index3, index2));
                }
            }
        }
        return matrix;
    }

    public Matrix<T> transposition(Matrix<T> matrix){
        Matrix<T> result = new Matrix<>(matrix.getColumns(), matrix.getRows());
        for (int index1 = 0; index1 < matrix.getRows(); index1++) {
            for (int index2 = 0; index2 < matrix.getColumns(); index2++) {
                result.put(index2, index1, matrix.get(index1, index2));
            }
        }
        return result;
    }
}
