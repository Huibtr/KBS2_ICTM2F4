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
        ArrayList<Integer> reached = new ArrayList<>();
        reached.add(0);
        double berekening;

        for(int route = 0; route<cordinaat_y.size();route++) {
            int index = 0;
            double distance = 1000;
            boolean isReached = false;

            // for(int i) zoekt de kortste afstand naar het eerst volgende punt die nog niet geweest is
            for (int i = 0; i < cordinaat_y.size(); i++) {

                for (int g = 0; g < reached.size(); g++) {
                    if(reached.get(g) == i) {
                        isReached = true;
                        break;
                    }else{
                        isReached = false;
                    }
                }

                if(!isReached) {
                    if (startCoordinate != i) {
                        int x = cordinaat_x.get(i);
                        int y = cordinaat_y.get(i);


                        double tussenberekening_x = startX - x;
                        double tussenberekening_y = startY - y;
                        if (tussenberekening_x < 0) {
                            tussenberekening_x = -tussenberekening_x;
                        }
                        if (tussenberekening_y < 0) {
                            tussenberekening_y = -tussenberekening_y;
                        }

                        berekening = sqrt(Math.pow(tussenberekening_x, 2) + Math.pow(tussenberekening_y, 2));

                        if (berekening < distance) {
                            distance = berekening;
                            index = i;
                        }
                    }
                }
            }

            startCoordinate = index;
            reached.add(startCoordinate);
            startX = cordinaat_x.get(index);
            startY = cordinaat_y.get(index);
            System.out.println(distance + " | " + index);

        }



        }
    }
