public class LocationMap {
    private int[][] adjointMatrix;
    private int numOfScenes;

    public LocationMap(int[][] adjointMatrix, int numOfScenes) {
        this.adjointMatrix = adjointMatrix;
        this.numOfScenes = numOfScenes;
    }

    public void addRoad(int src, int dest, int distance) {
        adjointMatrix[src - 1][dest - 1] = distance;
        adjointMatrix[dest - 1][src - 1] = distance;
    }

    public int[][] getAdjointMatrix() {
        return adjointMatrix;
    }

    public void setAdjointMatrix(int[][] adjointMatrix) {
        this.adjointMatrix = adjointMatrix;
    }

    public int getNumOfScenes() {
        return numOfScenes;
    }

    public void setNumOfScenes(int numOfScenes) {
        this.numOfScenes = numOfScenes;
    }
}
