package com.sdkj.TimeScore.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StandardVo {
    private   String   id;//
    private   String   zbname;//指标名称
    private   String   score;//分数
    private   String   state;//加分减分
}
