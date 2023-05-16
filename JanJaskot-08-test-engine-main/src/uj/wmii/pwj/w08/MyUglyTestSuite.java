package uj.wmii.pwj.w08;

public class MyUglyTestSuite {
    @OwnTest(param = "Ala",result = "Ala")
    public boolean test1(String param,String result)
    {
        return param.equals(result);
    }
    @OwnTest(param = "Tata",result = "Ala")
    public boolean test2(String param,String result)
    {
        return param.equals(result);
    }
    @OwnTest(param = "",result = "")
    public boolean test3(String param,String result)
    {
        return param.equals(result);
    }
    @OwnTest(param = "Ala",result = "")
    public boolean test4(String param,String result)
    {
        return param.equals(result);
    }
    @OwnTest(param = "",result = "Mama")
    public boolean test5(String param,String result)
    {
        return param.equals(result);
    }
}
