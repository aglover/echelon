/**
 * Created by aglover on 12/8/13.
 */


def json = new groovy.json.JsonBuilder()

json.message {
    name "Andrew Glover"
    age 37
    email "aglover@acme.com"
}

send json to "new_customer" using 'key','secret'