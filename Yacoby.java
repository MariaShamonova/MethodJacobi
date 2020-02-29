package com.metanit;

import java.text.NumberFormat;

public class Yacoby {

    public double[][] data;
    public double[] matrixB;

    public int N;
    public int q;
    double EPS = Math.pow(10, (-6));

    public Yacoby(int size) {
        N = size;

        data = new double[N][];
        matrixB = new double[N];

        for (int i = 0; i < size; i++) {
            data[i] = new double[N];
            for (int j = 0; j < size; j++) {
                data[i][j] = 0.0;
            }
        }
    }

    public void Generate(double q) {
        double sum;
        for (int i = 0; i < N; i++) {
            sum = 0;;
            for (int j = 0; j < N; j++) {
                if (i != j) data[i][j] = (int) (Math.random() * (20 + 1)) - 10;
                sum+= Math.abs(data[i][j]);
            }
            data[i][i] = sum * q;
            matrixB[i] = (int) (Math.random() * (20 + 1)) - 10;
        }
    }

    public void Print() {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        nf.setGroupingUsed(false);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(nf.format(data[i][j]) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void PrintArray(double[] array) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        nf.setGroupingUsed(false);

        for (int i = 0; i < N; i++)
        {
            System.out.println(nf.format(array[i]));
        }
        System.out.println();
    }

    public double[] MethodZeidel(double eps){


        double[] previousVariableValues = new double[N];

        while(true){
            double[] currentVariableValues = new double[N];
            for (int i = 0; i < N; i++) {
                // Инициализируем i-ую неизвестную значением
                // свободного члена i-ой строки матрицы
                currentVariableValues[i] = matrixB[i];

                // Вычитаем сумму по всем отличным от i-ой неизвестным
                for (int j = 0; j < N; j++) {
                    // При j < i можем использовать уже посчитанные
                    // на этой итерации значения неизвестных
                    if (j < i) {
                        currentVariableValues[i] -= this.data[i][j] * currentVariableValues[j];
                    }

                    // При j > i используем значения с прошлой итерации
                    if (j > i) {
                        currentVariableValues[i] -= this.data[i][j] * previousVariableValues[j];
                    }
                }

                // Делим на коэффициент при i-ой неизвестной
                currentVariableValues[i] /= this.data[i][i];
            }

            double error = 0.0;

            for (int i = 0; i < N; i++) {
                error += Math.abs(currentVariableValues[i] - previousVariableValues[i]);
            }
            if (error < EPS) {
                break;
            }

            previousVariableValues = currentVariableValues;
        }

        return previousVariableValues;
    }

    public double[] MethodYacoby(double eps){

        double[] previousVariableValues = new double[N];

        for (int i = 0; i < N; i++) {
            previousVariableValues[i] = 0.0;

        }

        while (true) {

            double[] currentVariableValues = new double[N];

            for (int i = 0; i < N; i++) {
                currentVariableValues[i] = this.data[i][N];
                for (int j = 0; j < N; j++)
                {
                    if (i != j)
                    {
                        currentVariableValues[i] -= this.data[i][j] * previousVariableValues[j];
                    }

                }
                currentVariableValues[i] /= this.data[i][i];
            }

            double error = 0.0;
            for (int i = 0; i < N; i++)
            {
                error += Math.abs(currentVariableValues[i] - previousVariableValues[i]);
            }

            if (error < eps)
            {
                break;
            }
            previousVariableValues = currentVariableValues;

        }
    }


    public double MethodСonditional () {
        double sum, maximumSum = 0;

        for(int i = 0; i < N; i++)
        {
            sum = 0;
            for(int j = 0; j < N; j++)
            {
                sum += Math.abs(this.data[i][j]);
            }
            if (sum > maximumSum) maximumSum = sum;
        }
        return maximumSum;
    }

    зги
}
