package com.news.news.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class PaginationResponse<T> implements Serializable {

    private List<T> response;
    private int totalPages;
    private Long totalElements;

}

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class PaginationResponse {
//
//    private List<Noticia> noticiaList;
//    private Integer currentPage;
//    private Integer countPerPage;
//    private Integer totalCount;
//}