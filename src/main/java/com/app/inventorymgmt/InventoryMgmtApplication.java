package com.app.inventorymgmt;

import com.app.inventorymgmt.controller.InventoryMgmtRestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class InventoryMgmtApplication {

    public static void main(String[] args) {

        SpringApplication.run(InventoryMgmtApplication.class, args);

        //ConfigurableApplicationContext ctx = SpringApplication.run(InventoryMgmtApplication.class, args);

        //String[] beanDefinitionNames = ctx.getBeanDefinitionNames();

        /*Arrays.stream(beanDefinitionNames)
                .forEach(System.out::println);*/


       // System.out.println("========================Check Controller is getting call or not ========================");
        //InventoryMgmtRestController inventory = (InventoryMgmtRestController)ctx.getBean("inventoryMgmtRestController");

        //System.out.println(inventory.ping());
    }

}
