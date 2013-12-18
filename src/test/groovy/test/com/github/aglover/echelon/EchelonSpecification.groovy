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

    def 'A missing field value will return false for validation'() {
        given:
        def delegate = new EchelonDelegate()

        when:
        delegate.queueName = 'test_queue'

        then:
        delegate.'queueName' == 'test_queue'
        delegate.fieldsValidate() == false
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
