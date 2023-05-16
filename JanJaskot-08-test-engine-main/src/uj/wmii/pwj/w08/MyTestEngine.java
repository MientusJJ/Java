package uj.wmii.pwj.w08;


import java.io.Console;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MyTestEngine {

    private final String className;

    public static void main(String[] args) {
        art();
        if (args.length < 1) {
            System.out.println("Please specify test class name");
            System.exit(-1);
        }
        MyTestEngine engine;
        for (var str:args)
        {
            String className = str;
            System.out.printf("Testing class: %s\n", className);
            engine = new MyTestEngine(className);
            engine.runTests();
        }
    }

    public MyTestEngine(String className) {
        this.className = className;
    }

    public void runTests() {
        final Object unit = getObject(className);
        List<Method> testMethods = getTestMethods(unit,className);
        int successCount = 0;
        int failCount = 0;
        int errorCount = 0;
        for (Method m: testMethods) {
            TestResult result = launchSingleMethod(m, unit);
            if (result == TestResult.SUCCESS) successCount++;
            else if(result == TestResult.FAIL) failCount++;
            else errorCount++;
        }
        System.out.printf("Engine launched %d tests.\n", testMethods.size());
        System.out.printf("%d of them passed, %d failed. %d had error\n", successCount, failCount,errorCount);

    }

    private TestResult launchSingleMethod(Method m, Object unit) {
        if(this.className.equals("uj.wmii.pwj.w08.MyBeautifulTestSuite"))
        {
            return myTestMethod(m,unit);
        }
        else if(this.className.equals("uj.wmii.pwj.w08.MyUglyTestSuite"))
        {
            return ownTestMethod(m,unit);
        }
        return TestResult.ERROR;
    }
    private TestResult myTestMethod(Method m, Object unit)
    {
        try {
            String[] params = m.getAnnotation(MyTest.class).params();
            if (params.length == 0) {
                m.invoke(unit);
            } else {
                for (String param: params) {
                    m.invoke(unit, param);
                }
            }
            System.out.println("Tested method: " + m.getName() + " test successful.");
            return TestResult.SUCCESS;
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            return TestResult.FAIL;
        }
    }
    private TestResult ownTestMethod(Method m, Object unit)
    {
        try {
            var annotation = m.getAnnotation(OwnTest.class);
            String param = annotation.param();
            String result = annotation.result();
            Object a = m.invoke(unit, param, result);
            if((boolean)a== true) {
                return TestResult.SUCCESS;
            }
            else if((boolean)a== false)
            {
                return TestResult.FAIL;
            }
            else
            {
                return TestResult.ERROR;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return TestResult.FAIL;
        }

    }
    private static List<Method> getTestMethods(Object unit,String name) {
        Method[] methods = unit.getClass().getDeclaredMethods();
        if (name.equals("uj.wmii.pwj.w08.MyBeautifulTestSuite")) {
            return Arrays.stream(methods).filter(
                    m -> m.getAnnotation(MyTest.class) != null).collect(Collectors.toList());
        }
        else if(name.equals("uj.wmii.pwj.w08.MyUglyTestSuite"))
        {
            return Arrays.stream(methods).filter(
                    m -> m.getAnnotation(OwnTest.class) != null).collect(Collectors.toList());
        }
        else return null;
    }

    private static Object getObject(String className) {
        try {
            Class<?> unitClass = Class.forName(className);
            return unitClass.getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            return new Object();
        }
    }
    private static void art()
    {
        System.out.println(
                        "::::    ::::  :::   :::      ::::::::::: :::::::::: :::::::: :::::::::::      :::::::::: ::::    :::  :::::::: ::::::::::: ::::    ::: :::::::::: \n"
                                + "+:+:+: :+:+:+ :+:   :+:          :+:     :+:       :+:    :+:    :+:          :+:        :+:+:   :+: :+:    :+:    :+:     :+:+:   :+: :+:        \n"
                                + "+:+ +:+:+ +:+  +:+ +:+           +:+     +:+       +:+           +:+          +:+        :+:+:+  +:+ +:+           +:+     :+:+:+  +:+ +:+        \n"
                                +"+#+  +:+  +#+   +#++:            +#+     +#++:++#  +#++:++#++    +#+          +#++:++#   +#+ +:+ +#+ :#:           +#+     +#+ +:+ +#+ +#++:++#   \n"
                                +"+#+       +#+    +#+             +#+     +#+              +#+    +#+          +#+        +#+  +#+#+# +#+   +#+#    +#+     +#+  +#+#+# +#+        \n"
                                +"#+#       #+#    #+#             #+#     #+#       #+#    #+#    #+#          #+#        #+#   #+#+# #+#    #+#    #+#     #+#   #+#+# #+#        \n"
                                +"###       ###    ###             ###     ########## ########     ###          ########## ###    ####  ######## ########### ###    #### ########## \n"
        );
    }
}
