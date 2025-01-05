package com.jeyofdev.shopping_krist.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDataResponse {
    private String title;
    private String review;
    private int rating;
}
