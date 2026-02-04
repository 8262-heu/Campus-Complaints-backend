package com.campusguardian.backend.config;

import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Component
public class FirebaseConfig {

    @PostConstruct
    public void init() {
        try {
            // prevent double init
            if (!FirebaseApp.getApps().isEmpty()) return;

            InputStream serviceAccount = getClass()
                    .getClassLoader()
                    .getResourceAsStream(
                            "campusguardian-8620a-firebase-adminsdk-fbsvc-86a3b1a41b.json"
                    );

            if (serviceAccount == null) {
                throw new RuntimeException("❌ Firebase JSON not found in resources");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
            System.out.println("🔥 Firebase initialized (resources)");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Firebase init failed");
        }
    }
}