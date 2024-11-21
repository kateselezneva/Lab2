import java.io.PrintStream;
import java.util.Scanner;
import java.io.IOException;
public class Main {
    // Объявляем объект класса Scanner для ввода данных
    public static Scanner in = new Scanner(System.in);
    // Объявляем объект класса PrintStream для вывода данных
    public static PrintStream out = System.out;
    public static void main(String[] args) throws IOException {

//      считывание двух натуральных чисел (размеры массива)из консоли
        int n = in.nextInt();
        int m = in.nextInt();
//        создание массивов
        String [][]a = new String[n][m];
        Boolean [][]b = new Boolean[n][m];
        in.nextLine();
//         заполнение массива a строками из консоли
        for (int i=0; i<n; i++)
            for (int j=0; j<m; j++)
                a[i][j] = in.nextLine();
//считывание вещественной перменной k
        double k = in.nextDouble();
//        заполнение массива b значениями логических выражений
        for (int i=0; i<n; i++)
            for (int j=0; j<m; j++)
                if (a[i][j].charAt(1)=='>')
                    b[i][j] = k > (double)(a[i][j].charAt(2)-'0');
                else
                    if (a[i][j].charAt(1)=='<')
                        b[i][j] = k < (double)(a[i][j].charAt(2)-'0');
                    else
                        b[i][j] = k == (double)(a[i][j].charAt(2)-'0');

//      вывод массива с значениями логических выражений
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++)
                out.print(b[i][j]+" ");
            out.println();
        }


        int []t = new int[n]; //массив для кол-ва истинных выражений для каждой строки
        int []mf = new int[n]; //массива для количества ложных выражений подряд для каждой строки

        String []s = new String[m]; //массив строк для сортировки массива a
        Boolean []z = new Boolean[m]; //массив логических переменных для сортировки массива b
        int arr; //переменная для сортировки массивов t и mf
        //переменные для подсчета количества ложных выражение подряд
        int c;
        int mc;

//        заполнение массивов t и mf
        for (int i=0; i<n; i++){
            mc = 0;
            c = 0;
            t[i] = 0;
            for (int j=0; j<m; j++){
                if (b[i][j]){
                    t[i]++;
                    c = 0;
                }
                else{
                    c++;
                    if (mc<c)
                        mc = c;
                }
            }
            mf[i] = mc;
        }

//        сортировка массивов a,b,t,mf
        for (int l = 1; l<n; l++)//повторение чтобы отсортировалось
            for (int i = 0; i<n-l; i++) { //строка
//              если в строке меньше истинных выражений, чем в следующей
//              или если кол-во истинных выражений в строках одинаково и в строке больше ложных выражений подряд, чем в следующей
                if (t[i]<t[i+1] || (t[i]==t[i+1] && mf[i]>mf[i+1])){
//                  перестановка строк в массиве b
                    z = b[i];
                    b[i] = b[i+1];
                    b[i+1] = z;

//                  перестановка строк в массиве a
                    s = a[i];
                    a[i] = a[i+1];
                    a[i+1] = s;

//                  перестановка эл-тов в массиве t
                    arr = t[i];
                    t[i] = t[i+1];
                    t[i+1] = arr;

//                  перестановка эл-тов в массиве mf
                    arr = mf[i];
                    mf[i] = mf[i+1];
                    mf[i+1] = arr;
                }
            }

//      создаем массив x для подсчета уникальных правых частей
        int []x = new int[10];
        //заполняем x нулями
        for (int i = 0; i<10; i++)
            x[i]=0;

        //считаем, сколько раз встречается каждая цифра
        for (int i=0; i<n; i++)
            for (int j=0; j<m; j++)
                x[(a[i][j].charAt(2))-'0'] ++;
        //целочсленная переменная для подсчета уникальных правых частей
        int s2 = 0;
        //подсчет уникальных правых частей
        for (int i=0; i<x.length; i++){
            if (x[i]==1)
                s2++;
        }

        out.println();
        //вывод отсортированного массива строк a
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++)
                out.print(a[i][j]+" ");
            out.println();
        }

        out.println();
//        вывод отсортированного массива b
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++)
                if (b[i][j])
                    out.print("Истина"+" ");
                else
                    out.print("Ложь"+" ");
            out.println();
        }

        out.println();
        //вывод кол-ва уникальных правых частей
        out.println(s2);

    }
}
