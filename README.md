spectrum-service
================

Service layer for the spectrum library.

The service utilizes:
- the Play2 framework
** https://www.playframework.com
- Swagger
** http://swagger.io
- dropwizard metrics (formerly codahale metrics, formerly yammer metrics)
** http://dropwizard.io
- Grizzled SLF4J
** http://software.clapper.org/grizzled-slf4j/




logging Configuration:

Add to startup

-Dlogger.file=conf/logger.xml

place logger.xml to conf directory. Sample:

<configuration scan="true" scanPeriod="60 seconds">

    <jmxConfigurator />

    <conversionRule conversionWord="coloredLevel" converterClass="play.api.Logger$ColoredLevel" />

    <appender name="FILE" class="ch.qos.logback.core.FileAppender">
        <file>${application.home}/logs/application.log</file>
        <encoder>
            <pattern>%date - [%level] - from %logger in %thread %n%message%n%xException%n</pattern>
        </encoder>
    </appender>

    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <logger name="play" level="DEBUG" additivity="false">
        <appender-ref ref="STDOUT"/>
        <appender-ref ref="FILE"/>
    </logger>

    <logger name="application" level="DEBUG" additivity="false">
        <appender-ref ref="STDOUT"/>
        <appender-ref ref="FILE"/>
    </logger>

    <root level="DEBUG">
        <appender-ref ref="STDOUT" />
    </root>

</configuration>


