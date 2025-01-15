package com.auff.apiConnector.infra.config;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.FormHttpMessageWriter;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

  @Value("${webclient.url.nasa}")
  private String nasaUrl;

  @Bean
  public WebClient toNasa() throws SSLException {
    SslContext sslContext = SslContextBuilder.forClient().build();
    FormHttpMessageWriter formHttpMessageWriter = new FormHttpMessageWriter();
    formHttpMessageWriter.setDefaultCharset(Charset.forName("euc-kr"));

    return WebClient.builder()
        .baseUrl(nasaUrl)
        .defaultHeaders(httpHeaders -> {
          httpHeaders.setAcceptCharset(Collections.singletonList(Charset.forName("euc-kr")));
          httpHeaders.setAccept(List.of(APPLICATION_FORM_URLENCODED, APPLICATION_JSON));
        })
        .clientConnector(
            new ReactorClientHttpConnector(
                HttpClient.create()
                    .secure(spec -> spec.sslContext(sslContext)
                        .handshakeTimeout(Duration.ofSeconds(20))
                        .closeNotifyFlushTimeout(Duration.ofSeconds(10))
                        .closeNotifyReadTimeout(Duration.ofSeconds(10)))
                    .doOnConnected(connection -> connection.addHandlerFirst(new ReadTimeoutHandler(300)))
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 50000)
            )
        ).build();
  }

}
