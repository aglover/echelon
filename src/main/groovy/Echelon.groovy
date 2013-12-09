import groovy.transform.ToString


/**
 * Created by aglover on 12/8/13.
 */
@ToString
class Echelon {

    String message
    String queueName
    String key
    String secret

    Echelon(String message){
        this.message = message
    }

    Echelon to(String queueName){
        this.queueName = queueName
        return this
    }

    void using(String key, String secret){
        this.key = key
        this.secret = secret

        println this.toString()
    }
}
