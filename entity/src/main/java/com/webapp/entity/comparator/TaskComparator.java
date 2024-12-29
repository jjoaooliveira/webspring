package com.webapp.entity.comparator;

import java.util.Comparator;

import com.webapp.entity.Task;

public class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task task1, Task task2) {
        return task1.getExpirationDate().compareTo(task2.getExpirationDate());
    }

}
