package com.example.demo.service.impl;

import com.example.demo.entity.Worklog;
import com.example.demo.mapper.WorklogMapper;
import com.example.demo.service.IWorklogService;
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
public class WorklogServiceImpl extends ServiceImpl<WorklogMapper, Worklog> implements IWorklogService {

}
