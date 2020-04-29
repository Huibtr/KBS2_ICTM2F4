import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Math.exp;
import static java.lang.Math.sqrt;

public class TSP {
    public ArrayList<Integer> cordinaat_x;
    public ArrayList<Integer> cordinaat_y;

    public TSP(){
        cordinaat_x = new ArrayList<>();
        cordinaat_y = new ArrayList<>();
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
        double berekening;
        double distance;
        int kosteIndex = 0;
        int begin = 0;
        boolean skip;
        int[][] cordinate = new int[cordinaat_y.size()][cordinaat_x.size()];
        ArrayList<Integer> isReached = new ArrayList<>();
        isReached.add(0);

        //Odd Degree
        ArrayList<Integer> oddDegree = new ArrayList<>();


        for (int i = 0; i < cordinaat_x.size(); i++){
            for(int j = 0; j < 2; j++){
                if(j == 0){
                    cordinate[i][j] = cordinaat_x.get(i);
                }else {
                    cordinate[i][j] = cordinaat_y.get(i);
                }
            }
        }

        for(int eerste = 0; eerste < cordinate.length - 1; eerste++){
            distance = 1000;
            for (int reached = 0; reached < isReached.size(); reached++) {
                skip = false;
                for (int index = 0; index < cordinate.length; index++) {
                    if (index != reached) {
                        for(int check = 0; check < isReached.size(); check++){
                            if(index == check){
                                skip = true;
                                break;
                            }
                            else{
                                skip = false;
                            }
                        }
                        if(skip != true){
                            double tussenberekening_x = cordinate[reached][0] - cordinate[index][0];
                            double tussenberekening_y = cordinate[reached][1] - cordinate[index][1];
                            if (tussenberekening_x < 0) {
                                tussenberekening_x = -tussenberekening_x;
                            }
                            if (tussenberekening_y < 0) {
                                tussenberekening_y = -tussenberekening_y;
                            }

                            berekening = sqrt(Math.pow(tussenberekening_x, 2) + Math.pow(tussenberekening_y, 2));

                            if (berekening < distance) {
                                distance = berekening;
                                begin = reached;
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


        // zoekt de punten met de de oneven hoeken
        int teller;
        int indexOddDegree;
        for(int calOddDegree = 0; calOddDegree < oddDegree.size(); calOddDegree++){
            teller = 0;
            for(int i = 0; i< oddDegree.size(); i++){
                if(oddDegree.get(i)==calOddDegree){
                    teller = teller + 1;
                }
            }
            if ( teller % 2 != 0 ){
                indexOddDegree = calOddDegree;
                System.out.println( teller + " aantal hoeken op index " + indexOddDegree);

            }
        }


        }
    }
