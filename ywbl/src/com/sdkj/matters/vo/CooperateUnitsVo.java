package com.sdkj.matters.vo;

import lombok.Data;
import lombok.ToString;

/**
 * 配合单位关联表
 * @return
 */
@Data
@ToString
public class CooperateUnitsVo {
  private   String  id;//
  private   String  dept_id;//处室id
  private   String  role_id;//处室下id
  private   String  user_id;//用户id
  private   String  matter_id;//事项id
  /**
   * （01 未查看、02已查看、03 提交）
   */
  private   String  state;//
  /**
   * (01 牵头单位、02 配合单位)
   */
  private   String  unitstate;//
   
  
}
