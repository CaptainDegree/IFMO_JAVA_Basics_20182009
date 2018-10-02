package ru.ifmo.cet.javabasics;

class EnglishNumberToWord {

    private static final String[] tensNames = {
            "",
            " ten",
            " twenty",
            " thirty",
            " forty",
            " fifty",
            " sixty",
            " seventy",
            " eighty",
            " ninety"
    };

    private static final String[] numNames = {
            " no more",
            " one",
            " two",
            " three",
            " four",
            " five",
            " six",
            " seven",
            " eight",
            " nine",
            " ten",
            " eleven",
            " twelve",
            " thirteen",
            " fourteen",
            " fifteen",
            " sixteen",
            " seventeen",
            " eighteen",
            " nineteen"
    };

    public static String convert (int number) {
        String soFar = "";

        if (number % 100 < 20)
            soFar = numNames[number % 100];
        else {
            if (number % 10 != 0)
                soFar = numNames[number % 10];
            
            soFar = tensNames[(int)(number / 10) % 10] + soFar;
        }

        return soFar;
    }
}
