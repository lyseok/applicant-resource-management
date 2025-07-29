package kr.or.ddit.vo.common;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductListResponseVO {
    private List<PaymentProductVO> products;
    private int totalPages;
}
