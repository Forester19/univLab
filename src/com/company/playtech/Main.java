package com.company.playtech;

import java.io.*;

public class Main {
    private static double[][] r = new double[2][3];
    private static double[][] v = new double[2][3];
    private static double[][] a = new double[2][3];
    private static double dt = 0.001;
    private static double r12, r13, r23;


    public static void main(String[] args) {
        initArray();
        calculate();

    }

    private static void initArray() {
        r[0][0] = 1.0;
        r[0][1] = 1.0;
        r[0][2] = 1.0;
        r[1][0] = 1.0;
        r[1][1] = 2.0;
        r[1][2] = 3.0;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                v[i][j] = 0;
            }
        }
    }

    private static void calculate() {

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("res.txt"), "utf-8"))) {
            for (int l = 0; l < 45; l++) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 3; j++) {

                        r12 = Math.sqrt(((r[0][1] - r[0][0]) * (r[0][1] - r[0][0])) + ((r[1][1] - r[1][0]) * (r[1][1] - r[1][0])));
                        r13 = Math.sqrt(((r[0][2] - r[0][0]) * (r[0][2] - r[0][0])) + ((r[1][2] - r[1][0]) * (r[1][2] - r[1][0])));
                        r23 = Math.sqrt(((r[0][2] - r[0][1]) * (r[0][2] - r[0][1])) + ((r[1][2] - r[1][1]) * (r[1][2] - r[1][1])));


                        a[i][0] = (6 * r[i][0] / Math.pow(r12, 8)) - (12 * r[i][0] / Math.pow(r12, 14)) + (6 * r[i][0] / Math.pow(r13, 8)) - (12 * r[i][0] / Math.pow(r13, 14));
                        a[i][1] = (6 * r[i][1] / Math.pow(r12, 8)) - (12 * r[i][1] / Math.pow(r12, 14)) + (6 * r[i][1] / Math.pow(r23, 8)) - (12 * r[i][1] / Math.pow(r23, 14));
                        a[i][2] = (6 * r[i][2] / Math.pow(r13, 8)) - (12 * r[i][2] / Math.pow(r13, 14)) + (6 * r[i][2] / Math.pow(r23, 8)) - (12 * r[i][2] / Math.pow(r23, 14));
                        r[i][j] = r[i][j] + v[i][j] * dt;
                        v[i][j] = v[i][j] + a[i][j] * dt;
                        writer.write(String.valueOf(r[i][j]) + " \n");
                    }
                }
            }
            writer.flush();
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
