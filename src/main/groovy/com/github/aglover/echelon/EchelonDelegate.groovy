package com.github.aglover.echelon

import com.b50.sqs.SQSAdapter
import groovy.json.JsonBuilder
import groovy.transform.ToString


/**
 * Created by aglover on 12/8/13.
 */
@ToString(excludes = 'json')
class EchelonDelegate implements Echelon {

    String message
    String queueName
    String key
    String secret
    JsonBuilder json


    EchelonDelegate() {
        json = new JsonBuilder()
    }

    Echelon send(message) {
        this.message = message.toString()
        return this
    }

    Echelon to(String queueName) {
        this.queueName = queueName
        if (fieldsValidate()) {
            this.send()
        }
        return this
    }

    boolean fieldsValidate() {
        !this.class.metaClass.properties.find {
            if (it.type == String.class && (this."${it.name}".equals(null) || this."${it.name}".equals(""))) {
                return true
            }
        }
    }

    Echelon using(String key, String secret) {
        this.key = key
        this.secret = secret
        return this
    }

    void send() {
        new SQSAdapter(this.key, this.secret, this.queueName).send(this.message)
    }

}
