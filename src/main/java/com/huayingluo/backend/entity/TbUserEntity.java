package com.huayingluo.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@TableName("tb_user")
public class TbUserEntity extends BaseEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @NotEmpty(message = "用户名不能为空")
    @Length(min = 6, max = 10, message = "用户名长度为 6 - 10 位")
    private String username;

    private String password;
    @NotEmpty(message = "手机号码不能为空")
    private String phone;

    @NotEmpty(message = "邮箱不能为空")
    private String email;

    @JsonIgnore
    public void setPassword(String password) {
        this.password = password;
    }

}