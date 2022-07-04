package com.example.sprint2.models.vo;

import com.example.sprint2.mybatis.entity.SpecialWorkload;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author:wwq
 * @Date：2022/7/1：10:03
 */
@Getter
@Setter
public class SpecialReceiveVo {

    private List<SpecialWorkload> data;//特殊工作量数据

    private MultipartFile[] files;//附件

    private Integer id;//主键
    private double teachingScore;//教分
    private String remark;//打分备注
}
