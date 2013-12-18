import com.github.aglover.echelon.EchelonDelegate
import org.codehaus.groovy.control.CompilerConfiguration

def compilerConfiguration = new CompilerConfiguration()
compilerConfiguration.scriptBaseClass = DelegatingScript.class.name
def shell = new GroovyShell(this.class.classLoader, new Binding(), compilerConfiguration)

def script = shell.parse("send 'this is a test' to 'queue_test' using 'key','secret'")
script.setDelegate(new EchelonDelegate())
script.run()

def script2 = shell.parse(new File("SQSExample.groovy"))
script2.setDelegate(new EchelonDelegate())
script2.run()


def script3 = shell.parse("using 'key','secret' send 'this is a test' to 'queue_test'")
script3.setDelegate(new EchelonDelegate())
script3.run()

def script4 = shell.parse(new File("SQSExample2.groovy"))
script4.setDelegate(new EchelonDelegate())
script4.run()

