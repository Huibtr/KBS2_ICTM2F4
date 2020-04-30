import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Math.exp;
import static java.lang.Math.sqrt;

public class TSP {
    public ArrayList<Integer> cordinaat_x;
    public ArrayList<Integer> cordinaat_y;
    // 2D array

    //MST algoritme
    ArrayList<Integer> isReached;
    //Odd Degree
    ArrayList<Integer> oddDegree;
    //Perfect Matching
    ArrayList<Integer> perfectMatching;

    public TSP(){
        cordinaat_x = new ArrayList<>();
        cordinaat_y = new ArrayList<>();
        isReached = new ArrayList<>();
        oddDegree = new ArrayList<>();
        perfectMatching = new ArrayList<>();
    }

    public void addcordinaten() throws SQLException {
        DBConnection dbConnection = new DBConnection();
        ResultSet resultSet = dbConnection.getCoordinates();

        //start reached van Nerdy Gadgets
        cordinaat_x.add(0);
        cordinaat_y.add(0);


        while(resultSet.next()){
            int x = resultSet.getInt("X");
            int y = resultSet.getInt("Y");
            cordinaat_x.add(x);
            cordinaat_y.add(y);
        }
    }

    public void getcordinaten() {
        for(int i = 0; i<cordinaat_y.size();i++){
            System.out.println("[" + cordinaat_x.get(i) + "] " + "[" + cordinaat_y.get(i) + "]");
        }

    }

    public void berekenAfstand(){
        //prim algoritme
        //int[][] cordinate = new int[cordinaat_y.size()][cordinaat_x.size()];
        double distance;
        int kosteIndex = 0;
        int begin = 0;
        boolean skip;
        isReached.add(0);

        // maakt van een array een 2D array
//        for (int i = 0; i < cordinaat_x.size(); i++){
//            for(int j = 0; j < 2; j++){
//                if(j == 0){
//                    cordinate[i][j] = cordinaat_x.get(i);
//                }else {
//                    cordinate[i][j] = cordinaat_y.get(i);
//                }
//            }
//        }

        //1. Prim algritme
        for(int eerste = 0; eerste < cordinaat_x.size() - 1; eerste++){
            distance = 1000;
            for (int reached = 0; reached < isReached.size(); reached++) {
                skip = false;
                for (int index = 0; index < cordinaat_x.size(); index++) {
                    int reachedIndex = isReached.get(reached);
                    if (index != reachedIndex) {
                        for(int check = 0; check < isReached.size(); check++){
                            int getcheck = isReached.get(check);
                            if(index == getcheck){
                                skip = true;
                                break;
                            }
                            else{
                                skip = false;
                            }
                        }
                        if(skip != true){
                            double getDistance = getDistance(reachedIndex, index);

                            if (getDistance < distance) {
                                distance = getDistance;
                                begin = reachedIndex;
                                kosteIndex = index;
                            }
                        }
                    }
                }


            }
            System.out.println("vanaf reached  " + begin + " -> " + kosteIndex + " = " + distance);
            isReached.add(kosteIndex);

            oddDegree.add(begin);
            oddDegree.add(kosteIndex);
        }


        //2. Odd Degree Vertices
        int teller;
        for(int indexOddDegree = 0; indexOddDegree < oddDegree.size(); indexOddDegree++){
            teller = 0;
            for(int i = 0; i< oddDegree.size(); i++){
                if(oddDegree.get(i)==indexOddDegree){
                    teller = teller + 1;
                }
            }
            if ( teller % 2 != 0 ){
                perfectMatching.add(indexOddDegree);
            }
        }


        //3. Perfect Matching
        for(int perfectIndex = 0; perfectIndex < perfectMatching.size(); perfectIndex++){
            System.out.println(perfectMatching.get(perfectIndex));
        }


        }

        public double getDistance(int beginIndex, int endIndex){
            double distance;

            double tussenberekening_x = cordinaat_x.get(beginIndex) - cordinaat_x.get(endIndex);
            double tussenberekening_y = cordinaat_y.get(beginIndex) - cordinaat_y.get(endIndex);
            if (tussenberekening_x < 0) {
                tussenberekening_x = -tussenberekening_x;
            }
            if (tussenberekening_y < 0) {
                tussenberekening_y = -tussenberekening_y;
            }

            distance = sqrt(Math.pow(tussenberekening_x, 2) + Math.pow(tussenberekening_y, 2));

            return distance;
        }
    }
