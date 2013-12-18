/**
 * Created by aglover on 12/8/13.
 */

using 'key','secret'

json.customer {
    name "Andrew Glover"
    age 37
    email "aglover@acme.com"
}

send json to "new_customer_queue"