package com.example.demo.service.impl;

import com.example.demo.entity.Sms;
import com.example.demo.mapper.SmsMapper;
import com.example.demo.service.ISmsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
@Service
public class SmsServiceImpl extends ServiceImpl<SmsMapper, Sms> implements ISmsService {

}
