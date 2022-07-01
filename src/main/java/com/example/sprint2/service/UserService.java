package com.example.sprint2.service;

import com.example.sprint2.models.vo.LoginVo;
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
