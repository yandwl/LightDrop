package me.neiizun.lightdrop.registion;


import java.lang.annotation.*;


/**
 * use this anotation on classes containing commands for scanner
 */
@Target(ElementType.TYPE)
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandCluster {
    String forBot();
}
