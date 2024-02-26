package Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // This annotation will be retained at runtime
@Target(ElementType.TYPE) // This annotation can be applied to methods
public @interface MimServlet {
    String[] urlPatterns();

}
