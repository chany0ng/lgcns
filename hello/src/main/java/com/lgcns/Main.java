package com.lgcns;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
        int month = 3;
        String season = switch (month) {
            case 12, 1, 2 -> {
                System.out.println("month = " + month);
                yield "겨울";
            }
            case 3, 4, 5 -> "봄";
            case 6, 7, 8 -> "여름";
            case 9, 10, 11 -> "가을";
            default -> "모름";
        };

    }
}