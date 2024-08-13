package com.losgai.gulimall.common.common.constant;

import lombok.Getter;

public enum ProductConstant {;
    @Getter
    public enum AttrEnum {
        BASE_ATTR(0, "规格参数"),
        SALE_ATTR(1, "销售属性"),
        BOTH_ATTR(2,"销售属性和规格参数");
        private final int code;
        private final String msg;
        AttrEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }
}
