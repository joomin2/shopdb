    package edu.sm.dto;

    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.util.Date;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    //database와 동일하게..

    public class Orders {
        private int order_id; // 주문 테이블 아이디
        private Date order_date; // 생성 날짜 (Date 타입으로 변경)
        private String status; // 진행 상황
        private int total_price; // 총 가격
        private int user_id; // 사용자 ID

    }
