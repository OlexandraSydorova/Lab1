import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
public class lab1 {
    public static void main(String[] args) {

        Scanner sym = new Scanner(System.in);
        System.out.println("Для вводу символа роздільника введіть 1, за замовчуванням введіть 0");
        String choice = sym.nextLine();
        while (!choice.equals("1") && !choice.equals("0"))
        {
            System.out.println("Введення є некоректним, введіть ще раз");
            choice = sym.nextLine();
        }
        String del = ",";

        if (choice.equals("1")) {
            System.out.println("Введіть символ роздільник");
            del = sym.nextLine();
        }
        System.out.println("Введіть символ об'єднувач");
        String diz = sym.nextLine();
        while (del.length()>1)
        {
            System.out.println("Введіть один символ");
            del = sym.nextLine();
        }
        char[] delim = del.toCharArray();

        System.out.println("Введіть шлях до вхідного файлу");
        String filename = sym.nextLine();
        File file = new File(filename);
        while (!file.isFile())
        {
            System.out.println("Такий файл не існує, або його неможливо відкрити, введіть шлях ще раз");
            filename = sym.nextLine();
            file = new File(filename);
        }
        System.out.println("Введіть шлях до результативного файлу");
        String filename_res = sym.nextLine();
        File file_res = new File(filename_res);
        while (!file_res.isFile())
        {
            System.out.println("Такий файл не існує, або його неможливо відкрити, введіть шлях ще раз");
            filename_res = sym.nextLine();
            file_res = new File(filename_res);
        }
        try {

            Scanner sc = new Scanner(file);
            PrintWriter res = new PrintWriter(file_res);
            while(sc.hasNext()){

                String data = sc.nextLine();
                char[] line = data.toCharArray();

                int count = 0;

                int k = 0;
                for (int i = 0; i < line.length; i++)
                {

                    k = 0;
                    if (i == 0 && line[i] == '\"')
                    {
                        k = i+1;
                        while (k!=line.length)
                        {
                            if(k!=line.length-1)
                            {
                                if (line[k] == '\"' && line[k + 1] == delim[0]) {
                                    count = k - i - 2;
                                    i = k;
                                    break;
                                }
                            }
                            if (line[k] == '\"' &&  k==line.length-1)
                            {
                                count = k-i-2;
                                i = k;
                                break;
                            }
                            k++;
                        }
                    }
                    if (i!=0)
                        if (line[i-1] == delim[0] && line[i] == '\"')
                        {
                            k = i+1;
                            while (k!=line.length)
                            {
                                if(k!=line.length-1)
                                {
                                    if (line[k] == '\"' && line[k + 1] == delim[0]) {
                                        count = k - i - 2;
                                        i = k;
                                        break;
                                    }
                                }
                                if (line[k] == '\"' &&  k==line.length-1)
                                {
                                    count = k-i-2;
                                    i = k;
                                    break;
                                }
                                k++;
                            }
                        }
                    if (line[i] != delim[0] && i<line.length-1 )
                    {
                        count++;
                    }
                    else
                    {

                        if (i != line.length - 1)
                            res.write(count + diz);
                        else {
                            if (line[i] != delim[0])
                                count++;
                            res.write(count + "\r\n");
                        }
                        count = 0;
                    }

                }
            }
            sc.close();
            res.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}