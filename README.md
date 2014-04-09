## Vert.x basic rest http server which dispatch request to other module

{ip}:{port}/module_name/* => send a message with the adresse module_name

# Help for IntelliJ configuration Setup
Main Class : org.vertx.java.platform.impl.cli.Starter
VM options : -Dorg.vertx.logger-delegate-factory-class-name=org.vertx.java.core.logging.impl.Log4jLogDelegateFactory
Program Arguments runmod com.zenika~rest-dispatcher-servlet~0.1 -conf mods/com.zenika~rest-dispatcher-verticle~0.1/config.json