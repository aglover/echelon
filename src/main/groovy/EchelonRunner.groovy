/**
 * Created by aglover on 12/8/13.
 */

def binding = new EchelonBinding()

def shell = new GroovyShell(binding)

shell.evaluate("send 'this is a test' to 'queue_test' using 'key','secret'")

println "\n"

shell.evaluate(new File("SQSExample.groovy"))