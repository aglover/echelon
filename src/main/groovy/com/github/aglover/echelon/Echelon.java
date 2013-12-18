package com.github.aglover.echelon;

/**
 * Created by aglover on 12/14/13.
 */
public interface Echelon {
    Echelon send(Object message);

    Echelon to(String queueName);

    Echelon using(String key, String secret);
}
