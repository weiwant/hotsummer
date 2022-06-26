package com.example.summer.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:wwq
 * @Date：2022/6/25：9:28
 */
@Service
public interface ColumnShowService {
    List<String> getColumn(List<String> names);
}
