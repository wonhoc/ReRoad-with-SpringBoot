package com.example.schedule.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.schedule.vo.UserScRequsetVo;
import com.example.schedule.vo.expBus.ExpBusScVo;
import com.example.schedule.vo.expBus.ExpBusSetInfoVo;
import com.example.schedule.vo.expBus.ExpBusTmlInfoVo;

public interface ExpBusScheduleService {

	ArrayList<ExpBusTmlInfoVo> getTmlInfo();
	HashMap<String, Object> parseUserRequestInfoBeforeResponeScList(UserScRequsetVo usrv);
	ExpBusScVo getExpBusSc(ExpBusSetInfoVo setting);
	
}//interface end
