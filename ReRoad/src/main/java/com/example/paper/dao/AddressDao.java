package com.example.paper.dao;

import java.util.HashMap;
import java.util.Map;

public interface AddressDao {
    void insertAddr(Map map);
    void updateAddressRead(HashMap<String,Object> updateMap);
}
