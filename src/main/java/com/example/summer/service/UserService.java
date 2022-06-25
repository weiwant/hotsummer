package com.example.summer.service;

import com.example.summer.models.vo.LoginVo;
import org.springframework.stereotype.Service;

/**
 * @author hy
 * @Date 2022/6/23
 */
@Service
public interface UserService {

    public boolean checkUserExist(LoginVo loginVo);

    public boolean checkPsw(LoginVo loginVo);

    public int selectIdentify(LoginVo loginVo);
}
