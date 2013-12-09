/**
 * Created by aglover on 12/8/13.
 */
class EchelonBinding extends Binding {

    EchelonBinding() {
        send = { message ->
            return new Echelon(message.toString())
        }
    }
}
