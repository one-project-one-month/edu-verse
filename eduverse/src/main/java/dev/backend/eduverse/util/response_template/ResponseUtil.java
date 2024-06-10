package dev.backend.eduverse.util.response_template;

import dev.backend.eduverse.util.PaginationParams;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.function.Function;

@Component
public class ResponseUtil {

    public static <T> ResponseEntity<ApiResponse<T>> createSuccessResponse(
            HttpStatus httpStatus, String message, T data) {
        ApiResponse<T> response = new ApiResponse<>(httpStatus, message, data);
        return ResponseEntity.status(httpStatus).body(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> createErrorResponse(
            HttpStatus httpStatus, String message, T data) {
        ApiResponse<T> response = new ApiResponse<>(httpStatus, message, data);
        return ResponseEntity.status(httpStatus).body(response);
    }

    public static <T> ResponseEntity<ApiResponse<PageNumberResponse<List<T>>>> getApiResponseResponseEntity(
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
            Function<PaginationParams, List<T>> serviceMethod,
            Logger logger,
            String noDataMessage,
            String successMessage) {
        try {
            List<T> entityList = serviceMethod.apply(new PaginationParams(pageNo, limit));
            if (entityList.isEmpty()) {
                return ResponseUtil.createSuccessResponse(HttpStatus.OK, noDataMessage,
                        new PageNumberResponse<>(pageNo, limit, entityList));
            } else {
                return ResponseUtil.createSuccessResponse(HttpStatus.OK, successMessage,
                        new PageNumberResponse<>(pageNo, limit, entityList));
            }
        } catch (Exception e) {
            logger.error("Failed to retrieve entities", e);
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve entities",
                    null);
        }
    }
}
