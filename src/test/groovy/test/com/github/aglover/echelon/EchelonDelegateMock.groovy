/*
 * Copyright 2013 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
