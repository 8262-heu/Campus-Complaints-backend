package com.campusguardian.backend.service;

import com.campusguardian.backend.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private static final String COLLECTION = "users";

    private Firestore db() {
        return FirestoreClient.getFirestore();
    }

    // Create user
    public User createUser(User user) throws ExecutionException, InterruptedException {
        db().collection(COLLECTION).document(user.getEmail()).set(user).get();
        return user;
    }

    // Get user by email
    public User getUserByEmail(String email) throws ExecutionException, InterruptedException {
        DocumentSnapshot doc = db().collection(COLLECTION).document(email).get().get();
        if (doc.exists()) {
            return doc.toObject(User.class);
        }
        return null;
    }

    // Check if user exists
    public boolean userExists(String email) throws ExecutionException, InterruptedException {
        return getUserByEmail(email) != null;
    }

    // Validate login
    public boolean validateLogin(String email, String password) throws ExecutionException, InterruptedException {
        User user = getUserByEmail(email);
        return user != null && user.getPassword().equals(password); // In production, use hashed password
    }
}