import org.junit.Assert;
import org.junit.Test;

public class TestCases {
    @Test
    public void Test1(){
        int numberOfRows=2;
        int numberOfColumns=2;
        MatrixSize matrixSize=new MatrixSize(numberOfRows,numberOfColumns);
        int[][] matrix={{1,0},{1,2}};
        int[][] expected={{0,0},{1,0}};
        int[][] answer= MatrixConversion.changeMatrix(matrix,matrixSize);
        Assert.assertArrayEquals(expected,answer);
    }
    @Test
    public void Test2(){
        int numberOfRows=3;
        int numberOfColumns=3;
        MatrixSize matrixSize=new MatrixSize(numberOfRows,numberOfColumns);
        int[][] matrix={{0 ,1, 1},{0 ,1, 1},{1 ,1, 1}};
        int[][] expected={{0,0,0},{0,0,0},{0,1,1}};
        int[][] answer= MatrixConversion.changeMatrix(matrix,matrixSize);
        Assert.assertArrayEquals(expected,answer);
        //Assert.assertFalse(Arrays.equals(expected,answer));
    }
    @Test
    public void Test3(){
        int numberOfRows=4;
        int numberOfColumns=4;
        MatrixSize matrixSize=new MatrixSize(numberOfRows,numberOfColumns);
        int[][] matrix={{0 ,1, 1,2},{0 ,1, 1, 0},{1 ,1, 1, 1},{ 1, 2, 3, 4}};
        int[][] expected={{0,0,0,0},{0,0,0,0},{0,1,1,0},{0,2,3,0}};
        int[][] answer= MatrixConversion.changeMatrix(matrix,matrixSize);
        Assert.assertArrayEquals(expected,answer);
        //Assert.assertFalse(Arrays.equals(expected,answer));
    }
}
