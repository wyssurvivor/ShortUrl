package com.ssdut.wys.generator.factory;

import com.ssdut.wys.generator.Generator;
import com.ssdut.wys.generator.MD5Generator;

public class MD5GenFactory implements GenFactory{

    public Generator getGenerator() {
        return MD5Generator.getInstance();
    }

}
