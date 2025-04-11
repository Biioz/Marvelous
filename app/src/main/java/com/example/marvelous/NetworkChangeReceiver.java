package com.example.marvelous;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Vérification basique de sécurité
        if (intent == null || intent.getAction() == null) {
            return;
        }

        // Pour les appareils récents (API 24+), on ne fait rien ici
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return;
        }

        // Vérification de l'action seulement pour les anciennes APIs
        if (!intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            return;
        }

        // Vérification de la connexion (méthode moderne si disponible)
        if (estConnecteInternet(context)) {
            Toast.makeText(context, "Connexion Internet rétablie", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Connexion Internet perdue", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean estConnecteInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network network = cm.getActiveNetwork();
            if (network == null) return false;

            NetworkCapabilities capabilities = cm.getNetworkCapabilities(network);
            return capabilities != null &&
                    capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
        } else {
            // Méthode dépréciée mais nécessaire pour les anciens appareils
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
    }
}