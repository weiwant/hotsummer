package com.example.sprint2.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.dao.SpecialJoinDao;
import com.example.sprint2.models.pojo.SpecialJoinResult;
import com.example.sprint2.models.pojo.TeacherAndOrder;
import com.example.sprint2.models.vo.SpecialVo;
import com.example.sprint2.service.SpecialJoinService;
import com.example.sprint2.utils.ListToPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hy
 * @Date 2022/7/5
 * @Description
 */
@Service
public class SpecialJoinServiceImpl implements SpecialJoinService {

    @Autowired
    SpecialJoinDao specialJoinDao;

    /**
     * @param specialVo
     * @author hy
     * @description 所有查询条件封装
     */
    @Override
    public IPage<SpecialVo> selectByConditions(SpecialVo specialVo) throws InvocationTargetException, IllegalAccessException {
        List<SpecialJoinResult> specialJoinResults = new ArrayList<>();
        List<Method> methods = Arrays.stream(SpecialVo.class.getDeclaredMethods()).filter(method -> method.getName().contains("get"))
                .filter(method -> !method.getName().equals("getFiles"))
                .collect(Collectors.toList());
        List<Method> resultMethods = Arrays.stream(SpecialJoinResult.class.getDeclaredMethods()).filter(method -> method.getName().contains("set"))
                .filter(method -> !method.getName().equals("setFilePath"))
                .collect(Collectors.toList());
        for (int i = 0; i < (specialVo.getSomePeople() != null ? specialVo.getSomePeople().size() : 1); i++) {
            specialJoinResults.add(new SpecialJoinResult());
        }
        for (int i = 0; i < (specialVo.getSomePeople() != null ? specialVo.getSomePeople().size() : 1); i++) {
            SpecialJoinResult specialJoinResult = specialJoinResults.get(i);
            for (Method voMethod : methods) {
                voMethod.setAccessible(true);
                if (voMethod.getName().equals("getSomePeople")) {
                    if (specialVo.getSomePeople() != null) {
                        specialJoinResult.setTeacherName(specialVo.getSomePeople().get(i).getTeacherName());
                        specialJoinResult.setTeachingScores(specialVo.getSomePeople().get(i).getTeachingScores());
                        specialJoinResult.setAuthorOrder(specialVo.getSomePeople().get(i).getAuthorOrder());
                    }
                } else {
                    for (Method method : resultMethods) {
                        if (method.getName().replace("set", "").equals(voMethod.getName().replace("get", ""))) {
                            method.invoke(specialJoinResult, voMethod.invoke(specialVo));
                        }
                    }
                }
            }
        }
        List<SpecialJoinResult> specialJoinResultList = new ArrayList<>();
        for (SpecialJoinResult specialJoinResult : specialJoinResults) {
            List<SpecialJoinResult> list = specialJoinDao.selectListByConditions(specialJoinResult);
            specialJoinResultList.addAll(list);
        }
        List<SpecialVo> specialVoList = new ArrayList<>();
        List<Method> methods1 = Arrays.stream(SpecialVo.class.getDeclaredMethods()).filter(method -> method.getName().contains("set"))
                .filter(method -> !method.getName().equals("setFiles") && !method.getName().equals("setSomePeople"))
                .collect(Collectors.toList());
        List<Method> resultMethods1 = Arrays.stream(SpecialJoinResult.class.getDeclaredMethods()).filter(method -> method.getName().contains("get"))
                .filter(method -> !method.getName().equals("getYear") && !method.getName().equals("getFilePath"))
                .collect(Collectors.toList());
        while (!specialJoinResultList.isEmpty()) {
            List<SpecialJoinResult> joinResultList = specialJoinResultList.stream().filter(specialJoinResult -> specialJoinResult.getId().equals(specialJoinResultList.get(0).getId())).collect(Collectors.toList());
            SpecialVo specialVo1 = new SpecialVo();
            List<TeacherAndOrder> teacherAndOrders = new ArrayList<>();
            for (SpecialJoinResult specialJoinResult : joinResultList) {
                TeacherAndOrder teacherAndOrder = new TeacherAndOrder();
                teacherAndOrder.setAuthorOrder(specialJoinResult.getAuthorOrder());
                teacherAndOrder.setTeacherName(specialJoinResult.getTeacherName());
                teacherAndOrder.setTeachingScores(specialJoinResult.getTeachingScores());
                teacherAndOrders.add(teacherAndOrder);
            }
            specialVo1.setSomePeople(teacherAndOrders);
            for (Method method : methods1) {
                method.setAccessible(true);
                for (Method method1 : resultMethods1) {
                    method1.setAccessible(true);
                    if (method.getName().replace("set", "").equals(method1.getName().replace("get", ""))) {
                        method.invoke(specialVo1, method1.invoke(joinResultList.get(0)));
                    }
                }
            }
            specialVoList.add(specialVo1);
            specialJoinResultList.removeAll(joinResultList);
        }
        int size = 5;
        IPage<SpecialVo> iPage;
        iPage = ListToPageUtil.listToPage(specialVoList, specialVo.getPageNumber() != null ? specialVo.getPageNumber() : 1, size);

        return iPage;
    }

    @Override
    public String setSubmit(SpecialVo specialVo) throws InvocationTargetException, IllegalAccessException {
        SpecialJoinResult specialJoinResult = new SpecialJoinResult();
        List<Method> methods = Arrays.stream(SpecialVo.class.getDeclaredMethods()).filter(method -> method.getName().contains("get"))
                .filter(method -> !method.getName().equals("getFiles"))
                .collect(Collectors.toList());
        List<Method> resultMethods = Arrays.stream(SpecialJoinResult.class.getDeclaredMethods()).filter(method -> method.getName().contains("set"))
                .filter(method -> !method.getName().equals("setFilePath"))
                .collect(Collectors.toList());
        for (Method method : methods) {
            method.setAccessible(true);
            for (Method resultMethod : resultMethods) {
                resultMethod.setAccessible(true);
                if (method.getName().replace("get", "").equals(resultMethod.getName().replace("set", ""))) {
                    resultMethod.invoke(specialJoinResult, method.invoke(specialVo));
                }
            }
        }
        String r = specialJoinDao.setSubmit(specialJoinResult);
        return r;
    }

}
