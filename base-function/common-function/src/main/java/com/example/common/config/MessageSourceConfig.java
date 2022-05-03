package com.example.common.config;

import java.time.Duration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.StringUtils;

import net.rakugakibox.util.YamlResourceBundle;

@Configuration
public class MessageSourceConfig {

    // (1) Spring Boot設定ファイルのプロパティspring.messages.*を受け取るための@ConfigurationPropertiesBeanを定義します。
    @Bean
    @ConfigurationProperties(prefix = "spring.messages")
    public MessageSourceProperties messageSourceProperties() {
        return new MessageSourceProperties();
    }

    // (2) messageSourceBeanを定義します。
    // messageSourceという名前のBeanを定義することで、Spring BootのMessageSourceAutoConfigurationが無効になり、Auto-ConfigではMessageSourceがセットアップされなくなります。Auto-Configを理解しないと適切にカスタマイズできないポイントですね。
    @Bean("messageSource")
    public MessageSource messageSource(MessageSourceProperties properties) {

        // (3) YamlResourceBundleクラスを利用してYAML形式のメッセージ定義ファイルを読み込みます。
        YamlMessageSource messageSource = new YamlMessageSource();

        // (4) 生成したYamlMessageSourceにMessageSourcePropertiesのプロパティをすべてセットします。
        // これにより、Spring Boot標準のResourceBundleMessageSourceと完全に同じように扱うことができます。
        if (StringUtils.hasText(properties.getBasename())) {
            messageSource.setBasenames(StringUtils
                    .commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(properties.getBasename())));
        }
        if (properties.getEncoding() != null) {
            messageSource.setDefaultEncoding(properties.getEncoding().name());
        }
        messageSource.setFallbackToSystemLocale(properties.isFallbackToSystemLocale());
        Duration cacheDuration = properties.getCacheDuration();
        if (cacheDuration != null) {
            messageSource.setCacheMillis(cacheDuration.toMillis());
        }
        messageSource.setAlwaysUseMessageFormat(properties.isAlwaysUseMessageFormat());
        messageSource.setUseCodeAsDefaultMessage(properties.isUseCodeAsDefaultMessage());
        return messageSource;
    }

    // (3')
    private static class YamlMessageSource extends ResourceBundleMessageSource {
        @Override
        protected ResourceBundle doGetBundle(String basename, Locale locale) throws MissingResourceException {
            return ResourceBundle.getBundle(basename, locale, YamlResourceBundle.Control.INSTANCE);
        }
    }
}
