package com.studiozero.projeto.infrastructure.configs;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collections;

@Configuration
public class DriveConfig {

    private static final String APPLICATION_NAME = "Backoffice";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    @Value("${google.credentials.path:}")
    private String credentialsPath;

    @Bean(name = "customGoogleDriveService")
    public Drive googleDriveService() throws Exception {
        InputStream credentialsStream;

        if (credentialsPath != null && !credentialsPath.isBlank()) {
            File credentialsFile = new File(credentialsPath);
            if (!credentialsFile.exists()) {
                throw new IllegalStateException("Arquivo de credenciais não encontrado em: " + credentialsPath);
            }
            credentialsStream = new FileInputStream(credentialsFile);
        } else {
            ClassPathResource resource = new ClassPathResource("credentials.json");
            if (!resource.exists()) {
                throw new IllegalStateException("Arquivo credentials.json não encontrado no classpath.");
            }
            credentialsStream = resource.getInputStream();
        }

        GoogleCredential credential = GoogleCredential.fromStream(credentialsStream)
                .createScoped(Collections.singleton(DriveScopes.DRIVE));

        return new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                credential
        ).setApplicationName(APPLICATION_NAME).build();
    }
}
