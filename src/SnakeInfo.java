public class SnakeInfo {

    int startRow;
    int startCol;
    int startNumber;
    int endRow;
    int endCol;
    int endNumber;

    public SnakeInfo(int startRow, int startCol, int startNumber, int endRow, int endCol, int endNumber) {
        this.startRow = startRow;
        this.startCol = startCol;
        this.startNumber = startNumber;
        this.endRow = endRow;
        this.endCol = endCol;
        this.endNumber = endNumber;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public void setStartCol(int startCol) {
        this.startCol = startCol;
    }

    public int getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(int startNumber) {
        this.startNumber = startNumber;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getEndCol() {
        return endCol;
    }

    public void setEndCol(int endCol) {
        this.endCol = endCol;
    }

    public int getEndNumber() {
        return endNumber;
    }

    public void setEndNumber(int endNumber) {
        this.endNumber = endNumber;
    }
}
