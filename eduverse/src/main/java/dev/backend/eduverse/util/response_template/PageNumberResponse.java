package dev.backend.eduverse.util.response_template;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageNumberResponse<T> {
    private int pageNumber;
    private int limit;
    private T data;
}
