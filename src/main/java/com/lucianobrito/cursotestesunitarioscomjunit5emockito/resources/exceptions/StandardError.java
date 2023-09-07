package com.lucianobrito.cursotestesunitarioscomjunit5emockito.resources.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@JsonPropertyOrder(value = {"timestap", "statusCode", "path", "title", "error", "fields", "cause"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'XXXXX")
    private OffsetDateTime timestap;
    private Integer statusCode;
    private String path;
    private String title;
    private String error;
    private List<Field> fields;
    private Throwable cause;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Field {

        private String name;
        private String message;

    }

}

