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
        Script script = null;
        if (dsl.endsWith('.groovy')) { //must be a script
            script = shell.parse(new File(dsl))
        } else {
            script = shell.parse(dsl)
        }
        script.setDelegate(this.delegate)
        script.run()
    }
}
