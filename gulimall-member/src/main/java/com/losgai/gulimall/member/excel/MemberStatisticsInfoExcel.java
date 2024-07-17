package com.losgai.gulimall.member.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员统计信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
public class MemberStatisticsInfoExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "会员id")
    private Long memberId;
    @ExcelProperty(value = "累计消费金额")
    private BigDecimal consumeAmount;
    @ExcelProperty(value = "累计优惠金额")
    private BigDecimal couponAmount;
    @ExcelProperty(value = "订单数量")
    private Integer orderCount;
    @ExcelProperty(value = "优惠券数量")
    private Integer couponCount;
    @ExcelProperty(value = "评价数")
    private Integer commentCount;
    @ExcelProperty(value = "退货数量")
    private Integer returnOrderCount;
    @ExcelProperty(value = "登录次数")
    private Integer loginCount;
    @ExcelProperty(value = "关注数量")
    private Integer attendCount;
    @ExcelProperty(value = "粉丝数量")
    private Integer fansCount;
    @ExcelProperty(value = "收藏的商品数量")
    private Integer collectProductCount;
    @ExcelProperty(value = "收藏的专题活动数量")
    private Integer collectSubjectCount;
    @ExcelProperty(value = "收藏的评论数量")
    private Integer collectCommentCount;
    @ExcelProperty(value = "邀请的朋友数量")
    private Integer inviteFriendCount;

}