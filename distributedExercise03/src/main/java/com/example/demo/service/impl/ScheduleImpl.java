package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Schedule;
import com.example.demo.mapper.ScheduleMapper;
import com.example.demo.service.IScheduleService;
import org.springframework.stereotype.Service;

@Service
public class ScheduleImpl extends ServiceImpl<ScheduleMapper, Schedule> implements IScheduleService {

}
