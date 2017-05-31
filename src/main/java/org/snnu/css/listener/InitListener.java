package org.snnu.css.listener;

import org.snnu.css.lockserver.NettyServer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by zero on 2017/1/18.
 */
public class InitListener implements ServletContextListener{


    private NettyServer nettyServer = null;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("contextInitialized......");
        nettyServer = new NettyServer();
        nettyServer.start();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("contextDestroyed.......");
        if(nettyServer!=null){
            new Thread(){
                @Override
                public void run() {
                    nettyServer.stopServer();
                }
            }.start();

            ///nettyServer = null;
        }

    }
}
