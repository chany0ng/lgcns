package com.lgcns;

import java.util.Scanner;

public class Scan {
    public static void main(String[] args) {
        System.out.print("Input id1 id2 성 이름: ");
        Scanner sc = new Scanner(System.in);
        // 1 2 Hong Kildong 입력했다면,
        int num = sc.nextInt();  // 1
        double dbl = sc.nextDouble(); // 2

        String name = sc.next(); // Hong
// 여기서 next가 아니라 nextLine이면
// name은 Hong Kildong

//        sc.nextLine(); // 버퍼(뒷 부분) 읽어서 버림
        String str = sc.nextLine();
        sc.close(); // release scanner

        System.out.printf("%d %f %s %s%n", num, dbl, name, str);
    }
}
