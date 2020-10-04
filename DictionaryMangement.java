package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DictionaryMangement {
//    private ArrayList<String> wordTarget = new ArrayList<>();
 //   private ArrayList<String> interpretation = new ArrayList<>();
    HashMap<String, String> dictionary = new HashMap<>();

    public  void insertFromFile() {
        File file = new File("en_vi.txt");
        FileInputStream fi = null;
        long length = 0;
        long count = 0;

    //    wordTarget = new String[(int) length];
    //    interpretation = new String[Math.toIntExact(length)];

        try {
            fi= new FileInputStream(file);
            InputStreamReader fin = new InputStreamReader(fi, "utf-8");
            int data = fin.read();
            StringBuilder line = new StringBuilder();
            String wordTarget =  "";
            String interpretation;
            while (data != -1) {
                if (((char) data == '\n')) {
                    interpretation = line.toString();
            //        String[] result = interpretation.split("#");
            //       interpretation.add(line.toString());
            //        System.out.println(interpretation[count-1]);
                    dictionary.put(wordTarget, interpretation);
                    line.delete(0, line.length());
                    data = fin.read();
                    continue;
                } else if ((char) data == '\t') {
                    wordTarget = line.toString();
                //    wordTarget.add(line.toString());
            //        System.out.println(wordTarget[count]);
                    line.delete(0, line.length());
                    data = fin.read();
                    count++;
                }
                line.append((char) data);
                data = fin.read();
            }
            interpretation = line.toString();
            dictionary.put(wordTarget, interpretation);
         //   interpretation.add(line.toString());
         //   System.out.println(interpretation[count-1] + count);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fi != null) {
                    fi.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /*
        if (count != 0) {
           for (int i = 0; i < interpretation.size(); i++) {
               String[] result = interpretation.get(Math.toIntExact(i)).split("#");
           //    System.out.println(wordTarget[i]);
               String temp = "";
               String tmp;

               for (int j = 0; j < result.length; j++) {
                   tmp = result[j];
                   if (tmp.charAt(0) == '@') {
                       tmp = tmp.substring(1);
                       tmp = tmp.toUpperCase();
                   }
                   temp += tmp + '\n';
               //    System.out.println(result[j]);
               }
               interpretation.set(Math.toIntExact(i), temp);
            //   System.out.println(interpretation.get(Math.toIntExact(i)));
           }

        }

         */

        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            String[] result = entry.getValue().split("#");
            String temp = "";
            String tmp;
        //    System.out.println(entry.getKey());

            for (int j = 0; j < result.length; j++) {
                tmp = result[j];
            //    if (tmp.charAt(0) == '@') {
            //        tmp = tmp.substring(1);
            //        tmp = tmp.toUpperCase();
            //    }
                temp += tmp + '\n';
                //    System.out.println(result[j]);
            }
            entry.setValue(temp);
         //   System.out.println(entry.getValue());
        }

    }


    public HashMap<String, String> getDictionary() {
        return dictionary;
    }

    public String Interpretation(String a) {
        return dictionary.get(a);
    }

    public static void main(String[] args) {
        String arr = "";
        boolean kt = false;
        Scanner sc = new Scanner(System.in);
       DictionaryMangement a = new DictionaryMangement();
       a.insertFromFile();
    }

}
