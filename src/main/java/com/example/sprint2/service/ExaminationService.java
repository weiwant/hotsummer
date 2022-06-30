package com.example.sprint2.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.models.vo.ExaminationVo;
import com.example.sprint2.mybatis.entity.ExaminationWorkload;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:wwq
 * @Date：2022/6/29：16:16
 */
@Service
public interface ExaminationService {
    List<ExaminationWorkload> getYearWorkload(ExaminationVo examinationVo);

    IPage<ExaminationWorkload> getPageYearWorkload(ExaminationVo exam);

    List<ExaminationWorkload> getTeacherWorkload(ExaminationVo examinationVo);

    List<ExaminationWorkload> getCourseWorkload(ExaminationVo examinationVo);
}
