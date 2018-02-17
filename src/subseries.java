
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;


public class subseries {
    // function to get the Euclidean distance
    public static double getDistance(int[] S, int[] Q){
        int total = 0;
        // loop over S and Q
        for(int i =0; i < S.length; i++){
            // collect a total to be square rooted
            total = total + (S[i] - Q[i]) * (S[i] - Q[i]);         
        }
        // square root total and return
        double fullTotal = Math.sqrt(total);
        return fullTotal;
    }
    // get a random value           
    public static int getRandomValue(int min, int max){
        Random ran = new Random();
        // get value
        int value = ran.nextInt((max - min) + 1) + min;
        return value;
    }    
    
    // get randomly generated S based on size k
    public static int[] setS(int k){
        int[] S = new int[k];
        for(int i = 0; i < k; i++){
            S[i] = getRandomValue(0,10);
        }
        return S;
    }
    
    public static int[][] setT(int n){
        int[][] T = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j<n; j++){
                T[i][j] = getRandomValue(0,10);
            }
        }
        return T;
    }
    
    // main function
    public static Result SubSeriesSearch(int[][] T, int[] S) {
        
        // intitiate variables
        double distance;
        int[] Q;
        int n =  T.length;
        int k = S.length;
        double smallestDistance = 0;
        int startPoint = 0;
        int seriesIndex = 0;
        // loop over all possible series stored in T
        for (int i = 0; i< n; i++){
            // loop over each element and create a sub series            
            for (int j = 0; j< n-k;j++){
                // creates a new array based on the current series and a starting and finishing point
                Q = Arrays.copyOfRange(T[i], j, j+k);
                // gets the distance for that Q and the input series S
                distance = getDistance(S,Q);
                // checks if distance is zero
                if (distance == 0){
                    Result r2 = new Result(i,j);
                    return r2;
                // checks to see if a new smaller distance has been found or if the loop is on its first increment
                } else if(distance < smallestDistance || (j==0 && i ==0)){
                    // stores the values which will later hold the answers to the problems
                    startPoint = j;
                    smallestDistance = distance;
                    seriesIndex = i;
                }              
            }  
        }
        Result r1 = new Result(seriesIndex, startPoint);
        return r1;
        // old output
        //System.out.println("Series Index: " + (seriesIndex) + "\nStart Point: " + (startPoint) + "\nDistance: " + smallestDistance);   
    }
    
    public static void meanAndStandardDeviation(int n, int reps) {
        // get values for T
        int[][] T = setT(n);
        // k is constant
        int k = 5;
        double sum = 0;
        double sumSquared = 0;
        // input array
        int[] S = setS(k);
        Result result;
        // loop for certain number of reps
        for (int i = 0; i < reps; i++) {
            long t1 = System.nanoTime();
            result = SubSeriesSearch(T,S);   
            long t2 = System.nanoTime() - t1;
            // keep track of total time
            sum += (double) t2 / 1000000.0;
            // keep track of total time square
            sumSquared += (t2 / 1000000.0) * (t2 / 1000000.0);
        }
        // calculate mean
        double mean = sum / reps;
        // calculate variance
        double variance = sumSquared / reps - (mean * mean);
        double stdDev = Math.sqrt(variance);
        DecimalFormat df = new DecimalFormat("#.####");
        //System.out.println(result.toString());
        System.out.println(df.format(mean)+","+df.format(stdDev));
    }

    public static void main(String[] args) {
        // run algorithm for different values of n
        for(int n = 0; n <= 1000; n+=10){
            meanAndStandardDeviation(n,1000);
        }
        
    }
    
}
