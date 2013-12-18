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
package com.github.aglover.echelon

import org.codehaus.groovy.control.CompilerConfiguration


/**
 * Created by aglover on 12/14/13.
 */
class EchelonRunner {

    def delegate
    
    EchelonRunner() {
        super()
        this.delegate = new EchelonDelegate()
    }

    void run(String dsl) {
        def compilerConfiguration = new CompilerConfiguration()
        compilerConfiguration.scriptBaseClass = DelegatingScript.class.name
        GroovyShell shell = new GroovyShell(this.class.classLoader, new Binding(), compilerConfiguration)
        Script script = shell.parse(dsl)
        script.setDelegate(this.delegate)
        script.run()
    }
}
