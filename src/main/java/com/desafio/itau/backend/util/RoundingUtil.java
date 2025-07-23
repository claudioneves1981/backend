package com.desafio.itau.backend.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundingUtil {


    private Double roundingUp(Double number){

        BigDecimal bg = BigDecimal.valueOf(number);
        bg = bg.setScale(2, RoundingMode.HALF_UP);
        return bg.doubleValue();

    }

    private Double roundingDown(Double number){

        BigDecimal bg = BigDecimal.valueOf(number);
        bg = bg.setScale(2, RoundingMode.HALF_DOWN);
        return bg.doubleValue();

    }



    public Double roundingNumber(Double number){

        String numberConvert = String.valueOf(number);

        String[] halfNumber = numberConvert.split("\\.");

        Double result  = 0.0d;

        if(halfNumber[1].length() > 3){

            if(halfNumber[1].charAt(3) > 0 && halfNumber[1].charAt(2) == 5){

                result = roundingUp(number);

            }


        }else if(halfNumber[1].length() == 3){


            if(halfNumber[1].charAt(2) == 5 && halfNumber[1].charAt(1) % 2 == 0) {

                result = roundingDown(number);

            }else if(halfNumber[1].charAt(2) == 5 && halfNumber[1].charAt(1) % 2 == 1){

                result = roundingUp(number);

            }else if(halfNumber[1].charAt(2) > 5){

                result = roundingUp(number);

            }

        }else{

            result =  number;

        }

         return result;
    }


}
