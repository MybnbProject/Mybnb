package com.project.mybnb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageInfo {

    private int page;
    private int limit;
    private long totalElement;
    private int totalPages;

}
