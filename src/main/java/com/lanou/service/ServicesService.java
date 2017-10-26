package com.lanou.service;



import com.lanou.bean.Servicee;

import java.util.List;

/**
 * Created by yugege on 17/10/25.
 */
public interface ServicesService {
     List<Servicee> findAllServices();

     int addService(Servicee service);

     Servicee findById(Integer id);

     int updataService(Servicee servicee);

     int deleteServicee(Integer id);



}
