package com.example.summer.service.impl;

import com.example.summer.dao.ColumnShowDao;
import com.example.summer.entity.TeachingWorkloadStatistics;
import com.example.summer.models.pojo.EntityToView;
import com.example.summer.models.pojo.WorkloadData;
import com.example.summer.models.vo.TableShowDetailVo;
import com.example.summer.models.vo.TableShowVo;
import com.example.summer.service.ColumnShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author:wwq
 * @Date：2022/6/25：9:28
 */

/**
 * @Author：wwq
 * @Return：
 * @Description：根据列名返回对应内容
 */
@Service
public class ColumnShowServiceImpl implements ColumnShowService {
    @Autowired
    ColumnShowDao columnShowDao;

    public List<String> getColumn(List<String> names) {
        List<TeachingWorkloadStatistics> someWork = columnShowDao.getWorkInColumn(names);//获取
        //实体类转为Map
        EntityToView entityToView = new EntityToView();
        //存储了一些列Map的List<String>
        List<String> workloadRecord = new ArrayList<>();
        for (TeachingWorkloadStatistics item : someWork) {
            Map<String, Object> myMap = entityToView.TeachingWorkloadToView(names, item);//每一条记录转为一个map
//            String mapString= JSON.toJSONString(myMap);//每个map转为一个JSON字符串
            workloadRecord.add(myMap.toString());
        }
        return workloadRecord;
    }

    @Override
    public List<WorkloadData[]> getColumnCustom(List<String> names, List<String> ChineseNames, String teacherName) {
        /**
         * @author 24047
         * @date 2022/6/27
         * 返回一条教学工作量记录
         */
        //返回一整张表，每一行都是一条记录
        List<TeachingWorkloadStatistics> someWork = columnShowDao.getWorkInColumnCustom(names,teacherName);
        //实体类转为Map
        EntityToView entityToView = new EntityToView();
        //存储了一些列Map的List<String>[]，一个map就是对应一个List<String>
        List<WorkloadData[]> workloadList=new ArrayList<>();
        for (int i=0;i<someWork.size();i++) {
            //一个workloadData[]
            WorkloadData[] data=new WorkloadData[names.size()];
            //将一条记录转化为一个WorkloadData数组
            data= entityToView.TeachingWorkloadToViewChinese(names,ChineseNames,someWork.get(i));
            workloadList.add(data);
       }
        return workloadList;
    }

    @Override
    public List<WorkloadData[]>getColumnCustomByYear(List<String> names, List<String> ChineseNames, TableShowDetailVo tableShowVo){
        //返回一整张表，每一行都是一条记录
        List<TeachingWorkloadStatistics> someWork = columnShowDao.getWorkInColumnCustomByYear(tableShowVo,names);
        //实体类转为Map
        EntityToView entityToView = new EntityToView();
        //存储了一些列Map的List<String>[]，一个map就是对应一个List<String>
        List<WorkloadData[]> workloadList=new ArrayList<>();
        for (int i=0;i<someWork.size();i++) {
            //一个workloadData[]
            WorkloadData[] data=new WorkloadData[names.size()];
            //将一条记录转化为一个WorkloadData数组
            data= entityToView.TeachingWorkloadToViewChinese(names,ChineseNames,someWork.get(i));
            workloadList.add(data);
        }
        return workloadList;
    }
}
