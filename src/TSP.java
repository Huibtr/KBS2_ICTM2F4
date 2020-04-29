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
        
        //start punt van Nerdy Gadgets
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
        int startCoordinate = 0;
        int startX = 0;
        int startY = 0;
        double berekening;
        double distance;
        int kosteIndex = 0;
        int[][] cordinate = new int[cordinaat_y.size()][cordinaat_x.size()];

        for (int i = 0; i < cordinaat_x.size(); i++){
            for(int j = 0; j < 2; j++){
                if(j == 0){
                    cordinate[i][j] = cordinaat_x.get(i);
                }else {
                    cordinate[i][j] = cordinaat_y.get(i);
                }
            }
        }

        //for (int i = 0; i < cordinate.length; i++){
        //    for (int j = 0; j < 2; j++){
        //       System.out.println("cordinate[" + i + "][" + j + "] = " + cordinate[i][j]);
        //    }
        //}

        for (int punt = 0; punt < cordinate.length; punt++){
            distance = 1000;
            for(int index = 0; index < cordinate.length; index ++) {
                if (index != punt) {
                    double tussenberekening_x = cordinate[punt][0] - cordinate[index][0];
                    double tussenberekening_y = cordinate[punt][1] - cordinate[index][1];
                    if (tussenberekening_x < 0) {
                        tussenberekening_x = -tussenberekening_x;
                    }
                    if (tussenberekening_y < 0) {
                        tussenberekening_y = -tussenberekening_y;
                    }

                    berekening = sqrt(Math.pow(tussenberekening_x, 2) + Math.pow(tussenberekening_y, 2));

                    if (berekening < distance) {
                        distance = berekening;
                        kosteIndex = index;
                    }
                }
            }
            //System.out.println("vanaf punt  " + cordinate[punt][0] + " "+ cordinate[punt][1] + " -> " + cordinate[kosteIndex][0] + " " + cordinate[kosteIndex][1] + " = " + distance);
            System.out.println("vanaf punt  " + punt + " -> " + kosteIndex + " = " + distance);

        }
        
        }
    }
