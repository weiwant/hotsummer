package com.example.summer.service;

import java.util.Map;

/**
 * @author 24047
 * @date 2022/6/25
 */
public interface SendStatisticTitleService {

    Map<String, Object> getTitle(String[] titleShow);

    Map<String, String> getTitleFormat(String[] titleShow);
}
