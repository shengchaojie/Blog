package com.scj.webservice;

/**
 * Created by Administrator on 2017/2/7 0007.
 */
/*@WebService(endpointInterface = "com.scj.webservice.HelloWorld")
public class HelloWorldImpl implements HelloWorld{
    private static final String WEBSERVICE_URL="http://localhost:8080/blog/services/HelloWorld?wsd";

    @Override
    public String sayHi(String text) {
        System.out.println("sayHi called");
        return "Hello"+text+"!";
    }

    @Override
    public String sayHi2() {
        System.out.println("sayHi called");
        return "HelloWorld!";
    }

    @Override
    public String sayHi3(World world) {
        System.out.println("sayHi3 called");
        return world.getGoverner()+"rules the "+world.getCentury() +" world!";
    }

    public static void main(String[] args) {
        //testClientByProxy();
        testClientByProxyWithVo();
    }

    public static void testClientByProxy(){
        JaxWsProxyFactoryBean factoryBean =new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(HelloWorld.class);
        factoryBean.setAddress(WEBSERVICE_URL);
        HelloWorld helloWorld =(HelloWorld)factoryBean.create();
        System.out.println(helloWorld.sayHi("new shengchaojie"));
        System.out.println(helloWorld.sayHi2());
    }

    public static void testClientByProxyWithVo(){
        JaxWsProxyFactoryBean factoryBean =new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(HelloWorld.class);
        factoryBean.setAddress(WEBSERVICE_URL);
        HelloWorld helloWorld =(HelloWorld)factoryBean.create();
        World world =new World("shengchaojie",2016);
        System.out.println(helloWorld.sayHi3(world));
    }

    public static void testClient(){
        JaxWsDynamicClientFactory  clientFactory =JaxWsDynamicClientFactory.newInstance();
        Client client = clientFactory.createClient(WEBSERVICE_URL);
        try {
            Object[] result = client.invoke("sayHi", "scj");
            System.out.println(result[0]);
            result = client.invoke("sayHi2");
            System.out.println(result[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/
