package com.github.aglover.echelon

import com.b50.sqs.SQSAdapter


/**
 * Created by aglover on 12/17/13.
 */
class EchelonImpl implements Echelon{

    private SQSAdapter ahoy

    EchelonImpl() {
        super()
    }

    @Override
    Echelon send(Object message) {
        return null
    }

    @Override
    Echelon to(String queueName) {
        return null
    }

    @Override
    Echelon using(String key, String secret) {
        return null
    }
}
