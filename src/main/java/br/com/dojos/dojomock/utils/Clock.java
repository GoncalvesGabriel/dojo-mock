package br.com.dojos.dojomock.utils;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class Clock {

    public LocalDateTime devolveHoje(){
        return LocalDateTime.now();
    }

}
