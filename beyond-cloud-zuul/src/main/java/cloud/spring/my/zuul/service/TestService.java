package cloud.spring.my.zuul.service;

import java.util.ArrayList;
import java.util.List;


public class TestService {
    protected final List<String> data;

    public TestService(List<String> data){
        this.data = data;
    }

    public List<String> getData(){
        return new ArrayList<>(data);
    }

}




