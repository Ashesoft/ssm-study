package com.longrise.myssm.service;

import com.longrise.myssm.vo.DataMsg;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DataMsgServiceTest {
    @Test
    public void testQuery(){
        try (ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("application-spring.xml")) {
            DataMsgService dataMsgService = (DataMsgService) context.getBean("dataMsgService");
            System.out.println(dataMsgService.queryAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAdd(){
        try (ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("application-spring.xml")) {
            DataMsgService dataMsgService = (DataMsgService) context.getBean("dataMsgService");
            DataMsg dm = new DataMsg();
            dm.setMsg("面朝大海");
            // System.out.print(dataMsgService.addOne(dm));
            System.out.print(dataMsgService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDel(){
        try (ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("application-spring.xml")) {
            DataMsgService dataMsgService = (DataMsgService) context.getBean("dataMsgService");
            System.out.println(dataMsgService.delOne(33));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}