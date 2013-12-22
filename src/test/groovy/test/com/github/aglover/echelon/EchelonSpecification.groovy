package test.com.github.aglover.echelon

import com.github.aglover.echelon.EchelonDelegate
import com.github.aglover.echelon.EchelonRunner
import spock.lang.Specification


/**
 * Created by aglover on 12/14/13.
 */
class EchelonSpecification extends Specification {

    def delegate = new EchelonDelegateMock()

    def 'All aspects of DSL should be invoked'() {
        given:
        def txt = """\
using 'ikey','isecret'
json.message {
    name "Andrew Glover"
    age 37
    email "aglover@acme.com"
}
send json to "new_customer"
"""
        when:
        def runner = new EchelonRunner()
        runner.setDelegate(delegate)
        runner.run(txt)

        then:
        delegate.toCalled == true
        delegate.sendCalled == true
        delegate.usingCalled == true
        delegate.messageReceived == "{\"message\":{\"name\":\"Andrew Glover\",\"age\":37,\"email\":\"aglover@acme.com\"}}"
        delegate.queueName == 'new_customer'
        delegate.authentication['key'] == 'ikey'
        delegate.authentication['secret'] == 'isecret'
    }

    def 'A script path can be sent to the run command'() {
        given:
        def runner = new EchelonRunner()
        runner.setDelegate(delegate)

        when:
        runner.run('./src/main/resources/SQSExample2.groovy')

        then:
        delegate.toCalled == true
        delegate.sendCalled == true
        delegate.usingCalled == true
        delegate.queueName == 'new_customer_queue'
        delegate.authentication['key'] == 'key'
        delegate.authentication['secret'] == 'secret'
        delegate.messageReceived != null
    }

    def 'A missing field value will return false for validation'() {
        given:
        def delegate = new EchelonDelegate()

        when:
        delegate.queueName = 'test_queue'

        then:
        delegate.'queueName' == 'test_queue'
        delegate.fieldsValidate() == false
    }

    def 'A simple string can be the dsl'() {
        given:
        def dsl = "using 'key','secret' send 'this is a test' to 'queue_test'"
        def runner = new EchelonRunner()
        runner.setDelegate(delegate)

        when:
        runner.run(dsl)

        then:
        delegate.toCalled == true
        delegate.sendCalled == true
        delegate.usingCalled == true
        delegate.queueName == 'queue_test'
        delegate.authentication['key'] == 'key'
        delegate.authentication['secret'] == 'secret'
        delegate.messageReceived == 'this is a test'
    }

    def 'All field values filled out will return true for validation'() {
        given:
        def delegate = new EchelonDelegate()

        when:
        delegate.queueName = 'test_queue'
        delegate.key = 'some key'
        delegate.secret = 'some secret'
        delegate.message = 'message'

        then:
        delegate.fieldsValidate() == true
    }
}
