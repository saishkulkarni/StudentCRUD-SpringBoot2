package org.boot.student_crud.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class DataShouldNotRepeatException extends RuntimeException {
    String message = "Data Should not Repeat";
}
