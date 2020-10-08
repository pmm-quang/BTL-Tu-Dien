package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DictionaryMangement {
    private HashMap<String, ArrayList<String>> dictionary = new HashMap<>();

    private ArrayList<String> same = new ArrayList<>();

    private final String FILENAME = "en_vi.txt";

    public  void insertFromFile() {
        File file = new File(FILENAME);
        FileInputStream fi = null;
        try {
            fi= new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fi, "utf-8");
            int data = isr.read();
            StringBuilder line = new StringBuilder();
            String wordTarget =  "";
            String interpretation;
            while (data != -1) {
                if (((char) data == '\n')) {
                    interpretation = line.toString();
                    String[] result = interpretation.split("#");
                    ArrayList<String> resultList = new ArrayList<>();
                    for (int i = 0; i < result.length; i++) {
                        resultList.add(result[i]);
                    }
                    dictionary.put(wordTarget, resultList);
                    line.delete(0, line.length());
                    data = isr.read();
                    continue;
                } else if ((char) data == '\t') {
                    wordTarget = line.toString();
                    line.delete(0, line.length());
                    data = isr.read();
                }
                line.append((char) data);
                data = isr.read();
            }
            interpretation = line.toString();
            String[] result = interpretation.split("#");
            ArrayList<String> resultList = new ArrayList<>();
            for (int i = 0; i < result.length; i++) {
                resultList.add(result[i]);
            }
            dictionary.put(wordTarget, resultList);

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

        /*
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

         */

    }

    public void AppendToFile(String wordTarget, ArrayList <String> interpretation) {
        try {
            File file = new File(FILENAME);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println("");
            pw.print(wordTarget + '\t');
            for (int i = 0; i < interpretation.size(); i++) {
                if (i == interpretation.size() - 1) {
                    pw.println(interpretation.get(i));
                } else {
                    pw.print(interpretation.get(i) + "#");
                }
            }
            pw.close();
            System.out.println("Data successfully appended at the end of file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, ArrayList<String>> getDictionary() {
        return dictionary;
    }

    public ArrayList<String> Interpretation(String a) {
       return dictionary.get(a);
    }

    public ArrayList<String> Same(String a) {
        for (String key : dictionary.keySet()) {
            int pos = key.indexOf(a);
            if(pos == 0) {
                same.add(key);
            }
        }
        return same;
    }
    public void deleteListSame() {
        same.clear();
    }

    public static void main(String[] args) {
        String arr = "";
        boolean kt = false;
        Scanner sc = new Scanner(System.in);
       DictionaryMangement a = new DictionaryMangement();
       a.insertFromFile();
       String key = "Javacore";
       ArrayList <String> value = new ArrayList<>();
       value.add("lap");
       value.add("trinh");
       value.add("huong");
       value.add("tuong");
       a.AppendToFile(key, value);
    }

}
