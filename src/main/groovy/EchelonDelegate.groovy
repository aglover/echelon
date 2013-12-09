import groovy.json.JsonBuilder
import groovy.transform.ToString


/**
 * Created by aglover on 12/8/13.
 */
@ToString
class EchelonDelegate {
    String message
    String queueName
    String key
    String secret

    JsonBuilder json

    EchelonDelegate() {
        json = new JsonBuilder()
    }

    EchelonDelegate send(message) {
        this.message = message.toString()
        return this
    }

    EchelonDelegate to(String queueName) {
        this.queueName = queueName
        return this
    }

    void using(String key, String secret) {
        this.key = key
        this.secret = secret

        println this.toString()
    }
}
