public class Result {
    int seriesIndex;
    int startPoint;
    
    Result(int index, int point){
        seriesIndex = index;
        startPoint = point;
    }
    
    @Override
    public String toString(){
        return ("Series Index: " + (seriesIndex) + "\nStart Point: " + (startPoint));
    }
}
