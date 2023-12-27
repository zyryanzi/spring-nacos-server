package cloud.spring.my.study.interview;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * 租金年利率为a，每期应还本金b=租赁金额/租赁期限*12, 还款频率（每月一次），最大还款期次为N (N<=租赁期限*12)
 * <p>
 * 每期租金计算为：
 * 第一期租金：b+租赁金额*a*(放款日后遇到第一个20号-放款日)/360
 * 第二期租金：b+（租赁金额-b*1）*a*(第二期租金还款日-第一期租金还款日)/360
 * 第三期租金：b+（租赁金额-b*2）*a*(第三期租金还款日-第二期租金还款日)/360
 * 第四期租金：b+（租赁金额-b*3）*a*(第四期租金还款日-第三期租金还款日)/360
 * .......
 * 第N期租金： b+（租赁金额-b*（N-1））*a*(第N期租金还款日-第N-1期租金还款日)/360
 * 要求：
 * 最后一期租金计算结束后，剩余的租赁金额为0
 */
public class ReceivablePlanA {

    /**
     * 计算方案一应收租金
     *
     * @param loanDate          放款日期
     * @param loanAmount        放款金额
     * @param annualRate        租金年利率
     * @param repaymentList     应收租金金额列表
     * @param payablePrincipal  应付本金
     * @param leasingTerm       最大租期
     * @return
     */
    private List<BigDecimal> execCalc(LocalDate loanDate,
                                      BigDecimal loanAmount,
                                      BigDecimal annualRate,
                                      List<BigDecimal> repaymentList,
                                      BigDecimal payablePrincipal,
                                      Integer leasingTerm) {

        if (repaymentList.size() > leasingTerm) {
            // 超期异常
            return null;
        }

        if (loanAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return repaymentList;
        } else {
            BigDecimal interest = loanAmount
                    .multiply(annualRate)
                    .multiply(BigDecimal.valueOf(getDiffDays(loanDate, repaymentList.size())))
                    .divide(BigDecimal.valueOf(360), RoundingMode.HALF_UP);
            // 本期应还
            BigDecimal repayment = payablePrincipal.add(interest);
            repaymentList.add(repayment);
            // 剩余本金
            loanAmount = loanAmount.compareTo(payablePrincipal) > 0 ? loanAmount.min(payablePrincipal) : BigDecimal.ZERO;
            return execCalc(loanDate, loanAmount, annualRate, repaymentList, payablePrincipal, leasingTerm);
        }

    }

    /**
     * 每期差别天数
     * @param loanDate 放款日期
     * @param termsIdx 租金期数
     * @return
     */
    private Integer getDiffDays(LocalDate loanDate, Integer termsIdx) {
        if (loanDate == null) {
            return null;
        }
        if (termsIdx > 0) {
            // 第二到最后一期
            LocalDate start;
            LocalDate end;
            if (loanDate.getDayOfMonth() < 20) {
                start = loanDate.plusMonths(termsIdx);
                end = loanDate.plusMonths(termsIdx + 1);
            } else {
                start = loanDate.plusMonths(termsIdx + 1);
                end = loanDate.plusMonths(termsIdx + 2);
            }
            Period next = Period.between(start,end);
            return next.getDays();//相差天数
        } else if (loanDate.getDayOfMonth() < 20) {
            // 首期 放款日早于20日
            return 20 - loanDate.getDayOfMonth();
        } else {
            // 首期 放款日晚于20日
            return 20 + loanDate.lengthOfMonth() - loanDate.getDayOfMonth();
        }
    }

    public static void main(String[] args) {
        System.out.println(LocalDate.now().lengthOfMonth() - LocalDate.now().getDayOfMonth());
    }

}
