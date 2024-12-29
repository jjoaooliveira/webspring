package com.webapp.entity.comparator;

import java.util.Comparator;

import com.webapp.entity.Annotation;

public class AnnotationComparator implements Comparator<Annotation> {
    @Override
    public int compare(Annotation annotation1, Annotation annotation2) {
        return annotation1.getCreationDate().compareTo(annotation2.getCreationDate());
    }

}
