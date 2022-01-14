package com.example.schedule.vo.expBus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExpBusTmlInfoVo {

	private String tmId;	//터미널 ID
	private String tmName;  //터미널 이름
	private String city;	//도시이름
	
	
	public ExpBusTmlInfoVo(String tmId, String tmName) {
		this.tmId = tmId;
		this.tmName = tmName;
	}
	
}//class end
