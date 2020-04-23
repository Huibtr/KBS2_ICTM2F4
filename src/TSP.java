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
        int beginpunt_x = 0;
        int beginpunt_y = 0;
        ArrayList<Integer> bereikt = new ArrayList<>();
        double afstand = 1000;
        double berekening;
        int index = 0;
        for(int i = 0; i<cordinaat_y.size();i++) {
                if(cordinaat_y.get(i) != beginpunt_y && cordinaat_x.get(i) != beginpunt_x){


            for (int j = 0; j < cordinaat_y.size(); j++) {
                int x = cordinaat_x.get(i);
                int y = cordinaat_y.get(i);
                double tussenberekening_x = beginpunt_x - x;
                double tussenberekening_y = beginpunt_y - y;
                if (tussenberekening_x < 0) {
                    tussenberekening_x = -tussenberekening_x;
                }
                if (tussenberekening_y < 0) {
                    tussenberekening_y = -tussenberekening_y;
                }
                berekening = sqrt(Math.pow(tussenberekening_x, 2) + Math.pow(tussenberekening_y, 2));
                if (berekening < afstand) {
                    afstand = berekening;
                    index = i;
                    beginpunt_y = cordinaat_y.get(i);
                    beginpunt_x = cordinaat_x.get(i);
                    bereikt.add(index);
                }
            }
            }
            System.out.println(afstand + " " + index);

        }
    }
}
