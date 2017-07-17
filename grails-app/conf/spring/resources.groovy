import org.apache.activemq.ActiveMQConnection

beans = {

    jmsConnectionFactory(org.apache.activemq.ActiveMQConnectionFactory ){
        brokerURL="tcp://localhost:61616"
        userName="admin"
        password="admin"
    }

}
