package com.psfd.springboot.service.impl;

import com.psfd.springboot.entity.Operator;
import com.psfd.springboot.mapper.OperatorMapper;
import com.psfd.springboot.service.OperatorService;
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
public class OperatorServiceImpl extends ServiceImpl<OperatorMapper, Operator> implements OperatorService {

}
