package br.com.uniciv.gestaotarefas.configuracoes;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

@Configuration
public class InternacionalizacaoMensagensConfig {

  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource mensagemInternacional = new ReloadableResourceBundleMessageSource();

    mensagemInternacional.setBasename("classpath:messages");
    mensagemInternacional.setDefaultEncoding("UTF-8");
    mensagemInternacional.setDefaultLocale(Locale.getDefault());

    return mensagemInternacional;
  }

  @Bean
  public LocalValidatorFactoryBean validatorFactoryBean() {
    LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
    validatorFactoryBean.setValidationMessageSource(messageSource());

    return validatorFactoryBean;
  }
}

