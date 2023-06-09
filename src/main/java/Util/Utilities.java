package Util;

import com.precize.CodingAssignment.Enum.Result;

public class Utilities {
    public static Result calculateResult(Double score){
        if(score > 33.33) return Result.PASS;
        return Result.FAIL;
    }

    public static String getRank(Double score){
        if(score>=90) return "A";
        if(score >= 80 && score < 90) return "B";
        if(score >= 70 && score < 80) return "C";
        if(score >= 60 && score < 70) return "D";
        if(score >= 50 && score < 60) return "C";
        if(score >= 33.34 && score <50) return "D";
        return "F";
    }
}
