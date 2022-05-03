package com.example.goodmorning;

import java.util.TimeZone;

import com.example.common.model.CommonOutput;
import com.example.common.model.ErrorOutput;
import com.example.goodmorning.model.GoodMorningInput;
import com.example.goodmorning.model.GoodMorningOutput;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.nativex.hint.ResourceHint;
import org.springframework.nativex.hint.SerializationHint;
import org.springframework.nativex.hint.TypeHint;

import net.logstash.logback.encoder.LogstashEncoder;

@SerializationHint(types = { GoodMorningInput.class, GoodMorningOutput.class, CommonOutput.class, ErrorOutput.class })
@TypeHint(types = { LogstashEncoder.class })
@ResourceHint(patterns = { "messages.yml", "org/joda/time/tz/data/ZoneInfoMap", "org/joda/time/tz/data/Asia/Tokyo" })
@ComponentScan(basePackages = "com.example")
@SpringBootApplication
public class Application {

    /*
     * You need this main method (empty) or explicit
     * <start-class>example.FunctionConfiguration</start-class>
     * in the POM to ensure boot plug-in makes the correct entry
     */
    public static void main(String[] args) {
        // empty unless using Custom runtime at which point it should include
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Tokyo"));
        SpringApplication.run(Application.class, args);
    }
}
