package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {
    /**
     *
     * @param member
     * @param price
     * @return 할인 대상 그앰
     */
    int discount(Member member, int price);
}
