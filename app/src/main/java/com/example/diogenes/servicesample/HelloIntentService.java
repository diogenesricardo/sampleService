package com.example.diogenes.servicesample;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

public class HelloIntentService extends IntentService {

    private static final int MAX = 10;
    private static final String TAG = "fafica";
    private boolean running;

    public HelloIntentService() {
        super("NomeDaThreadAqui");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        running = true;
        // Este método executa em uma thread
        // Quando ele terminar, o método stopSelf() será chamado automaticamente
        int count = 0;
        while (running && count < MAX) {
            fazAlgumaCoisa();
            Log.d(TAG, "Exemplo Servico executando com Intent Service... " + count);
            count++;
        }
        Log.d(TAG, "ExemploServico fim.");
    }

    private void fazAlgumaCoisa() {
        try {
            // Simula algum processamento
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Ao encerrar o serviço, altera o flag para o loop parar
        running = false;
        Log.d(TAG, "ExemploServico.onDestroy()");
    }

}
