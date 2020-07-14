package com.psfd.springboot.service.impl;

import com.psfd.springboot.entity.Guest;
import com.psfd.springboot.mapper.GuestMapper;
import com.psfd.springboot.service.GuestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
@Service
@Transactional//开启事务
public class GuestServiceImpl extends ServiceImpl<GuestMapper, Guest> implements GuestService {

}
