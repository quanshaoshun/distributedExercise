package com.example.demo.service.impl;

import com.example.demo.entity.Contactperson;
import com.example.demo.mapper.ContactpersonMapper;
import com.example.demo.service.IContactpersonService;
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
public class ContactpersonServiceImpl extends ServiceImpl<ContactpersonMapper, Contactperson> implements IContactpersonService {

}
