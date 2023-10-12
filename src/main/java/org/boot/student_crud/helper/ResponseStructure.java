package org.boot.student_crud.helper;

import lombok.Data;

@Data
public class ResponseStructure<T> {
    String message;
    int status;
    T data;
}
