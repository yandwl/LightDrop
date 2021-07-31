package me.neiizun.lightdrop.util;

import org.reflections8.Reflections;
import org.reflections8.scanners.MethodAnnotationsScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Util for reflection
 *
 * @since 1.2.0
 */
public class ReflectionsUtil {

    /**
     * Check if a class has 0 args constructor.
     *
     * @param clazz The clazz to check.
     * @return true if the class has 0 args constructor.
     */
    public static boolean hasNoArgConstructor(Class<?> clazz) {
        for (Constructor<?> constructor : clazz.getConstructors()) {
            if (constructor.getParameterCount() == 0) return true;
        }

        return false;
    }

    /**
     * Get a Set of methods having a certain annotation.
     *
     * @param clazz           The clazz to loop through.
     * @param annotationClass The class of the annotation to find.
     * @return {@link Set<Method>} of specified annotated classes.
     */
    public static Set<Method> getMethodsAnnotatedWith(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        return new Reflections(clazz, new MethodAnnotationsScanner()).getMethodsAnnotatedWith(annotationClass);
    }
}
