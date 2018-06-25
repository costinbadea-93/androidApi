package androidApi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class idParser {
    public static void main(String [] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Costin Badea\\Desktop\\Api Android\\androidApi\\src\\main\\java\\androidApi\\ex.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (line != null) {
                if(line.contains("),") || line.contains(")")) {
                    System.out.println(line.replace("),",","+ new randomGenerator().random(1,30) +"),"));
                    //replace for cities
                    //line.replace("),",","+ new randomGenerator().random(1,30) +"),");
                    //replace for wishes
                    line.replace("),",","+ new randomGenerator().random(23,38) +"),");
                }
                sb.append(line.replace("),",","+ new randomGenerator().random(23,38) +"),"));
                sb.append(System.lineSeparator());


                try {
                    line = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String everything = sb.toString();
            System.out.println( everything);


        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
