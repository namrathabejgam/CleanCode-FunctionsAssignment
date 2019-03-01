import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;
class MatrixSize{
    int numberOfRows;
    int numberOfColumns;
    MatrixSize(int x,int y){
        numberOfRows=x;
        numberOfColumns=y;
    }
}
class ImproperInputException extends Exception
{

    public ImproperInputException(String s)
    {
        // Call constructor of parent Exception
        super(s);
    }
}
public class MatrixConversion {

    private final static Logger logger = Logger.getLogger(MatrixConversion.class.getName());

    //this method changes the entire matrix as per requirement
    static int[][] changeMatrix(int[][] matrix,MatrixSize matrixSize){
        boolean firstRowContainsZero=doesFirstRowContainZero(matrix,matrixSize);
        boolean firstColumnContainsZero=doesFirstColumnContainZero(matrix,matrixSize);
        changeFirstRowAndColumn(matrix,matrixSize);
        changeSubMatrix(matrix,matrixSize);
        if(firstRowContainsZero)
            setFirstRowToZero(matrix,matrixSize.numberOfColumns);
        if(firstColumnContainsZero)
            setFirstColumnToZero(matrix,matrixSize.numberOfRows);
        return matrix;
    }

    //returns if the first row contains zero
    static boolean doesFirstRowContainZero(int[][] matrix,MatrixSize matrixSize){
        boolean rowFlag=false;
        for(int i=0;i<matrixSize.numberOfColumns;i++)
        {
            if(matrix[0][i]==0){
                rowFlag=true;
                break;
            }
        }
        return rowFlag;
    }

    //returns if the first column contains zero
    static boolean doesFirstColumnContainZero(int[][] matrix,MatrixSize matrixSize){
        boolean columnFlag=false;
        for(int i=0;i<matrixSize.numberOfRows;i++){
            if(matrix[i][0]==0){
                columnFlag=true;
                break;
            }
        }
        return columnFlag;
    }

    /*this method changes the element of corresponding first row element and corresponding first
    column element to 0*/
    static void changeFirstRowAndColumn(int[][] matrix,MatrixSize matrixSize){
        for(int i=1;i<matrixSize.numberOfRows;i++){
            for(int j=1;j<matrixSize.numberOfColumns;j++) {
                if(matrix[i][j]==0){
                    matrix[i][0]=0;
                    matrix[0][j]=0;
                }
            }
        }
    }

    /*this modifies the rest of the matrix except first row and first column,
    according to values set in first row and column
     */
    static void changeSubMatrix(int[][] matrix,MatrixSize matrixSize){
        for(int i=1;i<matrixSize.numberOfRows;i++){
            for(int j=1;j<matrixSize.numberOfColumns;j++){
                if(matrix[i][0]==0 || matrix[0][j]==0){
                    matrix[i][j]=0;
                }
            }
        }
    }

    //changes the entire first row to zero if atleast one of the elements is 0
    static void setFirstRowToZero(int[][] matrix,int numberOfColumns){
        for(int i=0;i<numberOfColumns;i++){
            matrix[0][i]=0;
        }
    }

    //changes the entire first column to zero if atleast one of the elements is 0
    static void setFirstColumnToZero(int[][] matrix,int numberOfRows){
        for(int i=0;i<numberOfRows;i++){
            matrix[i][0]=0;
        }
    }

    static void printModifiedMatrix(int[][] matrix,MatrixSize matrixSize){
        String modifiedMatrix="";
        modifiedMatrix+=("\nThe modified matrix is:\n");
        for(int i=0;i<matrixSize.numberOfRows;i++){
            for(int j=0;j<matrixSize.numberOfColumns;j++){
                modifiedMatrix+=matrix[i][j]+" ";
            }
            modifiedMatrix+="\n";
        }
        logger.info(modifiedMatrix);
    }
    static void logError(Exception e) {
        if(e.getClass().getCanonicalName().equalsIgnoreCase("java.lang.NumberFormatException"))
            logger.info("Please make sure you entered a proper number");
        else if(e.getClass().getCanonicalName().equalsIgnoreCase("ImproperInputException"))
            logger.info(e.getMessage());
    }
    public static void main(String[] args){
        InputStreamReader inputStreamReader=new InputStreamReader(System.in);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        try {
            logger.info("Please enter the number of rows:");
            String[] rowInput=bufferedReader.readLine().split("\\s+");
            String improperInputExceptionMessage="Please enter a single, positive integer";
            if(rowInput.length!=1)
                throw new ImproperInputException(improperInputExceptionMessage);
            int numberOfRows = Integer.parseInt(rowInput[0]);
            if(numberOfRows<=0)
                throw new ImproperInputException(improperInputExceptionMessage);
            logger.info("Please enter the number of columns:");
            String[] columnInput=bufferedReader.readLine().split("\\s+");
            if(columnInput.length!=1)
                throw new ImproperInputException(improperInputExceptionMessage);
            int numberOfColumns = Integer.parseInt(columnInput[0]);
            if(numberOfColumns<=0)
                throw new ImproperInputException(improperInputExceptionMessage);
            MatrixSize matrixSize = new MatrixSize(numberOfRows, numberOfColumns);
            logger.info("Please enter the elements, row-wise(enter new row in new line):");
            int matrix[][] = new int[numberOfRows][numberOfColumns];
            for (int i = 0; i < numberOfRows; i++) {
                String row[]=bufferedReader.readLine().split("\\s+");
                if(row.length!=numberOfColumns)
                    throw new ImproperInputException("Please enter proper number of elements");
                for (int j = 0; j < numberOfColumns; j++) {
                    matrix[i][j] = Integer.parseInt(row[j]);
                }
            }
            changeMatrix(matrix, matrixSize);
            printModifiedMatrix(matrix, matrixSize);
        }
        catch (Exception e){
            logError(e);
        }
    }
}