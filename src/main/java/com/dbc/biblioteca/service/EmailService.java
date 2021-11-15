package com.dbc.biblioteca.service;

import com.dbc.biblioteca.entity.ContaClienteEntity;
import com.dbc.biblioteca.entity.TipoCliente;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender emailSender;
    @Value("${spring.mail.username}")
    private String remetente;
    private final Configuration configuration;

    public void enviarEmailComTemplate(ContaClienteEntity cliente) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        String mensagem = "";
        if (cliente.getTipoCliente() == TipoCliente.COMUM) {
            mensagem = "Parabéns!<br />"+
                    "Você já possui pontos fidelidade suficiente para trocar por duas semanas Premium.<br />" +
                    "Pontos Fidelidade adquiridos: " + cliente.getPontosFidelidade();
        } else if (cliente.getTipoCliente() == TipoCliente.PREMIUM) {
            mensagem = "Parabéns!<br />" +
                    "Você já possui pontos fidelidade suficiente para trocar por um mês de assinatura grátis.<br />" +
                    "Pontos Fidelidade adquiridos: " + cliente.getPontosFidelidade();
        }

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(remetente, "Biblioteca DBC");
        helper.setTo(cliente.getEmail());
        helper.setSubject("Premio Pontos Fidelidade Biblioteca");

        Template template = configuration.getTemplate("templateBiblioteca.ftl");
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", cliente.getNome());
        dados.put("mensagem",mensagem);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);

        helper.setText(html, true);

        emailSender.send(mimeMessage);

    }
}
