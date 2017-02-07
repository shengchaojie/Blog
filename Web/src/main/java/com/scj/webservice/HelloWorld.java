package com.scj.webservice;

import javax.jws.WebService;

/**
 * Created by Administrator on 2017/2/7 0007.
 */
@WebService
public interface HelloWorld {
    String sayHi(String text);

    String sayHi2();

    String sayHi3(World world);
}
