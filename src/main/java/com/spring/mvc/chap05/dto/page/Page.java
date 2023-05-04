package com.spring.mvc.chap05.dto.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Page {
    private int pageNo; // 클라이언트가 보낸 페이지 번호
    private int amount; // 클라이언트가 보낸 목록게시물 수


    // /list로 들어올때 기본값을 주기위한 방법
    // spring은 기본생성자를 만들기때문에 여기서 초기값을 설정해줄 수도 있음
    public Page() {
        this.pageNo = 1;
        this.amount = 6;
    }

    public void setPageNo(int pageNo) {
        if (pageNo < 1 || pageNo > Integer.MAX_VALUE) {
            this.pageNo = 1;
            return;
        }
        this.pageNo = pageNo;
    }

    public void setAmount(int amount) {
        if (amount < 6 || amount > 10) {
            this.amount = 6;
            return;
        }
        this.amount = amount;
    }

    public int getPageStart() {
        /*
            pageNo : 1 -> return 0
            pageNo : 2 -> return 6
            pageNo : 3 -> return 12
            pageNo : n -> return (n - 1) * 6

         */
        return (pageNo - 1) * amount;
    }
}
