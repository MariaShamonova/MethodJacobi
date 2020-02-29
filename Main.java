package com.metanit;

import java.io.FileReader;
import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception  {

        FileReader reader = new FileReader("C:\\Users\\honey\\IdeaProjects\\Yacoby\\src\\com\\metanit\\text.txt");
        Scanner scan = new Scanner(reader);

        String str = new String(scan.nextLine());
        System.out.println(str);

        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        nf.setGroupingUsed(false);

        int i = 0, count = 0;
        for (String retval : str.split(" ")) {
            count++;
        }
        double[] array = new double[count];

        for (String retval : str.split(" ")) {
            array[i] = Double.parseDouble(retval);
            i++;
        }

        Yacoby matrixA = new Yacoby((int)array[0]);
        matrixA.Generate(array[1]);

        matrixA.Print();

        double EPS = Math.pow(10, (-6));
        int size = matrixA.N;
        double[] solution = new double[size];
        solution = matrixA.MethodZeidel(EPS);
        System.out.println("X: ");
        matrixA.PrintArray(solution);


    }
}
