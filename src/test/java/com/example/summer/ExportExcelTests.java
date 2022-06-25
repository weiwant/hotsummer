package com.example.summer;

import com.example.summer.entity.TeachingWorkloadStatistics;
import com.example.summer.service.impl.ExportExcelImpl;
import com.example.summer.utils.ReflexUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class ExportExcelTests {
    @Autowired
    private ExportExcelImpl exportExcel;
    /**
     * @Author theLastNYF
     * @description 测试Utils的getFieldValueByFieldName方法
     * @Date 2022/06/25
     */
    @Test
    void testOut(){

        TeachingWorkloadStatistics teachingWorkloadStatistics=new TeachingWorkloadStatistics();
        teachingWorkloadStatistics.setMainTeacherName("王老师");
        Object test= ReflexUtil.getFieldValueByFieldName("mainTeacherName", teachingWorkloadStatistics);
        if (test==null){
            System.out.println("发生错误");
        }
        else{
            System.out.println(test);
        }
    }

    /**
     * @Author theLastNYF
     * @Description 测试Utils的readAttributeValue方法
     * @Date 2022/06/25
     */
    @Test
    void testReadAttributeValue(){
        TeachingWorkloadStatistics teachingWorkloadStatistics=new TeachingWorkloadStatistics();
        teachingWorkloadStatistics.setAcademicYear("2020-2021");
        teachingWorkloadStatistics.setMainTeacherName("王老师");
        Map<String,Object>[] test= ReflexUtil.readAttributeValue(teachingWorkloadStatistics);
        System.out.println("学年为："+test[1]);
//            输出结果如下
//            学年为：{academicYear=2020-2021}
    }

    /**
     * @Author theLastNYF
     * @Description 测试ExportExcelImpl的输出content[][]方法
     * @Date 2022/06/25
     */
    @Test
    void testOutContent(){
        System.out.println(exportExcel.getContentInScope("2019-2020","2020-2021","何政").getContent()[1][1]);

    }
}
