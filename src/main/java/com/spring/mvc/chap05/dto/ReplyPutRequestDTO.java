package com.spring.mvc.chap05.dto;

import com.spring.mvc.chap05.entity.Reply;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyPutRequestDTO {
    @NotBlank
    private String text; // 수정 된 댓글 내용

    @NotNull
    @Min(0) @Max(Long.MAX_VALUE)
    private Long replyNo; // 수정 대상 댓글 번호

    @NotNull
    @Min(0) @Max(Long.MAX_VALUE)
    private Long boardNo; // 원본 글 번호


    public Reply toEntity() {
        return Reply.builder()
                .boardNo(this.boardNo)
                .replyText(this.text)
                .replyNo(this.replyNo)
                .build();
    }


}
