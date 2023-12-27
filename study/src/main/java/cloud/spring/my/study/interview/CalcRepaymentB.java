package cloud.spring.my.study.interview;

import java.math.BigDecimal;

/**
 * 每月发电基准值为 a 度，电价为b元/度，农户每月补贴c元, 每月从电网公司归集收益为可变数。租金按季度收取。
 * 每期租金计算为：
 * 每期应收租金=（a*b-c ）*3
 * 每期实际发电收益为计算期间从电网公司归集收益
 * 每期差额补足额=每期应收租金-每期实际收益   如每期差额补足额<0 ,则计为0。
 */
public class CalcRepaymentB {

    /**
     * 方案二 租金计算
     *
     * @param electricBase   发电基准值
     * @param electricPrice     电价
     * @param subsidy   补贴
     * @param income    归集收益
     * @return
     */
    public BigDecimal execCalc(Integer electricBase, BigDecimal electricPrice, BigDecimal subsidy, BigDecimal income) {
        BigDecimal complement = electricPrice.multiply(BigDecimal.valueOf(electricBase)).min(subsidy).multiply(BigDecimal.valueOf(3));
        return income.compareTo(complement) >= 0 ? BigDecimal.ZERO : complement.min(income);
    }

}
