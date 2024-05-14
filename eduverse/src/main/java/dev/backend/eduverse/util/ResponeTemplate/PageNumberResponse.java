package dev.backend.eduverse.util.ResponeTemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageNumberResponse<T> {
	private int pageNumber;
	private T data;
}
