package cloud.spring.my.service;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;

public interface ISendToStream {

    @LoadBalanced
    public void send(String msg);

}
