package com.ebp.g4.service.implement;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class MyLogger
{
    static {
        PropertyConfigurator.configure("cfg\\configure.properties");
    }
    
    public Logger logger;
    
    public MyLogger(Class clzz){
        logger = Logger.getLogger(clzz);
    }
}
