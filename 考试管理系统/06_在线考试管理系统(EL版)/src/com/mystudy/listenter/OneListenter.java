package com.mystudy.listenter;

import com.mystudy.util.JdbcUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OneListenter implements ServletContextListener {
    JdbcUtil ju = new JdbcUtil();
    //在tomcat启动时创建20个Connection对象
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Map map = new HashMap();
        for (int i = 0; i < 20; i++) {
            Connection con = ju.createCon();
            System.out.println(con+"被创建了");
            map.put(con,true);//true表示con尚未被使用，使用时将con置为false
        }
        //因为当前map属于一个局部变量，当此方法结束后，作为局部变量的map将会被销毁
        //为了让map中的20个con对象不被销毁，我们可以将map保存当全局作用域对象中作为共享数据
        ServletContext appliaction = sce.getServletContext();
        appliaction.setAttribute("key1",map);
    }
    //在tomcat即将关闭的时候，此时将创建的20个connection对象进行销毁
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //向tomcat索要当前网站下的全局作用域对象
        ServletContext appliaction = sce.getServletContext();
        //得到共享数据
        Map map =(Map) appliaction.getAttribute("key1");
        //将map中的key转为set集合，利用迭代器遍历进行一一关闭
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            Connection con = (Connection) it.next();
            if (con != null) {
                System.out.println(con+"被关闭了");
                ju.close();
            }
        }
    }
}
