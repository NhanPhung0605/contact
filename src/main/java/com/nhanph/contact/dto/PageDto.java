package com.nhanph.contact.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
@NoArgsConstructor
public class PageDto {
    @SuppressWarnings("rawtypes")
    private List content;
    private long totalElements;
    private int number;
    private int numberOfElements;
    private int totalPages;
}
