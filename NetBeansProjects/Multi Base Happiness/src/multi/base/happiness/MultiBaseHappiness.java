package multi.base.happiness;

/**
 *
 * @author Serge Jabo Byusa
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MultiBaseHappiness {

    public static void main(String[] args) throws Exception {
        long time = System.currentTimeMillis();

        StringTokenizer st;
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            File input  = new File("/home/serge/NetBeansProjects/Multi Base Happiness/src/multi/base/happiness/in.txt");
            File output = new File("/home/serge/NetBeansProjects/Multi Base Happiness/src/multi/base/happiness/out.txt");
            br = new BufferedReader(new FileReader(input));
            bw = new BufferedWriter(new FileWriter(output));
            String readint = br.readLine();
            int T = Integer.parseInt(readint);
            //int T = INT(readint);
            
            for (int t = 1; t <= T; t++) {
                st = new StringTokenizer(br.readLine());
                int n = st.countTokens();
                int[] bs = new int[n];
                int[] ans = new int[n];
                for (int i = 0; i < n; i++) {
                    bs[i] = Integer.parseInt(st.nextToken());
                }
                int answer = allNumberTest(bs);
                bw.write("Case #" + t + ": " + answer + "\n");
            }
           // System.out.printf("Time spent: %.3fs%n",(System.currentTimeMillis()-time)/1000.0);

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
            }
        }
    }
////////Finding the smallest number that is happy in all bases
    public static int allNumberTest(int base[]) {
        int small = 0;
        int n = base.length;
        int temp[] = new int[n];
        int k = 0;
        for (int i = 2; i < 1000; i++) { //check all numbers from 2 to infinity

            inner:
            for (int j = 0; j < n; j++) { //for each number check all bases
                if ((base[j] == 2) || (base[j] == 4)) {
                    k++;
                    continue;
                }
                if (isHappy(i, base[j]) == true) { //if any small number is happy increment k;
                    k++;
                }
            }
            if (k != base.length) {//if k is not equal to the lenght of the base make it zero other wise return the smallest number
                k = 0;
            } else {
                small = i;
                return small;
            }

        }
        return small;
    }

///////////Base converter
    public static String convert(int n, int b) {
        int q = n, r;
        String ans = "";
        while (q != 0) {
            r = q % b;
            ans = r + ans;
            q = q / b;
        }
        return ans;
    }

///////////the sum of digits turned into the inputed base
    public static int sumSqDigitsTurnedbase(int n, int b) {
        int s, i, d;
        s = 0;
        int result = 0;
        String num = convert(n, b);
        int number = Integer.parseInt(num);
        for (i = number; i != 0; i = i / 10) {
            d = i % 10;
            s = s + d * d;
        }
        String ans = convert(s, b);
        result = Integer.parseInt(ans);
        return s;
    }

//////////Checking if the number is happy or not in the given base
    public static boolean isHappy(int n, int b) {
        HashSet<Integer> set = new HashSet<Integer>();

        while (!set.contains(n)) {
            set.add(n);

            n = sumSqDigitsTurnedbase(n, b);

            if (n == 1) {
                return true;
            }
        }

        return false;
    }

}

