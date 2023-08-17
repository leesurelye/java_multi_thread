package org.annotation;

import java.lang.annotation.Annotation;

public class Main
{
    public static void main(String[] args)
    {
        Class<AnnotationObj> clazz = AnnotationObj.class;
        ContentView annotation = clazz.getAnnotation(ContentView.class);
        System.out.println(annotation.value());
    }
}
