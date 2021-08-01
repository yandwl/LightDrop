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
}
