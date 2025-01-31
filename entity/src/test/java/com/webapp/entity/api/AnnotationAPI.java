package com.webapp.entity.api;

import com.webapp.entity.Annotation;
import com.webapp.entity.Content;
import com.webapp.entity.TimeMark;
import com.webapp.entity.Title;

import java.time.OffsetDateTime;
import java.util.UUID;

public class AnnotationAPI {
    private Annotation annotation;
    private TitleAPI titleAPI;

    /**
     * @param title the Annotation title
     * @param content the Annotation content
     * @param timeMark the Annotation timeMark
     * @return a new Annotation
     */
    public AnnotationAPI(String title, String content, OffsetDateTime timeMark) {
        this.annotation = new Annotation(new Title(title), new Content(content), new TimeMark(timeMark));
    }

    /**
     * @param uuid the Annotation id
     * @param title the Annotation title
     * @param content the Annotation content
     * @param timeMark the Annotation timeMark
     * @return a new Annotation
     */
    public AnnotationAPI(UUID uuid, Title title, Content content, TimeMark timeMark) {
        this.annotation = new Annotation(uuid, title, content, timeMark);
    }

    public String getTitle() {
        return annotation.getTitle();
    }

    public String getContent() {
        return annotation.getContent();
    }

    public OffsetDateTime getCreation() {
        return annotation.getCreation();
    }

    public String annotationToString() {
        return annotation.toString();
    }
}
