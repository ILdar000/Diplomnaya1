package com.example.diplomnaya.algoritm;

public class Size {
    private static final int[][] ParamWoman = {
            {80,84,88,92,93,100,104,108,112,116},
            {60,64,68,70,74,79,83,88,93,98},
            {88,92,96,100,104,108,112,116,120,124},
            {170,170,170,170,170,170,170,170,170,170}
    };
    private static final String[][] SizesWoman = {
            {"40","42","44","46","48","50","52","54","56","58"},//RU
            {"XXS","XS","S","M","L","XL","XXL","3XL","4XL","5XL"},
            {"34","36","38","40","42","44","46","48","50","52"}, //EU
            {"2-4","6","8-10","12-14","16-18","20-22","24-26","-","-","-"} //US
    };
    private static final int[][] ParamMan = {
            {92,96,100,104,110,114,118,122},
            {80,84,88,92,98,102,106,110},
            {96,99,101,104,108,111,113,116},
            {176,176,182,182,188,188,188,188}
    };
    private static final String[][] SizesMan = {
        {"46","48","50","52","54","56","58","60"},//RU
        {"S","M","L","XL","XXL","3XL","4XL","5XL"},
        {"46","48","50","52","54","56","58","60"},//EU
        {"36","38","40","42","44","46","48","50"}//US
    };

    public static String[] selectSize(String sex,int height, int chest, int waist, int hips) {
        String[] size = new String[8];
        int sizeNumber;
        if (sex.equals("Мужской")){
            sizeNumber= clothesSize(ParamMan,height,chest,waist,hips);
            size[0] = SizesMan[0][sizeNumber];
            size[1] = SizesMan[1][sizeNumber];
            size[2] = SizesMan[2][sizeNumber];
            size[3] = SizesMan[3][sizeNumber];
            sizeNumber= clothesSize(ParamMan,height,0,waist,hips);
            size[4] = SizesMan[0][sizeNumber];
            size[5] = SizesMan[1][sizeNumber];
            size[6] = SizesMan[2][sizeNumber];
            size[7] = SizesMan[3][sizeNumber];
        }
        else {
            sizeNumber= clothesSize(ParamWoman,height,chest,waist,hips);
            size[0] = SizesWoman[0][sizeNumber];
            size[1] = SizesWoman[1][sizeNumber];
            size[2] = SizesWoman[2][sizeNumber];
            size[3] = SizesWoman[3][sizeNumber];
            sizeNumber= clothesSize(ParamWoman,height,0,waist,hips);
            size[4] = SizesWoman[0][sizeNumber];
            size[5] = SizesWoman[1][sizeNumber];
            size[6] = SizesWoman[2][sizeNumber];
            size[7] = SizesWoman[3][sizeNumber];
        }

        return size;
    }

    private static int clothesSize(int[][] Param,int height, int chest, int waist, int hips) {
        int size = 0;
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < Param[0].length; i++) {
            int distance = calculateDistance(Param,height, chest, waist, hips, i);
            if (distance < minDistance) {
                minDistance = distance;
                size = i;
            }
        }
        return size;
    }

    private static int calculateDistance(int[][] Param,int height, int chest, int waist, int hips, int sizeIndex) {
        int distance = 0;
        if (chest != 0) {
            distance += Math.abs(chest - Param[0][sizeIndex]);
        }
        distance += Math.abs(waist - Param[1][sizeIndex]);
        distance += Math.abs(hips - Param[2][sizeIndex]);
        distance += Math.abs(height - Param[3][sizeIndex]);
        return distance;
    }
    //https://baon.ru/index/sizes/
}

