package com.oddhov.meteorfinder.base;

import java.lang.reflect.Field;

/**
 * Created by sammy on 19/09/17.
 */

public class BaseTestCase {
    protected boolean setFieldValueThroughReflection(Object object, String fieldName, Object value) {
        Field field;
        Class className = object.getClass();
        try {
            field = className.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
            return true;
        } catch (NoSuchFieldException e) {
            while (className.getSuperclass() != null) {
                try {
                    className = className.getSuperclass();
                    field = className.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    field.set(object, value);
                    return true;
                } catch (NoSuchFieldException ex) {
                    continue;
                } catch (IllegalAccessException ex) {
                    return false;
                }
            }
            return false;
        } catch (IllegalAccessException e) {
            return false;
        }
    }
}
