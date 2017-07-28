package tidynumbers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.*;

/**
 *
 * @author serge Byusa
 */
public class TidyNumbers {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO Auto-generated method stub
        File in = new File("/home/serge/NetBeansProjects/TidyNumbers/src/tidynumbers/in.txt");
        File out = new File("/home/serge/NetBeansProjects/TidyNumbers/src/tidynumbers/out.txt");
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(in));
            bw = new BufferedWriter(new FileWriter(out));

            String rd = br.readLine();	 //read the first line and turn it into integer
            int n = Integer.parseInt(rd);
            for (int x = 0; x < n; x++) {
                String rd2 = br.readLine();	 //read the first line and turn it into integer
                BigInteger tmp2 = new BigInteger(rd2 + "");
                char[] tmpArr = tmp2.toString().toCharArray(); //split the number  into char array eg: 123 to '1''2''3'
                String tmp = tmp2.toString(); //put it in a string tmp
                lebal:
                while (true) { //till 
                    int prev = 0;
                    for (int charCount = 0; charCount < tmp.length(); charCount++) { //
                        //		System.out.println(tmp2 + " " + a + " " + ((int) tmpArr[a]) + " " + prev);
                        if (tmpArr[charCount] < prev) { //if it  is in asccending order 
                            for (int b = charCount; b < tmp.length(); b++) {
                                tmpArr[b] = '0';
                            }
                            String xD = new String(tmpArr);
                            tmp2 = new BigInteger(xD); //typecast to bigInteger
                            tmp2 = tmp2.subtract(BigInteger.ONE); //subtract one to see the largest number that follows that non tity number
                            tmp = tmp2.toString();//change it to string
                            tmpArr = tmp.toCharArray();//then into chars
                            continue lebal; //repeat the same thing
                        } else {
                            prev = tmpArr[charCount]; //else it in asccending order 
                        }
                    }
                    break;
                }
                bw.write("Case #" + (x + 1) + ": " + tmp2 + "\n");
            }
        } catch (IOException e) {
            System.out.print(e);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                System.out.print(ex);
            }
        }
    }
}
