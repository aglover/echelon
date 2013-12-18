package test.com.github.aglover.echelon

import com.github.aglover.echelon.Echelon
import groovy.json.JsonBuilder


/**
 * Created by aglover on 12/14/13.
 */
class EchelonDelegateMock implements Echelon {

    JsonBuilder json
    def sendCalled = false
    def toCalled = false
    def usingCalled = false
    String messageReceived
    String queueName
    def authentication

    EchelonDelegateMock() {
        json = new JsonBuilder()
    }

    @Override
    Echelon send(Object message) {
        this.messageReceived = message.toString()
        this.sendCalled = true
        return this
    }

    @Override
    Echelon to(String queueName) {
        this.queueName = queueName
        this.toCalled = true
        return this
    }

    @Override
    Echelon using(String key, String secret) {
        this.authentication = [key: key, secret: secret]
        this.usingCalled = true
        return this
    }
}
