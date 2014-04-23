package my.lab.freemarker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface GenerateDao {
    public String packageName() default "";
    public String simpleName() default "";
    public String target() default "target/generated-sources/apt/";
}
